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
}