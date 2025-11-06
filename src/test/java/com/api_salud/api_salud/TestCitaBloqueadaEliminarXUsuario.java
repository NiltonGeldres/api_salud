package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.request.CitaBloqueadaRequest;
import com.api_salud.api_salud.service.CitaBloqueadaService;

@SpringBootTest
public class TestCitaBloqueadaEliminarXUsuario {

	@Autowired
	CitaBloqueadaService citaBloqueadaService;

	@Test
	void contextLoads() {
		CitaBloqueadaRequest c = new CitaBloqueadaRequest();
		c.setUsuario("ngeldres");
		
		int response =	citaBloqueadaService.eliminarCitaBloqueadaXUsuario(c);
		System.out.println("Cita Bloqueada Numero:  " +response);
	}
}


