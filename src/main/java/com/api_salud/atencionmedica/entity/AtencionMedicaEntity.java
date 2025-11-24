// Archivo: com.api_salud.atencionmedica.entity.AtencionMedicaEntity.java
package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Entidad principal (Aggregate Root) para la capa de persistencia.
 * Mapea la tabla igm_atenciones_medicas.atenciones_medicas (Cabecera) 
 * y transporta las Entidades de Detalle para la inserción transaccional.
 */
public class AtencionMedicaEntity {

    // --- 1. Campos de la Tabla igm_atenciones_medicas (Cabecera) ---

    private Long idAtencion; // PK: id_atencion bigint
    private Integer idPaciente;
    private Integer idCuentaAtencion;
    private Integer idServicio;
    private Integer idMedicoIngreso;
    private Integer idEstadoAtencion;
    private OffsetDateTime tsIngreso;
    private OffsetDateTime tsActualizacion; // ts_actualizacion (default now())
    private Integer idUsuarioRegistro;
    private String origenRegistroUsuario;

    // --- 2. Colecciones de Entidades de Detalle (Para Mapeo y Persistencia) ---

    // 1. Antecedentes (atenciones_medicas_antecedentes)
    private List<AtencionMedicaAntecedenteEntity> antecedentes; 

    // 2. Diagnósticos (atenciones_medicas_diagnosticos)
    private List<AtencionMedicaDiagnosticoEntity> diagnosticos; 

    // 3. Discapacidad (atenciones_medicas_discapacidad)
    private List<AtencionMedicaDiscapacidadEntity> discapacidades; 

    // 4. Discapacidad Otros (atenciones_medicas_discapacidad_otros)
    private List<AtencionMedicaDiscapacidadOtrosEntity> discapacidadOtros;

    // 5. Examen Físico (atenciones_medicas_examen_fisico)
    private List<AtencionMedicaExamenFisicoEntity> examenesFisicos;

    // 6. Órdenes Médicas (atenciones_medicas_ordenes_medicas)
    private List<AtencionMedicaOrdenMedicaEntity> ordenesMedicas;

    // 7. Medicación (atenciones_medicas_medicacion)
    private List<AtencionMedicaMedicacionEntity> medicacion;

    // 8. Síntomas (atenciones_medicas_sintomas)
    private List<AtencionMedicaSintomaEntity> sintomas;

    // 9. Tratamientos (atenciones_medicas_tratamientos)
    private List<AtencionMedicaTratamientoEntity> tratamientos;

    // --- 3. Constructor ---

    public AtencionMedicaEntity() {
    }

    // --- 4. Getters y Setters de Cabecera (Simplificados) ---

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    public Integer getIdCuentaAtencion() { return idCuentaAtencion; }
    public void setIdCuentaAtencion(Integer idCuentaAtencion) { this.idCuentaAtencion = idCuentaAtencion; }
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    public Integer getIdMedicoIngreso() { return idMedicoIngreso; }
    public void setIdMedicoIngreso(Integer idMedicoIngreso) { this.idMedicoIngreso = idMedicoIngreso; }
    public Integer getIdEstadoAtencion() { return idEstadoAtencion; }
    public void setIdEstadoAtencion(Integer idEstadoAtencion) { this.idEstadoAtencion = idEstadoAtencion; }
    public OffsetDateTime getTsIngreso() { return tsIngreso; }
    public void setTsIngreso(OffsetDateTime tsIngreso) { this.tsIngreso = tsIngreso; }
    public OffsetDateTime getTsActualizacion() { return tsActualizacion; }
    public void setTsActualizacion(OffsetDateTime tsActualizacion) { this.tsActualizacion = tsActualizacion; }
    public Integer getIdUsuarioRegistro() { return idUsuarioRegistro; }
    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) { this.idUsuarioRegistro = idUsuarioRegistro; }
    public String getOrigenRegistroUsuario() { return origenRegistroUsuario; }
    public void setOrigenRegistroUsuario(String origenRegistroUsuario) { this.origenRegistroUsuario = origenRegistroUsuario; }


    // --- 5. Getters y Setters de Detalles (Obligatorios para MapStruct y Repository) ---
    
    // Antecedentes
    public List<AtencionMedicaAntecedenteEntity> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<AtencionMedicaAntecedenteEntity> antecedentes) { this.antecedentes = antecedentes; }

    // Diagnósticos
    public List<AtencionMedicaDiagnosticoEntity> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<AtencionMedicaDiagnosticoEntity> diagnosticos) { this.diagnosticos = diagnosticos; }

    // Discapacidad
    public List<AtencionMedicaDiscapacidadEntity> getDiscapacidades() { return discapacidades; }
    public void setDiscapacidades(List<AtencionMedicaDiscapacidadEntity> discapacidades) { this.discapacidades = discapacidades; }

    // Discapacidad Otros
    public List<AtencionMedicaDiscapacidadOtrosEntity> getDiscapacidadOtros() { return discapacidadOtros; }
    public void setDiscapacidadOtros(List<AtencionMedicaDiscapacidadOtrosEntity> discapacidadOtros) { this.discapacidadOtros = discapacidadOtros; }

    // Examen Físico
    public List<AtencionMedicaExamenFisicoEntity> getExamenesFisicos() { return examenesFisicos; }
    public void setExamenesFisicos(List<AtencionMedicaExamenFisicoEntity> examenesFisicos) { this.examenesFisicos = examenesFisicos; }

    // Órdenes Médicas
    public List<AtencionMedicaOrdenMedicaEntity> getOrdenesMedicas() { return ordenesMedicas; }
    public void setOrdenesMedicas(List<AtencionMedicaOrdenMedicaEntity> ordenesMedicas) { this.ordenesMedicas = ordenesMedicas; }

    // Medicación
    public List<AtencionMedicaMedicacionEntity> getMedicacion() { return medicacion; }
    public void setMedicacion(List<AtencionMedicaMedicacionEntity> medicacion) { this.medicacion = medicacion; }

    // Síntomas
    public List<AtencionMedicaSintomaEntity> getSintomas() { return sintomas; }
    public void setSintomas(List<AtencionMedicaSintomaEntity> sintomas) { this.sintomas = sintomas; }

    // Tratamientos
    public List<AtencionMedicaTratamientoEntity> getTratamientos() { return tratamientos; }
    public void setTratamientos(List<AtencionMedicaTratamientoEntity> tratamientos) { this.tratamientos = tratamientos; }
}