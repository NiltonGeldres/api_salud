package com.api_salud.api_salud.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtencionMedicaExamenAuxiliarRequest {

    private Integer idPuntoCarga; // Puede ser nulo o manejarse por defecto en la BD

    @NotNull(message = "El idProducto es obligatorio.")
    @Min(value = 1)
    private Integer idProducto;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(value = 1)
    private Integer cantidad;

    @Size(max = 500, message = "La observación excede los 500 caracteres.")
    private String observacion;

    @NotNull(message = "El idDiagnostico asociado es obligatorio.")
    @Min(value = 1)
    private Integer idDiagnostico;

    public Integer getIdPuntoCarga() { return idPuntoCarga; }
    public void setIdPuntoCarga(Integer idPuntoCarga) { this.idPuntoCarga = idPuntoCarga; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public String getObservacion() { return SecuritySanitizer.sanitize(observacion); }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }
}