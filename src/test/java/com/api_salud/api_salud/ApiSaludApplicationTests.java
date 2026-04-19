package com.api_salud.api_salud;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api_salud.api_salud.entity.Usuario;

import com.api_salud.api_salud.repository.UsuarioDao;



@SpringBootTest
class ApiSaludApplicationTests {

	//@Autowired
	//UsuarioRepo repo;
	
	@Autowired
	UsuarioDao dao; 

	
	@Autowired
	private BCryptPasswordEncoder encoder ; 
	
	@Test
	void contextLoads() {
		
		Usuario us1 = new Usuario();
		us1.setUsername("ngeldres");
		us1.setPassword(encoder.encode("09800710"));
		us1.setEmail("ngeldres@hotmail.com");
		us1.setApellido_paterno("Geldres");
		us1.setApellido_materno("Cayo");
		us1.setPrimer_nombre("Nilton");
		us1.setSegundo_nombre("Cesar");
		us1.setNumero_celular("987076794");
		us1.setId_sexo("1");
		us1.setId_tipo_documento("1");
		us1.setNumero_documento("09800710");
		us1.setFecha_alta("20221004");
		us1.setFecha_baja("20221004");
		us1.setFecha_modificacion("20221004");
		us1.setEstado("A");
		Usuario retorno1 = dao.usuarioSave(us1);
//		System.out.println("password jdbc " + us1.getPassword()) ;
	//	System.out.println("Id "+retorno1.getUsuario_id());
	//	System.out.println("Usuario "+retorno1.getUsername());
	//	System.out.println("Password   "+retorno1.getPassword());
	assertTrue(retorno1.getPassword().equalsIgnoreCase(us1.getPassword()));

	
	/*
		Usuario us = new Usuario();
		us.setUsername("ngeldres");
		us.setPassword(encoder.encode("09800710"));
		us.setEmail("ngeldres@hotmail.com");
		System.out.println("pasword jpa " + us.getPassword()) ;
		
		Usuario retorno = repo.save(us);
//		Usuario retorno = repo.saveUsuario(us.getUsername(),us.getPassword(),us.getEmail());
	//	System.out.println("Id "+retorno.getUsuario_id());
	//	System.out.println("Usuario "+retorno.getUsername());
		System.out.println("Password   "+retorno.getPassword());
		
		
	//	assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));
		
	*/	
		
	}

}
