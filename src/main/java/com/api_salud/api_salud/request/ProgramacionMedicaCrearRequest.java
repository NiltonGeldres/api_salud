package com.api_salud.api_salud.request;

import java.util.List;

import com.api_salud.api_salud.response.ProgramacionMedicaDiaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;

public class ProgramacionMedicaCrearRequest {
	
	String fecha ;
	int idEspecialidad;
	int idMedico;
	int idServicio; 
	
	//List<ProgramacionMedicaCrearDetalleRequest> programacion ;
	List<ProgramacionMedicaDiaResponse> programacion ;
	String usuario;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public List<ProgramacionMedicaDiaResponse> getProgramacion() {
		return programacion;
	}
	public void setProgramacion(List<ProgramacionMedicaDiaResponse> programacion) {
		this.programacion = programacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	
		
	
}
