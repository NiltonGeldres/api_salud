package com.api_salud.api_salud.request;

import javax.validation.constraints.Size;

public class AtencionMedicaAltaRequest {

    @Size(max = 2000, message = "La descripción de alta excede los 2000 caracteres.")
    private String descripcion;

    // --- GETTERS Y SETTERS ---
    public String getDescripcion() { 
        return SecuritySanitizer.sanitize(descripcion); 
    }
    
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }
}