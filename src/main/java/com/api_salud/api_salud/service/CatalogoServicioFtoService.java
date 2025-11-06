package com.api_salud.api_salud.service;

import com.api_salud.api_salud.request.CatalogoServicioFtoRequest;
import com.api_salud.api_salud.response.CatalogoServicioFtoResponse;

public interface CatalogoServicioFtoService {
	
	public CatalogoServicioFtoResponse leerCatalogoServiciosXIdTipoFinanciamiento(CatalogoServicioFtoRequest request);
	

}
