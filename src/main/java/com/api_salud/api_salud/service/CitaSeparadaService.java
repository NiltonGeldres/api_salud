package com.api_salud.api_salud.service;


import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;

public interface CitaSeparadaService {

	public CitaSeparadaEntityResponse crearCitaSeparada(CitaSeparadaRequest request ) ;
	public CitaSeparadaResponse leerCitaSeparadaXIdPaciente(int idUsuario) ;	
	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualXIdPaciente(int idUsuario) ;	
	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualConcomprobanteXIdPaciente(int idUsuario) ;
	public CitaSeparadaEntityResponse leerCitaSeparadaXIdCitaSeparada(int idCitaSeparada );
	public int confirmarCitaSeparada(CitaFacturacionRequest  idCitaSeparada ) ;	
}
