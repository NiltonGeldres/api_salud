package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.repository.CajaComprobantePagoDao;

@Service
public class CajaComprobantePagoServiceImpl  implements CajaComprobantePagoService{
	@Autowired
	CajaComprobantePagoDao cajaComprobantePagoDao ;
	
	@Override
	public int crearCajaComprobantePago(CajaComprobantePagoEntity cajaComprobantePagoEntity) {
		
		return  cajaComprobantePagoDao.crearCajaComprobantePago(cajaComprobantePagoEntity);
	}
	

}
