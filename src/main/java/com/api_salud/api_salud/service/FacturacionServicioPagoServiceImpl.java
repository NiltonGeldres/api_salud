package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;
import com.api_salud.api_salud.repository.FacturacionServicioPagoDao;

@Service
public class FacturacionServicioPagoServiceImpl   implements FacturacionServicioPagoService{

	@Autowired
	FacturacionServicioPagoDao facturacionServicioPagoDao;

	@Override
	public int crearFacturacionServicioPago(FacturacionServicioPagoEntity facturacionServicioPagoEntity) {
		// TODO Auto-generated method stub
		return facturacionServicioPagoDao.crearFacturacionServicioPago(facturacionServicioPagoEntity);
	}  
	

}
