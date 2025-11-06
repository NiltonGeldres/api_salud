package com.api_salud.api_salud.repository;

import java.util.List;

import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.response.CitaResponse;

public interface CitaDao {
	
	public CitaResponse citasDisponiblesDia(int idMedico, String fecha, int idEspecialidad);
	public int crearCita (CitaEntity citaEntity) ;
	public CitaResponse asignadas(int idMedico, String fecha, int idEspecialidad);
	public List<String> crearCitasProgramadasDiaTodas(String fecha, String horaInicio,String horaFin) ;
	public List<String> citasNoDisponibleDia(int idMedico, String fecha, int idEspecialidad);
	public int leerXIdProgramacionMedica(int  idProgramacionMedica) ;
	
	
}
