package com.api_salud.api_salud.response;

public class AtencionMedicaTriajeResponse {
	private Long idTriaje;
    private String nombreTriaje;
    private String valorTriaje;
	public Long getIdTriaje() {
		return idTriaje;
	}
	public void setIdTriaje(Long idTriaje) {
		this.idTriaje = idTriaje;
	}

	public String getNombreTriaje() {
		return nombreTriaje;
	}
	public void setNombreTriaje(String nombreTriaje) {
		this.nombreTriaje = nombreTriaje;
	}
	public String getValorTriaje() {
		return valorTriaje;
	}
	public void setValorTriaje(String valorTriaje) {
		this.valorTriaje = valorTriaje;
	}
    

}
