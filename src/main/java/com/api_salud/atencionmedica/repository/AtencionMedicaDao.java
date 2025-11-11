package com.api_salud.atencionmedica.repository;

import com.api_salud.api_salud.response.CitaResponse;

public interface AtencionMedicaDao {
	
	public CitaResponse asignadasJdbcTemplates(int idMedico, String fecha, int idEspecialidad);	

}
