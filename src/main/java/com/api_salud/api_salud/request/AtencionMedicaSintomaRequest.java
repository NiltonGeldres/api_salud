package com.api_salud.api_salud.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtencionMedicaSintomaRequest {

    @NotBlank(message = "La descripción del síntoma es obligatoria.")
    @Size(max = 2000, message = "El texto del síntoma supera los 2000 caracteres.")
    private String descripcion;
    private int idSintoma;
    

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public int getIdSintoma() {
		return idSintoma;
	}
	public void setIdSintoma(int idSintoma) {
		this.idSintoma = idSintoma;
	}
    
}