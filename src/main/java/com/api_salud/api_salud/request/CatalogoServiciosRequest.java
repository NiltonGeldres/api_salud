package com.api_salud.api_salud.request;
import javax.validation.constraints.NotNull;

public class CatalogoServiciosRequest {

    @NotNull(message = "El idEntidad es obligatorio")
    private Integer idEntidad;

    private String busqueda = "";
    private Integer tipoServicio;
    private Integer limite = 20;
    private Integer pagina = 1;

    public CatalogoServiciosRequest() {
    }

    public CatalogoServiciosRequest(Integer idEntidad, String busqueda, Integer tipoServicio, Integer limite, Integer pagina) {
        this.idEntidad = idEntidad;
        this.busqueda = busqueda;
        this.tipoServicio = tipoServicio;
        this.limite = limite;
        this.pagina = pagina;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Integer getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(Integer tipoServicio) {
        this.tipoServicio = tipoServicio;
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