package com.api_salud.api_salud.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api_salud.api_salud.request.CatalogoServiciosPaqueteDetalleRequest;
import com.api_salud.api_salud.request.CatalogoServiciosRequest;
import com.api_salud.api_salud.response.CatalogoServiciosResponse;
import com.api_salud.api_salud.service.CatalogoServiciosService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/catalogos/servicios")
public class CatalogoServiciosController {

    private final CatalogoServiciosService serviciosService;

    public CatalogoServiciosController(CatalogoServiciosService serviciosService) {
        this.serviciosService = serviciosService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<CatalogoServiciosResponse> buscarServicios(@Valid CatalogoServiciosRequest request) {
        CatalogoServiciosResponse response = serviciosService.buscarServicios(
                request.getIdEntidad(),
                request.getBusqueda(),
                request.getTipoServicio(),
                request.getLimite(),
                request.getPagina()
        );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping(value = "/paquete/detalle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerDetallePaqueteServicios(@Valid @ModelAttribute CatalogoServiciosPaqueteDetalleRequest request) {
        String resultadoJson = serviciosService.obtenerDetallePaqueteServicios(request);
        return ResponseEntity.ok(resultadoJson);
    }    
    
}