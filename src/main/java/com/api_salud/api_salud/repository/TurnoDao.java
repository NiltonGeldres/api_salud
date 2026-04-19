package com.api_salud.api_salud.repository;

import java.util.List;

import com.api_salud.api_salud.entity.TurnoEntity;
import com.api_salud.api_salud.response.TurnoResponse;

public interface  TurnoDao {
//	public TurnoResponse turno();
	public List<TurnoEntity> turnoLeer() ;	
	
}
