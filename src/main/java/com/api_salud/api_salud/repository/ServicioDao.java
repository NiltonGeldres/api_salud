package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.response.ServicioResponse;

public interface ServicioDao {
	public ServicioResponse xIdEntidadEspecialidad(int idEntidadEspecialidad)	;
	
	public ServicioResponse xIdEntidad(int idEntidad)	;
}
