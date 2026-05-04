package com.api_salud.api_salud.repository;

import java.util.List;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaDiaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;


//import com.api_salud.api_salud.response.ProgramacionMedicaResponse ;

public interface ProgramacionMedicaDao {


	void crearProgramacionMasiva(int idUsuario, List<ProgramacionMedicaDiaResponse> programacion);
//	public  ProgramacionMedicaResponse programacionMedicoTodos(int idMedico, int idEspecialidad);
	public  ProgramacionMedicaResponse programacionMedicoFecha(int idMedico, String fecha, int idEspecialidad);
	public List<ProgramacionMedicaEntity>  programacionMedicoMesLeer(int mes, int ano, int idMedico) ;	
//	public int programacionMedicoCrear(ProgramacionMedicaEntity programacion ) ;
	public int   programacionMedicaActualizar(ProgramacionMedicaEntity programacion) ;
	public List<ProgramacionMedicaEntity>  leerXIdProgramacionMedicaCabecera(int  idProgramacionmedicaCabecera);
	public void eliminarXIdProgramacion(int  idProgramacionMedica);
	/*	
	public CitaResponse cuposDisponiblesDia(int idMedico, String fecha, int idEspecialidad);
	
	public List<String> crearCuposProgramadasDiaTodas(String fecha, String horaInicio,String horaFin) ;
	public List<String> cuposNoDisponibleDia(int idMedico, String fecha, int idEspecialidad);
//	public int leerXIdProgramacionMedica(int  idProgramacionMedica) ;
	public CitaResponse asignadas(int idMedico, String fecha, int idEspecialidad);
	*/
	
}
