package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.request.FacturacionRequest;
import com.api_salud.api_salud.response.FacturacionCuentaAtencionResponse;
import com.api_salud.api_salud.service.FacturacionCuentaAtencionService;


@SpringBootTest
public class TestFacturacionCuentaAtencion {
	@Autowired
	FacturacionCuentaAtencionService facturacionCuentaAtencionService;

	@Test
	void contextLoads() {
		FacturacionRequest  c = new  FacturacionRequest();
		c.setTotalPorPagar(9);
		c.setIdEstado(1);
		c.setTotalPagado(10.0);
		c.setTotalAsegurado(1.0);
		c.setTotalExonerado(1.0);
		c.setHoraCierre("l0:00");
		c.setFechaCierre("20230301");
		c.setHoraApertura("11:00");
		c.setFechaApertura("20230501");
		c.setIdPaciente(19);
		c.setFechaCreacion("20230101");
		c.setIdUsuarioAuditoria(10);
			
		
//		FacturacionCuentaAtencionResponse data =	facturacionCuentaAtencionService.crearFacturacionCuentaAtencion(c);
/*		for (FacturacionCuentaAtencionEntity element : data) {
			System.out.println("Elemento Entity :  " +element.getIdProducto());
		}*/
	}
}

