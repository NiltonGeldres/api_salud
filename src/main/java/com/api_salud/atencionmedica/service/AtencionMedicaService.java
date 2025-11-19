package com.api_salud.atencionmedica.service;

import com.api_salud.atencionmedica.domain.AtencionMedicaModel;
import com.api_salud.atencionmedica.entity.AtencionMedicaEntity;
import com.api_salud.atencionmedica.mapper.AtencionMedicaMapper;
import com.api_salud.atencionmedica.repository.AtencionMedicaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;


/**
 * SERVICIO DE NEGOCIO: Implementa la lógica de la aplicación.
 * Recibe y retorna el AtencionMedicaModel (objeto de dominio limpio).
 * Es responsable de validar la data y coordinar con el Mapper y el Repository.
 */
@Service
@RequiredArgsConstructor
public class AtencionMedicaService {
    
    // Inyección de dependencias
    private final AtencionMedicaRepository atencionMedicaRepository;
    private final AtencionMedicaMapper mapper;

    /**
     * Procesa la creación de una atención médica completa (cabecera y detalles).
     * @param atencionModel El modelo de dominio de Atención Médica.
     * @return El ID de la atención médica creada.
     */
    @Transactional
    public Long crearAtencionMedicaCompleta(AtencionMedicaModel atencionModel) {
        
        // ====================================================================
        // 1. Lógica de Negocio y Validación (Se ejecuta sobre el Model)
        // ====================================================================
        
        // Ejemplo de lógica: Establecer timestamps y estado antes de persistir
        if (atencionModel.getTsIngreso() == null) {
            atencionModel.setTsIngreso(OffsetDateTime.now());
        }
        atencionModel.setTsActualizacion(OffsetDateTime.now());
        
        // Ejemplo de validación: Asegurar que el ID del paciente es válido
        if (atencionModel.getIdPaciente() == null || atencionModel.getIdPaciente() <= 0) {
            throw new IllegalArgumentException("El ID del paciente es obligatorio para registrar una atención.");
        }
        
        System.out.println("Lógica de Negocio ejecutada exitosamente para el paciente: " + atencionModel.getIdPaciente());

        // ====================================================================
        // 2. Mapeo a Entidad (Model -> Entity)
        // ====================================================================

        // Conversión del Modelo de Dominio (Model) al objeto de Persistencia (Entity)
        AtencionMedicaEntity atencionEntity = mapper.toEntity(atencionModel);

        // ====================================================================
        // 3. Persistencia (Guardar en la BD)
        // ====================================================================
        
        // El repositorio guarda la Entidad completa
        AtencionMedicaEntity savedEntity = atencionMedicaRepository.save(atencionEntity);
        
        System.out.println("Atención médica y sus detalles guardados con éxito.");

        // Retorna el ID generado por la base de datos
        return savedEntity.getId();
    }

    // Aquí irían otros métodos de servicio: buscar, actualizar, eliminar, etc.
}