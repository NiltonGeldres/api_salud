package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.request.CitaBloqueadaRequest;
import com.api_salud.api_salud.service.CitaBloqueadaService;

@SpringBootTest
public class TestCitaBloqueadaEliminar {

	@Autowired
	CitaBloqueadaService citaBloqueadaService;

	@Test
	void contextLoads() {
		CitaBloqueadaRequest c = new CitaBloqueadaRequest();
		c.setIdCitaBloqueada(269);
		
		int response =	citaBloqueadaService.eliminarCitaBloqueada(c);
		System.out.println("Cita Bloqueada Numero:  " +response);
	}
}


