package com.api_salud.api_salud.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AtencionMedicaDiagnosticoRequest {

    @NotNull(message = "El idDiagnostico es obligatorio.")
    @Min(value = 1, message = "idDiagnostico no válido.")
    private Integer idDiagnostico;

    @Min(value = 1, message = "idSubclasificacion no válido.")
    private Integer idSubclasificacion;

    @Min(value = 1, message = "idDiagnosticoOrden no válido.")
    private Integer idDiagnosticoOrden;

    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }
    public Integer getIdSubclasificacion() { return idSubclasificacion; }
    public void setIdSubclasificacion(Integer idSubclasificacion) { this.idSubclasificacion = idSubclasificacion; }
    public Integer getIdDiagnosticoOrden() { return idDiagnosticoOrden; }
    public void setIdDiagnosticoOrden(Integer idDiagnosticoOrden) { this.idDiagnosticoOrden = idDiagnosticoOrden; }
}