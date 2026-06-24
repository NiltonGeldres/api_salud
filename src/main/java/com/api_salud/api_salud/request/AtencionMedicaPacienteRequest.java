package com.api_salud.api_salud.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtencionMedicaPacienteRequest {

    @NotBlank(message = "El número de historia clínica (hc) es obligatorio.")
    @Size(max = 20, message = "La HC no puede superar los 20 caracteres.")
    private String hc;

    @NotBlank(message = "El nombre del paciente es obligatorio.")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres.")
    private String name;

    public String getHc() { return hc; }
    public void setHc(String hc) { this.hc = hc; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}