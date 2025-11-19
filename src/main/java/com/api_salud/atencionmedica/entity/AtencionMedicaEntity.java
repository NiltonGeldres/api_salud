package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad principal que mapea la tabla igm_atenciones_medicas.atenciones_medicas
 */
public class AtencionMedicaEntity {

    // PK: id_atencion bigint
    private Long idAtencion;

    // FKs y campos obligatorios
    // id_paciente integer
    private Integer idPaciente;
    // id_cuenta_atencion integer
    private Integer idCuentaAtencion;
    // id_servicio integer
    private Integer idServicio;
    // id_medico_ingreso integer
    private Integer idMedicoIngreso;
    // id_estado_atencion integer
    private Integer idEstadoAtencion;

    // Fechas y Trazabilidad
    // ts_ingreso timestamp with time zone
    private OffsetDateTime tsIngreso;
    // ts_actualizacion timestamp with time zone (default now())
    private OffsetDateTime tsActualizacion;
    // id_usuario_registro integer
    private Integer idUsuarioRegistro;
    // origen_registro_usuario character varying(50)
    private String origenRegistroUsuario;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaEntity() {
    }

    // Getters y Setters (se omiten por brevedad en este ejemplo,
    // pero deben ser incluidos en una implementación real)
    // ...
    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdCuentaAtencion() {
        return idCuentaAtencion;
    }

    public void setIdCuentaAtencion(Integer idCuentaAtencion) {
        this.idCuentaAtencion = idCuentaAtencion;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdMedicoIngreso() {
        return idMedicoIngreso;
    }

    public void setIdMedicoIngreso(Integer idMedicoIngreso) {
        this.idMedicoIngreso = idMedicoIngreso;
    }

    public Integer getIdEstadoAtencion() {
        return idEstadoAtencion;
    }

    public void setIdEstadoAtencion(Integer idEstadoAtencion) {
        this.idEstadoAtencion = idEstadoAtencion;
    }

    public OffsetDateTime getTsIngreso() {
        return tsIngreso;
    }

    public void setTsIngreso(OffsetDateTime tsIngreso) {
        this.tsIngreso = tsIngreso;
    }

    public OffsetDateTime getTsActualizacion() {
        return tsActualizacion;
    }

    public void setTsActualizacion(OffsetDateTime tsActualizacion) {
        this.tsActualizacion = tsActualizacion;
    }

    public Integer getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public String getOrigenRegistroUsuario() {
        return origenRegistroUsuario;
    }

    public void setOrigenRegistroUsuario(String origenRegistroUsuario) {
        this.origenRegistroUsuario = origenRegistroUsuario;
    }
}