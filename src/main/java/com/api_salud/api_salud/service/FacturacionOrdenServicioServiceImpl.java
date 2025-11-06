package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.repository.FacturacionOrdenServicioDao;


@Service
public class FacturacionOrdenServicioServiceImpl  implements FacturacionOrdenServicioService{

	@Autowired
	FacturacionOrdenServicioDao facturacionOrdenServicioDao;

	@Override
	public int crearFacturacionOrdenServicio(FacturacionOrdenServicioEntity request) {
		return facturacionOrdenServicioDao.crearFacturacionOrdenServicio(request);
	}	
}




/*
request.getIdPuntoCarga()
,request.getIdPaciente()
,request.getIdCuentaAtencion()
,request.getIdServicioPaciente()
,request.getIdTipoFinanciamiento()
,request.getIdFuenteFinanciamiento()
,request.getFechaCreacion()
,request.getIdUsuario()
,request.getFechaDespacho()
,request.getIdUsuarioDespacho()
,request.getIdEstadoFacturacion()
,request.getFechaHoraRealizaCpt()
,request.getIdUsuario()  //usuario auditoria
*/