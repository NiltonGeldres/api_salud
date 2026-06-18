package com.api_salud.api_salud.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AtencionMedicaTriajeRequest {

    @NotNull(message = "El idTipoTriaje es obligatorio.")
    @Min(value = 1)
    private Integer idTipoTriaje;
    
    @NotNull(message = "El valor del triaje es obligatorio.")
    @Size(max = 20, message = "El valor del triaje excede los 20 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9./ ]+$", message = "El valor de triaje contiene caracteres inválidos.")
    private String valorTriaje;

    public Integer getIdTipoTriaje() { return idTipoTriaje; }
    public void setIdTipoTriaje(Integer idTipoTriaje) { this.idTipoTriaje = idTipoTriaje; }

    public String getValorTriaje() { return valorTriaje; }
    public void setValorTriaje(String valorTriaje) { this.valorTriaje = valorTriaje; }
}