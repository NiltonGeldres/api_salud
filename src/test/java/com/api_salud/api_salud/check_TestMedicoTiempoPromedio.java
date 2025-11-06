package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.service.MedicoService;


@SpringBootTest
public class check_TestMedicoTiempoPromedio {

	@Autowired
	MedicoService medicoService ;

	@Test
	void contextLoads() {
		System.out.println("Tiempo Promedio de atencion ");
		MedicoRequest r = new MedicoRequest();
		r.setIdEspecialidad(9);
		int retorno = medicoService.tiempoPromedioAtencion_leer(1762, 9);
		System.out.println("Tiempo Promedio de atencion "+retorno);
	}
}

