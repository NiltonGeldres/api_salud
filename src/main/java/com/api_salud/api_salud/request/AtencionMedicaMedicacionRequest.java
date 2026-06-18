package com.api_salud.api_salud.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtencionMedicaMedicacionRequest {

    private Integer idAlmacen;

    @NotNull(message = "El idProducto es obligatorio.")
    @Min(value = 1)
    private Integer idProducto;

    @NotNull(message = "La cantidad de dosis es obligatoria.")
    @Min(value = 1)
    private Integer cantidadDosis;

    @NotNull(message = "El idUmDosis es obligatorio.")
    @Min(value = 1)
    private Integer idUmDosis;

    @NotNull(message = "El idFrecuenciaDosis es obligatorio.")
    @Min(value = 1)
    private Integer idFrecuenciaDosis;

    @NotNull(message = "La cantidad de periodo es obligatoria.")
    @Min(value = 1)
    private Integer cantidadPeriodo;

    @NotNull(message = "El idViaAdministracion es obligatorio.")
    @Min(value = 1)
    private Integer idViaAdministracion;

    @NotNull(message = "La cantidad total es obligatoria.")
    @Min(value = 1)
    private Integer cantidadTotal;

    @Size(max = 1000, message = "Las indicaciones exceden los 1000 caracteres.")
    private String indicaciones;

    @NotNull(message = "El idDiagnostico asociado es obligatorio.")
    @Min(value = 1)
    private Integer idDiagnostico;

    public Integer getIdAlmacen() { return idAlmacen; }
    public void setIdAlmacen(Integer idAlmacen) { this.idAlmacen = idAlmacen; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Integer getCantidadDosis() { return cantidadDosis; }
    public void setCantidadDosis(Integer cantidadDosis) { this.cantidadDosis = cantidadDosis; }

    public Integer getIdUmDosis() { return idUmDosis; }
    public void setIdUmDosis(Integer idUmDosis) { this.idUmDosis = idUmDosis; }

    public Integer getIdFrecuenciaDosis() { return idFrecuenciaDosis; }
    public void setIdFrecuenciaDosis(Integer idFrecuenciaDosis) { this.idFrecuenciaDosis = idFrecuenciaDosis; }

    public Integer getCantidadPeriodo() { return cantidadPeriodo; }
    public void setCantidadPeriodo(Integer cantidadPeriodo) { this.cantidadPeriodo = cantidadPeriodo; }

    public Integer getIdViaAdministracion() { return idViaAdministracion; }
    public void setIdViaAdministracion(Integer idViaAdministracion) { this.idViaAdministracion = idViaAdministracion; }

    public Integer getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(Integer cantidadTotal) { this.cantidadTotal = cantidadTotal; }

    public String getIndicaciones() { return SecuritySanitizer.sanitize(indicaciones); }
    public void setIndicaciones(String indicaciones) { this.indicaciones = indicaciones; }

    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }
}