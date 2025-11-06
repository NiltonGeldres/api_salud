package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.CitaController;
import com.api_salud.api_salud.controller.CitaSeparadaController;
import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.CitaSeparadaResponse;
import com.api_salud.api_salud.service.CitaSeparadaService;

@SpringBootTest
public class TestCitaSeparadaLeer {

	@Autowired
	CitaSeparadaController citaController;
	
	@Test
	void contextLoads() {
		CitaSeparadaRequest c = new CitaSeparadaRequest();
		c.setIdPaciente(29);
		c.setUsuario("ngeldres");
		 ResponseEntity<?>  response =	citaController.leerCitaSeparadaXIdPaciente(c);
		 
		  System.out.println("Especialidad = "+ response.toString());
	
		
	}


}