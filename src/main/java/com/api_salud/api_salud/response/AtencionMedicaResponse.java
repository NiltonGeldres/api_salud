package com.api_salud.api_salud.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtencionMedicaResponse {

    private boolean exito;
    private String mensaje;
    private Long idAtencion; // ID retornado directamente por la función de Postgres
    private Integer idEstadoAtencion; // 2 = Borrador, 3 = Finalizado
    private String estadoFirma;
    private String rutaPdfFirmado;    
    private OffsetDateTime tsProcesamiento;

    // Constructor vacío requerido para serialización JSON
    public AtencionMedicaResponse() {}

    // Constructor de conveniencia para respuestas de éxito rápidas
    public AtencionMedicaResponse(boolean exito, String mensaje, Long idAtencion, Integer idEstadoAtencion, String estadoFirma) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.idAtencion = idAtencion;
        this.idEstadoAtencion = idEstadoAtencion;
        this.estadoFirma = estadoFirma;
        this.tsProcesamiento = OffsetDateTime.now();
    }

    // Getters y Setters
    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public Integer getIdEstadoAtencion() { return idEstadoAtencion; }
    public void setIdEstadoAtencion(Integer idEstadoAtencion) { this.idEstadoAtencion = idEstadoAtencion; }

    public String getEstadoFirma() { return estadoFirma; }
    public void setEstadoFirma(String estadoFirma) { this.estadoFirma = estadoFirma; }

    public OffsetDateTime getTsProcesamiento() { return tsProcesamiento; }
    public void setTsProcesamiento(OffsetDateTime tsProcesamiento) { this.tsProcesamiento = tsProcesamiento; }
    
    public String getRutaPdfFirmado() {
        return rutaPdfFirmado;
    }

    public void setRutaPdfFirmado(String rutaPdfFirmado) {
        this.rutaPdfFirmado = rutaPdfFirmado;
    }
    
}