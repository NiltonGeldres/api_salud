package com.api_salud.atencionmedica.mapper;
//===================================================================================
//IMPORTACIONES
//===================================================================================

//Modelos de Dominio (Terminan en Model)
import com.api_salud.atencionmedica.domain.AtencionMedicaModel; 
import com.api_salud.atencionmedica.domain.AtencionMedicaModel.Antecedente; 

//Entidades de Persistencia (Terminan en Entity)
import com.api_salud.atencionmedica.entity.AtencionMedicaEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaAntecedenteEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaDiagnosticoEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaDiscapacidadEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaDiscapacidadOtrosEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaExamenFisicoEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaMedicacionEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaOrdenMedicaEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaSintomaEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaTratamientoEntity;

//DTOs de Request (Terminan en DTO)
import com.api_salud.atencionmedica.request.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

/**
* Mapper de MapStruct para la conversión entre DTOs, Modelos y Entidades de Atención Médica.
* Asegura la separación de capas usando la nomenclatura:
* - Modelos: AtencionMedicaModel
* - Entidades: AtencionMedicaEntity
* - DTOs: AtencionMedicaRequestDTO
*/
@Mapper(componentModel = "spring")
public interface AtencionMedicaMapper {

	
 // ===================================================================================
 // 1. DTO a MODELO (Controller -> Service)
 // ===================================================================================
 
 // Mapea el DTO (recibido por HTTP) al MODELO de Dominio.
 AtencionMedicaModel toModel(AtencionMedicaRequestDTO dto); 

 // ===================================================================================
 // 2. MODELO a Entidad (Service -> Repository)
 // ===================================================================================
 
 @Mappings({
     // Ignorar el ID de la entidad principal (se genera al guardar en la BD)
     @Mapping(target = "idAtencion", ignore = true),
     
     // Mapeo de las listas de detalles
     @Mapping(target = "antecedentes", source = "antecedentes"),
     @Mapping(target = "diagnosticos", source = "diagnosticos"),
     @Mapping(target = "discapacidades", source = "discapacidades"),
     @Mapping(target = "discapacidadOtros", source = "discapacidadOtros"),
     @Mapping(target = "examenesFisicos", source = "examenesFisicos"),
     @Mapping(target = "medicacion", source = "medicacion"),
     @Mapping(target = "ordenesMedicas", source = "ordenesMedicas"),
     @Mapping(target = "sintomas", source = "sintomas"),
     @Mapping(target = "tratamientos", source = "tratamientos"),
     
     // Mapeo de campos directos de la cabecera 
     @Mapping(target = "idPaciente", source = "idPaciente"),
     @Mapping(target = "idCuentaAtencion", source = "idCuentaAtencion"),
     @Mapping(target = "idServicio", source = "idServicio"),
     @Mapping(target = "idMedicoIngreso", source = "idMedicoIngreso"),
     @Mapping(target = "idEstadoAtencion", source = "idEstadoAtencion"),
     @Mapping(target = "tsIngreso", source = "tsIngreso"),
     @Mapping(target = "idUsuarioRegistro", source = "idUsuarioRegistro"),
     @Mapping(target = "origenRegistroUsuario", source = "origenRegistroUsuario")
 })
 // Mapea el Modelo de Dominio (AtencionMedicaModel) al objeto de Persistencia (AtencionMedicaEntity).
 AtencionMedicaEntity toEntity(AtencionMedicaModel model); 
 
 
 // ===================================================================================
 // 3. Mapeo de Detalles (DTO a Entity de Detalle)
 // ===================================================================================

 // --- Antecedente ---
 @Mapping(target = "idAtencionAntecedente", ignore = true)
 AtencionMedicaAntecedenteEntity toEntity(AntecedenteRequestDTO dto);
 List<AtencionMedicaAntecedenteEntity> toAntecedenteEntityList(List<AntecedenteRequestDTO> dtos);

 // --- Diagnostico ---
 @Mapping(target = "idAtencionDiagnostico", ignore = true)
 AtencionMedicaDiagnosticoEntity toEntity(DiagnosticoRequestDTO dto);
 List<AtencionMedicaDiagnosticoEntity> toDiagnosticoEntityList(List<DiagnosticoRequestDTO> dtos);

 // --- Discapacidad ---
 @Mapping(target = "idAtencionDiscapacidad", ignore = true)
 AtencionMedicaDiscapacidadEntity toEntity(DiscapacidadRequestDTO dto);
 List<AtencionMedicaDiscapacidadEntity> toDiscapacidadEntityList(List<DiscapacidadRequestDTO> dtos);

 // --- Discapacidad Otros ---
 @Mapping(target = "idAtencionDiscapacidadOtros", ignore = true)
 AtencionMedicaDiscapacidadOtrosEntity toEntity(DiscapacidadOtrosRequestDTO dto);
 List<AtencionMedicaDiscapacidadOtrosEntity> toDiscapacidadOtrosEntityList(List<DiscapacidadOtrosRequestDTO> dtos);

 // --- Examen Físico ---
 @Mapping(target = "idAtencionExamenFisico", ignore = true)
 AtencionMedicaExamenFisicoEntity toEntity(ExamenFisicoRequestDTO dto);
 List<AtencionMedicaExamenFisicoEntity> toExamenFisicoEntityList(List<ExamenFisicoRequestDTO> dtos);

 // --- Medicación ---
 @Mapping(target = "idAtencionMedicacion", ignore = true)
 AtencionMedicaMedicacionEntity toEntity(MedicacionRequestDTO dto);
 List<AtencionMedicaMedicacionEntity> toMedicacionEntityList(List<MedicacionRequestDTO> dtos);

 // --- Orden Médica ---
 @Mapping(target = "idAtencionOrdenMedica", ignore = true)
 AtencionMedicaOrdenMedicaEntity toEntity(OrdenMedicaRequestDTO dto);
 List<AtencionMedicaOrdenMedicaEntity> toOrdenMedicaEntityList(List<OrdenMedicaRequestDTO> dtos);

 // --- Síntoma ---
 @Mapping(target = "idAtencionSintoma", ignore = true)
 AtencionMedicaSintomaEntity toEntity(SintomaRequestDTO dto);
 List<AtencionMedicaSintomaEntity> toSintomaEntityList(List<SintomaRequestDTO> dtos);

 // --- Tratamiento ---
 @Mapping(target = "idAtencionTratamiento", ignore = true)
 AtencionMedicaTratamientoEntity toEntity(TratamientoRequestDTO dto);
 List<AtencionMedicaTratamientoEntity> toTratamientoEntityList(List<TratamientoRequestDTO> dtos);
}