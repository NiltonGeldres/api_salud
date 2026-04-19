package com.api_salud.api_salud.service;

import java.util.List;

import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;
import com.api_salud.api_salud.request.FacturacionRequest;
import com.api_salud.api_salud.response.EspecialidadResponse;

public interface FacturacionService {
	
	public String FacturacionDigital (
			FacturacionCuentaAtencionEntity fca,
			FacturacionOrdenServicioEntity  fos ,
			List<FacturacionServicioDespachoEntity> fosd,
			FacturacionOrdenServicioPagoEntity fosp	,
			List<FacturacionServicioPagoEntity>  listFacturacionServicioPago,
			CajaComprobantePagoEntity ccp
);
	
	
}
