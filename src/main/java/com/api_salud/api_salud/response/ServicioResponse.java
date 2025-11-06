package com.api_salud.api_salud.response;

import java.util.List;

import com.api_salud.api_salud.entity.ServicioEntity;

public class ServicioResponse {
	
	String totalRecord;
	List<ServicioEntity> servicio;
	
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<ServicioEntity> getServicio() {
		return servicio;
	}
	public void setServicio(List<ServicioEntity> servicio) {
		this.servicio = servicio;
	}

	
	
/*
	int idServicio ;
    String NombreServicio ;
    int idEspecialidad ;
    
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public String getNombreServicio() {
		return NombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		NombreServicio = nombreServicio;
	}
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
  */  
}
