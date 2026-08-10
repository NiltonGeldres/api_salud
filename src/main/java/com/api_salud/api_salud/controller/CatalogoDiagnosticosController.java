package com.api_salud.api_salud.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.request.CatalogoDiagnosticosRequest;
import com.api_salud.api_salud.response.CatalogoDiagnosticosResponse;
import com.api_salud.api_salud.service.CatalogoDiagnosticosService;

@RestController
@RequestMapping("/api/v1/catalogos/diagnosticos")
public class CatalogoDiagnosticosController {

    private final CatalogoDiagnosticosService diagnosticosService;

    public CatalogoDiagnosticosController(CatalogoDiagnosticosService diagnosticosService) {
        this.diagnosticosService = diagnosticosService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<CatalogoDiagnosticosResponse> buscarDiagnosticos(@Valid CatalogoDiagnosticosRequest request) {
        CatalogoDiagnosticosResponse response = diagnosticosService.buscarDiagnosticos(
                request.getBusqueda(),
                request.getLimite(),
                request.getPagina()
        );
        return ResponseEntity.ok(response);
    }
}