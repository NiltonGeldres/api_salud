package com.api_salud.api_salud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class AtencionMedicaAntecedenteRequest {

    private Integer idAntecedente;

    @NotBlank(message = "La descripción del antecedente es obligatoria.")
    @JsonProperty("nombreAntecedente")
    private String nombreAntecedente;

    @JsonProperty("nombreAntecedente")
    public void setNombreAntecedente(String nombreAntecedente) {
        this.nombreAntecedente = nombreAntecedente;
    }

	public Integer getIdAntecedente() {
		return idAntecedente;
	}

	public void setIdAntecedente(Integer idAntecedente) {
		this.idAntecedente = idAntecedente;
	}

	public String getNombreAntecedente() {
		return nombreAntecedente;
	}

}

/*
package com.api_salud.api_salud.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtencionMedicaAntecedenteRequest {

    @NotBlank(message = "La descripción del antecedente es obligatoria.")
    @Size(max = 2000, message = "El texto del antecedente supera los 2000 caracteres.")
    private String descripcion;
    private int idAntecedente ;

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public int getIdAntecedente() {
		return idAntecedente;
	}
	public void setIdAntecedente(int idAntecedente) {
		this.idAntecedente = idAntecedente;
	}
    
    
    
}*/