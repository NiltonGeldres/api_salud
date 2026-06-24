package com.api_salud.api_salud.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtencionMedicaSintomaRequest {

    @NotBlank(message = "La descripción del síntoma es obligatoria.")
    @Size(max = 2000, message = "El texto del síntoma supera los 2000 caracteres.")
    private String descripcion;

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}