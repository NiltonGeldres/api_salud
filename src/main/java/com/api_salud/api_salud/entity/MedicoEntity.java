package com.api_salud.api_salud.entity;

public class MedicoEntity {
	
	String idMedico;
	String nombres;
	int preciounitario ;
	
	public String getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(String idMedico) {
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

}
