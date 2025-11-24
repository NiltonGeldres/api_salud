// Archivo: com.api_salud.atencionmedica.service.AtencionMedicaService.java
package com.api_salud.atencionmedica.service;

import com.api_salud.atencionmedica.domain.AtencionMedicaModel;

public interface AtencionMedicaService {
    
    /**
     * Procesa la solicitud de creación de una Atención Médica completa.
     * Recibe el Modelo de Dominio, lo mapea a Entidad y delega la persistencia.
     * * @param model El Modelo de Dominio con la cabecera y los detalles.
     * @return El ID de la Atención Médica (id_atencion) recién creado.
     */
    Long crearAtencionMedicaCompleta(AtencionMedicaModel model);
}
