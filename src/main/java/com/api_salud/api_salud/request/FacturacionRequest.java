package com.api_salud.api_salud.request;

import java.util.List;

import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;

public class FacturacionRequest {
	
	String fecha ;
	String hora;
	int idEspecialidad;
    int idServicio;
    
	//compartidas
	int idTipoFinanciamiento;
	int idUsuario;
	String fechaCreacion;
	String fechaDespacho;
	int idUsuarioAuditoria;
	
	//cuenta atencion
	int idCuentaAtencion;
	int idPaciente;
	String fechaModificacion;
	String fechaApertura;
	String horaApertura;
	String fechaCierre;
	String horaCierre;
    int idUsuarioModifica ;
    int idUsuarioCrea ;
	double totalAsegurado;
	double totalExonerado;
	double totalPorPagar;
	double totalPagado;
	int idEstado;

	
	
	FacturacionOrdenServicioEntity  fos = new FacturacionOrdenServicioEntity();
	int dOrden ;
	int idPuntoCarga;
	int idServicioPaciente;
	int idFuenteFinanciamiento;
	int idUsuarioDespacho;
	int idEstadoFacturacion;
	
	
	
	
//	int idUsuarioAuditoria ; 

	// comprobante pago
	String ruc;
	String razonSocial;
	int idComprobantePago;                   
	String nroSerie;
	String nroDocumento;
	String fechaCobranza;
	String tipoCambio;
	String observaciones;
	int idTipoComprobante;
	int idEstadoComprobante;
	int idGestionCaja;
	int idTipoPago;
	int idTipoOrden;
	Double dctos;
	int idCajero;
	int idTurno;
	int idCaja;
	int idFarmacia;
	double Exoneraciones;
	double adelantos;
	String dni;
	int idFormaPago;
	double subTotal;
	double igv;
	double total;
	
	List<FacturacionDetalleRequest> listFacturacionDetalle;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
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

	public int getIdTipoFinanciamiento() {
		return idTipoFinanciamiento;
	}

	public void setIdTipoFinanciamiento(int idTipoFinanciamiento) {
		this.idTipoFinanciamiento = idTipoFinanciamiento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaDespacho() {
		return fechaDespacho;
	}

	public void setFechaDespacho(String fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}

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

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
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

	public int getIdUsuarioModifica() {
		return idUsuarioModifica;
	}

	public void setIdUsuarioModifica(int idUsuarioModifica) {
		this.idUsuarioModifica = idUsuarioModifica;
	}

	public int getIdUsuarioCrea() {
		return idUsuarioCrea;
	}

	public void setIdUsuarioCrea(int idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public double getTotalAsegurado() {
		return totalAsegurado;
	}

	public void setTotalAsegurado(double totalAsegurado) {
		this.totalAsegurado = totalAsegurado;
	}

	public double getTotalExonerado() {
		return totalExonerado;
	}

	public void setTotalExonerado(double totalExonerado) {
		this.totalExonerado = totalExonerado;
	}

	public double getTotalPorPagar() {
		return totalPorPagar;
	}

	public void setTotalPorPagar(double totalPorPagar) {
		this.totalPorPagar = totalPorPagar;
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

	public FacturacionOrdenServicioEntity getFos() {
		return fos;
	}

	public void setFos(FacturacionOrdenServicioEntity fos) {
		this.fos = fos;
	}

	public int getdOrden() {
		return dOrden;
	}

	public void setdOrden(int dOrden) {
		this.dOrden = dOrden;
	}

	public int getIdPuntoCarga() {
		return idPuntoCarga;
	}

	public void setIdPuntoCarga(int idPuntoCarga) {
		this.idPuntoCarga = idPuntoCarga;
	}

	public int getIdServicioPaciente() {
		return idServicioPaciente;
	}

	public void setIdServicioPaciente(int idServicioPaciente) {
		this.idServicioPaciente = idServicioPaciente;
	}

	public int getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}

	public void setIdFuenteFinanciamiento(int idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public int getIdComprobantePago() {
		return idComprobantePago;
	}

	public void setIdComprobantePago(int idComprobantePago) {
		this.idComprobantePago = idComprobantePago;
	}

	public String getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getFechaCobranza() {
		return fechaCobranza;
	}

	public void setFechaCobranza(String fechaCobranza) {
		this.fechaCobranza = fechaCobranza;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getIdTipoComprobante() {
		return idTipoComprobante;
	}

	public void setIdTipoComprobante(int idTipoComprobante) {
		this.idTipoComprobante = idTipoComprobante;
	}

	public int getIdEstadoComprobante() {
		return idEstadoComprobante;
	}

	public void setIdEstadoComprobante(int idEstadoComprobante) {
		this.idEstadoComprobante = idEstadoComprobante;
	}

	public int getIdGestionCaja() {
		return idGestionCaja;
	}

	public void setIdGestionCaja(int idGestionCaja) {
		this.idGestionCaja = idGestionCaja;
	}

	public int getIdTipoPago() {
		return idTipoPago;
	}

	public void setIdTipoPago(int idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	public int getIdTipoOrden() {
		return idTipoOrden;
	}

	public void setIdTipoOrden(int idTipoOrden) {
		this.idTipoOrden = idTipoOrden;
	}

	public Double getDctos() {
		return dctos;
	}

	public void setDctos(Double dctos) {
		this.dctos = dctos;
	}

	public int getIdCajero() {
		return idCajero;
	}

	public void setIdCajero(int idCajero) {
		this.idCajero = idCajero;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public int getIdFarmacia() {
		return idFarmacia;
	}

	public void setIdFarmacia(int idFarmacia) {
		this.idFarmacia = idFarmacia;
	}

	public double getExoneraciones() {
		return Exoneraciones;
	}

	public void setExoneraciones(double exoneraciones) {
		Exoneraciones = exoneraciones;
	}

	public double getAdelantos() {
		return adelantos;
	}

	public void setAdelantos(double adelantos) {
		this.adelantos = adelantos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getIdFormaPago() {
		return idFormaPago;
	}

	public void setIdFormaPago(int idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<FacturacionDetalleRequest> getListFacturacionDetalle() {
		return listFacturacionDetalle;
	}

	public void setListFacturacionDetalle(List<FacturacionDetalleRequest> listFacturacionDetalle) {
		this.listFacturacionDetalle = listFacturacionDetalle;
	}

	public int getIdUsuarioAuditoria() {
		return idUsuarioAuditoria;
	}

	public void setIdUsuarioAuditoria(int idUsuarioAuditoria) {
		this.idUsuarioAuditoria = idUsuarioAuditoria;
	}

	
	
}



/*
@NotNull(message = "El dato FacturacionCuentaAtencionEntity, no puede estar en blanco")
FacturacionCuentaAtencionEntity  facturacionCuentaAtencionEntity;

@NotNull(message = "El dato FacturacionOrdenServicioEntity, no puede estar en blanco")
FacturacionOrdenServicioEntity  facturacionOrdenServicioEntity;

@NotNull(message = "El dato ListFacturacionServicioDespachoEntity, no puede estar en blanco")
List<FacturacionServicioDespachoEntity> listFacturacionServicioDespachoEntity;

@NotNull(message = "El dato FacturacionOrdenServicioPagosEntity, no puede estar en blanco")
FacturacionOrdenServicioPagoEntity facturacionOrdenServicioPagoEntity;

@NotNull(message = "El dato ListFacturacionServicioPagosEntity, no puede estar en blanco")
List<FacturacionServicioPagoEntity> listFacturacionServicioPagoEntity;

@NotNull(message = "El dato CajaComprobantePagoEntity, no puede estar en blanco")
CajaComprobantePagoEntity cajaComprobantePagoEntity;
*/


/*
@NotEmpty
@NotNull(message = "El dato fechaSolicitud, no puede estar en blanco")
String fechaSolicitud;
@NotEmpty
@NotNull(message = "El dato horaSolicitud, no puede estar en blanco")
String horaSolicitud;
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
*/
