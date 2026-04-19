package com.api_salud.api_salud.entity;



public class EspecialidadEntity {
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	
    int idEspecialidad ;
    String descripcionEspecialidad;
    int tiempoPromedioAtencion ;
    
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public String getDescripcionEspecialidad() {
		return descripcionEspecialidad;
	}
	public void setDescripcionEspecialidad(String descripcionEspecialidad) {
		this.descripcionEspecialidad = descripcionEspecialidad;
	}
	public int getTiempoPromedioAtencion() {
		return tiempoPromedioAtencion;
	}
	public void setTiempoPromedioAtencion(int tiempoPromedioAtencion) {
		this.tiempoPromedioAtencion = tiempoPromedioAtencion;
	}

  
	    
}
