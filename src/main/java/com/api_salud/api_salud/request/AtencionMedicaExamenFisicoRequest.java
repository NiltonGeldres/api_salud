package com.api_salud.api_salud.request;

import javax.validation.constraints.Size;

public class AtencionMedicaExamenFisicoRequest {

    private Integer idExamenFisico;
    private Integer idTipoExamenFisico;

    @Size(max = 1000, message = "La descripción del examen físico excede los 1000 caracteres.")
    private String descripcion;

    public Integer getIdExamenFisico() { return idExamenFisico; }
    public void setIdExamenFisico(Integer idExamenFisico) { this.idExamenFisico = idExamenFisico; }

    public Integer getIdTipoExamenFisico() { return idTipoExamenFisico; }
    public void setIdTipoExamenFisico(Integer idTipoExamenFisico) { this.idTipoExamenFisico = idTipoExamenFisico; }

    public String getDescripcion() { return SecuritySanitizer.sanitize(descripcion); }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}