package com.api_salud.api_salud.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CitaRequest {
	

	@NotNull(message = "El dato idMedico, no puede estar en blanco")
	int idMedico;
		
	@NotEmpty
	@NotNull(message = "El dato fecha, no puede estar en blanco")
	String fecha;
	
	@NotNull(message = "El dato idEspecialidad, no puede estar en blanco")
	int idEspecialidad;
	
//	@NotNull(message = "El idMedico, nopuede estar en blanco")
    int	idCita;
	
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
	
	@NotEmpty
	@NotNull(message = "El dato esCitaAdicional, no puede estar en blanco")
    String esCitaAdicional;
	
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
	
	@NotNull(message = "El dato idProducto, no puede estar en blanco")
    int idProducto ;
	
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


	@NotEmpty
	double precioUnitario;
	@NotEmpty
	int idPuntoCarga;
	@NotEmpty
	int dTipoFinanciamiento;
	@NotEmpty
	int idFuenteFinanciamiento;
    int idUsuario ;
		
	
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

	public String getEsCitaAdicional() {
		return esCitaAdicional;
	}

	public void setEsCitaAdicional(String esCitaAdicional) {
		this.esCitaAdicional = esCitaAdicional;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public int getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(int idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	public int getIdTipoCondicionAlEstab() {
		return idTipoCondicionAlEstab;
	}

	public void setIdTipoCondicionAlEstab(int idTipoCondicionAlEstab) {
		this.idTipoCondicionAlEstab = idTipoCondicionAlEstab;
	}

	public int getIdTipoEdad() {
		return idTipoEdad;
	}

	public void setIdTipoEdad(int idTipoEdad) {
		this.idTipoEdad = idTipoEdad;
	}

	public int getIdOrigenAtencion() {
		return idOrigenAtencion;
	}

	public void setIdOrigenAtencion(int idOrigenAtencion) {
		this.idOrigenAtencion = idOrigenAtencion;
	}

	public int getIdTipoCondicionAlServicio() {
		return idTipoCondicionAlServicio;
	}

	public void setIdTipoCondicionAlServicio(int idTipoCondicionAlServicio) {
		this.idTipoCondicionAlServicio = idTipoCondicionAlServicio;
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

	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getEntidadDestino() {
		return EntidadDestino;
	}

	public void setEntidadDestino(String entidadDestino) {
		EntidadDestino = entidadDestino;
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

	
	
}
