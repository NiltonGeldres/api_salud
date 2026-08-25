package com.api_salud.api_salud.controller;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.AtencionMedicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/atenciones-medicas")
//@CrossOrigin(origins = "*") // Ajustar luego al dominio específico de React por seguridad CORS
public class AtencionMedicaController {

    private final AtencionMedicaService atencionMedicaService;

    // Inyección por constructor (Buena práctica, facilita pruebas unitarias)
    public AtencionMedicaController(AtencionMedicaService atencionMedicaService) {
        this.atencionMedicaService = atencionMedicaService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<AtencionMedicaResponse> guardarAtencionCompleta(
            @Valid @RequestBody AtencionMedicaRequest request) {
        
        // CAPA 1: Si el JSON viola alguna regla (@NotNull, @Size, etc.), 
        // Spring Boot rebotará la petición antes de que toque el servicio.
        
        AtencionMedicaResponse response = atencionMedicaService.guardarAtencionMedica(request);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/{idAtencion}/firmar")
    public ResponseEntity<AtencionMedicaResponse> firmarAtencion(
            @PathVariable Long idAtencion) {
        AtencionMedicaResponse response = atencionMedicaService.firmarAtencion(idAtencion);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{idAtencion}/firmar-pdf")
    public ResponseEntity<AtencionMedicaResponse> firmarYGenerarPdf(
            @PathVariable Long idAtencion) {
        AtencionMedicaResponse response = atencionMedicaService.firmarYGenerarPdf(idAtencion);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para consultar y llevar los datos de la atención hacia afuera (sistemas externos/frontend).
     * Devuelve el JSON plano generado por PostgreSQL.
     */
    @GetMapping(value = "/detalle/{idAtencion}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerAtencionPorId(@PathVariable Long idAtencion) {
        String jsonAtencion = atencionMedicaService.obtenerJsonAtencion(idAtencion);
        return ResponseEntity.ok(jsonAtencion);
    }   
    
}    