package com.api_salud.api_salud.request;

import javax.validation.constraints.Size;

public class AtencionMedicaAntecedenteRequest {

    private Integer idAntecedente;
    private Integer idTipoAntecedente;
    
    @Size(max = 1000, message = "La descripción del antecedente excede los 1000 caracteres.")
    private String descripcion;

    public Integer getIdAntecedente() { return idAntecedente; }
    public void setIdAntecedente(Integer idAntecedente) { this.idAntecedente = idAntecedente; }

    public Integer getIdTipoAntecedente() { return idTipoAntecedente; }
    public void setIdTipoAntecedente(Integer idTipoAntecedente) { this.idTipoAntecedente = idTipoAntecedente; }

    public String getDescripcion() { return SecuritySanitizer.sanitize(descripcion); }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}