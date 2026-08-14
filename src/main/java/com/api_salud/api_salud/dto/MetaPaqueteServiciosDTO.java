package com.api_salud.api_salud.dto;

import java.io.Serializable;

public class MetaPaqueteServiciosDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idPaquete;
    private Integer totalItems;
    private String tipoAlcance;
    private Integer idEntidadConsulta;
    private String descripcionPaquete;

    public MetaPaqueteServiciosDTO() {}

    public Integer getIdPaquete() { return idPaquete; }
    public void setIdPaquete(Integer idPaquete) { this.idPaquete = idPaquete; }

    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }

    public String getTipoAlcance() { return tipoAlcance; }
    public void setTipoAlcance(String tipoAlcance) { this.tipoAlcance = tipoAlcance; }

    public Integer getIdEntidadConsulta() { return idEntidadConsulta; }
    public void setIdEntidadConsulta(Integer idEntidadConsulta) { this.idEntidadConsulta = idEntidadConsulta; }

    public String getDescripcionPaquete() { return descripcionPaquete; }
    public void setDescripcionPaquete(String descripcionPaquete) { this.descripcionPaquete = descripcionPaquete; }
}