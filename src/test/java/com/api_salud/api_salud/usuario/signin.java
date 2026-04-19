package com.api_salud.api_salud.usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.request.UsuarioRequest;
import com.api_salud.api_salud.response.UsuarioResponse;
import com.api_salud.api_salud.service.UsuarioService;

@SpringBootTest
public class signin {
	@Autowired
	UsuarioService usuarioService;
	
    @Test
    void usuario_crearUsuario_Exito() {
        System.out.println("en Test");          
		UsuarioRequest us1 = new UsuarioRequest();
		us1.setUsername("mcayo");
		us1.setPassword("1234");
		us1.setEmail("mcayo@hotmail.com");
		us1.setApellidoPaterno("cayo");
		us1.setApellidoMaterno("Bohorquez");
		us1.setPrimerNombre("Maria");
		us1.setSegundoNombre("Concepcion");
		us1.setNumeroCelular("99999999");
		us1.setIdSexo(2);
		us1.setIdTipoDocumento(2);
		us1.setNumeroDocumento("99999999");
		us1.setFechaAlta("20260320");
		us1.setFechaBaja("20260320");
		us1.setFechaModificacion("20260320");
		us1.setEstado("A");
		us1.setIdEntidad(4);
        System.out.println("Lego de llenar Mapper");          
		
    	   	

		 UsuarioResponse response = usuarioService.usuarioCrear(us1);
	     System.out.println("Response: " + response );
		
	}	
	
}
