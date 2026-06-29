package com.api_salud.api_salud.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtencionMedicaMedicacionRequest {

    @NotNull(message = "El idAlmacen es requerido.")
    @Min(value = 1, message = "idAlmacen no válido.")
    private Integer idAlmacen;

    @NotNull(message = "El idProducto es obligatorio.")
    @Min(value = 1, message = "idProducto de medicamento no válido.")
    private Integer idProducto;

    @NotNull(message = "La cantidad de dosis es obligatoria.")
    @Min(value = 1, message = "La cantidad de dosis debe ser mayor a 0.")
    private Integer cantidadDosis;

    @NotNull(message = "El idUmDosis es obligatorio.")
    @Min(value = 1, message = "idUmDosis no válido.")
    private Integer idUmDosis;

    @NotNull(message = "La frecuencia de dosis es obligatoria.")
    @Min(value = 1, message = "idFrecuenciaDosis no válido.")
    private Integer idFrecuenciaDosis;

    @NotNull(message = "La cantidad del periodo es obligatoria.")
    @Min(value = 1, message = "La cantidad del periodo debe ser mayor a 0.")
    private Integer cantidadPeriodo;

    @NotNull(message = "La vía de administración es obligatoria.")
    @Min(value = 1, message = "idViaAdministracion no válido.")
    private Integer idViaAdministracion;

    @NotNull(message = "La cantidad total del medicamento es obligatoria.")
    @Min(value = 1, message = "La cantidad total debe ser mayor a 0.")
    private Integer cantidadTotal;

    @NotBlank(message = "Las indicaciones del medicamento son obligatorias.")
    @Size(max = 1000, message = "Las indicaciones superan el límite de 1000 caracteres.")
    private String indicaciones;

    @NotNull(message = "El idDiagnostico asociado es obligatorio.")
    @Min(value = 1, message = "El diagnóstico amarrado al medicamento no es válido.")
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
    public String getIndicaciones() { return indicaciones; }
    public void setIndicaciones(String indicaciones) { this.indicaciones = indicaciones; }
    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }
}