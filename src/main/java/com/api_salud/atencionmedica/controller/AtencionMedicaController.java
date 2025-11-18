package com.api_salud.atencionmedica.controller;

import com.api_salud.atencionmedica.domain.AtencionMedicaModel.AtencionMedica;
import com.api_salud.atencionmedica.request.AtencionMedicaRequestDTO;
import com.api_salud.atencionmedica.service.AtencionMedicaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Controlador REST para la gestión completa de Atenciones Médicas.
 * Expone endpoints para el CRUD de la entidad maestra y sus detalles.
 */
@RestController
@RequestMapping("/api/atenciones-medicas")
public class AtencionMedicaController {

    private static final Logger LOGGER = Logger.getLogger(AtencionMedicaController.class.getName());

    private final AtencionMedicaService atencionMedicaService;

    @Autowired
    public AtencionMedicaController(AtencionMedicaService atencionMedicaService) {
        this.atencionMedicaService = atencionMedicaService;
    }

    /**
     * POST: Crea una nueva Atención Médica con todos sus detalles.
     * URL: /api/atenciones-medicas
     *
     * @param atencion Objeto AtencionMedica completo enviado en el cuerpo de la petición.
     * @return ResponseEntity con el ID de la nueva atención y status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Long> crearAtencionMedica(@RequestBody AtencionMedicaRequestDTO requestDTO) {
        // 1. Mapear DTO a la Entidad de Dominio (AtencionMedica)
        AtencionMedica atencion = mapper.toEntity(requestDTO);

        // 2. Ejecutar lógica de negocio
        Long idAtencion = atencionMedicaService.crearAtencionMedicaCompleta(atencion);
        
        // ...
    }

    /**
     * GET: Obtiene una Atención Médica completa (maestra + detalles) por ID.
     * URL: /api/atenciones-medicas/{id}
     *
     * @param idAtencion ID de la atención a buscar.
     * @return ResponseEntity con el objeto AtencionMedica y status 200 (OK) o 404 (Not Found).
     */
    @GetMapping("/{idAtencion}")
    public ResponseEntity<AtencionMedica> obtenerAtencionMedica(@PathVariable Long idAtencion) {
        AtencionMedica atencion = atencionMedicaService.obtenerAtencionMedicaCompletaPorId(idAtencion);

        if (atencion == null) {
            LOGGER.info("Atención Médica con ID " + idAtencion + " no encontrada.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(atencion, HttpStatus.OK);
    }

    /**
     * GET: Lista todas las Atenciones Médicas (solo maestra) de un paciente específico.
     * URL: /api/atenciones-medicas/paciente/{idPaciente}
     *
     * @param idPaciente ID del paciente.
     * @return ResponseEntity con la lista de Atenciones Médicas y status 200 (OK).
     */
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<AtencionMedica>> listarAtencionesPorPaciente(@PathVariable Integer idPaciente) {
        List<AtencionMedica> atenciones = atencionMedicaService.listarAtencionesPorPaciente(idPaciente);

        if (atenciones.isEmpty()) {
            LOGGER.info("No se encontraron Atenciones Médicas para el paciente ID: " + idPaciente);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }

        return new ResponseEntity<>(atenciones, HttpStatus.OK);
    }

    /**
     * PUT: Actualiza la información maestra de una Atención Médica.
     * URL: /api/atenciones-medicas/{id}
     *
     * @param idAtencion ID de la atención a actualizar.
     * @param atencion Objeto AtencionMedica con los datos actualizados.
     * @return ResponseEntity con status 200 (OK) o 404 (Not Found).
     */
    @PutMapping("/{idAtencion}")
    public ResponseEntity<Void> actualizarAtencionMedica(@PathVariable Long idAtencion, @RequestBody AtencionMedica atencion) {
        // Aseguramos que el ID del path coincida con el ID del cuerpo para consistencia
        atencion.setIdAtencion(idAtencion); 
        
        // Verificación básica de existencia antes de intentar actualizar (opcional, pero útil)
        if (atencionMedicaService.obtenerAtencionMedicaCompletaPorId(idAtencion) == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean isUpdated = atencionMedicaService.actualizarAtencionMedicaCompleta(atencion);

        if (isUpdated) {
            LOGGER.info("Atención Médica ID " + idAtencion + " actualizada exitosamente.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Podría ser un error lógico o de BD
            LOGGER.warning("Fallo al actualizar Atención Médica ID " + idAtencion);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE: Elimina lógicamente una Atención Médica por ID.
     * URL: /api/atenciones-medicas/{id}
     *
     * @param idAtencion ID de la atención a eliminar.
     * @return ResponseEntity con status 200 (OK) o 404 (Not Found).
     */
    @DeleteMapping("/{idAtencion}")
    public ResponseEntity<Void> eliminarAtencionMedica(@PathVariable Long idAtencion) {
        // Verificación de existencia
        if (atencionMedicaService.obtenerAtencionMedicaCompletaPorId(idAtencion) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Boolean isDeleted = atencionMedicaService.eliminarAtencionMedica(idAtencion);

        if (isDeleted) {
            LOGGER.info("Atención Médica ID " + idAtencion + " eliminada (lógicamente) exitosamente.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            LOGGER.warning("Fallo al eliminar Atención Médica ID " + idAtencion);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
