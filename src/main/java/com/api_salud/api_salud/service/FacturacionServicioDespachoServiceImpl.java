package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.repository.FacturacionServicioDespachoDao;
@Service
public class FacturacionServicioDespachoServiceImpl  implements FacturacionServicioDespachoService{
	@Autowired
	FacturacionServicioDespachoDao facturacionServicioDespachoServiceDao;
	
	@Override
	public int crearFacturacionServicioDespacho(FacturacionServicioDespachoEntity facturacionServicioDespachoEntity) {
		
		return  facturacionServicioDespachoServiceDao.crearFacturacionServicioDespacho(facturacionServicioDespachoEntity);
	}

}
