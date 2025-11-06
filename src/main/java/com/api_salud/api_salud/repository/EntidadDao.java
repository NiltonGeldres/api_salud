package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.entity.EntidadEntity;
import com.api_salud.api_salud.response.EntidadResponse;

public interface  EntidadDao {

	public EntidadResponse   xIdMedico(int idMedicos);
	
}
