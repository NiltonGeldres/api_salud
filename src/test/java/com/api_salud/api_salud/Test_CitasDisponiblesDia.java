package com.api_salud.api_salud;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.CitaController;
import com.api_salud.api_salud.repository.CitaDao;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaDisponibleResponse;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.service.CitaService;

@SpringBootTest
public class Test_CitasDisponiblesDia {
	@Autowired
	CitaService citaService;

	
	@Test
	void contextLoads() {
		CitaRequest c = new CitaRequest();
		c.setFecha("20251129");
		c.setIdEspecialidad(9);
		c.setIdMedico(1762);
		
		CitaResponse data =	citaService.citaDisponible(c);
		
		for (CitaDisponibleResponse element : data.getCita()) {
			System.out.println("Elemento Entity :  " +element.getHoraInicio());
			System.out.println("Elemento Entity :  " +element.getIdProgramacion());
			System.out.println("Elemento Entity :  " +element.getNombreServicio());
			System.out.println("Elemento Entity :  " +element.getIdServicio());
	}
	}
}

