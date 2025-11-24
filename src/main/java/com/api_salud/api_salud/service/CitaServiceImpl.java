package com.api_salud.api_salud.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.repository.CitaDao;

import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaResponse;

@Service
public class CitaServiceImpl  implements  CitaService{
	@Autowired
	private CitaDao citaDao;
/*
	@Override
	public CitaResponse citaDisponible(CitaRequest request) {
		
		return citaDao.citasDisponiblesDia(
			request.getIdMedico(),
	        request.getFecha(),
	        request.getIdEspecialidad()
        );
	}
*/
	@Override
	public int crearCita(CitaEntity c) { 
			//CitaResponse response = new CitaResponse(); 
		return citaDao.crearCita(c);
	}

	@Override
	public CitaResponse leerCita(int idCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int leerXIdProgramacionMedica(int idProgramacionMedica) {
		return citaDao.leerXIdProgramacionMedica(idProgramacionMedica);
	}
	
}
