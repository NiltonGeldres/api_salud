package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.request.FacturacionOrdenServicioRequest;
import com.api_salud.api_salud.request.FacturacionRequest;
import com.api_salud.api_salud.response.FacturacionCuentaAtencionResponse;
import com.api_salud.api_salud.response.FacturacionOrdenServicioResponse;
import com.api_salud.api_salud.service.FacturacionCuentaAtencionService;
import com.api_salud.api_salud.service.FacturacionOrdenServicioService;

@SpringBootTest
public class TestFacturacionOrdenServicio {

	@Autowired
	FacturacionOrdenServicioService facturacionOrdenServicioService;

	@Test
	void contextLoads() {
		FacturacionOrdenServicioRequest  c = new  FacturacionOrdenServicioRequest();
		c.setIdOrden(0) ;
		c.setIdPuntoCarga(100);
		c.setIdPaciente(100);
		c.setIdCuentaAtencion(100);
		c.setIdServicioPaciente(100);
		c.setIdTipoFinanciamiento(100);
		c.setIdFuenteFinanciamiento(100);
		c.setFechaCreacion("20230101");
		c.setIdUsuario(0);
		c.setFechaDespacho("20230101");
		c.setIdUsuarioDespacho(100);
		c.setIdEstadoFacturacion(1);
		c.setFechaHoraRealizaCpt("20230101");
		
//		FacturacionOrdenServicioResponse data =	facturacionOrdenServicioService.crearFacturacionOrdenServicio(c);
//		System.out.println("Orden Servicio numero  :  " +data.getIdOrdenServicio());
		
/*		for (FacturacionCuentaAtencionEntity element : data) {
			System.out.println("Elemento Entity :  " +element.getIdProducto());
		}*/
	}

	
}



