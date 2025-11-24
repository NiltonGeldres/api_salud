package com.api_salud.api_salud.service;

import java.util.List;

import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaCrearRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaMesResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;

public interface ProgramacionMedicaService {

	public ProgramacionMedicaResponse medicoTodos(ProgramacionMedicaRequest request) ;  
	
	public ProgramacionMedicaResponse medicoFecha(ProgramacionMedicaRequest request) ;  

	public  ProgramacionMedicaMesResponse programacionMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) ;

	public ProgramacionMedicaMesResponse  programacionMedicoMesBlancoLeer(int mes, int ano, int idMedico,int idEspecialidad) ;
	
	public ProgramacionMedicaMesResponse  programacionMedicoCrear(ProgramacionMedicaCrearRequest request ,int idUsuario) ;
	
	public ProgramacionMedicaMesResponse  programacionMedicaActualizar(ProgramacionMedicaCrearRequest request ,int idUsuario) ;

	public CitaResponse citaDisponible(CitaRequest request);
	
}
