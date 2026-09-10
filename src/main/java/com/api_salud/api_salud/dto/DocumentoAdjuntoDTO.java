package com.api_salud.api_salud.dto;

public class DocumentoAdjuntoDTO {
    private String tipoDocumento;     // "historia", "receta", "orden", "indicaciones"
    private String rutaBorrador;
    private String rutaFirmado;
    private String urlLecturaBorrador; // GET
    private String urlSubidaFirmado;   // PUT

    public DocumentoAdjuntoDTO(String tipoDocumento, String rutaBorrador, String rutaFirmado, String urlLecturaBorrador, String urlSubidaFirmado) {
        this.tipoDocumento = tipoDocumento;
        this.rutaBorrador = rutaBorrador;
        this.rutaFirmado = rutaFirmado;
        this.urlLecturaBorrador = urlLecturaBorrador;
        this.urlSubidaFirmado = urlSubidaFirmado;
    }

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getRutaBorrador() {
		return rutaBorrador;
	}

	public void setRutaBorrador(String rutaBorrador) {
		this.rutaBorrador = rutaBorrador;
	}

	public String getRutaFirmado() {
		return rutaFirmado;
	}

	public void setRutaFirmado(String rutaFirmado) {
		this.rutaFirmado = rutaFirmado;
	}

	public String getUrlLecturaBorrador() {
		return urlLecturaBorrador;
	}

	public void setUrlLecturaBorrador(String urlLecturaBorrador) {
		this.urlLecturaBorrador = urlLecturaBorrador;
	}

	public String getUrlSubidaFirmado() {
		return urlSubidaFirmado;
	}

	public void setUrlSubidaFirmado(String urlSubidaFirmado) {
		this.urlSubidaFirmado = urlSubidaFirmado;
	}

 
}