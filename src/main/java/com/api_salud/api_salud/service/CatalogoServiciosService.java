package com.api_salud.api_salud.service;

import com.api_salud.api_salud.context.TenantContext;
import com.api_salud.api_salud.repository.CatalogoServiciosRepository;
import com.api_salud.api_salud.request.CatalogoServiciosPaqueteDetalleRequest;
import com.api_salud.api_salud.response.CatalogoServiciosResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CatalogoServiciosService {

    private final CatalogoServiciosRepository serviciosRepository;
    private final ObjectMapper objectMapper;

    public CatalogoServiciosService(CatalogoServiciosRepository serviciosRepository, ObjectMapper objectMapper) {
        this.serviciosRepository = serviciosRepository;
        this.objectMapper = objectMapper;
    }

    public CatalogoServiciosResponse buscarServicios(Integer idEntidad, String busqueda, Integer tipoServicio, Integer limite, Integer pagina) {
    	
        String jsonResult = serviciosRepository.ejecutarFnBuscarCatalogoServicios(idEntidad, busqueda, tipoServicio, limite, pagina);
        try {
            return objectMapper.readValue(jsonResult, CatalogoServiciosResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON de catálogo de servicios", e);
        }
    }
    
    public String obtenerDetallePaqueteServicios(CatalogoServiciosPaqueteDetalleRequest request) {
        Integer idEntidad = TenantContext.getEntidadId();
        return serviciosRepository.obtenerDetallePaqueteServicios(
                request.getIdPaquete(),
                idEntidad
        );
    }
    
}