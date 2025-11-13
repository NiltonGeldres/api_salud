package com.api_salud.atencionmedica.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api_salud.atencionmedica.entity.AtencionMedica;
import com.api_salud.atencionmedica.request.AtencionMedicaRequest;
import com.api_salud.atencionmedica.service.AtencionMedicaService;

/**
 * Controlador REST para la gestión de la cabecera de Atenciones Médicas.
 */
@RestController
@RequestMapping("/api/v1/atenciones-medicas")
public class AtencionMedicaController {

    private final AtencionMedicaService service;

    public AtencionMedicaController(AtencionMedicaService service) {
        this.service = service;
    }

    /**
     * Endpoint para crear una nueva atención médica (llama a fn_atenciones_medicas_insertar).
     */
    @PostMapping
    public ResponseEntity<Long> crearAtencion(@Valid @RequestBody AtencionMedicaRequest request) {
        try {
            Long newId = service.crearAtencion(request);
            return new ResponseEntity<>(newId, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo de errores de base de datos o lógica de negocio
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para obtener una atención por ID (llama a fn_atenciones_medicas_obtener_por_id).
     */
    @GetMapping("/{id}")
    public ResponseEntity<AtencionMedica> obtenerAtencion(@PathVariable("id") Long id) {
        Optional<AtencionMedica> atencion = service.obtenerAtencionPorId(id);
        return atencion.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para listar atenciones por ID de paciente (llama a fn_atenciones_medicas_listar_por_paciente).
     */
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<AtencionMedica>> listarPorPaciente(@PathVariable("idPaciente") Integer idPaciente) {
        List<AtencionMedica> atenciones = service.listarAtencionesPorPaciente(idPaciente);
        return ResponseEntity.ok(atenciones);
    }

    /**
     * Endpoint para actualizar una atención existente (llama a fn_atenciones_medicas_actualizar).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarAtencion(@PathVariable("id") Long id, @Valid @RequestBody AtencionMedicaRequest request) {
        boolean updated = service.actualizarAtencion(id, request);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para eliminar una atención (llama a sp_atenciones_medicas_eliminar).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAtencion(@PathVariable("id") Long id) {
        try {
            service.eliminarAtencion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // NOTA: La eliminación de la cabecera puede fallar por llaves foráneas.
            // Se puede manejar un error 409 CONFLICT o 404 NOT FOUND.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


