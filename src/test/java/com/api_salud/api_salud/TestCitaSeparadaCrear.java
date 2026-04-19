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
public class TestCitaSeparadaCrear {
	@Autowired
	CitaSeparadaService citaSeparadaService;
	
	@Autowired
	CitaSeparadaController citaSeparadaController;
	

// DESDE EL CONTROLLER		
	@Test
	void contextLoads() {
		CitaSeparadaRequest c = new CitaSeparadaRequest();
		c.setIdEntidad(2);
		c.setFecha("20230419");
		c.setHoraInicio("10:00");
		c.setHoraFin("10:15");
		c.setIdPaciente(3);
		c.setIdMedico(2);
		c.setIdEspecialidad(2);
		c.setIdServicio(1);
		c.setIdProgramacion(20230419);
		c.setIdProducto(20230419);
		c.setFechaSolicitud("20230419");
		c.setHoraSolicitud("08:00");
		c.setTipoUsuario("WEB");
		c.setFechaSeparacion("20230419");
		c.setPrecioUnitario(70.10);
		c.setUsuario("ngeldres");
		System.out.println("Testeando");
		ResponseEntity<?> response =	citaSeparadaController.crearCitaSeparada(c);

		
/*	 	   for (CitaSeparadaEntity row : response.getCitaSeparada()) {
	   		  System.out.println("TEST ID DE CITA SEPARADA = "+ row.getIdCitaSeparada());
	  	   }*/
	 	   
	}
	

//	DESDE EL @SERVICE	
	
/*	
	@Test
	void contextLoads() {
		
		CitaSeparadaRequest c = new CitaSeparadaRequest();
		c.setFecha("20230419");
		c.setHoraInicio("10:00");
		c.setHoraFin("10:15");
		c.setIdPaciente(20230419);
		c.setIdMedico(20230419);
		c.setIdEspecialidad(20230419);
		c.setIdServicio(20230419);
		c.setIdProgramacion(20230419);
		c.setIdProducto(20230419);
		c.setFechaSolicitud("20230419");
		c.setHoraSolicitud("08:00");
		c.setTipoUsuario("WEB");
		c.setFechaSeparacion("20230419");
		CitaSeparadaResponse response =	citaSeparadaService.crearCitaSeparada(c);
		
	 	   for (CitaSeparadaEntity row : response.getCitaSeparada()) {
	   		  System.out.println("TEST ID DE CITA SEPARADA = "+ row.getIdCitaSeparada());
	  	   }
	 	   
	}
	*/
	
	
}



