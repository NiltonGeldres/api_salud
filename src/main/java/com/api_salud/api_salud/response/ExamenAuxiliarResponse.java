package com.api_salud.api_salud.response;

public class ExamenAuxiliarResponse {
	private Long idProducto;
    private String nombre;
    private Integer cantidad;
    private String observacion;
    private Long idPuntoCarga;
    private Long idDiagnostico;
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Long getIdPuntoCarga() {
		return idPuntoCarga;
	}
	public void setIdPuntoCarga(Long idPuntoCarga) {
		this.idPuntoCarga = idPuntoCarga;
	}
	public Long getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(Long idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
    
    
}
