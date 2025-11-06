package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.controller.EmailController;

@SpringBootTest
public class TestMailEnviar {

	@Autowired
	EmailController emailController ;
	
	@Test
	void contextLoads() {
		emailController.enviarCorreo("niltongeldres@hotmail.com", "Mensaje de correo automatizado");
	}
}


