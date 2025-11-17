package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_discapacidad_otros
 * Contiene campos relacionados con la duración y consecuencias de la discapacidad/condición.
 */
public class AtencionMedicaDiscapacidadOtros {

    // PK: id_atencion_discapacidad_otros bigint
    private Long idAtencionDiscapacidadOtros;

    // FK: id_atencion bigint
    private Long idAtencion;

    // Campos
    // id_tipo_actividad integer
    private Integer idTipoActividad;
    // id_tiempo_discapacidad_aa integer (Años)
    private Integer idTiempoDiscapacidadAnios;
    // id_tiempo_discapacidad_mm integer (Meses)
    private Integer idTiempoDiscapacidadMeses;
    // id_tiempo_discapacidad_dd integer (Días)
    private Integer idTiempoDiscapacidadDias;
    // id_tiempo_sintrabajar_aa integer (Años)
    private Integer idTiempoSinTrabajarAnios;
    // id_tiempo_sintrabajar_mm integer (Meses)
    private Integer idTiempoSinTrabajarMeses;
    // id_tiempo_sintrabajar_dd integer (Días)
    private Integer idTiempoSinTrabajarDias;
    // id_alta integer
    private Integer idAlta;
    // id_productividad integer
    private Integer idProductividad;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaDiscapacidadOtros() {
    }

    // Getters y Setters
    // ...
    public Long getIdAtencionDiscapacidadOtros() {
        return idAtencionDiscapacidadOtros;
    }

    public void setIdAtencionDiscapacidadOtros(Long idAtencionDiscapacidadOtros) {
        this.idAtencionDiscapacidadOtros = idAtencionDiscapacidadOtros;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public Integer getIdTiempoDiscapacidadAnios() {
        return idTiempoDiscapacidadAnios;
    }

    public void setIdTiempoDiscapacidadAnios(Integer idTiempoDiscapacidadAnios) {
        this.idTiempoDiscapacidadAnios = idTiempoDiscapacidadAnios;
    }

    public Integer getIdTiempoDiscapacidadMeses() {
        return idTiempoDiscapacidadMeses;
    }

    public void setIdTiempoDiscapacidadMeses(Integer idTiempoDiscapacidadMeses) {
        this.idTiempoDiscapacidadMeses = idTiempoDiscapacidadMeses;
    }

    public Integer getIdTiempoDiscapacidadDias() {
        return idTiempoDiscapacidadDias;
    }

    public void setIdTiempoDiscapacidadDias(Integer idTiempoDiscapacidadDias) {
        this.idTiempoDiscapacidadDias = idTiempoDiscapacidadDias;
    }

    public Integer getIdTiempoSinTrabajarAnios() {
        return idTiempoSinTrabajarAnios;
    }

    public void setIdTiempoSinTrabajarAnios(Integer idTiempoSinTrabajarAnios) {
        this.idTiempoSinTrabajarAnios = idTiempoSinTrabajarAnios;
    }

    public Integer getIdTiempoSinTrabajarMeses() {
        return idTiempoSinTrabajarMeses;
    }

    public void setIdTiempoSinTrabajarMeses(Integer idTiempoSinTrabajarMeses) {
        this.idTiempoSinTrabajarMeses = idTiempoSinTrabajarMeses;
    }

    public Integer getIdTiempoSinTrabajarDias() {
        return idTiempoSinTrabajarDias;
    }

    public void setIdTiempoSinTrabajarDias(Integer idTiempoSinTrabajarDias) {
        this.idTiempoSinTrabajarDias = idTiempoSinTrabajarDias;
    }

    public Integer getIdAlta() {
        return idAlta;
    }

    public void setIdAlta(Integer idAlta) {
        this.idAlta = idAlta;
    }

    public Integer getIdProductividad() {
        return idProductividad;
    }

    public void setIdProductividad(Integer idProductividad) {
        this.idProductividad = idProductividad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public OffsetDateTime getTsRegistro() {
        return tsRegistro;
    }

    public void setTsRegistro(OffsetDateTime tsRegistro) {
        this.tsRegistro = tsRegistro;
    }
}