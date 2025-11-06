package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.MedicoDao;
import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.MedicoResponse;

@Service
public class MedicoServiceImpl  implements MedicoService{

	@Autowired
	private MedicoDao medicoDao  ;		
	
	@Override
	public MedicoResponse medicoEspecialidad(MedicoRequest request) {
		MedicoResponse retorno = null ;
		int idEspecialidad = request.getIdEspecialidad();
		if(idEspecialidad>0) {
			retorno = medicoDao.medicoEspecialidad(idEspecialidad);
		}
		return retorno;
	}
	
	@Override
	public int  medicoXUsuarioLeer(String usuario) {
		return	 medicoDao.medicoXUsuarioLeer(usuario);
	}		

	@Override
	public int tiempoPromedioAtencion_leer(int idMedico, int idEspecialidad) {	
		return	 medicoDao.tiempoPromedioAtencion_leer(idMedico,idEspecialidad);
	}

	@Override
	public MedicoResponse medicoXIdMedicosXEspecialidad(int idEspecialidad, int idMedico) {
		  		
		return medicoDao.medicoXIdMedicosXEspecialidad(idEspecialidad, idMedico);
	}		

	
}
