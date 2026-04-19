package com.api_salud.api_salud.repository;


import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;

public  interface FacturacionOrdenServicioPagoDao {
	
	 public int crearFacturacionOrdenServicioPago(FacturacionOrdenServicioPagoEntity facturacionOrdenServicioPagoEntity);

	 public int actualizaIdComprobantePago(int idordenpago , int idcomprobantwpago);

}
