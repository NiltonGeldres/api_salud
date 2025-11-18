package com.api_salud.atencionmedica.service;

import com.api_salud.atencionmedica.domain.AtencionMedicaModel.AtencionMedica;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la gestión de Atención Médica.
 * Incluye la orquestación del CRUD maestro y sus múltiples detalles.
 */
public interface AtencionMedicaService {

    /**
     * Inserta una nueva Atención Médica junto con todos sus detalles asociados.
     *
     * @param atencion El objeto AtencionMedica completo (maestro + detalles).
     * @return El ID de la Atención Médica insertada (idAtencion).
     */
    Long crearAtencionMedicaCompleta(AtencionMedica atencion);

    /**
     * Obtiene una Atención Médica por su ID, incluyendo todos sus detalles.
     *
     * @param idAtencion ID de la Atención Médica a buscar.
     * @return El objeto AtencionMedica completo o null si no se encuentra.
     */
    AtencionMedica obtenerAtencionMedicaCompletaPorId(Long idAtencion);

    /**
     * Lista todas las Atenciones Médicas de un paciente específico (sin cargar los detalles por defecto).
     *
     * @param idPaciente ID del paciente.
     * @return Lista de objetos AtencionMedica (solo la información maestra).
     */
    List<AtencionMedica> listarAtencionesPorPaciente(Integer idPaciente);

    /**
     * Actualiza la información maestra de una Atención Médica y sus detalles.
     * NOTA: La lógica de actualización de detalles puede variar (eliminar/insertar vs. update individual).
     * Aquí se implementará una lógica básica de reemplazo.
     *
     * @param atencion El objeto AtencionMedica con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    Boolean actualizarAtencionMedicaCompleta(AtencionMedica atencion);

    /**
     * Elimina lógicamente una Atención Médica.
     *
     * @param idAtencion ID de la Atención Médica a eliminar.
     * @return true si la eliminación fue exitosa.
     */
    Boolean eliminarAtencionMedica(Long idAtencion);
}