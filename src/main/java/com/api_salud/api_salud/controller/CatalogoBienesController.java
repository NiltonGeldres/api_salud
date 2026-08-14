package com.api_salud.api_salud.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api_salud.api_salud.request.CatalogoBienesPaqueteDetalleRequest;
import com.api_salud.api_salud.request.CatalogoBienesRequest;
import com.api_salud.api_salud.response.CatalogoBienesResponse;
import com.api_salud.api_salud.service.CatalogoBienesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/catalogos/bienes")
//@CrossOrigin(origins = "*") // Permite peticiones desde el frontend (React/Angular)
public class CatalogoBienesController {

    private final CatalogoBienesService bienService;

    // Inyección por constructor (Excelente práctica)
    public CatalogoBienesController(CatalogoBienesService bienService) {
        this.bienService = bienService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<CatalogoBienesResponse> buscarBienes(@Valid CatalogoBienesRequest request) {

        CatalogoBienesResponse response = bienService.buscarBienes(request);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping(value = "/paquete/detalle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerDetallePaqueteBienes(@Valid @ModelAttribute CatalogoBienesPaqueteDetalleRequest request) {
        String resultadoJson = bienService.obtenerDetallePaqueteBienes(request);
        return ResponseEntity.ok(resultadoJson);
    }
    
}