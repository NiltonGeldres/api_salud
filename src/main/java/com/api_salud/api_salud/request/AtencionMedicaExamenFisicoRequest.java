package com.api_salud.api_salud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class AtencionMedicaExamenFisicoRequest {

    private Integer idExamenFisico;

    @NotBlank(message = "La descripción del examen físico es obligatoria.")
    @JsonProperty("nombreExamenFisico")
    private String nombreExamenFisico;

    @JsonProperty("nombreExamenFisico")
    public void setNombreExamenFisico(String nombreExamenFisico) {
        this.nombreExamenFisico = nombreExamenFisico;
    }

	public Integer getIdExamenFisico() {
		return idExamenFisico;
	}

	public void setIdExamenFisico(Integer idExamenFisico) {
		this.idExamenFisico = idExamenFisico;
	}

	public String getNombreExamenFisico() {
		return nombreExamenFisico;
	}
    
}

/*package com.api_salud.api_salud.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtencionMedicaExamenFisicoRequest {

    @NotBlank(message = "La descripción del examen físico es obligatoria.")
    @Size(max = 2000, message = "El texto del examen físico supera los 2000 caracteres.")
    private String descripcion;
    private int idExamenFisico;

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public int getIdExamenFisico() {
		return idExamenFisico;
	}
	public void setIdExamenFisico(int idExamenFisico) {
		this.idExamenFisico = idExamenFisico;
	}
    
    
}*/