package com.api_salud.api_salud.entity;
/*
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity*/
public class TurnoEntity {
	
//	@Id
	int idTurno;
	String descripcion;
	String horaInicio;
	String horaFin;
	int totHoras;
	int idTipoServicio;
	String color;
	
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public int getTotHoras() {
		return totHoras;
	}
	public void setTotHoras(int totHoras) {
		this.totHoras = totHoras;
	}
	public int getIdTipoServicio() {
		return idTipoServicio;
	}
	public void setIdTipoServicio(int idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	
}
