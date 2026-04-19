package com.api_salud.api_salud.response;

import java.util.List;

import com.api_salud.api_salud.entity.CitaBloqueadaEntity;


public class CitaBloqueadaResponse {

	String totalRecord;
	List<CitaBloqueadaEntity> citaBloqueada;
    int	idCitaBloqueada;
    int	idUsuario;
    String	Usuario;
    String fecha;
    String horaInicio;
    String horaFin;
    String fechaBloqueo;
    String horaBloqueo;
    int  idMedico;	
	
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<CitaBloqueadaEntity> getCitaBloqueada() {
		return citaBloqueada;
	}
	public void setCitaBloqueada(List<CitaBloqueadaEntity> citaBloqueada) {
		this.citaBloqueada = citaBloqueada;
	}
	public int getIdCitaBloqueada() {
		return idCitaBloqueada;
	}
	public void setIdCitaBloqueada(int idCitaBloqueada) {
		this.idCitaBloqueada = idCitaBloqueada;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	public String getFechaBloqueo() {
		return fechaBloqueo;
	}
	public void setFechaBloqueo(String fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}
	public String getHoraBloqueo() {
		return horaBloqueo;
	}
	public void setHoraBloqueo(String horaBloqueo) {
		this.horaBloqueo = horaBloqueo;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
}
