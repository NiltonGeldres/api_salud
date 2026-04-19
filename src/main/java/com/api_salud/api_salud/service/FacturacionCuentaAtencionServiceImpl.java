package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.repository.FacturacionCuentaAtencionDao;




@Service
public class FacturacionCuentaAtencionServiceImpl  implements FacturacionCuentaAtencionService
{
	@Autowired
	FacturacionCuentaAtencionDao facturacionCuentaAtencionDao;

	@Override
	public int  crearFacturacionCuentaAtencion(FacturacionCuentaAtencionEntity request) {
		 return facturacionCuentaAtencionDao.crearFacturacionCuentaAtencion(request);
	}

}

