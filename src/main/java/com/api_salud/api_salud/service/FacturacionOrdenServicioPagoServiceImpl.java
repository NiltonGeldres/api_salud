package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.repository.FacturacionOrdenServicioPagoDao;

@Service
public class FacturacionOrdenServicioPagoServiceImpl  implements FacturacionOrdenServicioPagoService {
	@Autowired
//	FacturacionOrdenServicioPagoService facturacionOrdenServicioPagoService ; 
	FacturacionOrdenServicioPagoDao facturacionOrdenServicioPagoDao;
	@Override
	public int crearFacturacionOrdenServicioPago(
			FacturacionOrdenServicioPagoEntity facturacionOrdenServicioPagoEntity) {
		return facturacionOrdenServicioPagoDao.crearFacturacionOrdenServicioPago(facturacionOrdenServicioPagoEntity) ;
//		return facturacionOrdenServicioPagoService.crearFacturacionOrdenServicioPago(facturacionOrdenServicioPagoEntity) ;
	}

	@Override
	public int actualizaIdComprobantePago(int idordenpago, int idcomprobantwpago) {
		// TODO Auto-generated method stub
		return  facturacionOrdenServicioPagoDao.actualizaIdComprobantePago(idordenpago, idcomprobantwpago) ;
//		return  facturacionOrdenServicioPagoService.actualizaIdComprobantePago(idordenpago, idcomprobantwpago) ;
	}

}
