package com.api_salud.api_salud.request;

public class CitaBloqueadaRequest {
	
	int idCitaBloqueada;
    int idUsuario;
    String fecha;
    String horaInicio;
    String horaFin;
    String fechaBloqueo;
    String horaBloqueo;
    int idMedico;
    String  tipoUsuario;
    String  usuario;
    int idEntidad ;
    
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
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}
    
    
	
}
