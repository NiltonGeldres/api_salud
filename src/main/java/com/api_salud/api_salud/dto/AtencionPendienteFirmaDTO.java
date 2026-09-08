package com.api_salud.api_salud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AtencionPendienteFirmaDTO {
    private Long idAtencion;
    private Integer idPaciente;
    private String hc;
    private String nombrePaciente;
    private LocalDateTime fechaAtencion;
    private String nombreEspecialidad;
    private String nombreServicio;
    private String estadoFirma;
    private String rutaPdfFirmado;
    private String urlVisualizacion;
    private String hashFirmaDigital;
    private String nombreArchivo;

    public AtencionPendienteFirmaDTO() {}

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }

    public String getHc() { return hc; }
    public void setHc(String hc) { this.hc = hc; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public LocalDateTime getFechaAtencion() { return fechaAtencion; }
    public void setFechaAtencion(LocalDateTime fechaAtencion) { this.fechaAtencion = fechaAtencion; }

    public String getNombreEspecialidad() { return nombreEspecialidad; }
    public void setNombreEspecialidad(String nombreEspecialidad) { this.nombreEspecialidad = nombreEspecialidad; }

    public String getNombreServicio() { return nombreServicio; }
    public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }

    public String getEstadoFirma() { return estadoFirma; }
    public void setEstadoFirma(String estadoFirma) { this.estadoFirma = estadoFirma; }

    public String getRutaPdfFirmado() { return rutaPdfFirmado; }
    public void setRutaPdfFirmado(String rutaPdfFirmado) { this.rutaPdfFirmado = rutaPdfFirmado; }

    public String getUrlVisualizacion() { return urlVisualizacion; }
    public void setUrlVisualizacion(String urlVisualizacion) { this.urlVisualizacion = urlVisualizacion; }

    public String getHashFirmaDigital() { return hashFirmaDigital; }
    public void setHashFirmaDigital(String hashFirmaDigital) { this.hashFirmaDigital = hashFirmaDigital; }

    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
}