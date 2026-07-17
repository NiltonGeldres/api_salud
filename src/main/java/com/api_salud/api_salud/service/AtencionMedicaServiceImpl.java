package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.storage.StorageService;
import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import com.api_salud.api_salud.repository.AtencionMedicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    private final AtencionMedicaRepository atencionMedicaRepository;
    private final ObjectMapper objectMapper;
    private final PdfGeneratorService pdfGeneratorService;
	private final StorageService storageService; 
    private final StorageConfig storageConfig;    
    
    
    @Value("${app.storage.ruta-pdfs}")
    private String rutaBasePdfs;

    public AtencionMedicaServiceImpl(
    		AtencionMedicaRepository atencionMedicaRepository, 
	        ObjectMapper objectMapper, 
	        PdfGeneratorService pdfGeneratorService,
	        StorageService storageService,
	        StorageConfig storageConfig)
    { 
		this.atencionMedicaRepository = atencionMedicaRepository;
		this.objectMapper = objectMapper;
		this.pdfGeneratorService = pdfGeneratorService;
		this.storageService = storageService;        
		this.storageConfig = storageConfig;          
	}    
    
    @Override
    @Transactional
    public AtencionMedicaResponse guardarAtencionMedica(AtencionMedicaRequest request) {
        try {
            request.setEstadoFirma("PENDIENTE");
            String jsonPayload = objectMapper.writeValueAsString(request);
            Long idAtencionGenerado = atencionMedicaRepository.guardarAtencionMedicaCompleta(jsonPayload);
            return new AtencionMedicaResponse(true, "Atención registrada.", idAtencionGenerado, request.getIdEstadoAtencion(), "PENDIENTE");
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar: " + e.getMessage(), e);
        }
    }
    
    @Override
    @Transactional 
    public AtencionMedicaResponse firmarYGenerarPdf(Long idAtencion) {
    	

        try {
            // 1. Obtener datos
            String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
            AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonPayloadBD, AtencionMedicaPdfDTO.class);
            // 2. Generar bytes
            byte[] pdfBytes = pdfGeneratorService.generarPdfHistoriaClinica(pdfDto);
            // 3. NUEVA INTEGRACIÓN CON STORAGE SERVICE
            // Obtenemos la plantilla: /{empresa}/historias_{empresa}/{paciente}/atencion_{atencion}_{empresa}.pdf
            String plantilla = storageConfig.getPath().getHistorias();
            // Construimos la ruta relativa (el StorageService ya sabe si es local o cloud)
            String hc = (pdfDto.getPaciente() != null) ? pdfDto.getPaciente().getHc() : "SIN_HC";
            String entidad = (pdfDto.getIdEntidad() != null) ? String.valueOf(pdfDto.getIdEntidad()) : "SIN_ENTIDAD";            
            
            // El buildPath reemplaza los {placeholders} definidos en tu application.properties
            String rutaRelativa = plantilla
                    .replace("{empresa}", entidad)
                    .replace("{paciente}", hc)
                    .replace("{atencion}", String.valueOf(idAtencion));

            // Guardado abstracto (No importa si es D:/ o Cloud)
            storageService.guardar(rutaRelativa, pdfBytes);
            
            // 4. Actualizar BD
            atencionMedicaRepository.actualizarRutaPdf(idAtencion, rutaRelativa);
            atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "FIRMADO_ELECTRONICO");
           
            AtencionMedicaResponse response = new AtencionMedicaResponse(true, "Firmado con éxito.", idAtencion, 3, "FIRMADO_ELECTRONICO");
            response.setRutaPdfFirmado(rutaRelativa); // Aquí asignas la ruta correctamente
            return response; // <--- Devuelve el objeto que ya tiene la ruta asignada
        } catch (Exception e) {
            e.printStackTrace(); // Esto es vital: imprimirá la línea exacta del error en la consola
            throw new RuntimeException("Error en proceso de firmado: " + e.getMessage(), e);
        }
        
    }
    
}

/*
@Override
@Transactional 
public AtencionMedicaResponse firmarYGenerarPdf(Long idAtencion) {
    try {
        String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        if (jsonPayloadBD == null) throw new IllegalArgumentException("No se encontró la atención.");

        // El DTO ahora contiene toda la información necesaria que viene del JSON
        AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonPayloadBD, AtencionMedicaPdfDTO.class);
        
        // Generación delegada al servicio de PDF
        byte[] pdfBytes = pdfGeneratorService.generarPdfHistoriaClinica(pdfDto);

        // Almacenamiento dinámico
        String hc = (pdfDto.getPaciente() != null) ? pdfDto.getPaciente().getHc() : "SIN_HC";
        String carpeta = this.rutaBasePdfs + "pacientes/" + hc + "/";
        new File(carpeta).mkdirs();

        String nombreArchivo = "ATENCION_" + idAtencion + ".pdf";
        String rutaCompleta = carpeta + nombreArchivo;
        
        Files.write(Paths.get(rutaCompleta), pdfBytes);
        
        atencionMedicaRepository.actualizarRutaPdf(idAtencion, rutaCompleta);
        atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "FIRMADO_ELECTRONICO");

        AtencionMedicaResponse response = new AtencionMedicaResponse(true, "Firmado con éxito.", idAtencion, 3, "FIRMADO_ELECTRONICO");
        response.setRutaPdfFirmado(rutaCompleta);
        return response;
    } catch (Exception e) {
        throw new RuntimeException("Error en proceso de firmado: " + e.getMessage(), e);
    }
}
*/ 