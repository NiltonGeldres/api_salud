package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.CitaBloqueadaRequest;
import com.api_salud.api_salud.response.CitaBloqueadaResponse;
import com.api_salud.api_salud.response.CitaResponse;


public interface CitaBloqueadaService {
	
	public CitaBloqueadaResponse crearCitaBloqueada(CitaBloqueadaRequest request);

	public int eliminarCitaBloqueada(CitaBloqueadaRequest request);

	public int eliminarCitaBloqueadaXUsuario(CitaBloqueadaRequest request);

	
}
