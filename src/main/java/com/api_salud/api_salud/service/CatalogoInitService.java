package com.api_salud.api_salud.service;

import com.api_salud.api_salud.context.TenantContext;
import com.api_salud.api_salud.repository.CatalogoInitRepository;
import com.api_salud.api_salud.response.CatalogoInitResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CatalogoInitService {

    private final CatalogoInitRepository initRepository;
    private final ObjectMapper objectMapper;

    public CatalogoInitService(CatalogoInitRepository initRepository, ObjectMapper objectMapper) {
        this.initRepository = initRepository;
        this.objectMapper = objectMapper;
    }

    public CatalogoInitResponse obtenerCatalogosInit() {
        Integer idEntidad = TenantContext.getEntidadId();
        
        String jsonResult = initRepository.ejecutarFnObtenerCatalogosInit(idEntidad);
        try {
            return objectMapper.readValue(jsonResult, CatalogoInitResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON de catálogos de inicio", e);
        }
    }
}