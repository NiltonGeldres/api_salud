package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.CatalogoServicioFtoDao;
import com.api_salud.api_salud.request.CatalogoServicioFtoRequest;
import com.api_salud.api_salud.response.CatalogoServicioFtoResponse;

@Service
public class CatalogoServicioFtoServiceImpl implements CatalogoServicioFtoService{
	@Autowired
	CatalogoServicioFtoDao catalogoServicioFto;
	
	
	@Override
	public CatalogoServicioFtoResponse leerCatalogoServiciosXIdTipoFinanciamiento(CatalogoServicioFtoRequest request) {
		
		return catalogoServicioFto.leerCatalogoServiciosXIdTipoFinanciamiento(request.getIdProducto(), request.getIdTipoFinanciamiento());
		
	}
	
	

}
