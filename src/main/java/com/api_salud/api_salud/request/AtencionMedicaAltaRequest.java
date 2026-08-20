package com.api_salud.api_salud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtencionMedicaAltaRequest {

    private int idAlta;

    @NotBlank(message = "La descripción del alta es obligatoria.")
    @Size(max = 2000, message = "El texto del alta supera los 2000 caracteres.")
    private String nombreAlta;

	public int getIdAlta() {
		return idAlta;
	}

	public void setIdAlta(int idAlta) {
		this.idAlta = idAlta;
	}

	public String getNombreAlta() {
		return nombreAlta;
	}

	public void setNombreAlta(String nombreAlta) {
		this.nombreAlta = nombreAlta;
	}



}


/*package com.api_salud.api_salud.request;

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
    
}*/