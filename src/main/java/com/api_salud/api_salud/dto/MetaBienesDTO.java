package com.api_salud.api_salud.dto;

import java.io.Serializable;

public class MetaBienesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idEntidad;
    private Integer paginaActual;
    private Integer tamanoPagina;
    private Integer totalRegistros;
    private String terminoBusqueda;
    private Integer tipoProductoFiltro;

    public MetaBienesDTO() {
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
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

    public Integer getTipoProductoFiltro() {
        return tipoProductoFiltro;
    }

    public void setTipoProductoFiltro(Integer tipoProductoFiltro) {
        this.tipoProductoFiltro = tipoProductoFiltro;
    }
}