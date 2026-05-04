package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.ProgramacionMedicaController;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.api_salud.api_salud.service.ProgramacionMedicaService;

@SpringBootTest
public class AgendaServiceTest {
	
	@Autowired
	ProgramacionMedicaController programacionMedicaController ;

	
	@Test
	void ObtenerAgendaTodas() {
		
		ProgramacionMedicaRequest x = new ProgramacionMedicaRequest();
		x.setIdMedico(1762);
		x.setIdEspecialidad(9);
//		ResponseEntity   data = programacionMedicaController.medicoTodos(x);
		System.out.println("******************************** TODOS ");
//		System.out.println(data.toString());		

		
	}	


}
