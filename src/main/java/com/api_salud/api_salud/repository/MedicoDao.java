package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.response.MedicoResponse;

import java.util.List;

import com.api_salud.api_salud.dto.MedicoDTO;
public interface MedicoDao {
	
//	public MedicoResponse medicoEspecialidad(int idEspecialidad);	
	public List<MedicoDTO> medicoEspecialidad(int idEspecialidad);	
	public int  medicoXUsuarioLeer(String usuario) ;
	public int tiempoPromedioAtencion_leer(int idMedico, int idEspecialidad) ;	
	public MedicoResponse medicoXIdMedicosXEspecialidad(int idEspecialidad, int idMedico) ;		
}
