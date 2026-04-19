package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.CitaController;
import com.api_salud.api_salud.request.CitaSeparadaPagoVirtualRequest;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.CitaSeparadaPagoVirtualResponse;
import com.api_salud.api_salud.service.CitaSeparadaPagoVirtualService;
import com.api_salud.api_salud.service.CitaSeparadaService;
@SpringBootTest
public class TestCitaSeparadaPagoVirtualCrear {

	@Autowired
	CitaSeparadaPagoVirtualService citaSeparadaPagoVirtualService;
	
	@Test
	void contextLoads() {

		CitaSeparadaPagoVirtualRequest c = new CitaSeparadaPagoVirtualRequest();
	
		c.setIdCitaSeparadaPagoVirtual(0);
		c.setIdComprobantePago(30);
		c.setIdCitaSeparada(30);
		c.setFecha("20230419");
		c.setNroOperacion("1762");
		c.setCorreo("@gmail");
		c.setCelular("98778767");
		c.setMonto(10.10);
		c.setIdTipoOperacion(30);
		c.setOrigen("alvarez");
		c.setDestino("doctora 3");
		c.setEntidadDestino("yape");
		c.setIdUsuario(29);
		
		System.out.println("Testeando");
		CitaSeparadaPagoVirtualResponse response =	citaSeparadaPagoVirtualService.crearCitaSeparadaPagoVirtual(c);



	
}

}

