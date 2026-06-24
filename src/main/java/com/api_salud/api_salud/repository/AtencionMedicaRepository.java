package com.api_salud.api_salud.repository;

public interface AtencionMedicaRepository {
    Long guardarAtencionMedicaCompleta(String jsonPayload);
    void actualizarRutaPdf(Long idAtencion, String rutaPdf);
    String obtenerJsonAtencionPorId(Long idAtencion); // 🆕
    void actualizarEstadoFirma(Long idAtencion, String estadoFirma); // 🆕    
}