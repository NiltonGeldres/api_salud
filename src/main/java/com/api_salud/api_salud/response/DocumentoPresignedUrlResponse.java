package com.api_salud.api_salud.response;

public class DocumentoPresignedUrlResponse {
	private String tipoDocumento;
    private String presignedUrl;

    public DocumentoPresignedUrlResponse() {}

    public DocumentoPresignedUrlResponse(String tipoDocumento, String presignedUrl) {
        this.tipoDocumento = tipoDocumento;
        this.presignedUrl = presignedUrl;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getPresignedUrl() {
        return presignedUrl;
    }

    public void setPresignedUrl(String presignedUrl) {
        this.presignedUrl = presignedUrl;
    }
}
