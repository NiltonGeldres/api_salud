package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_tratamientos
 */
public class AtencionMedicaTratamientoEntity {

    // PK: id_atencion_tratamiento bigint
    private Long idAtencionTratamiento;

    // FK: id_atencion bigint
    private Long idAtencion;

    // Campos
    // id_tratamiento integer
    private Integer idTratamiento;
    // id_tipo_tratamiento integer
    private Integer idTipoTratamiento;
    // descripcion character varying(5000)
    private String descripcion;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaTratamientoEntity() {
    }

    // Getters y Setters
    // ...
    public Long getIdAtencionTratamiento() {
        return idAtencionTratamiento;
    }

    public void setIdAtencionTratamiento(Long idAtencionTratamiento) {
        this.idAtencionTratamiento = idAtencionTratamiento;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public Integer getIdTipoTratamiento() {
        return idTipoTratamiento;
    }

    public void setIdTipoTratamiento(Integer idTipoTratamiento) {
        this.idTipoTratamiento = idTipoTratamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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