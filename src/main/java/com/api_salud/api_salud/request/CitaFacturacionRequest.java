package com.api_salud.api_salud.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CitaFacturacionRequest {

	
	@NotNull(message = "El dato idCitaSeparada, no puede estar en blanco")
	int idCitaSeparada;
	
	@NotNull(message = "El dato idMedico, no puede estar en blanco")
	int idMedico;
		
	@NotEmpty
	@NotNull(message = "El dato fecha, no puede estar en blanco")
	String fecha;
	
	@NotNull(message = "El dato idEspecialidad, no puede estar en blanco")
	int idEspecialidad;
	
    int	idCita;
	
	@NotEmpty
	@NotNull(message = "El dato idMedico, no puede estar en blanco")
    String horaInicio;
	
	@NotEmpty
	@NotNull(message = "El dato idMedico, no puede estar en blanco")
    String horaFin;
	
	@NotNull(message = "El dato idPaciente, no puede estar en blanco")
    int idPaciente;
	
	@NotNull(message = "El dato idServicio, no puede estar en blanco")
    int idServicio;
	
	@NotNull(message = "El dato idProgramacion no puede estar en blanco")
    int idProgramacion;
	
	@NotEmpty
	@NotNull(message = "El dato fechaSolicitud, no puede estar en blanco")
    String fechaSolicitud;
	
	@NotEmpty
	@NotNull(message = "El dato horaSolicitud, no puede estar en blanco")
    String horaSolicitud;
	
	@NotNull(message = "El dato idProducto, no puede estar en blanco")
    int idProducto ;
	
	@NotNull(message = "El dato idTipoFinanciamiento, no puede estar en blanco")
    int idTipoFinanciamiento;
	
	@NotEmpty
	@NotNull(message = "El dato usuario, no puede estar en blanco")
    String usuario;


	@NotEmpty
	@NotNull(message = "El dato precioUnitrio, no puede estar en blanco")
	double precioUnitario;
	@NotEmpty
	@NotNull(message = "El dato idPuntoCarga, no puede estar en blanco")
	int idPuntoCarga;
	@NotEmpty
	@NotNull(message = "El dato idTipoFinanciamiento, no puede estar en blanco")
	int dTipoFinanciamiento;
	@NotEmpty
	@NotNull(message = "El dato idFuenteFinanciamiento, no puede estar en blanco")
	int idFuenteFinanciamiento;

	@NotEmpty
	@NotNull(message = "El dato idUsuario, no puede estar en blanco")
    int idUsuario ;
    
    int idMedicoEspecialdiad ;
    
    
	public int getIdCitaSeparada() {
		return idCitaSeparada;
	}
	public void setIdCitaSeparada(int idCitaSeparada) {
		this.idCitaSeparada = idCitaSeparada;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
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
	public int getIdCita() {
		return idCita;
	}
	public void setIdCita(int idCita) {
		this.idCita = idCita;
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
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public int getIdProgramacion() {
		return idProgramacion;
	}
	public void setIdProgramacion(int idProgramacion) {
		this.idProgramacion = idProgramacion;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getHoraSolicitud() {
		return horaSolicitud;
	}
	public void setHoraSolicitud(String horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getIdTipoFinanciamiento() {
		return idTipoFinanciamiento;
	}
	public void setIdTipoFinanciamiento(int idTipoFinanciamiento) {
		this.idTipoFinanciamiento = idTipoFinanciamiento;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getIdPuntoCarga() {
		return idPuntoCarga;
	}
	public void setIdPuntoCarga(int idPuntoCarga) {
		this.idPuntoCarga = idPuntoCarga;
	}
	public int getdTipoFinanciamiento() {
		return dTipoFinanciamiento;
	}
	public void setdTipoFinanciamiento(int dTipoFinanciamiento) {
		this.dTipoFinanciamiento = dTipoFinanciamiento;
	}
	public int getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}
	public void setIdFuenteFinanciamiento(int idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdMedicoEspecialdiad() {
		return idMedicoEspecialdiad;
	}
	public void setIdMedicoEspecialdiad(int idMedicoEspecialdiad) {
		this.idMedicoEspecialdiad = idMedicoEspecialdiad;
	}
		
	
}
