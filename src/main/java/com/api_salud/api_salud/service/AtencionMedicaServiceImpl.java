package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.repository.AtencionMedicaRepository;
import com.api_salud.api_salud.service.AtencionMedicaService;
import com.api_salud.api_salud.service.PdfGeneratorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    private final AtencionMedicaRepository atencionMedicaRepository;
    private final ObjectMapper objectMapper;
    private final PdfGeneratorService pdfGeneratorService;

    @Value("${app.storage.ruta-pdfs}")
    private String rutaBasePdfs;

    public AtencionMedicaServiceImpl(AtencionMedicaRepository atencionMedicaRepository, 
                                     ObjectMapper objectMapper, 
                                     PdfGeneratorService pdfGeneratorService) {
        this.atencionMedicaRepository = atencionMedicaRepository;
        this.objectMapper = objectMapper;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    // =====================================================================
    // FLUJO 1: GUARDADO CLÍNICO INICIAL (Paso 1 y 2 del Frontend)
    // =====================================================================
    @Override
    @Transactional // Todo el guardado atómico va en su transacción estándar
    public AtencionMedicaResponse guardarAtencionMedica(AtencionMedicaRequest request) {
        try {
            // Forzamos a que el estado inicial de la firma sea PENDIENTE en la persistencia interna
            request.setEstadoFirma("PENDIENTE");

            // Convertimos a JSONB y mandamos a la función nativa de Postgres
            String jsonPayload = objectMapper.writeValueAsString(request);
            Long idAtencionGenerado = atencionMedicaRepository.guardarAtencionMedicaCompleta(jsonPayload);

            return new AtencionMedicaResponse(
                    true,
                    "Atención médica registrada correctamente en el sistema (Pendiente de Firma).",
                    idAtencionGenerado,
                    request.getIdEstadoAtencion(),
                    "PENDIENTE"
            );

        } catch (Exception e) {
            throw new RuntimeException("Error irreversible al guardar la atención clínica: " + e.getMessage(), e);
        }
    }

    // =====================================================================
    // FLUJO 2: FIRMA ELECTRÓNICA Y GENERACIÓN DE PDF (Paso 3 - Blindado)
    // =====================================================================
    @Override
    @Transactional // Transaccional para asegurar consistencia al leer y actualizar el Path final
    public AtencionMedicaResponse firmarYGenerarPdf(Long idAtencion) {
        try {
            System.out.println("🔒 Iniciando proceso de firma electrónica legal para la Atención ID: " + idAtencion);

            // 1. Levantamos la VERDAD ABSOLUTA desde la base de datos (Evitamos inyecciones del front)
            // Nota: Aquí invocas el método de tu repositorio que recupera el JSON crudo o mapeado por ID
            String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
            if (jsonPayloadBD == null) {
                throw new IllegalArgumentException("No se encontró ninguna atención médica registrada con el ID: " + idAtencion);
            }
            System.out.println("******* JSON   "+jsonPayloadBD);
            // Reconstruimos el objeto Request original basándonos estrictamente en la BD
            AtencionMedicaRequest requestBD = objectMapper.readValue(jsonPayloadBD, AtencionMedicaRequest.class);

            // 2. Extracción segura de metadatos inmutables para el PDF institucional
            Map<String, Object> payloadMap = objectMapper.readValue(jsonPayloadBD, Map.class);
            Map<String, Object> patientMap = (Map<String, Object>) payloadMap.get("paciente");

            String numeroHistoriaClinica = patientMap != null ? String.valueOf(patientMap.get("hc")) : "HC-DESCONOCIDA"; 
            String nombrePaciente = patientMap != null ? String.valueOf(patientMap.get("name")) : "PACIENTE ANÓNIMO";
            
            // Datos del Médico (En producción los extraerás de la sesión/JWT de forma segura)
            Integer idMedico = requestBD.getIdMedicoIngreso(); 
            String nombreMedicoCompleto = "DR(A). REGALADO MONTEVERDE MIGUEL ANGEL";
            String cmpMedico = "CMP-48592"; 

            // 3. Renderización síncrona del archivo PDF limpio (Thymeleaf)
            byte[] pdfBytes = pdfGeneratorService.generarPdfHistoriaClinica(
                    idAtencion, requestBD, nombrePaciente, numeroHistoriaClinica, "MEDICINA INTERNA", nombreMedicoCompleto
            );

            // 4. Estampado de la Rúbrica de Seguridad Incorruptible (iText)
            byte[] pdfFirmadoBytes = pdfGeneratorService.estamparRubricaMedico(
                    pdfBytes, idMedico, nombreMedicoCompleto, cmpMedico
            );
            
            // 5. Escritura física en la estructura de almacenamiento jerárquico
            String carpetaPacienteDestino = this.rutaBasePdfs + "pacientes/" + numeroHistoriaClinica + "/";
            File directorio = new File(carpetaPacienteDestino);
            if (!directorio.exists()) {
                directorio.mkdirs(); 
            }

            String stringFecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String nombreArchivo = "ATENCION_" + idAtencion + "_" + stringFecha + ".pdf";
            String rutaPdfAbsoluta = carpetaPacienteDestino + nombreArchivo;
            
            Path path = Paths.get(rutaPdfAbsoluta);
            Files.write(path, pdfFirmadoBytes);
            
            // 6. Sello final en base de datos: guardamos la ruta y actualizamos el estado de la firma
            atencionMedicaRepository.actualizarRutaPdf(idAtencion, rutaPdfAbsoluta);
            atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "FIRMADO_ELECTRONICO");

            // 7. Respuesta estructurada final
            AtencionMedicaResponse response = new AtencionMedicaResponse(
                    true,
                    "Documento clínico firmado digitalmente y archivado con éxito de forma inmutable.",
                    idAtencion,
                    requestBD.getIdEstadoAtencion(),
                    "FIRMADO_ELECTRONICO"
            );
            response.setRutaPdfFirmado(rutaPdfAbsoluta);
            return response;

        } catch (Exception e) {
            System.err.println("🚨 ERROR CRÍTICO EN EL PROCESO DE FIRMA LEGAL DE LA ATENCIÓN: " + idAtencion);
            e.printStackTrace();
            throw new RuntimeException("Fallo en el motor de firmado electrónico HCE: " + e.getMessage(), e);
        }
    }
}


/*package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.repository.AtencionMedicaRepository;
import com.api_salud.api_salud.service.AtencionMedicaService;
import com.api_salud.api_salud.service.PdfGeneratorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    private final AtencionMedicaRepository atencionMedicaRepository;
    private final ObjectMapper objectMapper;
    private final PdfGeneratorService pdfGeneratorService;

    @Value("${app.storage.ruta-pdfs}")
    private String rutaBasePdfs;

    public AtencionMedicaServiceImpl(AtencionMedicaRepository atencionMedicaRepository, 
                                     ObjectMapper objectMapper, 
                                     PdfGeneratorService pdfGeneratorService) {
        this.atencionMedicaRepository = atencionMedicaRepository;
        this.objectMapper = objectMapper;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @Override
    @Transactional
    public AtencionMedicaResponse guardarAtencionMedica(AtencionMedicaRequest request) {
        try {
            // STEP 1: Guardar la data clínica atómica en Postgres mediante el JSONB payload
            String jsonPayload = objectMapper.writeValueAsString(request);
            Long idAtencionGenerado = atencionMedicaRepository.guardarAtencionMedicaCompleta(jsonPayload);

            String rutaPdfAbsoluta = null;

            // STEP 2: Si el estado de la firma es el definitivo, procesamos el documento físico
            if ("FIRMADO_ELECTRONICO".equals(request.getEstadoFirma())) {
                
                // Mapeamos el payload crudo para interactuar de forma ágil y segura en Thymeleaf
                Map<String, Object> payloadMap = objectMapper.readValue(jsonPayload, Map.class);
                Map<String, Object> patientMap = (Map<String, Object>) payloadMap.get("patient");

                // Extraemos identificadores inmutables de negocio del JSON del frontend
                String numeroHistoriaClinica = String.valueOf(patientMap.get("hc")); 
                String nombrePaciente = String.valueOf(patientMap.get("name"));
                
                // Datos Institucionales del Médico Firmante (Se extraerán de tu sesión/entidad Médico)
                Integer idMedico = request.getIdMedicoIngreso(); // p.ej. 1
                String nombreMedicoCompleto = "DR(A). REGALADO MONTEVERDE MIGUEL ANGEL";
                String cmpMedico = "CMP-48592"; 

                // 2.1 Generamos el PDF crudo basándonos en la estructura exacta del front (Thymeleaf)
                // Se pasa el 'payloadMap' completo para renderizar las colecciones e inputs directamente
                byte[] pdfBytes = pdfGeneratorService.generarPdfHistoriaClinica(
                        idAtencionGenerado, 
                        request, 
                        nombrePaciente, 
                        numeroHistoriaClinica, 
                        "MEDICINA INTERNA", // Aquí puedes pasar la especialidad resuelta
                        nombreMedicoCompleto
                );

                // 2.2 Estampamos el bloque indivisible de validación en la base de cada hoja mediante iText
                byte[] pdfFirmadoBytes = pdfGeneratorService.estamparRubricaMedico(
                        pdfBytes, 
                        idMedico, 
                        nombreMedicoCompleto, 
                        cmpMedico
                );
                
                // 2.3 📂 ESTRUCTURA DE ALMACENAMIENTO JERÁRQUICO POR PACIENTE
                String carpetaPacienteDestino = this.rutaBasePdfs + "pacientes/" + numeroHistoriaClinica + "/";
                File directorio = new File(carpetaPacienteDestino);
                if (!directorio.exists()) {
                    directorio.mkdirs(); 
                }

                // Bautizamos el documento unificando ID y fecha para auditorías ágiles
                String stringFecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                String nombreArchivo = "ATENCION_" + idAtencionGenerado + "_" + stringFecha + ".pdf";
                
                rutaPdfAbsoluta = carpetaPacienteDestino + nombreArchivo;
                
                // Escribimos los bytes finales en el volumen físico
                Path path = Paths.get(rutaPdfAbsoluta);
                Files.write(path, pdfFirmadoBytes);
                
                // 2.4 Guardamos la dirección del archivo en la base de datos
                atencionMedicaRepository.actualizarRutaPdf(idAtencionGenerado, rutaPdfAbsoluta);
            }

            // STEP 3: Respuesta limpia y desacoplada para el frontend
            AtencionMedicaResponse response = new AtencionMedicaResponse(
                    true,
                    "Atención procesada e impresa correctamente al formato institucional.",
                    idAtencionGenerado,
                    request.getIdEstadoAtencion(),
                    request.getEstadoFirma()
            );
            
            response.setRutaPdfFirmado(rutaPdfAbsoluta); 
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error crítico en el motor de renderizado y persistencia física HCE: " + e.getMessage(), e);
        }
    }
}*/