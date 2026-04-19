package com.api_salud.api_salud;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.request.CitaBloqueadaRequest;
import com.api_salud.api_salud.response.CitaBloqueadaResponse;
import com.api_salud.api_salud.service.CitaBloqueadaService;

@SpringBootTest
public class TestCitaBloqueada {
	@Autowired
	CitaBloqueadaService citaBloqueadaService;

	@Test
	void contextLoads() {
		CitaBloqueadaRequest c = new CitaBloqueadaRequest();
		c.setIdUsuario(11);
		c.setFecha("20220701");
		c.setHoraInicio("07:00");
		c.setHoraFin("07:15");
		c.setFechaBloqueo("20230410");
		c.setHoraBloqueo("19:52");
		c.setIdMedico(1762);
		c.setTipoUsuario("WEB");
		c.setUsuario("ngeldres");
		
		CitaBloqueadaResponse response =	citaBloqueadaService.crearCitaBloqueada(c);
		System.out.println("Cita Bloqueada Numero:  " +response.getIdCitaBloqueada());
	}
}


