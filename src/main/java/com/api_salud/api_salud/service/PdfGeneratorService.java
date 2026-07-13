package com.api_salud.api_salud.service;

import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import com.api_salud.api_salud.request.AtencionMedicaRequest;

public interface PdfGeneratorService {
    
    byte[] generarPdfHistoriaClinica(
    		AtencionMedicaPdfDTO atencionDto    		
        /*Long idAtencion, 
        AtencionMedicaRequest request, 
        String nombrePaciente, 
        String numHistoriaClinica, 
        String nombreEspecialidad, 
        String nombreMedico*/
    );
    
}