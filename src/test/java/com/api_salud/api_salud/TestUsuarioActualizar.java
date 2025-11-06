package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.repository.UsuarioRepo;

@SpringBootTest
public class TestUsuarioActualizar {
	@Autowired
	UsuarioRepo repo;
	
	@Autowired
	UsuarioDao dao; 

	
	@Autowired
	private BCryptPasswordEncoder encoder ; 
	

	@Test
	void contextLoads() {
			
		Usuario us1 = new Usuario();
		us1.setUsername("ngeldres");
		us1.setPassword("09800710");
		us1.setEmail("niltongeldres@hotmail.com");
		us1.setApellido_paterno("GELDRES");
		us1.setApellido_materno("CAYO");
		us1.setPrimer_nombre("NILTON");
		us1.setSegundo_nombre("CESAR");
		us1.setNumero_celular("987076794");
		us1.setId_sexo("1");
		us1.setId_tipo_documento("1");
		us1.setNumero_documento("09800710");
		us1.setFecha_alta("20221004");
		us1.setFecha_baja("20221004");
		us1.setFecha_modificacion("20221004");
		us1.setEstado("A");
		Usuario retorno1 = dao.usuarioActualizar(us1);
		
//		System.out.println("password jdbc " + us1.getPassword()) ;
	//	System.out.println("Id "+retorno1.getUsuario_id());
	//	System.out.println("Usuario "+retorno1.getUsername());
	//	System.out.println("Password   "+retorno1.getPassword());
//	assertTrue(retorno1.getPassword().equalsIgnoreCase(us1.getPassword()));

	
}



	
}

