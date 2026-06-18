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

@Service
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    private final AtencionMedicaRepository atencionMedicaRepository;
    private final ObjectMapper objectMapper;
    private final PdfGeneratorService pdfGeneratorService;

    // 🎯 Inyectamos la ruta de la unidad D:\ configurada en tus properties
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

            String rutaPdfFinal = null;

            // STEP 2: Si el estado de la firma es definitivo, procesamos el archivo físico
            if ("FIRMADO_ELECTRONICO".equals(request.getEstadoFirma())) {
                
                // 2.1 Generamos el PDF crudo en memoria (HTML -> PDF)
                byte[] pdfBytes = pdfGeneratorService.generarPdfHistoriaClinica(idAtencionGenerado, request);
                
                // 2.2 Estampamos la rúbrica del médico leyéndola desde la carpeta de firmas de la unidad D
                byte[] pdfFirmadoBytes = pdfGeneratorService.estamparRubricaMedico(pdfBytes, request.getIdMedicoIngreso());
                
                // 2.3 Guardamos el archivo final directamente en tu disco local D:\08_PROYECTOS\STORAGE\pdfs\
                String nombreArchivo = "HCE_Atencion_" + idAtencionGenerado + ".pdf";
                rutaPdfFinal = this.rutaBasePdfs + nombreArchivo;
                
                Path path = Paths.get(rutaPdfFinal);
                Files.write(path, pdfFirmadoBytes); // Escribe los bytes directamente al disco duro de tu PC
                
                // 2.4 Registramos la ruta física absoluta en la base de datos para auditorías de Susalud
                atencionMedicaRepository.actualizarRutaPdf(idAtencionGenerado, rutaPdfFinal);
            }

            // STEP 3: Construimos la respuesta limpia para el frontend en React
            AtencionMedicaResponse response = new AtencionMedicaResponse(
                    true,
                    "Atención procesada correctamente",
                    idAtencionGenerado,
                    request.getIdEstadoAtencion(),
                    request.getEstadoFirma()
            );
            
            response.setRutaPdfFirmado(rutaPdfFinal); 
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error crítico en el flujo transaccional y físico de la atención médica: " + e.getMessage(), e);
        }
    }
}