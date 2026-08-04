package com.api_salud.api_salud.request;

import javax.validation.constraints.NotNull;

public class CatalogoInitRequest {

    @NotNull(message = "El idEntidad es obligatorio")
    private Integer idEntidad;

    public CatalogoInitRequest() {
    }

    public CatalogoInitRequest(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }
}
