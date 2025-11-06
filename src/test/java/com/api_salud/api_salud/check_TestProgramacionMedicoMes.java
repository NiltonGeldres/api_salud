package com.api_salud.api_salud;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.repository.ProgramacionMedicaDao;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.ProgramacionMedicaDiaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaMesResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.api_salud.api_salud.service.ProgramacionMedicaService;

@SpringBootTest
public class check_TestProgramacionMedicoMes {
	@Autowired
		ProgramacionMedicaService  programacionMedicadService ;

	
	@Test
	void contextLoads() {
	
		System.out.println(" MEDICO MES ");
		ProgramacionMedicaMesResponse data1 = programacionMedicadService.programacionMedicoMesLeer(2,2024,1762,9);
		System.out.println("***************************************FECHA");
		for (ProgramacionMedicaDiaResponse element1 : data1.getProgramacionMedicaDiaResponse()) {
			System.out.println("Elemento Entity :  " +element1.getDia());
		}

}
}