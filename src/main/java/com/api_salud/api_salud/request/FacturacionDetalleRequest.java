package com.api_salud.api_salud.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FacturacionDetalleRequest {

	@NotNull(message = "El dato idProducto, no puede estar en blanco")
    int idProducto ;
	@NotEmpty
	double precioUnitario;
	@NotEmpty
	int cantidad;
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
}
