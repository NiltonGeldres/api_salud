package com.api_salud.api_salud.request;

import java.io.Serializable;

public class CatalogoDiagnosticosRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String busqueda = "";
    private Integer limite = 20;
    private Integer pagina = 1;

    public CatalogoDiagnosticosRequest() {
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }
}

