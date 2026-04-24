package com.api_salud.api_salud.dto;

public class MedicoDTO {
	
	int  idMedico;
	String nombres;
	String nombreEspecialidad ;	
	int preciounitario ;
	
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public int getPreciounitario() {
		return preciounitario;
	}
	public void setPreciounitario(int preciounitario) {
		this.preciounitario = preciounitario;
	}
	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}
	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}	
	
	
}
