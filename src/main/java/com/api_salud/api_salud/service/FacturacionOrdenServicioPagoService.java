package com.api_salud.api_salud.service;

import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;

public interface FacturacionOrdenServicioPagoService {

	 public int crearFacturacionOrdenServicioPago(FacturacionOrdenServicioPagoEntity facturacionOrdenServicioPagoEntity);

	 public int actualizaIdComprobantePago(int idordenpago , int idcomprobantwpago);
}
