package com.api_salud.api_salud.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.request.CatalogoInitRequest;
import com.api_salud.api_salud.response.CatalogoInitResponse;
import com.api_salud.api_salud.service.CatalogoInitService;

@RestController
@RequestMapping("/api/v1/catalogos/init")
public class CatalogoInitController {

    private final CatalogoInitService initService;

    public CatalogoInitController(CatalogoInitService initService) {
        this.initService = initService;
    }

    @GetMapping
    public ResponseEntity<CatalogoInitResponse> obtenerCatalogosInit() {
        CatalogoInitResponse response = initService.obtenerCatalogosInit();
        return ResponseEntity.ok(response);
    }
}