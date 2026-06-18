package com.api_salud.api_salud.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AtencionMedicaDiagnosticoRequest {

    @NotNull(message = "El idDiagnostico es obligatorio.")
    @Min(value = 1)
    private Integer idDiagnostico;

    private Integer idSubclasificacion;
    private Integer idLab1;
    private Integer idDiagnosticoOrden;

    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }

    public Integer getIdSubclasificacion() { return idSubclasificacion; }
    public void setIdSubclasificacion(Integer idSubclasificacion) { this.idSubclasificacion = idSubclasificacion; }

    public Integer getIdLab1() { return idLab1; }
    public void setIdLab1(Integer idLab1) { this.idLab1 = idLab1; }

    public Integer getIdDiagnosticoOrden() { return idDiagnosticoOrden; }
    public void setIdDiagnosticoOrden(Integer idDiagnosticoOrden) { this.idDiagnosticoOrden = idDiagnosticoOrden; }
}