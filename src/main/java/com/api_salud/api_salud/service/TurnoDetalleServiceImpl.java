package com.api_salud.api_salud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.TurnoDetalleEntity;
import com.api_salud.api_salud.repository.TurnoDetalleDao;
@Service 
public class TurnoDetalleServiceImpl implements TurnoDetalleService {

	@Autowired
	TurnoDetalleDao turnoDetalleDao ;
	
	@Override
	public List<TurnoDetalleEntity> xIdTurnoLeer(int idTurno) {
		return  turnoDetalleDao.xIdTurnoLeer(idTurno);
	}

}
