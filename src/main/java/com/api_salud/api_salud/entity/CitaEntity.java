/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.api_salud.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ngeldres
 */
public class CitaEntity {

//	@NotNull(message = "El idMedico, nopuede estar en blanco")
    int	idCita;
    
	@NotEmpty
	@NotNull(message = "El dato fecha, no puede estar en blanco")
	String fecha;
	
	@NotEmpty
	@NotNull(message = "El dato idMedico, no puede estar en blanco")
    String horaInicio;
	
	@NotEmpty
	@NotNull(message = "El dato idMedico, no puede estar en blanco")
    String horaFin;
	
	@NotNull(message = "El dato idPaciente, no puede estar en blanco")
    int idPaciente;
	
	@NotNull(message = "El dato idEstadoCita, no puede estar en blanco")
    int idEstadoCita;
	
	@NotNull(message = "El dato idAtencion, no puede estar en blanco")
    int idAtencion;
	
	@NotNull(message = "El dato idMedico, no puede estar en blanco")
	int idMedico;
	
	@NotNull(message = "El dato idEspecialidad, no puede estar en blanco")
	int idEspecialidad;
	
	@NotNull(message = "El dato idServicio, no puede estar en blanco")
    int idServicio;
	
	@NotNull(message = "El dato idProgramacion no puede estar en blanco")
    int idProgramacion;
	
	@NotNull(message = "El dato idProducto, no puede estar en blanco")
    int idProducto ;
	
	@NotEmpty
	@NotNull(message = "El dato fechaSolicitud, no puede estar en blanco")
    String fechaSolicitud;
	
	@NotEmpty
	@NotNull(message = "El dato horaSolicitud, no puede estar en blanco")
    String horaSolicitud;
	
    boolean  esCitaAdicional;

	@NotEmpty
	@NotNull(message = "El dato esCitaAdicional, no puede estar en blanco")
    int idUsuario;

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
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

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public int getIdEstadoCita() {
		return idEstadoCita;
	}

	public void setIdEstadoCita(int idEstadoCita) {
		this.idEstadoCita = idEstadoCita;
	}

	public int getIdAtencion() {
		return idAtencion;
	}

	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
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

	public int getIdProgramacion() {
		return idProgramacion;
	}

	public void setIdProgramacion(int idProgramacion) {
		this.idProgramacion = idProgramacion;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public boolean isEsCitaAdicional() {
		return esCitaAdicional;
	}

	public void setEsCitaAdicional(boolean esCitaAdicional) {
		this.esCitaAdicional = esCitaAdicional;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	
    
}


/*
 	@NotEmpty
	@NotNull(message = "El dato servicio, no puede estar en blanco")
    String servicio;
	
	@NotEmpty
	@NotNull(message = "El dato medico, no puede estar en blanco")
    String medico ;
	
	@NotNull(message = "El dato idTipoServicio, no puede estar en blanco")
    int idTipoServicio;
	
	@NotNull(message = "El dato idTipoCondicionAlEstab, no puede estar en blanco")
    int idTipoCondicionAlEstab;
	
	@NotNull(message = "El dato idTipoEdad, no puede estar en blanco")
    int idTipoEdad    ;
	
	@NotNull(message = "El dato idOrigenAtencion, no puede estar en blanco")
    int idOrigenAtencion ;
	
	@NotNull(message = "El dato idTipoCondicionAlServicio, no puede estar en blanco")
    int idTipoCondicionAlServicio;
	
	@NotNull(message = "El dato idTipoFinanciamiento, no puede estar en blanco")
    int idTipoFinanciamiento;
	
	@NotEmpty
	@NotNull(message = "El dato usuario, no puede estar en blanco")
    String usuario;
	
	@NotEmpty
	@NotNull(message = "El dato numeroOperacion, no puede estar en blanco")
    String numeroOperacion;
	
	@NotEmpty
	@NotNull(message = "El dato numeroTelefono, no puede estar en blanco")
    String numeroTelefono;
	
	@NotEmpty
	@NotNull(message = "El dato EntidadDestino, no puede estar en blanco")
    String EntidadDestino;
	


 * */
 