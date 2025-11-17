package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_discapacidad
 */
public class AtencionMedicaDiscapacidad {

    // PK: id_atencion_discapacidad bigint
    private Long idAtencionDiscapacidad;

    // FK: id_atencion bigint
    private Long idAtencion;

    // Campos
    // id_discapacidad integer
    private Integer idDiscapacidad;
    // id_gravedad_discapacidad integer
    private Integer idGravedadDiscapacidad;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaDiscapacidad() {
    }

    // Getters y Setters
    // ...
    public Long getIdAtencionDiscapacidad() {
        return idAtencionDiscapacidad;
    }

    public void setIdAtencionDiscapacidad(Long idAtencionDiscapacidad) {
        this.idAtencionDiscapacidad = idAtencionDiscapacidad;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdDiscapacidad() {
        return idDiscapacidad;
    }

    public void setIdDiscapacidad(Integer idDiscapacidad) {
        this.idDiscapacidad = idDiscapacidad;
    }

    public Integer getIdGravedadDiscapacidad() {
        return idGravedadDiscapacidad;
    }

    public void setIdGravedadDiscapacidad(Integer idGravedadDiscapacidad) {
        this.idGravedadDiscapacidad = idGravedadDiscapacidad;
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