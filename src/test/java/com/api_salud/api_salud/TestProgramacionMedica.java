package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.api_salud.api_salud.service.ProgramacionMedicaService;

@SpringBootTest
public class TestProgramacionMedica {

	@Autowired
	ProgramacionMedicaService programacionMedicadService ;

	
	@Test
	void contextLoads() {
		/*
		ProgramacionMedicaRequest x = new ProgramacionMedicaRequest();
		x.setIdMedico(1762);
		x.setIdEspecialidad(9);
		ProgramacionMedicaResponse data = programacionMedicadService.medicoTodos(x);
		System.out.println("******************************** TODOS ");
		for (ProgramacionMedicaEntity element : data.getProgramacionMedica()) {
			System.out.println("Elemento Entity :  " +element.getFecha());
		}
		*/
		
		ProgramacionMedicaRequest x1 = new ProgramacionMedicaRequest();
		x1.setIdMedico(1762);
		x1.setIdEspecialidad(9);
		x1.setFecha("20220704");
		ProgramacionMedicaResponse data1 = programacionMedicadService.medicoFecha(x1);
		System.out.println("***************************************FECHA");
		for (ProgramacionMedicaEntity element1 : data1.getProgramacionMedica()) {
			System.out.println("Elemento Entity :  " +element1.getFecha());
		}

		
	}	
}

