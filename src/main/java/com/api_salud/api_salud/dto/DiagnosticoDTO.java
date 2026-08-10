package com.api_salud.api_salud.dto;

import java.io.Serializable;

public class DiagnosticoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDiagnostico;
    private String codigoCie;
    private String descripcion;

    public DiagnosticoDTO() {
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getCodigoCie() {
        return codigoCie;
    }

    public void setCodigoCie(String codigoCie) {
        this.codigoCie = codigoCie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}