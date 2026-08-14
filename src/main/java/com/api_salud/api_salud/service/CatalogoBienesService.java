package com.api_salud.api_salud.service;

import com.api_salud.api_salud.context.TenantContext;
import com.api_salud.api_salud.repository.CatalogoBienesRepository;
import com.api_salud.api_salud.request.CatalogoBienesPaqueteDetalleRequest;
import com.api_salud.api_salud.request.CatalogoBienesRequest;
import com.api_salud.api_salud.response.CatalogoBienesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CatalogoBienesService {

    private final CatalogoBienesRepository bienRepository;
    private final ObjectMapper objectMapper;

    public CatalogoBienesService(CatalogoBienesRepository bienRepository, ObjectMapper objectMapper) {
        this.bienRepository = bienRepository;
        this.objectMapper = objectMapper;
    }

    public CatalogoBienesResponse buscarBienes(CatalogoBienesRequest request) {
        // 1. Ejecutar la función para obtener el String JSON
        String jsonResult = bienRepository.ejecutarFnBuscarCatalogoBienes(
                request.getIdEntidad(),
                request.getTermino(),
                request.getTipoProducto(),
                request.getTamanoPagina(),
                request.getPaginaActual()
        );

        // 2. Mapear el JSON al DTO de Respuesta
        try {
            return objectMapper.readValue(jsonResult, CatalogoBienesResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON de catálogo de bienes", e);
        }
    }
    
    public String obtenerDetallePaqueteBienes(CatalogoBienesPaqueteDetalleRequest request) {
        Integer idEntidad = TenantContext.getEntidadId();
        return bienRepository.obtenerDetallePaqueteBienes(
                request.getIdPaquete(),
                idEntidad
        );
    }
    
}