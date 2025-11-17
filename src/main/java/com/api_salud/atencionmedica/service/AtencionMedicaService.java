package com.api_salud.atencionmedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api_salud.atencionmedica.entity.AtencionMedica;
import com.api_salud.atencionmedica.repository.AtencionMedicaRepository;
import com.api_salud.atencionmedica.request.AtencionMedicaRequest;

/**
 * Servicio para la lógica de negocio de la cabecera de Atención Médica.
 */
@Service
public class AtencionMedicaService {

    private final AtencionMedicaRepository repository;

    public AtencionMedicaService(AtencionMedicaRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea una nueva atención médica.
     * @param request Datos de la atención.
     * @return El ID de la atención creada.
     */
    @Transactional
    public Long crearAtencion(AtencionMedicaRequest request) {
        // Aquí podría ir lógica de negocio adicional antes de la inserción
        return repository.insertar(request);
    }

    /**
     * Obtiene una atención médica por su ID.
     */
    public Optional<AtencionMedica> obtenerAtencionPorId(Long idAtencion) {
        return repository.obtenerPorId(idAtencion);
    }

    /**
     * Lista las atenciones médicas por ID de paciente.
     */
    public List<AtencionMedica> listarAtencionesPorPaciente(Integer idPaciente) {
        return repository.listarPorPaciente(idPaciente);
    }

    /**
     * Actualiza una atención médica existente.
     * @param idAtencion ID de la atención a actualizar.
     * @param request Datos a actualizar.
     * @return true si se actualizó, false en caso contrario.
     */
    @Transactional
    public boolean actualizarAtencion(Long idAtencion, AtencionMedicaRequest request) {
        // Validar si la atención existe antes de actualizar (opcional, el SP maneja el FOUND)
        if (repository.obtenerPorId(idAtencion).isPresent()) {
            // Manejo de error si la atención no existe
            return false;
        }
        return repository.actualizar(idAtencion, request);
    }

    /**
     * Elimina una atención médica por su ID.
     */
    @Transactional
    public void eliminarAtencion(Long idAtencion) {
        repository.eliminar(idAtencion);
    }
}