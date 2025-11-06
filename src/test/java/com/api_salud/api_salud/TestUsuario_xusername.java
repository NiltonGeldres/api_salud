package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.service.UsuarioService;


@SpringBootTest
public class TestUsuario_xusername {

	
		@Autowired
		UsuarioService usuarioService;

		  
		@Test
		void contextLoads() {
			
		int  id =  usuarioService.xusername_leer("macuna");
		    System.out.println("Id usuario :   "+ id);

		}		
	
}
