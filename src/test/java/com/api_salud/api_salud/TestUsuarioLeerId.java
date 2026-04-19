package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.service.UsuarioService;


@SpringBootTest
public class TestUsuarioLeerId {
	
	@Autowired
	UsuarioDao usuarioDao;

	  
	@Test
	void contextLoads() {
		
	    System.out.println("Enviando :   ");
	  Usuario u = usuarioDao.usuarioLeer(29);
	    System.out.println("Email :   "+ u.getEmail());
	   

	    System.out.println("Enviando :   ");
	  Usuario d = usuarioDao.usuarioUsernameLeer("ngeldres");
	    System.out.println("Email :   "+ d.getEmail());
	    
	    

	}		

}

