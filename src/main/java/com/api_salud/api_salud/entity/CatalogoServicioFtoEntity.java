package com.api_salud.api_salud.entity;



public class CatalogoServicioFtoEntity {
	
    int	idFinanciamientoCatalogo;
    double precioUnitario;
    int	idProducto;
    int	idTipoFinanciamiento;
    boolean	activo;
    boolean	seUsaSinPrecio;
    
	public int getIdFinanciamientoCatalogo() {
		return idFinanciamientoCatalogo;
	}
	public void setIdFinanciamientoCatalogo(int idFinanciamientoCatalogo) {
		this.idFinanciamientoCatalogo = idFinanciamientoCatalogo;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
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
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public boolean isSeUsaSinPrecio() {
		return seUsaSinPrecio;
	}
	public void setSeUsaSinPrecio(boolean seUsaSinPrecio) {
		this.seUsaSinPrecio = seUsaSinPrecio;
	}
    
    
    

}
