package com.api_salud.api_salud.response;

public class TriajeResponse {
	private Long idTriaje;
    private String descripcion;
    private String valorTriaje;
	public Long getIdTriaje() {
		return idTriaje;
	}
	public void setIdTriaje(Long idTriaje) {
		this.idTriaje = idTriaje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValorTriaje() {
		return valorTriaje;
	}
	public void setValorTriaje(String valorTriaje) {
		this.valorTriaje = valorTriaje;
	}
    

}
