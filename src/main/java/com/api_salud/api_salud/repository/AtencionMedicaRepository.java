package com.api_salud.api_salud.repository;

public interface AtencionMedicaRepository {
    Long guardarAtencionMedicaCompleta(String jsonPayload);
    
    // 🎯 Agrega este método para actualizar la ruta del PDF
    void actualizarRutaPdf(Long idAtencion, String rutaPdf);
}