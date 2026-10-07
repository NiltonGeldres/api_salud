package com.api_salud.api_salud.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DocumentoPresignedUrlRequest {
	@NotNull(message = "El idAtencion es obligatorio")
    private Long idAtencion;

    @NotEmpty(message = "El tipoDocumento es obligatorio")
    private String tipoDocumento; // Ej: RECETA, SOLICITUD_EXAMEN, INFORME_MEDICO, CERTIFICADO

    public DocumentoPresignedUrlRequest() {}

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
