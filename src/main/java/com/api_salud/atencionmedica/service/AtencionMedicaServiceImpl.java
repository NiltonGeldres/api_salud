// Archivo: com.api_salud.atencionmedica.service.AtencionMedicaServiceImpl.java
package com.api_salud.atencionmedica.service;

import com.api_salud.atencionmedica.domain.AtencionMedicaModel;
import com.api_salud.atencionmedica.entity.AtencionMedicaEntity;
import com.api_salud.atencionmedica.mapper.AtencionMedicaMapper;
import com.api_salud.atencionmedica.repository.AtencionMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    private final AtencionMedicaRepository repository = null;
    private final AtencionMedicaMapper mapper = null;

    /**
     * Orquesta la creación de la Atención Médica completa.
     * Se comunica con la capa de persistencia (Repository).
     */
    @Override
    public Long crearAtencionMedicaCompleta(AtencionMedicaModel model) {
        
        // 1. Log: Iniciar procesamiento de negocio.
        // Aquí iría cualquier validación de negocio adicional (ej. disponibilidad, permisos, etc.)

        // 2. Mapear Modelo de Dominio a Entidad de Persistencia
        // Usa el mapper para transformar el objeto de negocio a la estructura de la BD.
        AtencionMedicaEntity entity = mapper.toEntity(model);
        
        // 3. Persistir la Entidad (Cabecera + Detalles)
        // El repositorio maneja la inserción de la cabecera, la obtención del ID,
        // la asignación de la FK a los detalles y las llamadas a las 10 funciones de PostgreSQL,
        // todo bajo una única transacción.
        Long idAtencion = repository.insertarAtencionMedicaCompleta(entity);
        
        // 4. Log: Finalizar procesamiento.
        
        return idAtencion;
    }
}