package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.entity.CatalogoServicioFtoEntity;
import com.api_salud.api_salud.response.CatalogoServicioFtoResponse;

public interface  CatalogoServicioFtoDao {

	public CatalogoServicioFtoResponse leerCatalogoServiciosXIdTipoFinanciamiento(int idProducto,int idTipoFinanciamiento);
	
}
