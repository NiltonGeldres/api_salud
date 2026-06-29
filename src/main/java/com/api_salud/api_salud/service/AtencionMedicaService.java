package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;

public interface AtencionMedicaService {

    /**
     * Orquesta el flujo de registro o actualización de una atención médica completa.
     * Convierte el árbol del Request a formato JSONB y delega la lógica transaccional 
     * directamente al motor de la base de datos PostgreSQL.
     *
     * @param request Payload estructurado con la cabecera y el detalle de la consulta médica.
     * @return AtencionMedicaResponse Metadatos de éxito y el ID definitivo de la atención.
     */
    AtencionMedicaResponse guardarAtencionMedica(AtencionMedicaRequest request);
    AtencionMedicaResponse firmarYGenerarPdf(Long idAtencion);
}