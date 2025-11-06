package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.ServicioDao;
import com.api_salud.api_salud.response.ServicioResponse;
@Service
public class ServicioServiceImpl  implements ServicioService {
@Autowired 
	ServicioDao servicioDao;
	
	@Override
	public ServicioResponse xIdEntidadEspecialidad(int idEntidadEspecialidad) {
		return servicioDao.xIdEntidadEspecialidad(idEntidadEspecialidad);
	}

	public ServicioResponse xIdEntidad(int idEntidad) {
		return servicioDao.xIdEntidad(idEntidad);
	}
}
