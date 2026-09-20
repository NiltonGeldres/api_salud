package com.api_salud.api_salud.request;

public class AtencionMedicaConfirmarFirmaRequest {

    private Long idAtencion;
    private String estadoFirma; // "FIRMADO"
    private String tipoFirma;   // "REFIRMA_PC" o "TOKEN"
    private String pdfRutaHistoria;
    private String pdfRutaReceta;
    private String pdfRutaOrdenes;
    private String pdfRutaIndicaciones;

    // 1. Constructor por defecto (necesario para la deserialización JSON en Spring/Jackson)
    public AtencionMedicaConfirmarFirmaRequest() {
    }

    // 2. Constructor parametrizado completo
    public AtencionMedicaConfirmarFirmaRequest(Long idAtencion, String estadoFirma, String tipoFirma,
                                               String pdfRutaHistoria, String pdfRutaReceta,
                                               String pdfRutaOrdenes, String pdfRutaIndicaciones) {
        this.idAtencion = idAtencion;
        this.estadoFirma = estadoFirma;
        this.tipoFirma = tipoFirma;
        this.pdfRutaHistoria = pdfRutaHistoria;
        this.pdfRutaReceta = pdfRutaReceta;
        this.pdfRutaOrdenes = pdfRutaOrdenes;
        this.pdfRutaIndicaciones = pdfRutaIndicaciones;
    }

    // 3. Getters y Setters
    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getEstadoFirma() {
        return estadoFirma;
    }

    public void setEstadoFirma(String estadoFirma) {
        this.estadoFirma = estadoFirma;
    }

    public String getTipoFirma() {
        return tipoFirma;
    }

    public void setTipoFirma(String tipoFirma) {
        this.tipoFirma = tipoFirma;
    }

    public String getPdfRutaHistoria() {
        return pdfRutaHistoria;
    }

    public void setPdfRutaHistoria(String pdfRutaHistoria) {
        this.pdfRutaHistoria = pdfRutaHistoria;
    }

    public String getPdfRutaReceta() {
        return pdfRutaReceta;
    }

    public void setPdfRutaReceta(String pdfRutaReceta) {
        this.pdfRutaReceta = pdfRutaReceta;
    }

    public String getPdfRutaOrdenes() {
        return pdfRutaOrdenes;
    }

    public void setPdfRutaOrdenes(String pdfRutaOrdenes) {
        this.pdfRutaOrdenes = pdfRutaOrdenes;
    }

    public String getPdfRutaIndicaciones() {
        return pdfRutaIndicaciones;
    }

    public void setPdfRutaIndicaciones(String pdfRutaIndicaciones) {
        this.pdfRutaIndicaciones = pdfRutaIndicaciones;
    }

    // 4. toString para logs y depuración
    @Override
    public String toString() {
        return "AtencionMedicaConfirmarFirmaRequest{" +
                "idAtencion=" + idAtencion +
                ", estadoFirma='" + estadoFirma + '\'' +
                ", tipoFirma='" + tipoFirma + '\'' +
                ", pdfRutaHistoria='" + pdfRutaHistoria + '\'' +
                ", pdfRutaReceta='" + pdfRutaReceta + '\'' +
                ", pdfRutaOrdenes='" + pdfRutaOrdenes + '\'' +
                ", pdfRutaIndicaciones='" + pdfRutaIndicaciones + '\'' +
                '}';
    }
}