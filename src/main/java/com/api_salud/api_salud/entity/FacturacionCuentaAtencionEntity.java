package com.api_salud.api_salud.entity;

import javax.validation.constraints.NotNull;


public class FacturacionCuentaAtencionEntity {
	
	
	@NotNull(message = "El dato idCuentaAtencion, no puede estar en blanco")
	int idCuentaAtencion;
	@NotNull(message = "El dato idPaciente, no puede estar en blanco")
	int idPaciente;
	@NotNull(message = "El dato fechaApertura, no puede estar en blanco")
	String fechaApertura;
	@NotNull(message = "El dato horaApertura, no puede estar en blanco")
	String horaApertura;
	@NotNull(message = "El dato fechaCierre, no puede estar en blanco")
	String fechaCierre;
	@NotNull(message = "El dato horaCierre, no puede estar en blanco")
	String horaCierre;
	@NotNull(message = "El dato  totalExonerado, no puede estar en blanco")
	double totalExonerado;
	@NotNull(message = "El dato totalAsegurado, no puede estar en blanco")
	double totalAsegurado;
	@NotNull(message = "El dato totalPagado, no puede estar en blanco")
	double totalPagado;
	@NotNull(message = "El dato idEstado, no puede estar en blanco")
	int idEstado;
	@NotNull(message = "El dato totalPorPagar, no puede estar en blanco")
	double totalPorPagar;
	@NotNull(message = "El dato idUsuarioCrea, no puede estar en blanco")
	int idUsuarioCrea;
	@NotNull(message = "El dato idUsuarioModifica, no puede estar en blanco")
	int idUsuarioModifica;
	@NotNull(message = "El dato fechaCreacion, no puede estar en blanco")
	String fechaCreacion;
	@NotNull(message = "El dato fechaModificacion, no puede estar en blanco")
	String fechaModificacion;
	
	
	public int getIdCuentaAtencion() {
		return idCuentaAtencion;
	}
	public void setIdCuentaAtencion(int idCuentaAtencion) {
		this.idCuentaAtencion = idCuentaAtencion;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getHoraApertura() {
		return horaApertura;
	}
	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getHoraCierre() {
		return horaCierre;
	}
	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}
	public double getTotalExonerado() {
		return totalExonerado;
	}
	public void setTotalExonerado(double totalExonerado) {
		this.totalExonerado = totalExonerado;
	}
	public double getTotalAsegurado() {
		return totalAsegurado;
	}
	public void setTotalAsegurado(double totalAsegurado) {
		this.totalAsegurado = totalAsegurado;
	}
	public double getTotalPagado() {
		return totalPagado;
	}
	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public double getTotalPorPagar() {
		return totalPorPagar;
	}
	public void setTotalPorPagar(double totalPorPagar) {
		this.totalPorPagar = totalPorPagar;
	}
	public int getIdUsuarioCrea() {
		return idUsuarioCrea;
	}
	public void setIdUsuarioCrea(int idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}
	public int getIdUsuarioModifica() {
		return idUsuarioModifica;
	}
	public void setIdUsuarioModifica(int idUsuarioModifica) {
		this.idUsuarioModifica = idUsuarioModifica;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
}
