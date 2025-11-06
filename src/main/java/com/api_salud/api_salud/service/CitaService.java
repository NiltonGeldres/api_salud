package com.api_salud.api_salud.service;


import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaResponse;

public interface CitaService {
	
	public CitaResponse citaDisponible(CitaRequest request);
//	public CitaResponse crearCita(CitaFacturacionRequest request);
	public int crearCita(CitaEntity c);	
	public  CitaResponse leerCita(int idCita);	
	public int  leerXIdProgramacionMedica(int  idProgramacionMedica);

}
