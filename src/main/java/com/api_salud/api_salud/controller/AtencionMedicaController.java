package com.api_salud.api_salud.controller;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.request.validation.ValidationGroups;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.AtencionMedicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    /**
     * 1. CREAR BORRADOR (POST)
     * Permisivo: Solo valida IDs de estructura y Tenant (BorradorGroup).
     */
    @PostMapping("/guardar-borrador")
    public ResponseEntity<AtencionMedicaResponse> crearAtencionBorrador(
            @Validated(ValidationGroups.BorradorGroup.class) @RequestBody AtencionMedicaRequest request) {
        
        AtencionMedicaResponse response = atencionMedicaService.guardarBorrador(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * 2. ACTUALIZAR BORRADOR (PUT)
     * Permisivo: Solo valida IDs de estructura y Tenant (BorradorGroup).
     */
    @PutMapping("/actualizar-borrador/{idAtencion}")
    public ResponseEntity<AtencionMedicaResponse> actualizarAtencionBorrador(
            @PathVariable Long idAtencion,
            @Validated(ValidationGroups.BorradorGroup.class) @RequestBody AtencionMedicaRequest request) {
        
        request.setIdAtencion(idAtencion);
        AtencionMedicaResponse response = atencionMedicaService.actualizarBorrador(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 3. GENERAR PDF BORRADOR / VALIDAR (POST)
     * Estricto: Valida tanto el BorradorGroup como las colecciones obligatorias (CompletoGroup).
     */
    @PostMapping("/preparar-pdf")
    public ResponseEntity<AtencionMedicaResponse> prepararPdfBorrador(
            @Validated(ValidationGroups.CompletoGroup.class) @RequestBody AtencionMedicaRequest request) {
        
        AtencionMedicaResponse response = atencionMedicaService.prepararPdf(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * 3. GUARDAR VERSION PROBADA ANTES DE FIRMA Y DE PDF / VALIDAR (POST)
     * Estricto: Valida tanto el BorradorGroup como las colecciones obligatorias (CompletoGroup).
     */    
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
/*
    @PostMapping("/{idAtencion}/preparar-pdf")
    public ResponseEntity<AtencionMedicaResponse> firmarYGenerarPdf(
            @PathVariable Long idAtencion) {
        AtencionMedicaResponse response = atencionMedicaService.prepararPdf(idAtencion);
        return ResponseEntity.ok(response);
    }
*/
    /**
     * Endpoint para consultar y llevar los datos de la atención hacia afuera (sistemas externos/frontend).
     * Devuelve el JSON plano generado por PostgreSQL.
     */
    @GetMapping(value = "/detalle/{idAtencion}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerAtencionPorId(@PathVariable Long idAtencion) {
        String jsonAtencion = atencionMedicaService.obtenerJsonAtencion(idAtencion);
        return ResponseEntity.ok(jsonAtencion);
    }   
    
 // =======================================================================
    // 🎯 GET: LISTAR ATENCIONES PENDIENTES DE FIRMA (PDF BORRADOR)
    // =======================================================================
    @GetMapping("/pendientes-firma")
    public ResponseEntity<String> listarAtencionesPendientesFirma(
            @RequestParam("idMedico") Integer idMedico) {

        String jsonResponse = atencionMedicaService.listarAtencionesPendientesFirma(idMedico);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonResponse);
    }
    
    
}    