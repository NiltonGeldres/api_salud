package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_examen_fisico
 */
public class AtencionMedicaExamenFisicoEntity {

    // PK: id_atencion_examen_fisico bigint
    private Long idAtencionExamenFisico;

    // FK: id_atencion bigint
    private Long idAtencion;

    // Campos
    // id_examen_fisico integer
    private Integer idExamenFisico;
    // id_tipo_examen_fisico integer
    private Integer idTipoExamenFisico;
    // descripcion character varying(5000)
    private String descripcion;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaExamenFisicoEntity() {
    }

    // Getters y Setters
    // ...
    public Long getIdAtencionExamenFisico() {
        return idAtencionExamenFisico;
    }

    public void setIdAtencionExamenFisico(Long idAtencionExamenFisico) {
        this.idAtencionExamenFisico = idAtencionExamenFisico;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdExamenFisico() {
        return idExamenFisico;
    }

    public void setIdExamenFisico(Integer idExamenFisico) {
        this.idExamenFisico = idExamenFisico;
    }

    public Integer getIdTipoExamenFisico() {
        return idTipoExamenFisico;
    }

    public void setIdTipoExamenFisico(Integer idTipoExamenFisico) {
        this.idTipoExamenFisico = idTipoExamenFisico;
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