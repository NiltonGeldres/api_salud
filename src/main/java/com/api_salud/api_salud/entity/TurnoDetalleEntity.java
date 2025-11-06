package com.api_salud.api_salud.entity;

public class TurnoDetalleEntity {
	int idTurnoDetalle;
	int idTurno;
	String horaInicio;
	String horaFin;
	int idEstado;
	int totHoras;
	public int getIdTurnoDetalle() {
		return idTurnoDetalle;
	}
	public void setIdTurnoDetalle(int idTurnoDetalle) {
		this.idTurnoDetalle = idTurnoDetalle;
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
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
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public int getTotHoras() {
		return totHoras;
	}
	public void setTotHoras(int totHoras) {
		this.totHoras = totHoras;
	}
	

}
