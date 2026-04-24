package com.api_salud.api_salud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.MedicoDao;
import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.MedicoResponse;
import com.api_salud.api_salud.dto.MedicoDTO;

@Service
public class MedicoServiceImpl  implements MedicoService{

	@Autowired
	private MedicoDao medicoDao  ;		

	
	@Override
	public MedicoResponse medicoEntidad(MedicoRequest request) {
	    // 1. Llamamos al DAO para traer los datos crudos (DTOs)
		int idEntidad = request.getIdEntidad();
	    List<MedicoDTO> listaMedicos = medicoDao.medicoEntidad(idEntidad);
	    
	    // 2. Armamos el MedicoResponse aquí
	    MedicoResponse response = new MedicoResponse();
	    response.setMedico(listaMedicos);
	    response.setTotalRecord(String.valueOf(listaMedicos.size()));
	    return response;		
	}
	
	
	@Override
	public MedicoResponse medicoEspecialidad(MedicoRequest request) {
/*		MedicoResponse retorno = null ;
		int idEspecialidad = request.getIdEspecialidad();
		if(idEspecialidad>0) {
			retorno = medicoDao.medicoEspecialidad(idEspecialidad);
		}
		return retorno;*/
		
	    // 1. Llamamos al DAO para traer los datos crudos (DTOs)
		int idEspecialidad = request.getIdEspecialidad();
	    List<MedicoDTO> listaMedicos = medicoDao.medicoEspecialidad(idEspecialidad);
	    
	    // 2. Armamos el MedicoResponse aquí
	    MedicoResponse response = new MedicoResponse();
	    response.setMedico(listaMedicos);
	    response.setTotalRecord(String.valueOf(listaMedicos.size()));
	    
	    return response;		
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
