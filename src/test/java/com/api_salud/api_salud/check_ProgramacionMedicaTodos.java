package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.ProgramacionMedicaDiaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaMesResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.api_salud.api_salud.service.ProgramacionMedicaService;

@SpringBootTest
public class check_ProgramacionMedicaTodos {

	@Autowired
	ProgramacionMedicaService  programacionMedicadService ;


	@Test
	void contextLoads() {
		
		ProgramacionMedicaRequest request = new ProgramacionMedicaRequest();
		request.setFecha("20260201");
		request.setIdEspecialidad(9);
		request.setIdMedico(1762);
		
//		ProgramacionMedicaResponse data1 = programacionMedicadService.medicoTodos(request);
//			System.out.println("***************************************FECHA");
//		for (ProgramacionMedicaEntity element1 : data1.getProgramacionMedica()) {
//			System.out.println("Elemento Entity :  " +element1.getFecha());
//		}
	
	}

}
/*	System.out.println("***************************************FECHA");
for (ProgramacionMedicaEntity element1 : data1.getProgramacionMedica()) {
	System.out.println("Elemento Entity :  " +element1.getFecha());
}
*/
