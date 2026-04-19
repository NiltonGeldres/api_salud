package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.MedicoController;
import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.MedicoResponse;


@SpringBootTest
public class TestMedico {

	@Autowired
	MedicoController medicoController ;

	@Test
	void contextLoads() {
		MedicoRequest r = new MedicoRequest();
		r.setIdEspecialidad(9);
		ResponseEntity<MedicoResponse> retorno1 = medicoController.medicoEspecialidad(r);
		System.out.println(retorno1.toString());
	}
}

