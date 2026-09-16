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
        String estadoFirma = rootNode.has("estadoFirma") ? rootNode.get("estadoFirma").asText() : "PENDIENTE_FIRMA";
        boolean esFirmado = "FIRMADO".equalsIgnoreCase(estadoFirma) || "FIRMADO_ELECTRONICO".equalsIgnoreCase(estadoFirma);

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
//        response.setRutaPdfFirmado(urlVisualizacion);
        response.setHashIntegridad(hashIntegridad);
        
        return response;

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error al guardar y preparar el PDF borrador: " + e.getMessage(), e);
    }
}
*/    



//*************************************************

/*    
@Override
@Transactional
public AtencionMedicaResponse prepararPdf(AtencionMedicaRequest request) {
    try {
        Long idAtencion = request.getIdAtencion();

        // 1. Establecer estado de firma y serializar el DTO a String JSON
        request.setEstadoFirma("PENDIENTE_FIRMA");
        String jsonPayload = objectMapper.writeValueAsString(request);

        // 2. Persistir en BD (Sobrescribe/Crea la atención en estado Borrador)
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

        // ----------------------------------------------------------------------------------
        // REFACTOR: Preparación y generación de PDFs (Utilizando el DTO en memoria)
        // ----------------------------------------------------------------------------------
        AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonPayload, AtencionMedicaPdfDTO.class);
        pdfDto.setIdAtencion(idAtencion);
        pdfDto.setEstadoFirma("PENDIENTE_FIRMA");

        if (pdfDto.getLogoTenantUrl() != null && !pdfDto.getLogoTenantUrl().isEmpty()) {
            String logoBase64 = storageService.obtenerLogoComoBase64(pdfDto.getLogoTenantUrl());
            pdfDto.setLogoTenantUrl(logoBase64);
        }

        Integer idEntidad = pdfDto.getIdEntidad() != null ? pdfDto.getIdEntidad() : 0;
        String hcPaciente = (pdfDto.getPaciente() != null && pdfDto.getPaciente().getHc() != null)
                ? pdfDto.getPaciente().getHc() 
                : "SIN_HC";

        // Recolectar condicionalmente los PDFs a generar
        Map<String, byte[]> documentos = new LinkedHashMap<>();
        documentos.put("historia", pdfGeneratorService.generarPdfHistoriaClinica(pdfDto));

        if (pdfDto.getMedicacion() != null && !pdfDto.getMedicacion().isEmpty()) {
            documentos.put("receta", pdfGeneratorService.generarPdfReceta(pdfDto));
        }
        if (pdfDto.getExamenesAuxiliares() != null && !pdfDto.getExamenesAuxiliares().isEmpty()) {
            documentos.put("orden", pdfGeneratorService.generarPdfOrdenes(pdfDto));
        }
        if (pdfDto.getAlta() != null && !pdfDto.getAlta().isEmpty()) {
            documentos.put("indicaciones", pdfGeneratorService.generarPdfIndicaciones(pdfDto));
        }

        // Subir a Storage (R2) y construir la lista de adjuntos
        List<DocumentoAdjuntoDTO> listaDocumentosBD = new ArrayList<>();
        List<DocumentoAdjuntoDTO> listaDocumentosResponse = new ArrayList<>();

        for (Map.Entry<String, byte[]> entry : documentos.entrySet()) {
            String tipoDoc = entry.getKey();
            byte[] pdfBytes = entry.getValue();

            String rutaBorrador = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, false);
            String rutaFirmado  = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, true);

            // Guardar borrador en Cloudflare R2
            storageService.guardar(rutaBorrador, pdfBytes);

            // Generar Presigned URLs
            String urlLectura = storageService.generarPresignedUrl(rutaBorrador);
            String urlSubida  = storageService.generarPresignedUrlSubida(rutaFirmado);

            DocumentoAdjuntoDTO docDTO = new DocumentoAdjuntoDTO(tipoDoc, rutaBorrador, rutaFirmado, urlLectura, urlSubida);
            listaDocumentosBD.add(docDTO);
            listaDocumentosResponse.add(docDTO);
        }

        // 3. PERSISTIR LAS RUTAS EN BD (Esto actualiza la columna o el JSON en PostgreSQL)
        atencionMedicaRepository.actualizarRutasPdf(idAtencion, listaDocumentosBD);
        atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "PENDIENTE_FIRMA");

        // ----------------------------------------------------------------------------------
        // REFACTOR: Ahora sí obtenemos el JSON DEFINITIVO (con rutas incorporadas)
        // ----------------------------------------------------------------------------------
        String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        System.out.println("=== JSON PAYLOAD BD (ID: " + idAtencion + ") ===");
        System.out.println(objectMapper.readTree(jsonPayloadBD).toPrettyString());
        
        
        if (jsonPayloadBD == null || jsonPayloadBD.trim().isEmpty()) {
            throw new RuntimeException("No se encontraron datos persistidos para la atención con ID: " + idAtencion);
        }

        // 4. Generar y congelar Hash SHA-256 sobre el JSON 100% COMPLETO
        String hashIntegridad = securityUtils.generarHashIntegridad(jsonPayloadBD, idAtencion);
        atencionMedicaRepository.actualizarHashFirma(idAtencion, hashIntegridad);

        // 5. Instanciar respuesta con el estado congelado e inmutable
        AtencionMedicaResponse response = new AtencionMedicaResponse(
                true,
                "Se generaron " + documentos.size() + " documento(s) borrador correctamente.",
                idAtencion,
                2, // Estado: Borrador / Pendiente Firma
                "PENDIENTE_FIRMA"
        );

        if (pdfDto.getPaciente() != null) {
            response.setIdPaciente(pdfDto.getPaciente().getIdPaciente());
        }
        
        // Asignar los documentos adjuntos con el helper
        for (DocumentoAdjuntoDTO doc : listaDocumentosResponse) {
            response.agregarDocumento(doc);
        }

        response.setHashIntegridad(hashIntegridad);
        response.setJsonEnriquecidoFirmado(jsonPayloadBD); // Contiene datos clínicos + rutas PDF

        return response;

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error al guardar y preparar los PDFs borradores: " + e.getMessage(), e);
    }
} 
*/    
/*    
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
        
     // ===> RESOLUCIÓN DE LA URL DEL LOGO <===
     //   if (pdfDto.getLogoTenantUrl() != null && !pdfDto.getLogoTenantUrl().isEmpty()) {
      //      pdfDto.setLogoTenantUrl(storageService.resolverUrlPublicaLogo(pdfDto.getLogoTenantUrl()));
      //  }	        

        if (pdfDto.getLogoTenantUrl() != null && !pdfDto.getLogoTenantUrl().isEmpty()) {
            // En lugar de resolver la URL pública HTTPS de Cloudflare (que da 403 / Error 1010),
            // convertimos la ruta a un Data URI Base64 optimizado en memoria.
            String logoBase64 = storageService.obtenerLogoComoBase64(pdfDto.getLogoTenantUrl());
            pdfDto.setLogoTenantUrl(logoBase64);
        }
        
        
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
*/    


//**************************************************

/*
@Override
@Transactional
public AtencionMedicaResponse prepararPdf(AtencionMedicaRequest request) {
    try {
        Long idAtencion = request.getIdAtencion();

        // 1. Establecer estado de firma y serializar el DTO a String JSON
        request.setEstadoFirma("PENDIENTE_FIRMA");
        String jsonPayload = objectMapper.writeValueAsString(request);

        // 2. Persistir en BD (Sobrescribe/Crea la atención en estado Borrador)
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

        // ----------------------------------------------------------------------------------
        // Preparación y generación de PDFs (Utilizando el DTO en memoria)
        // ----------------------------------------------------------------------------------
        AtencionMedicaPdfDTO pdfDto = objectMapper.readValue(jsonPayload, AtencionMedicaPdfDTO.class);
        pdfDto.setIdAtencion(idAtencion);
        pdfDto.setEstadoFirma("PENDIENTE_FIRMA");

        if (pdfDto.getLogoTenantUrl() != null && !pdfDto.getLogoTenantUrl().isEmpty()) {
            String logoBase64 = storageService.obtenerLogoComoBase64(pdfDto.getLogoTenantUrl());
            pdfDto.setLogoTenantUrl(logoBase64);
        }

        Integer idEntidad = pdfDto.getIdEntidad() != null ? pdfDto.getIdEntidad() : 0;
        String hcPaciente = (pdfDto.getPaciente() != null && pdfDto.getPaciente().getHc() != null)
                ? pdfDto.getPaciente().getHc() 
                : "SIN_HC";

        // Recolectar condicionalmente los PDFs a generar
        Map<String, byte[]> documentos = new LinkedHashMap<>();
        documentos.put("historia", pdfGeneratorService.generarPdfHistoriaClinica(pdfDto));

        if (pdfDto.getMedicacion() != null && !pdfDto.getMedicacion().isEmpty()) {
            documentos.put("receta", pdfGeneratorService.generarPdfReceta(pdfDto));
        }
        if (pdfDto.getExamenesAuxiliares() != null && !pdfDto.getExamenesAuxiliares().isEmpty()) {
            documentos.put("orden", pdfGeneratorService.generarPdfOrdenes(pdfDto));
        }
        if (pdfDto.getAlta() != null && !pdfDto.getAlta().isEmpty()) {
            documentos.put("indicaciones", pdfGeneratorService.generarPdfIndicaciones(pdfDto));
        }

        // Subir a Storage (R2) y construir la lista de adjuntos
        List<DocumentoAdjuntoDTO> listaDocumentosBD = new ArrayList<>();
        List<DocumentoAdjuntoDTO> listaDocumentosResponse = new ArrayList<>();

        for (Map.Entry<String, byte[]> entry : documentos.entrySet()) {
            String tipoDoc = entry.getKey();
            byte[] pdfBytes = entry.getValue();

            String rutaBorrador = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, false);
            String rutaFirmado  = storageService.construirRutaRelativa(idEntidad, hcPaciente, idAtencion, tipoDoc, true);

            // Guardar borrador en Cloudflare R2
            storageService.guardar(rutaBorrador, pdfBytes);

            // Generar Presigned URLs
            String urlLectura = storageService.generarPresignedUrl(rutaBorrador);
            String urlSubida  = storageService.generarPresignedUrlSubida(rutaFirmado);

            DocumentoAdjuntoDTO docDTO = new DocumentoAdjuntoDTO(tipoDoc, rutaBorrador, rutaFirmado, urlLectura, urlSubida);
            listaDocumentosBD.add(docDTO);
            listaDocumentosResponse.add(docDTO);
        }

        // 3. PERSISTIR LAS RUTAS EN BD (Actualiza la columna/JSON en PostgreSQL)
        atencionMedicaRepository.actualizarRutasPdf(idAtencion, listaDocumentosBD);
        atencionMedicaRepository.actualizarEstadoFirma(idAtencion, "PENDIENTE_FIRMA");

        // ----------------------------------------------------------------------------------
        // Obtención del JSON DEFINITIVO (con rutas incorporadas)
        // ----------------------------------------------------------------------------------
        String jsonPayloadBD = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencion);
        
        if (jsonPayloadBD == null || jsonPayloadBD.isEmpty()) {
            throw new RuntimeException("No se encontraron datos persistidos para la atención con ID: " + idAtencion);
        }

        System.out.println("=== JSON PAYLOAD BD (ID: " + idAtencion + ") ===");
        JsonNode payloadClinicoNode = objectMapper.readTree(jsonPayloadBD);
        System.out.println(payloadClinicoNode.toPrettyString());

        // 4. Generar y congelar Hash SHA-256 sobre el JSON 100% COMPLETO
        String hashIntegridad = securityUtils.generarHashIntegridad(jsonPayloadBD, idAtencion);
        atencionMedicaRepository.actualizarHashFirma(idAtencion, hashIntegridad);

        // 5. Instanciar DTO de respuesta con metadatos y payload aplanado
        AtencionMedicaResponse response = new AtencionMedicaResponse();
        response.setExito(true);
        response.setMensaje("Se generaron " + documentos.size() + " documento(s) borrador correctamente.");
        response.setIdAtencion(idAtencion);
        response.setIdEstadoAtencion(2); // Estado: Borrador / Pendiente Firma
        response.setEstadoFirma("PENDIENTE_FIRMA");
        response.setHashIntegridad(hashIntegridad);
        response.setTsProcesamiento(OffsetDateTime.now());

        if (pdfDto.getPaciente() != null) {
            response.setIdPaciente(pdfDto.getPaciente().getIdPaciente());
        }

        // Asignar documentos adjuntos
        for (DocumentoAdjuntoDTO doc : listaDocumentosResponse) {
            response.agregarDocumento(doc);
        }

        // 🟢 Asignar el node del JSON: @JsonUnwrapped lo desglosará en la raíz al salir por HTTP
        response.setPayloadClinico(payloadClinicoNode);

        return response;

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error al guardar y preparar los PDFs borradores: " + e.getMessage(), e);
    }
}
*/    
