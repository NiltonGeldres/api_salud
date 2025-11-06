package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.response.MedicoResponse;

public interface MedicoDao {
	
	public MedicoResponse medicoEspecialidad(int idEspecialidad);	
	public int  medicoXUsuarioLeer(String usuario) ;
	public int tiempoPromedioAtencion_leer(int idMedico, int idEspecialidad) ;	
	public MedicoResponse medicoXIdMedicosXEspecialidad(int idEspecialidad, int idMedico) ;		
}
