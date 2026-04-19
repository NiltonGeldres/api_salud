package com.api_salud.api_salud.request;

public class CitaSeparadaPagoVirtualRequest {
	
    int idCitaSeparadaPagoVirtual ;
    int idComprobantePago;
    int idCitaSeparada ;
    String fecha ;
    String nroOperacion ;
    String correo ;
    String celular ;
    Double monto ;
    int idTipoOperacion ;
    String origen ;
    String destino ;
    String entidadDestino ;
    String usuario;
    int idUsuario ;
    
	public int getIdCitaSeparadaPagoVirtual() {
		return idCitaSeparadaPagoVirtual;
	}
	public void setIdCitaSeparadaPagoVirtual(int idCitaSeparadaPagoVirtual) {
		this.idCitaSeparadaPagoVirtual = idCitaSeparadaPagoVirtual;
	}
	public int getIdComprobantePago() {
		return idComprobantePago;
	}
	public void setIdComprobantePago(int idComprobantePago) {
		this.idComprobantePago = idComprobantePago;
	}
	public int getIdCitaSeparada() {
		return idCitaSeparada;
	}
	public void setIdCitaSeparada(int idCitaSeparada) {
		this.idCitaSeparada = idCitaSeparada;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNroOperacion() {
		return nroOperacion;
	}
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public int getIdTipoOperacion() {
		return idTipoOperacion;
	}
	public void setIdTipoOperacion(int idTipoOperacion) {
		this.idTipoOperacion = idTipoOperacion;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getEntidadDestino() {
		return entidadDestino;
	}
	
	public void setEntidadDestino(String entidadDestino) {
		this.entidadDestino = entidadDestino;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
