package com.api_salud.api_salud.entity;

public class ProgramacionMedicaCabeceraEntity {
	
   String fechaReg ;
   int idProgramacionMedicaCabecera;
   int idMedico ;
   int idEspecialidad ;
   int idServicio;
   int idTurno ;
   String fecha;
   int idUsuario ;
   
   
	String nombreServicio;
	String color;
	int totHoras;
	String descripcionTurno;
	public String getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}
	public int getIdProgramacionMedicaCabecera() {
		return idProgramacionMedicaCabecera;
	}
	public void setIdProgramacionMedicaCabecera(int idProgramacionMedicaCabecera) {
		this.idProgramacionMedicaCabecera = idProgramacionMedicaCabecera;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getTotHoras() {
		return totHoras;
	}
	public void setTotHoras(int totHoras) {
		this.totHoras = totHoras;
	}
	public String getDescripcionTurno() {
		return descripcionTurno;
	}
	public void setDescripcionTurno(String descripcionTurno) {
		this.descripcionTurno = descripcionTurno;
	}   
   
	
}
