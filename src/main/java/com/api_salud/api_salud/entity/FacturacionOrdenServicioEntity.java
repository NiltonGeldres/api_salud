/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.api_salud.entity;


import javax.validation.constraints.NotNull;

/**
 *
 * @author ngeldres
 */

//@Entity
//@Table(name = "igm_facturacion.factordenservicio")
public class FacturacionOrdenServicioEntity {
	
	
  //  @Column(name="idorden")
	@NotNull(message = "El dato idOrden, no puede estar en blanco")
    int idOrden ;

  //  @Column(name="idpuntocarga")
	@NotNull(message = "El dato idPuntoCarga, no puede estar en blanco")
    int idPuntoCarga;
    
 //   @Column(name="idpaciente")
	@NotNull(message = "El dato idPaciente;, no puede estar en blanco")
    int idPaciente;
    
 //   @Column(name="idcuentaatencion")
	@NotNull(message = "El dato idCuentaAtencion, no puede estar en blanco")
    int idCuentaAtencion;
    
  //  @Column(name="idserviciopaciente")
	@NotNull(message = "El dato idServicioPaciente, no puede estar en blanco")
    int idServicioPaciente;
    
 //   @Column(name="idtipofinanciamiento")
	@NotNull(message = "El dato idTipoFinanciamiento, no puede estar en blanco")
    int idTipoFinanciamiento;
    
  //  @Column(name="idfuentefinanciamiento")
	@NotNull(message = "El dato idFuenteFinanciamiento, no puede estar en blanco")
    int idFuenteFinanciamiento;
    
 //   @Column(name="fechacreacion")
	@NotNull(message = "El dato FechaCreacion, no puede estar en blanco")
    String FechaCreacion;
    
  //  @Column(name="idusuario")
	@NotNull(message = "El dato idUsuario, no puede estar en blanco")
    int idUsuario;
    
  //  @Column(name="fechadespacho")
	@NotNull(message = "El dato FechaDespacho, no puede estar en blanco")
    String FechaDespacho;
    
  //  @Column(name="idusuariodespacho")
	@NotNull(message = "El dato idUsuarioDespacho, no puede estar en blanco")
    int idUsuarioDespacho;
    
 //   @Column(name="idestadofacturacion")
	@NotNull(message = "El dato idEstadoFacturacion, no puede estar en blanco")
    int idEstadoFacturacion;
    
 //   @Column(name="fechahorarealizacpt")
	@NotNull(message = "El dato FechaHoraRealizaCpt, no puede estar en blanco")
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


/*
int idorden ;
int idpuntocarga ;
int idpaciente ;
int idcuentaatencion ;
int idserviciopaciente ;
int idtipofinanciamiento ;
int idfuentefinanciamiento ;
String fechacreacion ;
int idusuario ;
String  fechadespacho ;
int idusuariodespacho ;
int idestadofacturacion ;
String fechahorarealizacpt ;
*/