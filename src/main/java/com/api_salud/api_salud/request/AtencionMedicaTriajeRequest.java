package com.api_salud.api_salud.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtencionMedicaTriajeRequest {

    @NotNull(message = "El idTipoTriaje es requerido.")
    @Min(value = 1, message = "idTipoTriaje inválido.")
    private Integer idTriaje;

    @NotBlank(message = "El valor del triaje no puede estar vacío.")
    @Size(max = 50, message = "El valor del triaje supera los 50 caracteres.")
    private String valorTriaje;

	public Integer getIdTriaje() {
		return idTriaje;
	}

	public void setIdTriaje(Integer idTriaje) {
		this.idTriaje = idTriaje;
	}

	public String getValorTriaje() {
		return valorTriaje;
	}

	public void setValorTriaje(String valorTriaje) {
		this.valorTriaje = valorTriaje;
	}

    
}