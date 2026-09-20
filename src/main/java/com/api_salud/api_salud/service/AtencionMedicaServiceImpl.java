package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.AtencionMedicaConfirmarFirmaRequest;
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
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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

    
    @Override
    @Transactional
    public ObjectNode confirmarFirmaYObtenerJson(AtencionMedicaConfirmarFirmaRequest dto) {
        try {
            // 1. Ejecutar la función almacenada (Actualiza columnas y payload_origen a 'FIRMADO')
            Long idAtencion = atencionMedicaRepository.confirmarFirmaJson(dto);

            // 2. Re-consultar el JSON actualizado desde la BD
//            String jsonActualizadoStr = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
            ObjectNode jsonActualizadoStr = obtenerJsonAtencion(idAtencion);

            if (jsonActualizadoStr == null) {
                throw new RuntimeException("No se encontró la atención médica confirmada con ID: " + idAtencion);
            }

            // 3. Parsear a ObjectNode
//            ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(jsonActualizadoStr);
            ObjectNode jsonNode = jsonActualizadoStr;

            // 4. Inyectar las Presigned GET URLs efímeras para los PDFs firmados
         //   enriquecerJsonConPresignedUrlsFirmadas(jsonNode);

            // 5. Retornar el JSON enriquecido
            return jsonNode;

        } catch (Exception e) {
            throw new RuntimeException("Error al confirmar firma de atención médica: " + e.getMessage(), e);
        }
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
     * PASO 2: Persistencia / Actualización + Generación del borrador PDF y congelamiento de Hash SHA-256
     * Estado en BD: PENDIENTE_FIRMA
     */
    @Override
    @Transactional
    public ObjectNode prepararPdf(AtencionMedicaRequest request) {
        try {
            request.setEstadoFirma("PENDIENTE_FIRMA");

            // 1. Guardar o actualizar borrador en PostgreSQL
            Long idAtencion = guardarOActualizarBorrador(request);

            // 2. Obtener la fuente de verdad (JSON Enriquecido por PostgreSQL)
            String jsonEnriquecidoBD = obtenerJsonEnriquecidoValido(idAtencion);

            // 3. Generar y registrar Hash SHA-256 de integridad
            String hashIntegridad = generarYPersistirHash(idAtencion, jsonEnriquecidoBD);

            // 4. Mapear DTO y generar binarios PDF en memoria
            AtencionMedicaPdfDTO pdfDto = mapearYPrepararPdfDto(jsonEnriquecidoBD, hashIntegridad);
            Map<String, byte[]> documentosPdfMap = generarBinariosPdf(pdfDto);

            // 5. Procesar almacenamiento en Cloudflare R2 (Rutas fijas vs. URLs efímeras)
            ProcesamientoDocumentosResult resultadoDocs = procesarArchivosEnStorage(pdfDto, idAtencion, documentosPdfMap);

            // 6. Actualizar rutas fijas y estado final en PostgreSQL
            atencionMedicaRepository.actualizarRutasPdf(idAtencion, resultadoDocs.getListaBD());
            atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "PENDIENTE_FIRMA");
            
            // 7. Ensamblar y devolver la respuesta plana enriquecida
            return construirRespuestaPlana(idAtencion, jsonEnriquecidoBD, hashIntegridad, pdfDto, resultadoDocs.getListaResponse());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al preparar y generar los PDFs borradores: " + e.getMessage(), e);
        }
    }

    // ==================================================================================
    // MÉTODOS PRIVADOS DE SOPORTE (RESPONSABILIDADES AISLADAS)
    // ==================================================================================

    /**
     * Persiste o actualiza la atención en la BD y vincula la cita si aplica.
     */
    private Long guardarOActualizarBorrador(AtencionMedicaRequest request) throws Exception {
        Long idAtencion = request.getIdAtencion();
        String jsonPayload = objectMapper.writeValueAsString(request);

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
        return idAtencion;
    }

    /**
     * Consulta la base de datos para obtener el JSON enriquecido consolidado.
     */
    private String obtenerJsonEnriquecidoValido(Long idAtencion) {
        String jsonEnriquecidoBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        if (jsonEnriquecidoBD == null || jsonEnriquecidoBD.trim().isEmpty()) {
            throw new RuntimeException("No se encontró el JSON enriquecido en la BD para la atención: " + idAtencion);
        }
        return jsonEnriquecidoBD;
    }

    /**
     * Calcula el hash de integridad sobre el JSON enriquecido y lo actualiza en la BD.
     */
    private String generarYPersistirHash(Long idAtencion, String jsonEnriquecidoBD) {
        String hashIntegridad = securityUtils.generarHashIntegridad(jsonEnriquecidoBD, idAtencion);
        atencionMedicaRepository.actualizarHashFirma(idAtencion, hashIntegridad);
        return hashIntegridad;
    }

    /**
     * Convierte el JSON enriquecido en el DTO de PDFs y prepara recursos como el logo en Base64.
     */
    private AtencionMedicaPdfDTO mapearYPrepararPdfDto(String jsonEnriquecidoBD, String hashIntegridad) throws Exception {
        AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonEnriquecidoBD, AtencionMedicaPdfDTO.class);
        pdfDto.setHashFirma(hashIntegridad);
        pdfDto.setEstadoFirma("PENDIENTE_FIRMA");

        if (pdfDto.getLogoTenantUrl() != null && !pdfDto.getLogoTenantUrl().isEmpty()) {
            String logoBase64 = storageService.obtenerLogoComoBase64(pdfDto.getLogoTenantUrl());
            pdfDto.setLogoTenantUrl(logoBase64);
        }
        return pdfDto;
    }

    /**
     * Genera de forma condicional los arreglos de bytes (PDFs) según la data clínica del DTO.
     */
    private Map<String, byte[]> generarBinariosPdf(AtencionMedicaPdfDTO pdfDto) {
        Map<String, byte[]> documentosMap = new LinkedHashMap<>();
        
        documentosMap.put("historia", pdfGeneratorService.generarPdfHistoriaClinica(pdfDto));

        if (pdfDto.getMedicacion() != null && !pdfDto.getMedicacion().isEmpty()) {
            documentosMap.put("receta", pdfGeneratorService.generarPdfReceta(pdfDto));
        }
        if (pdfDto.getExamenesAuxiliares() != null && !pdfDto.getExamenesAuxiliares().isEmpty()) {
            documentosMap.put("orden", pdfGeneratorService.generarPdfOrdenes(pdfDto));
        }
        if (pdfDto.getAlta() != null && !pdfDto.getAlta().isEmpty()) {
            documentosMap.put("indicaciones", pdfGeneratorService.generarPdfIndicaciones(pdfDto));
        }
        return documentosMap;
    }

    /**
     * Sube los binarios a Cloudflare R2 y genera la lista estática (BD) y la dinámica limpia (URLs efímeras hacia afuera).
     */
    private ProcesamientoDocumentosResult procesarArchivosEnStorage(AtencionMedicaPdfDTO pdfDto, Long idAtencion, Map<String, byte[]> documentosMap) {
        Integer idEntidad = pdfDto.getIdEntidad() != null ? pdfDto.getIdEntidad() : 0;
        String hcPaciente = (pdfDto.getPaciente() != null && pdfDto.getPaciente().getHc() != null)
                ? pdfDto.getPaciente().getHc()
                : "SIN_HC";

        List<DocumentoAdjuntoDTO> listaBD = new ArrayList<>();
        List<DocumentoAdjuntoDTO> listaResponse = new ArrayList<>();

        for (Map.Entry<String, byte[]> entry : documentosMap.entrySet()) {
            String tipoDoc = entry.getKey();
            byte[] pdfBytes = entry.getValue();

            String rutaBorrador = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, false);
            String rutaFirmado  = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, true);

            // Guardar físicamente el borrador en Cloudflare R2
            storageService.guardar(rutaBorrador, pdfBytes);

            // Generar URLs firmadas de lectura y subida
            String urlLectura = storageService.generarPresignedUrl(rutaBorrador);
            String urlSubida  = storageService.generarPresignedUrlSubida(rutaFirmado);

            // Para BD (Persistencia interna)
            listaBD.add(new DocumentoAdjuntoDTO(tipoDoc, rutaBorrador, rutaFirmado, null, null));

            // Para Response hacia el exterior (Únicamente Presigned URLs y tipo de documento)
            listaResponse.add(new DocumentoAdjuntoDTO(tipoDoc, null, null, urlLectura, urlSubida));
        }

        return new ProcesamientoDocumentosResult(listaBD, listaResponse);
    }

    /**
     * Ensambla el ObjectNode plano final fusionando la data de PostgreSQL con los metadatos y Presigned URLs.
     */
    private ObjectNode construirRespuestaPlana(Long idAtencion, String jsonEnriquecidoBD, String hashIntegridad,
                                               AtencionMedicaPdfDTO pdfDto, List<DocumentoAdjuntoDTO> listaResponse) throws Exception {
        
        // Obtener la última versión actualizada desde la BD
        String jsonFinalBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        ObjectNode responseNode = (ObjectNode) objectMapper.readTree(
                (jsonFinalBD != null && !jsonFinalBD.isEmpty()) ? jsonFinalBD : jsonEnriquecidoBD
        );

        // Inyección de atributos raíz
        responseNode.put("exito", true);
        responseNode.put("mensaje", "Se generaron " + listaResponse.size() + " documento(s) borrador correctamente.");
        responseNode.put("idAtencion", idAtencion);
        responseNode.put("idEstadoAtencion", 2);
        responseNode.put("estadoFirma", "PENDIENTE_FIRMA");

        if (pdfDto.getPaciente() != null && pdfDto.getPaciente().getIdPaciente() != null) {
            responseNode.put("idPaciente", pdfDto.getPaciente().getIdPaciente());
        }

        responseNode.put("hashIntegridad", hashIntegridad);
        responseNode.put("tsProcesamiento", OffsetDateTime.now().toString());

        // Inyectar colección de documentos con solo Presigned URLs (sin rutas internas)
        responseNode.set("documentos", objectMapper.valueToTree(listaResponse));

        // Inyectar propiedades planas de fácil acceso para React con URLs Presignadas puras
        for (DocumentoAdjuntoDTO doc : listaResponse) {
            if (doc.getTipoDocumento() == null) continue;
            
            // Solo toma la Presigned URL efímera
            String urlPresignada = doc.getUrlLecturaBorrador(); 
            if (urlPresignada == null) continue;

            switch (doc.getTipoDocumento().trim().toLowerCase()) {
                case "historia": responseNode.put("pdfRutaHistoria", urlPresignada); break;
                case "receta": responseNode.put("pdfRutaReceta", urlPresignada); break;
                case "orden": case "ordenes": responseNode.put("pdfRutaOrdenes", urlPresignada); break;
                case "indicaciones": responseNode.put("pdfRutaIndicaciones", urlPresignada); break;
            }
        }

        return responseNode;
    }

    // ==================================================================================
    // CLASE CONTENEDORA INTERNA
    // ==================================================================================

    private static class ProcesamientoDocumentosResult {
        private final List<DocumentoAdjuntoDTO> listaBD;
        private final List<DocumentoAdjuntoDTO> listaResponse;

        public ProcesamientoDocumentosResult(List<DocumentoAdjuntoDTO> listaBD, List<DocumentoAdjuntoDTO> listaResponse) {
            this.listaBD = listaBD;
            this.listaResponse = listaResponse;
        }

        public List<DocumentoAdjuntoDTO> getListaBD() { return listaBD; }
        public List<DocumentoAdjuntoDTO> getListaResponse() { return listaResponse; }
    }
    
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
    public ObjectNode obtenerJsonAtencion(Long idAtencion) {
        // 1. Obtener el JSON nativo directo desde la base de datos
        String jsonAtencionStr = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        
        if (jsonAtencionStr == null || jsonAtencionStr.trim().isEmpty() || "{}".equals(jsonAtencionStr)) {
            throw new RuntimeException("No se encontraron datos registrados para la atención con ID: " + idAtencion);
        }

        try {
            // 2. Parsear el String a un ObjectNode manipulable
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonAtencionStr);

            // 3. Enriquecer las rutas nativas e inyectar el arreglo "documentos" con Presigned URLs
            enriquecerJsonConPresignedUrls(rootNode);

            return rootNode;

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar y enriquecer el JSON de la atención ID: " + idAtencion, e);
        }
    }

    /**
     * Lee las rutas relativas nativas del JSON, genera las Presigned URLs en runtime 
     * y empaqueta el arreglo "documentos" sin exponer rutas relativas del storage.
     */
    
    private void enriquecerJsonConPresignedUrls(ObjectNode rootNode) {
        String estadoFirma = rootNode.hasNonNull("estadoFirma") ? rootNode.get("estadoFirma").asText() : "PENDIENTE_FIRMA";
        boolean esFirmado = "FIRMADO".equalsIgnoreCase(estadoFirma) || "FIRMADO_ELECTRONICO".equalsIgnoreCase(estadoFirma);

        // Mapeo: TipoDoc -> [ClaveBorrador, ClaveFirmado]
        Map<String, String[]> mapaClavesYTipos = new LinkedHashMap<>();
        mapaClavesYTipos.put("historia", new String[]{"pdfRutaHistoria", "pdfRutaHistoriaFirmado"});
        mapaClavesYTipos.put("receta", new String[]{"pdfRutaReceta", "pdfRutaRecetaFirmado"});
        mapaClavesYTipos.put("orden", new String[]{"pdfRutaOrdenes", "pdfRutaOrdenesFirmado"});
        mapaClavesYTipos.put("indicaciones", new String[]{"pdfRutaIndicaciones", "pdfRutaIndicacionesFirmado"});

        List<DocumentoAdjuntoDTO> listaDocumentos = new ArrayList<>();

        mapaClavesYTipos.forEach((tipoDoc, claves) -> {
            String claveBorrador = claves[0];
            String claveFirmado = claves[1];

            // 1. Obtener la ruta del borrador desde el JSON
            String rutaBorradorBD = rootNode.hasNonNull(claveBorrador) ? rootNode.get(claveBorrador).asText().trim() : null;

            if (rutaBorradorBD != null && !rutaBorradorBD.isEmpty()) {
                
                // 2. Obtener o calcular la ruta del archivo firmado
                String rutaFirmadoBD = rootNode.hasNonNull(claveFirmado) ? rootNode.get(claveFirmado).asText().trim() : null;
                
                if (rutaFirmadoBD == null || rutaFirmadoBD.isEmpty()) {
                    // Si la BD aún no tiene la ruta del firmado, la calculamos dinámicamente con tu patrón
                    rutaFirmadoBD = rutaBorradorBD.replace("-borrador.pdf", "-firmado.pdf")
                                                  .replace("/borradores/", "/atenciones/");
                }

                // 3. Limpieza de barras iniciales para Cloudflare R2 / S3
                String rutaBorradorLimpia = rutaBorradorBD.startsWith("/") ? rutaBorradorBD.substring(1) : rutaBorradorBD;
                String rutaFirmadoLimpia = rutaFirmadoBD.startsWith("/") ? rutaFirmadoBD.substring(1) : rutaFirmadoBD;

                // 4. Generación de URLs Presignadas
                String urlLecturaBorrador = storageService.generarPresignedUrl(rutaBorradorLimpia);
                String urlSubidaFirmado = null;
                String urlLecturaFirmado = null;

                if (esFirmado) {
                    // Si YA está firmado, generamos la Presigned GET del PDF firmado REAL
                    urlLecturaFirmado = storageService.generarPresignedUrl(rutaFirmadoLimpia);
                    rootNode.put(claveFirmado, urlLecturaFirmado);
                } else {
                    // Si está PENDIENTE de firma, generamos la Presigned PUT para que el Agente pueda SUBIR
                    urlSubidaFirmado = storageService.generarPresignedUrlSubida(rutaFirmadoLimpia);
                    rootNode.put(claveBorrador, urlLecturaBorrador);
                }

                // 5. Construir el DTO (sin exponer rutas internas de R2 si no lo deseas)
                DocumentoAdjuntoDTO dto = new DocumentoAdjuntoDTO(
                        tipoDoc, 
                        null, 
                        null, 
                        urlLecturaBorrador, 
                        urlSubidaFirmado
                );
                dto.setUrlLecturaFirmado(urlLecturaFirmado);
                
                // Seteamos urlLectura principal para que el visor frontend sepa cuál renderizar
                dto.setUrlLectura(esFirmado ? urlLecturaFirmado : urlLecturaBorrador);

                listaDocumentos.add(dto);
            }
        });

        rootNode.set("documentos", objectMapper.valueToTree(listaDocumentos));
    }
/*    private void enriquecerJsonConPresignedUrls(ObjectNode rootNode) {
        String estadoFirma = rootNode.has("estadoFirma") ? rootNode.get("estadoFirma").asText() : "PENDIENTE_FIRMA";
        boolean esFirmado = "FIRMADO".equalsIgnoreCase(estadoFirma) ;

        // Mapeo de claves planas nativas a sus tipos de documento correspondientes
        Map<String, String> mapaClavesYTipos = new HashMap<>();
        mapaClavesYTipos.put("pdfRutaHistoria", "historia");
        mapaClavesYTipos.put("pdfRutaReceta", "receta");
        mapaClavesYTipos.put("pdfRutaOrdenes", "orden");
        mapaClavesYTipos.put("pdfRutaIndicaciones", "indicaciones");

        List<DocumentoAdjuntoDTO> listaDocumentos = new ArrayList<>();

        // Recorrer las claves nativas del JSON
        mapaClavesYTipos.forEach((clavePlana, tipoDoc) -> {
            if (rootNode.has(clavePlana) && !rootNode.get(clavePlana).isNull()) {
                String rutaRelativaBD = rootNode.get(clavePlana).asText();

                if (!rutaRelativaBD.trim().isEmpty()) {
                    DocumentoAdjuntoDTO docDto = procesarDocumentoIndividual(
                            tipoDoc, 
                            rutaRelativaBD, 
                            esFirmado, 
                            rootNode, 
                            clavePlana
                    );
                    listaDocumentos.add(docDto);
                }
            }
        });

        // Inyectar el arreglo "documentos" unificado en el JSON de salida
        rootNode.set("documentos", objectMapper.valueToTree(listaDocumentos));
    }
 */   
/*
	private void enriquecerJsonConPresignedUrlsFirmadas(ObjectNode rootNode) {
	    // 1. Mapeo de claves de rutas firmadas a sus tipos de documento
	    Map<String, String> mapaClavesYTipos = new HashMap<>();
	    mapaClavesYTipos.put("pdfRutaHistoriaFirmado", "historia");
	    mapaClavesYTipos.put("pdfRutaRecetaFirmado", "receta");
	    mapaClavesYTipos.put("pdfRutaOrdenesFirmado", "orden");
	    mapaClavesYTipos.put("pdfRutaIndicacionesFirmado", "indicaciones");
	
	    // 2. Verificar si el arreglo "documentos" ya existe en el JSON
	    if (rootNode.has("documentos") && rootNode.get("documentos").isArray()) {
	        ArrayNode documentosArray = (ArrayNode) rootNode.get("documentos");
	
	        mapaClavesYTipos.forEach((claveRutaFirmado, tipoDoc) -> {
	            if (rootNode.hasNonNull(claveRutaFirmado)) {
	                String rutaRelativaFirmado = rootNode.get(claveRutaFirmado).asText().trim();
	
	                if (!rutaRelativaFirmado.isEmpty()) {
	                    // Limpiar barra inicial para R2/S3 si es necesario
	                    String rutaLimpia = rutaRelativaFirmado.startsWith("/") 
	                            ? rutaRelativaFirmado.substring(1) 
	                            : rutaRelativaFirmado;
	
	                    // Generar Presigned URL del archivo firmado
	                    String urlLecturaFirmado = storageService.generarPresignedGetUrl(rutaLimpia);
	
	                    // 3. Buscar el documento existente dentro del ArrayNode y actualizarlo
	                    boolean encontrado = false;
	                    for (JsonNode docNode : documentosArray) {
	                        if (docNode.isObject() && tipoDoc.equalsIgnoreCase(docNode.get("tipoDocumento").asText())) {
	                            ObjectNode docObject = (ObjectNode) docNode;
	                            docObject.put("rutaFirmado", rutaLimpia);
	                            docObject.put("urlLecturaFirmado", urlLecturaFirmado);
	                            docObject.put("urlLectura", urlLecturaFirmado); // Actualizar urlLectura principal
	                            encontrado = true;
	                            break;
	                        }
	                    }
	
	                    // 4. Si por alguna razón no existía previamente en el arreglo, se agrega
	                    if (!encontrado) {
	                        ObjectNode nuevoDoc = objectMapper.createObjectNode();
	                        nuevoDoc.put("tipoDocumento", tipoDoc);
	                        nuevoDoc.put("rutaFirmado", rutaLimpia);
	                        nuevoDoc.put("urlLecturaFirmado", urlLecturaFirmado);
	                        nuevoDoc.put("urlLectura", urlLecturaFirmado);
	                        documentosArray.add(nuevoDoc);
	                    }
	                }
	            }
	        });
	    } else {
	        // Si no existía el arreglo "documentos", invocar al método principal para construirlo primero
	        enriquecerJsonConPresignedUrls(rootNode);
	        enriquecerJsonConPresignedUrlsFirmadas(rootNode);
	    }
	}
    */
    /**
     * Construye las Presigned URLs de lectura y subida para cada documento y
     * oculta las rutas relativas (`null`) en el DTO de salida.
     */
    private DocumentoAdjuntoDTO procesarDocumentoIndividual(
            String tipoDoc, 
            String rutaRelativaBD, 
            boolean esFirmado, 
            ObjectNode rootNode, 
            String clavePlana) {

        String urlLecturaBorrador = null;
        String urlSubidaFirmado = null;
        String urlLecturaFirmado = null;

        if (esFirmado) {
            // Si está firmado, la ruta persistida corresponde al PDF firmado
            urlLecturaFirmado = storageService.generarPresignedUrl(rutaRelativaBD);
            rootNode.put(clavePlana, urlLecturaFirmado);
        } else {
            // Si está pendiente de firma, calcular dinámicamente la ruta futura de subida del firmado
            String rutaFirmadoFutura = rutaRelativaBD.replace("-borrador.pdf", "-firmado.pdf").replace("/borradores/", "/atenciones/");
            
            urlLecturaBorrador = storageService.generarPresignedUrl(rutaRelativaBD);
            urlSubidaFirmado = storageService.generarPresignedUrlSubida(rutaFirmadoFutura);
            
            rootNode.put(clavePlana, urlLecturaBorrador);
        }

        // Retornar el DTO con rutas relativas en 'null' para no exponer la estructura interna de Cloudflare R2
        DocumentoAdjuntoDTO dto = new DocumentoAdjuntoDTO(
                tipoDoc, 
                null, 
                null, 
                urlLecturaBorrador, 
                urlSubidaFirmado
        );
        dto.setUrlLecturaFirmado(urlLecturaFirmado);

        return dto;
    }
    
    
/*
    @Override
    @Transactional(readOnly = true)
    public String obtenerJsonAtencion(Long idAtencion) {
        String jsonAtencion = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        
        if (jsonAtencion == null || jsonAtencion.trim().isEmpty() || "{}".equals(jsonAtencion)) {
            throw new RuntimeException("No se encontraron datos registrados para la atención con ID: " + idAtencion);
        }

        System.out.println("=== JSON PAYLOAD BD (ID: " + idAtencion + ") ===");
        System.out.println("=== JSON PAYLOAD BD (ID: " + idAtencion + ") ===");
        try {
            System.out.println(objectMapper.readTree(jsonAtencion).toPrettyString());
        } catch (Exception e) {
            // Si el formateo falla por algún carácter especial, imprime la cadena directa sin romper la petición
            System.out.println(jsonAtencion);
        }        
        return jsonAtencion;
    }    

*/
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



