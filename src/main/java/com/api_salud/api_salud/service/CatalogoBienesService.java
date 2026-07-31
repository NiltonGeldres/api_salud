package com.api_salud.api_salud.service;


import com.api_salud.api_salud.repository.CatalogoBienesRepository;
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

    public CatalogoBienesResponse buscarBienes(Integer idEntidad, String busqueda, Integer tipoProducto, Integer limite, Integer pagina) {
        String jsonResult = bienRepository.ejecutarFnBuscarCatalogoBienes(idEntidad, busqueda, tipoProducto, limite, pagina);
        try {
            return objectMapper.readValue(jsonResult, CatalogoBienesResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON de catálogo de bienes", e);
        }
    }
}