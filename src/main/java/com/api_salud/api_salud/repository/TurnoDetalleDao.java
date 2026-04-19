package com.api_salud.api_salud.repository;

import java.util.List;

import com.api_salud.api_salud.entity.TurnoDetalleEntity;
import com.api_salud.api_salud.entity.TurnoEntity;

public interface TurnoDetalleDao {
	

	public List<TurnoDetalleEntity> xIdTurnoLeer(int  idTurno) ;	

}
