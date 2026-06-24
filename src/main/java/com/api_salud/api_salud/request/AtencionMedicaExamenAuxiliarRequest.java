package com.api_salud.api_salud.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtencionMedicaExamenAuxiliarRequest {

    @NotNull(message = "El idPuntoCarga es obligatorio.")
    @Min(value = 1, message = "idPuntoCarga no válido.")
    private Integer idPuntoCarga;

    @NotNull(message = "El idProducto es obligatorio.")
    @Min(value = 1, message = "idProducto de examen no válido.")
    private Integer idProducto;

    @NotNull(message = "La cantidad es requerida.")
    @Min(value = 1, message = "La cantidad mínima debe ser 1.")
    @Max(value = 99, message = "Cantidad inusual para órdenes de examen.")
    private Integer cantidad;

    @NotNull(message = "El idDiagnostico enlazado es obligatorio.")
    @Min(value = 1, message = "El diagnóstico amarrado al examen no es válido.")
    private Integer idDiagnostico;

    @Size(max = 500, message = "La observación no puede superar los 500 caracteres.")
    private String observacion;

    public Integer getIdPuntoCarga() { return idPuntoCarga; }
    public void setIdPuntoCarga(Integer idPuntoCarga) { this.idPuntoCarga = idPuntoCarga; }
    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public Integer getCantidad() { return cantidad; }
    public void setTotal(Integer cantidad) { this.cantidad = cantidad; }
    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}