package com.api_salud.api_salud.request;

import javax.validation.constraints.Size;

public class AtencionMedicaSintomaRequest {

    private Integer idSintoma;
    private Integer idTipoSintoma;

    @Size(max = 1000, message = "La descripción del síntoma excede los 1000 caracteres.")
    private String descripcion;

    public Integer getIdSintoma() { return idSintoma; }
    public void setIdSintoma(Integer idSintoma) { this.idSintoma = idSintoma; }

    public Integer getIdTipoSintoma() { return idTipoSintoma; }
    public void setIdTipoSintoma(Integer idTipoSintoma) { this.idTipoSintoma = idTipoSintoma; }

    public String getDescripcion() { return SecuritySanitizer.sanitize(descripcion); }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}