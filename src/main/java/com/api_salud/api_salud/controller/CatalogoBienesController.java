package com.api_salud.api_salud.controller;



import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.request.CatalogoBienesRequest;
import com.api_salud.api_salud.response.CatalogoBienesResponse;
import com.api_salud.api_salud.service.CatalogoBienesService;

@RestController
@RequestMapping("/api/v1/catalogos/bienes")
public class CatalogoBienesController {

    private final CatalogoBienesService bienService;

    public CatalogoBienesController(CatalogoBienesService bienService) {
        this.bienService = bienService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<CatalogoBienesResponse> buscarBienes(
            @Valid @ModelAttribute CatalogoBienesRequest request) {

        // Se envía directamente el objeto de contrato al Service
        CatalogoBienesResponse response = bienService.buscarBienes(request);

        return ResponseEntity.ok(response);
    }
}