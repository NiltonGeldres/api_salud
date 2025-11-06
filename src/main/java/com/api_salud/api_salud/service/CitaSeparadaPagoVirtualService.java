package com.api_salud.api_salud.service;

import java.util.List;

import com.api_salud.api_salud.entity.CitaSeparadaPagadaEntity;
import com.api_salud.api_salud.request.CitaSeparadaPagoVirtualRequest;
import com.api_salud.api_salud.response.CitaSeparadaPagoVirtualResponse;

public interface  CitaSeparadaPagoVirtualService {
	
	public CitaSeparadaPagoVirtualResponse crearCitaSeparadaPagoVirtual(CitaSeparadaPagoVirtualRequest request ) ;
	
	public List<CitaSeparadaPagadaEntity> leerCitaSeparadaPagadaXMedico(int idMedico ) ;
	

}
