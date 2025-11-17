package com.api_salud.atencionmedica.request;

import java.time.OffsetDateTime;

/**
 * DTO para el detalle de Examen Físico (igm_atenciones_medicas.atenciones_medicas_examen_fisico).
 */
public class ExamenFisicoRequestDTO {

    // id_examen_fisico integer (OBLIGATORIO por clave compuesta)
    private Integer idExamenFisico;
    // id_tipo_examen_fisico integer
    private Integer idTipoExamenFisico;
    // descripcion character varying(5000)
    private String descripcion;
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public Integer getIdExamenFisico() { return idExamenFisico; }
    public void setIdExamenFisico(Integer idExamenFisico) { this.idExamenFisico = idExamenFisico; }

    public Integer getIdTipoExamenFisico() { return idTipoExamenFisico; }
    public void setIdTipoExamenFisico(Integer idTipoExamenFisico) { this.idTipoExamenFisico = idTipoExamenFisico; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }
}