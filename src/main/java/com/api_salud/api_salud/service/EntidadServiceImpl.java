package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.EntidadDao;
import com.api_salud.api_salud.response.EntidadResponse;

@Service
public class EntidadServiceImpl implements EntidadService {
	@Autowired
	EntidadDao entidadDao ;
	
	@Override
	public EntidadResponse xIdMedico(int idMedico) {
		return entidadDao.xIdMedico(idMedico);
	}

}
