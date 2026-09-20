package com.api_salud.api_salud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentoAdjuntoDTO {

    // Tipos válidos: "historia", "receta", "orden", "indicaciones"
    private String tipoDocumento;     
    private String rutaBorrador;
    private String rutaFirmado;
    private String urlLecturaBorrador; // Presigned GET para descarga/visores
    private String urlSubidaFirmado;   // Presigned PUT para cargar el PDF firmado
    private String urlLecturaFirmado;
    private String urlLectura;
    
    // Constructor vacío requerido por Jackson
    public DocumentoAdjuntoDTO() {}

    public DocumentoAdjuntoDTO(String tipoDocumento, String rutaBorrador, String rutaFirmado, 
                               String urlLecturaBorrador, String urlSubidaFirmado) {
        this.tipoDocumento = tipoDocumento;
        this.rutaBorrador = rutaBorrador;
        this.rutaFirmado = rutaFirmado;
        this.urlLecturaBorrador = urlLecturaBorrador;
        this.urlSubidaFirmado = urlSubidaFirmado;
    }

    // --- GETTERS Y SETTERS ---

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

	public String getUrlLecturaFirmado() {
		return urlLecturaFirmado;
	}

	public void setUrlLecturaFirmado(String urlLecturaFirmado) {
		this.urlLecturaFirmado = urlLecturaFirmado;
	}

	public String getUrlLectura() {
		return urlLectura;
	}

	public void setUrlLectura(String urlLectura) {
		this.urlLectura = urlLectura;
	}

	
}

