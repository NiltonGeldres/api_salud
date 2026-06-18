package com.api_salud.api_salud.service;



import com.api_salud.api_salud.request.AtencionMedicaRequest;

public interface PdfGeneratorService {

    /**
     * Genera un arreglo de bytes del PDF de la Historia Clínica basándose en una plantilla HTML.
     */
    byte[] generarPdfHistoriaClinica(Long idAtencion, AtencionMedicaRequest request);

    /**
     * Recupera la rúbrica del médico desde la ruta física y la estampa visualmente en el PDF.
     */
    byte[] estamparRubricaMedico(byte[] pdfBytes, Integer idMedico);
}