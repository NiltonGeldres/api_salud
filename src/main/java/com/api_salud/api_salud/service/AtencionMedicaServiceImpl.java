package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.storage.StorageService;
import com.api_salud.api_salud.utils.SecurityUtils;
import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.context.TenantContext;
import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import com.api_salud.api_salud.dto.DocumentoAdjuntoDTO;
import com.api_salud.api_salud.repository.AtencionMedicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    private final AtencionMedicaRepository atencionMedicaRepository;
    private final ObjectMapper objectMapper;
    private final PdfGeneratorService pdfGeneratorService;
	private final StorageService storageService; 
    private final StorageConfig storageConfig;    
    private final SecurityUtils securityUtils; 
    private final CitaService citaService; // <--- INYECCIÓN
    
  //  @Value("${app.storage.ruta-pdfs}")
  //  private String rutaBasePdfs;

    public AtencionMedicaServiceImpl(
    		AtencionMedicaRepository atencionMedicaRepository, 
	        ObjectMapper objectMapper, 
	        PdfGeneratorService pdfGeneratorService,
	        StorageService storageService,
	        StorageConfig storageConfig,
	        SecurityUtils securityUtils,
	        CitaService citaService   )
    { 
		this.atencionMedicaRepository = atencionMedicaRepository;
		this.objectMapper = objectMapper;
		this.pdfGeneratorService = pdfGeneratorService;
		this.storageService = storageService;        
		this.storageConfig = storageConfig;          
		this.securityUtils = securityUtils;     
		this.citaService = citaService;		
	}    

    /**
     * 1. CREAR BORRADOR (POST)
     * Se asigna el estado BORRADOR, inserta en BD y vincula la cita con el nuevo ID generado.
     */
    @Override
    @Transactional
    public AtencionMedicaResponse guardarBorrador(AtencionMedicaRequest request) {
        try {
            request.setEstadoFirma("BORRADOR");
            String jsonPayload = objectMapper.writeValueAsString(request);

            // 1. Inserción inicial de borrador en BD
            Long idAtencionGenerado = atencionMedicaRepository.guardarAtencionMedicaBorrador(jsonPayload);

            // 2. Vinculación de la Cita con la Atención generada
            if (request.getIdCita() != null && request.getIdCita() > 0) {
                boolean vinculado = citaService.vincularAtencion(request.getIdCita(), idAtencionGenerado);
                if (!vinculado) {
                    System.err.println("Advertencia: No se pudo asociar la atención " + idAtencionGenerado + " a la cita " + request.getIdCita());
                }
            }

            return new AtencionMedicaResponse(
                true, 
                "Borrador creado correctamente.", 
                idAtencionGenerado, 
                request.getIdEstadoAtencion(), 
                "BORRADOR"
            );

        } catch (Exception e) {
            throw new RuntimeException("Error al crear borrador de atención: " + e.getMessage(), e);
        }
    }

    /**
     * 2. ACTUALIZAR BORRADOR (PUT)
     * Actualiza el registro existente en BD sin relanzar la vinculación de la cita.
     */
    @Override
    @Transactional
    public AtencionMedicaResponse actualizarBorrador(AtencionMedicaRequest request) {
        try {
            if (request.getIdAtencion() == null || request.getIdAtencion() <= 0) {
                throw new IllegalArgumentException("Se requiere un idAtencion válido para actualizar el borrador.");
            }

            request.setEstadoFirma("BORRADOR ACTUALIZADO");
            String jsonPayload = objectMapper.writeValueAsString(request);

            // Actualización parcial o total del borrador en BD
            atencionMedicaRepository.actualizarAtencionMedicaBorrador(request.getIdAtencion(), jsonPayload);

            return new AtencionMedicaResponse(
                true, 
                "Borrador actualizado correctamente.", 
                request.getIdAtencion(), 
                request.getIdEstadoAtencion(), 
                "BORRADOR ACTUALIZADO"
            );

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar borrador de atención: " + e.getMessage(), e);
        }
    }

    /**
     * 3. GUARDAR ATENCIÓN COMPLETA (FINALIZAR / PREPARAR FIRMA)
     */
    @Override
    @Transactional
    public AtencionMedicaResponse guardarAtencionMedica(AtencionMedicaRequest request) {
        try {
            request.setEstadoFirma("PENDIENTE");
            String jsonPayload = objectMapper.writeValueAsString(request);
            
            Long idAtencionGenerado = atencionMedicaRepository.guardarAtencionMedicaCompleta(jsonPayload);
            
            if (request.getIdCita() != null && request.getIdCita() > 0) {
                boolean vinculado = citaService.vincularAtencion(request.getIdCita(), idAtencionGenerado);
                if (!vinculado) {
                    System.err.println("Advertencia: No se pudo asociar la atención " + idAtencionGenerado + " a la cita " + request.getIdCita());
                }
            }

            return new AtencionMedicaResponse(
                true, 
                "Atención registrada completamente.", 
                idAtencionGenerado, 
                request.getIdEstadoAtencion(), 
                "PENDIENTE"
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar atención completa: " + e.getMessage(), e);
        }
    }    
    
  

    /**
     * PASO 2: Generación del borrador PDF y congelamiento de Hash SHA-256
     * Estado en BD: PENDIENTE_FIRMA
     */
    
    /**
     * PASO 2: Persistencia / Actualización + Generación del borrador PDF y congelamiento de Hash SHA-256
     * Estado en BD: PENDIENTE_FIRMA
     */
	@Override
	@Transactional
	public AtencionMedicaResponse prepararPdf(AtencionMedicaRequest request) {
	    try {
	        Long idAtencion = request.getIdAtencion();
	
	        // 1. Establecer estado de firma y serializar el DTO a String JSON
	        request.setEstadoFirma("PENDIENTE_FIRMA");
	        String jsonPayload = objectMapper.writeValueAsString(request);
	
	        // 2. Persistir en Base de Datos según la presencia de idAtencion
	        if (idAtencion == null || idAtencion <= 0L) {
	            idAtencion = atencionMedicaRepository.guardarAtencionMedicaBorrador(jsonPayload);
	            request.setIdAtencion(idAtencion);
	
	            if (request.getIdCita() != null && request.getIdCita() > 0) {
	                boolean vinculado = citaService.vincularAtencion(request.getIdCita(), idAtencion);
	                if (!vinculado) {
	                    System.err.println("Advertencia: No se pudo asociar la atención " + idAtencion + " a la cita " + request.getIdCita());
	                }
	            }
	        } else {
	            atencionMedicaRepository.actualizarAtencionMedicaBorrador(idAtencion, jsonPayload);
	        }
	
	        // 3. Obtener el JSON enriquecido y consolidado directamente desde PostgreSQL
	        String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
	        if (jsonPayloadBD == null || jsonPayloadBD.trim().isEmpty()) {
	            throw new RuntimeException("No se encontraron datos persistidos para la atención con ID: " + idAtencion);
	        }
	
	        // 4. Generar y congelar Hash SHA-256 de integridad sobre el JSON consolidado
	        String hashIntegridad = securityUtils.generarHashIntegridad(jsonPayloadBD, idAtencion);
	        atencionMedicaRepository.actualizarHashFirma(idAtencion, hashIntegridad);
	
	        // 5. Mapear a DTO de PDF e inyectar Hash temporal para el renderizado
	        AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonPayloadBD, AtencionMedicaPdfDTO.class);
	        pdfDto.setHashFirma(hashIntegridad);
	        pdfDto.setEstadoFirma("PENDIENTE_FIRMA");
	
	        Integer idEntidad = pdfDto.getIdEntidad() != null ? pdfDto.getIdEntidad() : 0;
	        String hcPaciente = (pdfDto.getPaciente() != null && pdfDto.getPaciente().getHc() != null)
	                ? pdfDto.getPaciente().getHc() 
	                : "SIN_HC";
	
	        // 6. Recolectar condicionalmente los 4 PDFs a generar segun presencia de datos
	        Map<String, byte[]> documentos = new LinkedHashMap<>();
	
	        // Documento 1: Historia Clínica (Siempre obligatorio) -> atencion_medica.html
	        documentos.put("historia", pdfGeneratorService.generarPdfHistoriaClinica(pdfDto));
	
	        // Documento 2: Receta Médica -> atencion_medica_receta.html
	        if (pdfDto.getMedicacion() != null && !pdfDto.getMedicacion().isEmpty()) {
	            documentos.put("receta", pdfGeneratorService.generarPdfReceta(pdfDto));
	        }
	
	        // Documento 3: Órdenes de Exámenes -> atencion_medica_orden.html
	        if (pdfDto.getExamenesAuxiliares() != null && !pdfDto.getExamenesAuxiliares().isEmpty()) {
	            documentos.put("orden", pdfGeneratorService.generarPdfOrdenes(pdfDto));
	        }
	
	        // Documento 4: Indicaciones / Alta -> atencion_medica_indicaciones.html
	        if (pdfDto.getAlta() != null && !pdfDto.getAlta().isEmpty()) {
	            documentos.put("indicaciones", pdfGeneratorService.generarPdfIndicaciones(pdfDto));
	        }
	
	        // 7. Iterar para guardar cada PDF en Storage y generar sus Presigned URLs
	        List<DocumentoAdjuntoDTO> listaDocumentos = new ArrayList<>();
	        String rutaHistoriaBorrador = null;
	
	        for (Map.Entry<String, byte[]> entry : documentos.entrySet()) {
	            String tipoDoc = entry.getKey();
	            byte[] pdfBytes = entry.getValue();
	
	            String rutaBorrador = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, false);
	            String rutaFirmado  = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, true);
	
	            // Guardar borrador en storage
	            storageService.guardar(rutaBorrador, pdfBytes);
	
	            // Generar URLs presignadas para el Agente Swing
	            String urlLectura = storageService.generarPresignedUrl(rutaBorrador);
	            String urlSubida  = storageService.generarPresignedUrlSubida(rutaFirmado);
	
	            listaDocumentos.add(new DocumentoAdjuntoDTO(tipoDoc, rutaBorrador, rutaFirmado, urlLectura, urlSubida));
	
	            if ("historia".equals(tipoDoc)) {
	                rutaHistoriaBorrador = rutaBorrador;
	            }
	        }
	
	        // 8. Actualizar estado y la ruta principal (Historia) en PostgreSQL
	        atencionMedicaRepository.actualizarRutasPdf(idAtencion, listaDocumentos);
	        atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "PENDIENTE_FIRMA");
	
	        // 9. Construir respuesta con la lista de los 4 documentos
	        AtencionMedicaResponse response = new AtencionMedicaResponse(
	                true,
	                "Se generaron " + listaDocumentos.size() + " documentos borrador correctamente.",
	                idAtencion,
	                2,
	                "PENDIENTE_FIRMA"
	        );
	
	        response.setDocumentos(listaDocumentos); // Asigna la lista con los 4 PDFs
	        response.setHashIntegridad(hashIntegridad);
	
	        return response;
	
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar y preparar los PDFs borradores: " + e.getMessage(), e);
	    }
	}
    
    
  /*  @Override
    @Transactional
    public AtencionMedicaResponse prepararPdf(AtencionMedicaRequest request) {
        try {
            Long idAtencion = request.getIdAtencion();

            // 1. Establecer estado de firma y serializar el DTO a String JSON
            request.setEstadoFirma("PENDIENTE_FIRMA");
            String jsonPayload = objectMapper.writeValueAsString(request);

            // 2. Persistir en Base de Datos según la presencia de idAtencion
            if (idAtencion == null || idAtencion <= 0L) {
                // Nuevo borrador -> Ejecuta fn_guardar_atencion_medica_borrador(jsonPayload)
                idAtencion = atencionMedicaRepository.guardarAtencionMedicaBorrador(jsonPayload);
                request.setIdAtencion(idAtencion);

                // Si viene de una cita, realizar la vinculación
                if (request.getIdCita() != null && request.getIdCita() > 0) {
                    boolean vinculado = citaService.vincularAtencion(request.getIdCita(), idAtencion);
                    if (!vinculado) {
                        System.err.println("Advertencia: No se pudo asociar la atención " + idAtencion + " a la cita " + request.getIdCita());
                    }
                }
            } else {
                // Borrador existente -> Ejecuta fn_actualizar_atencion_medica_borrador(idAtencion, jsonPayload)
                atencionMedicaRepository.actualizarAtencionMedicaBorrador(idAtencion, jsonPayload);
            }

            // 3. Obtener el JSON enriquecido y consolidado directamente desde PostgreSQL
            String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
            if (jsonPayloadBD == null || jsonPayloadBD.trim().isEmpty()) {
                throw new RuntimeException("No se encontraron datos persistidos para la atención con ID: " + idAtencion);
            }

            // 4. Generar y congelar Hash SHA-256 de integridad sobre el JSON consolidado
            String hashIntegridad = securityUtils.generarHashIntegridad(jsonPayloadBD, idAtencion);
            atencionMedicaRepository.actualizarHashFirma(idAtencion, hashIntegridad);

            // 5. Mapear a DTO de PDF e inyectar Hash temporal para el renderizado del documento
            AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonPayloadBD, AtencionMedicaPdfDTO.class);
            pdfDto.setHashFirma(hashIntegridad);
            pdfDto.setEstadoFirma("PENDIENTE_FIRMA");

            // 6. Generar bytes del PDF borrador
            byte[] pdfBytes = pdfGeneratorService.generarPdfHistoriaClinica(pdfDto);

            // 7. Construir la ruta relativa dinámica
            String plantilla = storageConfig.getPath().getBorradores();            
            //String plantilla = storageConfig.getPath().getHistorias();
            String hc = (pdfDto.getPaciente() != null && pdfDto.getPaciente().getHc() != null) 
                    ? pdfDto.getPaciente().getHc() : "SIN_HC";
            String entidad = (pdfDto.getIdEntidad() != null) 
                    ? String.valueOf(pdfDto.getIdEntidad()) : "SIN_ENTIDAD";            
            
            String rutaRelativa = plantilla
                    .replace("{empresa}", entidad)
                    .replace("{paciente}", hc)
                    .replace("{atencion}", String.valueOf(idAtencion));

            // 8. Guardar borrador PDF en Storage (Local o Cloud)
            storageService.guardar(rutaRelativa, pdfBytes);
            
            // 9. Actualizar estado y ruta en PostgreSQL
            atencionMedicaRepository.actualizarRutaPdf(idAtencion, rutaRelativa);
            atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "PENDIENTE_FIRMA");

            // 10. Construir respuesta para React obteniendo la URL dinámica según la estrategia (LOCAL o CLOUD)
            String urlVisualizacion = storageService.obtenerUrlPublica(rutaRelativa);
            
            // 11. Construir respuesta para React
            AtencionMedicaResponse response = new AtencionMedicaResponse(
                    true, 
                    "PDF borrador generado exitosamente. Pendiente de firma digital.", 
                    idAtencion, 
                    2, // ID Estado Pendiente de Firma
                    "PENDIENTE_FIRMA"
            );
            response.setRutaPdfFirmado(rutaRelativa);
//            response.setRutaPdfFirmado(urlVisualizacion);
            response.setHashIntegridad(hashIntegridad);
            
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar y preparar el PDF borrador: " + e.getMessage(), e);
        }
    }
*/    

    @Override
    @Transactional
    public AtencionMedicaResponse firmarAtencion(Long idAtencion) {
        try {
            // 1. Obtener el JSON desde la BD
            String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
            if (jsonPayloadBD == null) {
                throw new RuntimeException("No se encontró la atención médica con ID: " + idAtencion);
            }

            // 2. Parsear DTO para verificar el estado
            AtencionMedicaPdfDTO dto = objectMapper.readValue(jsonPayloadBD, AtencionMedicaPdfDTO.class);

            // =======================================================================
            // 🚀 RUTA RÁPIDA: SI YA ESTÁ FIRMADO -> RETORNAR DIRECTO AL FRONTEND
            // =======================================================================
            if ("FIRMADO_ELECTRONICO".equalsIgnoreCase(dto.getEstadoFirma())) {
                System.out.println("FIRMA YA REGISTRADA PARA ID " + idAtencion + ". Devolviendo información existente.");

                // Recuperar el hash existente (del DTO o fallback de cálculo)
                String hashExistente = dto.getHashFirma();
                if (hashExistente == null || hashExistente.isEmpty()) {
                    hashExistente = securityUtils.generarHashIntegridad(jsonPayloadBD, idAtencion);
                }

                AtencionMedicaResponse response = new AtencionMedicaResponse(
                        true, 
                        "La atención médica ya se encuentra firmada electrónicamente.", 
                        idAtencion, 
                        3, 
                        "FIRMADO_ELECTRONICO"
                );
                response.setJsonEnriquecidoFirmado(jsonPayloadBD);
                response.setHashIntegridad(hashExistente);

                return response; // Exit temprano
            }

            // =======================================================================
            // ⚙️ RUTA COMPLETA: DOCUMENTO PENDIENTE DE FIRMA
            // =======================================================================
            
            // Paso A: Generar Hash de integridad (SHA-256)
            String hashIntegridad = securityUtils.generarHashIntegridad(jsonPayloadBD, idAtencion);

            // Paso B: Inyectar datos de firma en el DTO
            dto.setHashFirma(hashIntegridad);
            dto.setEstadoFirma("FIRMADO_ELECTRONICO");
            dto.setFechaFirma(LocalDateTime.now().toString());

            // Paso C: Serializar JSON firmado
            String jsonFirmado = objectMapper.writeValueAsString(dto);

            // Paso D: Guardar en estructura de disco (.json)
            String entidad = (dto.getIdEntidad() != null) ? String.valueOf(dto.getIdEntidad()) : "SIN_ENTIDAD";
            String hc = (dto.getPaciente() != null) ? dto.getPaciente().getHc() : "SIN_HC";
            String plantilla = storageConfig.getPath().getFirmado(); 

            String rutaRelativaJson = plantilla
                    .replace("{empresa}", entidad)
                    .replace("{paciente}", hc)
                    .replace("{atencion}", String.valueOf(idAtencion))
                    .replace(".pdf", ".json");

            storageService.guardar(rutaRelativaJson, jsonFirmado.getBytes(StandardCharsets.UTF_8));

            // Paso E: Ejecutar la función almacenada en PostgreSQL (actualiza columnas SQL + JSONB)
            atencionMedicaRepository.firmarAtencion(idAtencion, hashIntegridad, "TOKEN");            

            // Paso F: Responder al Frontend
            AtencionMedicaResponse response = new AtencionMedicaResponse(
                    true, 
                    "Atención firmada digitalmente con éxito.", 
                    idAtencion, 
                    3, 
                    "FIRMADO_ELECTRONICO"
            );
            response.setJsonEnriquecidoFirmado(jsonFirmado);
            response.setHashIntegridad(hashIntegridad);

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error en proceso de firmado: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String obtenerJsonAtencion(Long idAtencion) {
        String jsonAtencion = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        
        if (jsonAtencion == null || jsonAtencion.trim().isEmpty() || "{}".equals(jsonAtencion)) {
            throw new RuntimeException("No se encontraron datos registrados para la atención con ID: " + idAtencion);
        }
        
        return jsonAtencion;
    }    

    
 // =======================================================================
 // 🎯 LISTAR ATENCIONES PENDIENTES DE FIRMA
 // =======================================================================
 @Override
 @Transactional(readOnly = true)
 public String listarAtencionesPendientesFirma(Integer idMedico) {
     Integer idEntidad = TenantContext.getEntidadId(); 

     if (idEntidad == null) {
         throw new IllegalStateException("No se pudo identificar el Tenant/Entidad en el contexto de la solicitud.");
     }
     
     String jsonResultado = atencionMedicaRepository.listarAtencionesPendientesFirma(idEntidad, idMedico);
     
     if (jsonResultado == null || jsonResultado.trim().isEmpty() || "{}".equals(jsonResultado) || "[]".equals(jsonResultado)) {
         throw new RuntimeException("No se encontraron atenciones para firmar.");
     }
     
     try {
         // 1. Convertir el String JSON devuelto por PostgreSQL en un árbol de nodos Jackson
         JsonNode rootNode = objectMapper.readTree(jsonResultado);

         if (rootNode.isArray()) {
             ArrayNode arrayNode = (ArrayNode) rootNode;

             // 2. Recorrer cada atención médica en el arreglo
             for (JsonNode node : arrayNode) {
                 ObjectNode atencionNode = (ObjectNode) node;

                 // 3. Extraer la ruta relativa guardada en BD (ej: "/2/borradores/21447464/atencion_284_borrador.pdf")
                 if (atencionNode.has("rutaPdfFirmado") && !atencionNode.get("rutaPdfFirmado").isNull()) {
                     String rutaRelativa = atencionNode.get("rutaPdfFirmado").asText();

                     // 4. Generar la Presigned URL (15 min) mediante tu storageService
                     String urlPreFirmada = storageService.generarPresignedUrl(rutaRelativa);

                     // 5. Sobrescribir el campo en el JSON que viajará al cliente
                     atencionNode.put("rutaPdfFirmado", urlPreFirmada);
                 }
             }
             
             // 6. Retornar el JSON transformado con las URLs temporales firmadas
             return objectMapper.writeValueAsString(arrayNode);
         }

         return jsonResultado;

     } catch (Exception e) {
         throw new RuntimeException("Error al procesar y firmar las URLs de los documentos PDF", e);
     }
 }
    
}




/*    @Override
@Transactional 
public AtencionMedicaResponse prepararPdf(Long idAtencion) {
    try {
        // 1. Obtener datos registrados desde BD
        String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        if (jsonPayloadBD == null || jsonPayloadBD.trim().isEmpty()) {
            throw new RuntimeException("No se encontraron datos para la atención con ID: " + idAtencion);
        }

        // 2. Generar y congelar Hash SHA-256 de integridad sobre el JSON
        String hashIntegridad = securityUtils.generarHashIntegridad(jsonPayloadBD, idAtencion);
        atencionMedicaRepository.actualizarHashFirma(idAtencion, hashIntegridad);

        // 3. Mapear DTO e inyectar Hash temporal para renderizado
        AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonPayloadBD, AtencionMedicaPdfDTO.class);
        pdfDto.setHashFirma(hashIntegridad);
        pdfDto.setEstadoFirma("PENDIENTE_FIRMA");

        // 4. Generar bytes del PDF borrador
        byte[] pdfBytes = pdfGeneratorService.generarPdfHistoriaClinica(pdfDto);

        // 5. Construir la ruta relativa dinámica
        String plantilla = storageConfig.getPath().getHistorias();
        String hc = (pdfDto.getPaciente() != null && pdfDto.getPaciente().getHc() != null) 
                ? pdfDto.getPaciente().getHc() : "SIN_HC";
        String entidad = (pdfDto.getIdEntidad() != null) 
                ? String.valueOf(pdfDto.getIdEntidad()) : "SIN_ENTIDAD";            
        
        String rutaRelativa = plantilla
                .replace("{empresa}", entidad)
                .replace("{paciente}", hc)
                .replace("{atencion}", String.valueOf(idAtencion));

        // 6. Guardar borrador PDF en Storage (Local o Cloud)
        storageService.guardar(rutaRelativa, pdfBytes);
        
        // 7. Actualizar estado y ruta en PostgreSQL
        atencionMedicaRepository.actualizarRutaPdf(idAtencion, rutaRelativa);
        atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "PENDIENTE_FIRMA");
       
        // 8. Responder a React
        AtencionMedicaResponse response = new AtencionMedicaResponse(
                true, 
                "PDF borrador generado exitosamente. Pendiente de firma digital.", 
                idAtencion, 
                2, // ID Estado Pendiente de Firma
                "PENDIENTE_FIRMA"
        );
        response.setRutaPdfFirmado(rutaRelativa);
        response.setHashIntegridad(hashIntegridad);
        
        return response;

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error al preparar el PDF borrador: " + e.getMessage(), e);
    }
}    
*/

/*    
// =======================================================================
   // 🎯 LISTAR ATENCIONES PENDIENTES DE FIRMA
   // =======================================================================
   @Override
   @Transactional(readOnly = true)
   public String listarAtencionesPendientesFirma(Integer idMedico) {
   	Integer idEntidad = TenantContext.getEntidadId(); 

       if (idEntidad == null) {
           throw new IllegalStateException("No se pudo identificar el Tenant/Entidad en el contexto de la solicitud.");
       }
       
       String jsonResultado = atencionMedicaRepository.listarAtencionesPendientesFirma(idEntidad, idMedico);
       
       if (jsonResultado == null || jsonResultado.trim().isEmpty() || "{}".equals(jsonResultado)) {
           throw new RuntimeException("No se encontraron atenciones para firmar: ");
       }
       
       // Garantizamos retorno de arreglo JSON válido si la BD devuelve null
       return jsonResultado;
   }    
 
 */  
