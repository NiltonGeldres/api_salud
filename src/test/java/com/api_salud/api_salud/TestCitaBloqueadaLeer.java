package com.api_salud.api_salud;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.CitaBloqueadaEntity;
import com.api_salud.api_salud.repository.CitaBloqueadaDao;
import com.api_salud.api_salud.request.CitaBloqueadaRequest;
import com.api_salud.api_salud.response.CitaBloqueadaResponse;


@SpringBootTest
public class TestCitaBloqueadaLeer {
	@Autowired
	CitaBloqueadaDao citaBloqueadaDao;

	@Test
	void contextLoads() {
		CitaBloqueadaRequest c = new CitaBloqueadaRequest();
		c.setFecha("20220704");
		c.setIdMedico(1762);
		
		List<CitaBloqueadaEntity> response =	citaBloqueadaDao.leerCitaBloqueada(1762,"20220704");
		
	 	   for (CitaBloqueadaEntity row : response) {
	   		  System.out.println("Hora dita bloqueada "+ row.getHoraInicio()+"   Decripcion "+ row.getHoraFin());
	  	   }		
	}

}



