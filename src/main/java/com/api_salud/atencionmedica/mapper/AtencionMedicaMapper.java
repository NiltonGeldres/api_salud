
package com.api_salud.atencionmedica.mapper;

import com.api_salud.atencionmedica.entity.*;
import com.api_salud.atencionmedica.request.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper de MapStruct para la conversión entre Request DTOs y Entidades de Atención Médica.
 * Se configura con componentModel = "spring" para la inyección de dependencias.
 *
 * NOTA: Este código es 100% compatible con Java 8.
 */
@Mapper(componentModel = "spring")
public interface AtencionMedicaMapper {

	
    // ===================================================================================
    // 1. Mapeo Principal: Request DTO a Entidad de Cabecera (AtencionMedica)
    // ===================================================================================

    /**
     * Convierte el DTO principal de la solicitud (incluyendo sus listas de detalles)
     * a la Entidad principal de Atención Médica.
     * @param dto El DTO de solicitud completo.
     * @return La entidad de Atención Médica.
     */
    @Mappings({
        // Ignorar el ID de la entidad principal (se genera al guardar en la BD)
        @Mapping(target = "id", ignore = true),
        
        // Mapeo de las listas de detalles (MapStruct maneja la recursividad automáticamente)
        @Mapping(target = "antecedentes", source = "antecedentes"),
        @Mapping(target = "diagnosticos", source = "diagnosticos"),
        @Mapping(target = "discapacidades", source = "discapacidades"),
        @Mapping(target = "discapacidadOtros", source = "discapacidadOtros"),
        @Mapping(target = "examenesFisicos", source = "examenesFisicos"),
        @Mapping(target = "medicacion", source = "medicacion"),
        @Mapping(target = "ordenesMedicas", source = "ordenesMedicas"),
        @Mapping(target = "sintomas", source = "sintomas"),
        @Mapping(target = "tratamientos", source = "tratamientos"),
        
        // Mapeo de campos directos de la cabecera (se asume que tienen el mismo nombre)
        @Mapping(target = "idPaciente", source = "idPaciente"),
        @Mapping(target = "idCuentaAtencion", source = "idCuentaAtencion"),
        @Mapping(target = "idServicio", source = "idServicio"),
        @Mapping(target = "idMedicoIngreso", source = "idMedicoIngreso"),
        @Mapping(target = "idEstadoAtencion", source = "idEstadoAtencion"),
        @Mapping(target = "tsIngreso", source = "tsIngreso"),
        @Mapping(target = "idUsuarioRegistro", source = "idUsuarioRegistro"),
        @Mapping(target = "origenRegistroUsuario", source = "origenRegistroUsuario")
    })
//    AtencionMedica toEntity(AtencionMedicaRequestDTO dto);

    AtencionMedica toModel(AtencionMedicaRequestDTO dto); 

    // Y añades un nuevo método para la conversión final, que solo usará el Service
    AtencionMedica toEntity(AtencionMedica model);
    
    
    // ===================================================================================
    // 2. Mapeo de Detalles (Métodos individuales y de lista)
    // ===================================================================================

    // --- Antecedente ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaAntecedente toEntity(AntecedenteRequestDTO dto);
    List<AtencionMedicaAntecedente> toAntecedenteEntityList(List<AntecedenteRequestDTO> dtos);

    // --- Diagnostico ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaDiagnostico toEntity(DiagnosticoRequestDTO dto);
    List<AtencionMedicaDiagnostico> toDiagnosticoEntityList(List<DiagnosticoRequestDTO> dtos);

    // --- Discapacidad ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaDiscapacidad toEntity(DiscapacidadRequestDTO dto);
    List<AtencionMedicaDiscapacidad> toDiscapacidadEntityList(List<DiscapacidadRequestDTO> dtos);

    // --- Discapacidad Otros ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaDiscapacidadOtros toEntity(DiscapacidadOtrosRequestDTO dto);
    List<AtencionMedicaDiscapacidadOtros> toDiscapacidadOtrosEntityList(List<DiscapacidadOtrosRequestDTO> dtos);

    // --- Examen Físico ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaExamenFisico toEntity(ExamenFisicoRequestDTO dto);
    List<AtencionMedicaExamenFisico> toExamenFisicoEntityList(List<ExamenFisicoRequestDTO> dtos);

    // --- Medicación ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaMedicacion toEntity(MedicacionRequestDTO dto);
    List<AtencionMedicaMedicacion> toMedicacionEntityList(List<MedicacionRequestDTO> dtos);

    // --- Orden Médica ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaOrdenMedica toEntity(OrdenMedicaRequestDTO dto);
    List<AtencionMedicaOrdenMedica> toOrdenMedicaEntityList(List<OrdenMedicaRequestDTO> dtos);

    // --- Síntoma ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaSintoma toEntity(SintomaRequestDTO dto);
    List<AtencionMedicaSintoma> toSintomaEntityList(List<SintomaRequestDTO> dtos);

    // --- Tratamiento ---
    @Mapping(target = "id", ignore = true)
    AtencionMedicaTratamiento toEntity(TratamientoRequestDTO dto);
    List<AtencionMedicaTratamiento> toTratamientoEntityList(List<TratamientoRequestDTO> dtos);
}