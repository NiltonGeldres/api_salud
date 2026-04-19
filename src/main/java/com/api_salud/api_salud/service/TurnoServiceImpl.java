package com.api_salud.api_salud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.TurnoEntity;
import com.api_salud.api_salud.repository.TurnoDao;
import com.api_salud.api_salud.response.TurnoResponse;

@Service
public class TurnoServiceImpl  implements TurnoService{
	@Autowired
	private TurnoDao turnoDao  ;

	@Override
	public List<TurnoEntity> turnos() {
			return turnoDao.turnoLeer();
		
	}
	
	

}
