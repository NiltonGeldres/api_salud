package com.api_salud.api_salud.request;

import javax.validation.constraints.NotNull;

public class FacturacionOrdenServicioRequest {

    int idOrden ;

    @NotNull(message = "El Id Punto de Carga, nopuede estar en blanco")
    int idPuntoCarga;
    @NotNull(message = "El Id del Paciente, no puede estar en blanco")
    int idPaciente;
    int idCuentaAtencion;
    int idServicioPaciente;
    int idTipoFinanciamiento;
    int idFuenteFinanciamiento;
    String FechaCreacion;
    int idUsuario;
    String FechaDespacho;
    int idUsuarioDespacho;
    int idEstadoFacturacion;
    String FechaHoraRealizaCpt;
    
    
	public int getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}
	public int getIdPuntoCarga() {
		return idPuntoCarga;
	}
	public void setIdPuntoCarga(int idPuntoCarga) {
		this.idPuntoCarga = idPuntoCarga;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdCuentaAtencion() {
		return idCuentaAtencion;
	}
	public void setIdCuentaAtencion(int idCuentaAtencion) {
		this.idCuentaAtencion = idCuentaAtencion;
	}
	public int getIdServicioPaciente() {
		return idServicioPaciente;
	}
	public void setIdServicioPaciente(int idServicioPaciente) {
		this.idServicioPaciente = idServicioPaciente;
	}
	public int getIdTipoFinanciamiento() {
		return idTipoFinanciamiento;
	}
	public void setIdTipoFinanciamiento(int idTipoFinanciamiento) {
		this.idTipoFinanciamiento = idTipoFinanciamiento;
	}
	public int getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}
	public void setIdFuenteFinanciamiento(int idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
	}
	public String getFechaCreacion() {
		return FechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getFechaDespacho() {
		return FechaDespacho;
	}
	public void setFechaDespacho(String fechaDespacho) {
		FechaDespacho = fechaDespacho;
	}
	public int getIdUsuarioDespacho() {
		return idUsuarioDespacho;
	}
	public void setIdUsuarioDespacho(int idUsuarioDespacho) {
		this.idUsuarioDespacho = idUsuarioDespacho;
	}
	public int getIdEstadoFacturacion() {
		return idEstadoFacturacion;
	}
	public void setIdEstadoFacturacion(int idEstadoFacturacion) {
		this.idEstadoFacturacion = idEstadoFacturacion;
	}
	public String getFechaHoraRealizaCpt() {
		return FechaHoraRealizaCpt;
	}
	public void setFechaHoraRealizaCpt(String fechaHoraRealizaCpt) {
		FechaHoraRealizaCpt = fechaHoraRealizaCpt;
	}    

    

    
    
}

