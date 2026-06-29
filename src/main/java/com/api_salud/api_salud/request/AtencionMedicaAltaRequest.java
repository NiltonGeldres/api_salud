package com.api_salud.api_salud.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtencionMedicaAltaRequest {

    @NotBlank(message = "La descripción del alta es obligatoria.")
    @Size(max = 2000, message = "La descripción del alta excede los 2000 caracteres.")
    private String descripcion;
    private int idAlta ;

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public int getIdAlta() {
		return idAlta;
	}
	public void setIdAlta(int idAlta) {
		this.idAlta = idAlta;
	}
    
}