package com.api_salud.api_salud.response;

import com.api_salud.api_salud.dto.DocumentoAdjuntoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtencionMedicaResponse {

    private boolean exito;
    private String mensaje;
    private Long idAtencion; // ID retornado directamente por la función de Postgres
    private Integer idEstadoAtencion; // 2 = Borrador / Pendiente, 3 = Finalizado / Firmado
    private String estadoFirma;
    private Integer idPaciente; // 2 = Borrador / Pendiente, 3 = Finalizado / Firmado
    
    // Rutas relativas guardadas en BD
    private String rutaPdfBorrador;
    private String rutaPdfFirmado;
    
    // Presigned URLs temporizadas para el flujo de Firma Digital
    private String urlLecturaBorrador; // GET: Descargar borrador para firmar
    private String urlSubidaFirmado;   // PUT: Subir PDF ya firmado
    
    private OffsetDateTime tsProcesamiento;
    private String jsonEnriquecidoFirmado;
    private String hashIntegridad;

    private List<DocumentoAdjuntoDTO> documentos = new ArrayList<>();

    
    
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
    public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public void setExito(boolean exito) { this.exito = exito; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public Integer getIdEstadoAtencion() { return idEstadoAtencion; }
    public void setIdEstadoAtencion(Integer idEstadoAtencion) { this.idEstadoAtencion = idEstadoAtencion; }

    public String getEstadoFirma() { return estadoFirma; }
    public void setEstadoFirma(String estadoFirma) { this.estadoFirma = estadoFirma; }

    public String getRutaPdfBorrador() { return rutaPdfBorrador; }
    public void setRutaPdfBorrador(String rutaPdfBorrador) { this.rutaPdfBorrador = rutaPdfBorrador; }

    public String getRutaPdfFirmado() { return rutaPdfFirmado; }
    public void setRutaPdfFirmado(String rutaPdfFirmado) { this.rutaPdfFirmado = rutaPdfFirmado; }

    public String getUrlLecturaBorrador() { return urlLecturaBorrador; }
    public void setUrlLecturaBorrador(String urlLecturaBorrador) { this.urlLecturaBorrador = urlLecturaBorrador; }

    public String getUrlSubidaFirmado() { return urlSubidaFirmado; }
    public void setUrlSubidaFirmado(String urlSubidaFirmado) { this.urlSubidaFirmado = urlSubidaFirmado; }

    public OffsetDateTime getTsProcesamiento() { return tsProcesamiento; }
    public void setTsProcesamiento(OffsetDateTime tsProcesamiento) { this.tsProcesamiento = tsProcesamiento; }

    public String getJsonEnriquecidoFirmado() { return jsonEnriquecidoFirmado; }
    public void setJsonEnriquecidoFirmado(String jsonEnriquecidoFirmado) { this.jsonEnriquecidoFirmado = jsonEnriquecidoFirmado; }

    public String getHashIntegridad() { return hashIntegridad; }
    public void setHashIntegridad(String hashIntegridad) { this.hashIntegridad = hashIntegridad; }

	public List<DocumentoAdjuntoDTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoAdjuntoDTO> documentos) {
		this.documentos = documentos;
	}
    
    
    
}