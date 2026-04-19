package com.api_salud.api_salud;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.ProgramacionMedicaController;
import com.api_salud.api_salud.request.ProgramacionMedicaCrearDetalleRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaCrearRequest;
import com.api_salud.api_salud.response.ProgramacionMedicaDiaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaMesResponse;
import com.api_salud.api_salud.service.ProgramacionMedicaService;

@SpringBootTest
public class check_TestProgramacionMedicaCrear {
	@Autowired
	ProgramacionMedicaController  programacionMedicadController ;

	@Test
	void contextLoads() {
		ProgramacionMedicaCrearRequest request = new ProgramacionMedicaCrearRequest();
		request.setFecha("20240207");
		request.setIdEspecialidad(9);
		request.setIdMedico(1762);
		request.setUsuario("macuna");
		
		List<ProgramacionMedicaDiaResponse> list = new ArrayList<>();
		ProgramacionMedicaDiaResponse p = new ProgramacionMedicaDiaResponse();
		p.setDia(1);
		p.setDiaSemana("");
		p.setFecha("20240208");
		p.setHoraFin("12:00");
		p.setHoraInicio("08:00");
		p.setId(1);
		p.setIdEspecialidad(9);
		p.setIdProgramacion(0);
		p.setIdServicio(1);
		p.setIdTurno(7);
		list.add( p );

//		ProgramacionMedicaCrearDetalleRequest p1 = new ProgramacionMedicaCrearDetalleRequest();
		ProgramacionMedicaDiaResponse  p1 = new ProgramacionMedicaDiaResponse();
		p1.setDia(2);
		p1.setDiaSemana("");
		p1.setFecha("20240208");
		p1.setHoraFin("12:00");
		p1.setHoraInicio("08:00");
		p1.setId(2);
		p1.setIdEspecialidad(9);
		p1.setIdProgramacion(0);
		p1.setIdServicio(1);
		p1.setIdTurno(1);
		list.add( p1 );
		
		request.setProgramacion(list);
		
		System.out.println(" MEDICO MES ");
		ResponseEntity  data1 = programacionMedicadController.programacionMedicaCrear(request);
		System.out.println("TERMINADO ");
/*		for (ProgramacionMedicaDiaResponse element1 : data1.getProgramacionMedicaDiaResponse()) {
			System.out.println("Elemento Entity :  " +element1.getDia());
		}*/

}
	
	
}


