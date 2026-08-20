package com.api_salud.api_salud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class AtencionMedicaSintomaRequest {

    private Integer idSintoma;

    @NotBlank(message = "La descripción del síntoma es obligatoria.")
    @JsonProperty("nombreSintoma")
    private String nombreSintoma;

    // Métodos alternativos para retrocompatibilidad con nombreSintoma
    @JsonProperty("nombreSintoma")
    public void setNombreSintoma(String nombreSintoma) {
        this.nombreSintoma = nombreSintoma;
    }

	public Integer getIdSintoma() {
		return idSintoma;
	}

	public void setIdSintoma(Integer idSintoma) {
		this.idSintoma = idSintoma;
	}

	public String getNombreSintoma() {
		return nombreSintoma;
	}


}

/*
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

*/