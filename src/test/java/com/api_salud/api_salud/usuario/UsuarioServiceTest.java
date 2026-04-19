package com.api_salud.api_salud.usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.api_salud.api_salud.dto.UsuarioDto;
import com.api_salud.api_salud.entity.UsuarioEntity;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.request.UsuarioRequest;
import com.api_salud.api_salud.response.UsuarioResponse;
import com.api_salud.api_salud.service.UsuarioService;



@SpringBootTest
@ActiveProfiles("test") // Usa application-test.properties 
public class UsuarioServiceTest {
	@Autowired
	UsuarioService usuarioService;

    @Nested
    @DisplayName("Pruebas para el flujo de Creación de Usuario")
    class CrearUsuarioFlow {
    	
	    @Test
	    @DisplayName("Debe guardar el usuario correctamente y devolver el idUsuario (Flujo Completo)")
	    void usuario_guardar_Exito() {
			UsuarioRequest us1 = new UsuarioRequest();
			us1.setUsername("jgeldres");
			us1.setPassword("1234");
			us1.setEmail("jgeldres@hotmail.com");
			us1.setApellidoPaterno("GELDRES");
			us1.setApellidoMaterno("SANCHEZ");
			us1.setPrimerNombre("JHON");
			us1.setSegundoNombre("");
			us1.setNumeroCelular("12345654");
			us1.setIdSexo(1);
			us1.setIdTipoDocumento(1);
			us1.setNumeroDocumento("55555555");
			us1.setFechaAlta("2026-03-30");
			us1.setFechaBaja("2026-03-30");
			us1.setFechaModificacion("2026-03-30");
			us1.setEstado("A");
			us1.setIdEntidad(2);
			
			UsuarioResponse retorno = usuarioService.usuarioCrear(us1);
		     System.out.println("Response: " + retorno );
			
		}		
    }		
    	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*	@Autowired
	UsuarioService usuarioService;
	
    @Nested
    @DisplayName("Pruebas para el flujo de Creación de Usuario")
    class CrearUsuarioService {	
    	
	    @Test
	    @DisplayName("Debe guardar el usuario y luego recuperar el usuario con el idUsuario correctamente (Flujo Completo)")
	    void usuario_crearUsuario_Exito() {
            System.out.println("en Test");          
			UsuarioRequest us1 = new UsuarioRequest();
			us1.setUsername("mcayo1");
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
		     System.out.println("Estado: " + response.getEstado());
		     System.out.println("Mensaje de Error: " + response.getUsername()); // Aquí guardamos el código del SP
		     System.out.println("Email enviado: " + response.getEmail());			
		}
    }
*/
}
