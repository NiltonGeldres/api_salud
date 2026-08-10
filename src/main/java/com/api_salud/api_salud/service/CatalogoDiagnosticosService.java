package com.api_salud.api_salud.service;

import com.api_salud.api_salud.repository.CatalogoDiagnosticosRepository;
import com.api_salud.api_salud.response.CatalogoDiagnosticosResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CatalogoDiagnosticosService {

    private final CatalogoDiagnosticosRepository diagnosticosRepository;
    private final ObjectMapper objectMapper;

    public CatalogoDiagnosticosService(CatalogoDiagnosticosRepository diagnosticosRepository, ObjectMapper objectMapper) {
        this.diagnosticosRepository = diagnosticosRepository;
        this.objectMapper = objectMapper;
    }

    public CatalogoDiagnosticosResponse buscarDiagnosticos(String busqueda, Integer limite, Integer pagina) {
        String jsonResult = diagnosticosRepository.ejecutarFnBuscarDiagnosticos(busqueda, limite, pagina);
        try {
            return objectMapper.readValue(jsonResult, CatalogoDiagnosticosResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON del catálogo de diagnósticos", e);
        }
    }
}