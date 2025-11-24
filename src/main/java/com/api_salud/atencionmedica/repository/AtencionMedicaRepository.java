// Archivo: com.api_salud.atencionmedica.repository.AtencionMedicaRepository.java
package com.api_salud.atencionmedica.repository;

import com.api_salud.atencionmedica.entity.AtencionMedicaEntity;

public interface AtencionMedicaRepository {

    /**
     * Inserta la cabecera de la Atención Médica y todos sus detalles asociados 
     * mediante llamadas a funciones almacenadas de PostgreSQL.
     * Esta operación DEBE ser atómica (transaccional).
     * * @param entity La entidad de persistencia completa, mapeada desde el Modelo.
     * @return El ID de la Atención Médica (id_atencion) recién creado.
     */
    Long insertarAtencionMedicaCompleta(AtencionMedicaEntity entity);
}