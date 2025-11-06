package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.EspecialidadDao;
import com.api_salud.api_salud.response.EspecialidadResponse;

@Service
public class EspecialidadServiceImpl  implements EspecialidadService {
	@Autowired
	private EspecialidadDao especialidadDao  ;

	
	@Override
	public EspecialidadResponse web() {
		return especialidadDao.web();
	}
	

	@Override
	public EspecialidadResponse web1() {
//		return especialidadRepo.web();
		return null;
	}


	@Override
	public EspecialidadResponse xIdMedico(int idMedico) {
		return especialidadDao.xIdMedico(idMedico);
	}

	

}
