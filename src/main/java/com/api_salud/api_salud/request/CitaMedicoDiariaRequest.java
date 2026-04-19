package com.api_salud.api_salud.request;

public class CitaMedicoDiariaRequest {
    private int idMedico;
    private String fecha; 

    public int getIdMedico() { return idMedico; }
    public void setIdMedico(int idMedico) { this.idMedico = idMedico; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}