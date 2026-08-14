package com.api_salud.api_salud.request;

import javax.validation.constraints.NotNull;

public class CatalogoBienesPaqueteDetalleRequest {

	@NotNull(message = "El idPaquete es obligatorio")
    private Integer idPaquete;

    public CatalogoBienesPaqueteDetalleRequest() {
    }

    public CatalogoBienesPaqueteDetalleRequest(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }
}