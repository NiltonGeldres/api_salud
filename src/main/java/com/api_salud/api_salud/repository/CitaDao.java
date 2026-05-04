package com.api_salud.api_salud.repository;

import java.util.List;

import com.api_salud.api_salud.dto.PacienteCitadoDto;
import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.response.CitaResponse;

public interface CitaDao {
	
	public List<PacienteCitadoDto> citaPacienteListarPendientes(int idPaciente, String fecha);
	public List<PacienteCitadoDto> citaMedicoListarDiaria(int idMedico, String fecha);	
	public CitaResponse citasDisponiblesDia(int idMedico, String fecha, int idEspecialidad);
	public int crearCita (CitaEntity citaEntity) ;
	public CitaResponse obtenerCitasAsignadasDia(int idMedico, String fecha, int idEspecialidad);
	public List<String> crearCuposProgramadasDiaTodas(String fecha, String horaInicio,String horaFin, int tiempoPromedioAtencion) ;
	public List<String> cuposNoDisponibleDia(int idMedico, String fecha, int idEspecialidad);
	public int leerXIdProgramacionMedica(int  idProgramacionMedica) ;
	
	
}
