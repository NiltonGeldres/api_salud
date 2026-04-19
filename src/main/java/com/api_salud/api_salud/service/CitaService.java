package com.api_salud.api_salud.service;


import java.util.List;

import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaMedicoDiariaRequest;
import com.api_salud.api_salud.request.CitaPacientePendientesRequest;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.PacienteCitadoResponse;

public interface CitaService {
	public List<PacienteCitadoResponse> citaPacienteListarPendientes(CitaPacientePendientesRequest request);
	public List<PacienteCitadoResponse> citaMedicoListarDiaria(CitaMedicoDiariaRequest request);
	public CitaResponse citaDisponible(CitaRequest request);
	public int crearCita(CitaEntity c);	
	public  CitaResponse leerCita(int idCita);	
	public int  leerXIdProgramacionMedica(int  idProgramacionMedica);

}


