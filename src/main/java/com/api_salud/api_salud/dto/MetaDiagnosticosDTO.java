package com.api_salud.api_salud.dto;

import java.io.Serializable;

public class MetaDiagnosticosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer paginaActual;
    private Integer tamanoPagina;
    private Integer totalRegistros;
    private String terminoBusqueda;

    public MetaDiagnosticosDTO() {
    }

    public Integer getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(Integer paginaActual) {
        this.paginaActual = paginaActual;
    }

    public Integer getTamanoPagina() {
        return tamanoPagina;
    }

    public void setTamanoPagina(Integer tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public String getTerminoBusqueda() {
        return terminoBusqueda;
    }

    public void setTerminoBusqueda(String terminoBusqueda) {
        this.terminoBusqueda = terminoBusqueda;
    }
}