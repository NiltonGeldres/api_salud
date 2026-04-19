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

@SpringBootTest
@ActiveProfiles("test") // Usa application-test.properties 

public class UsuarioDaoTest {

	@Autowired
	UsuarioDao usuarioDao;

    @Nested
    @DisplayName("Pruebas para el flujo de Creación de Usuario")
    class CrearUsuarioFlow {	
    	
	    @Test
	    @DisplayName("Debe recuperar el usuario con el idUsuario correctamente (Flujo Completo)")
	    void usuario_buscarPorIdUsuario_Exito() {
			int idUsuario=71;
			 UsuarioDto response = usuarioDao.buscarPorIdUsuario(idUsuario);
		     System.out.println("Response: " + response );
		     System.out.println("Apellido paterno: " + response.getApellidoPaterno() );
			
		}
	
	    @Test
	    @DisplayName("Debe guardar el usuario correctamente y devolver el idUsuario (Flujo Completo)")
	    void usuario_guardar_Exito() {
			UsuarioEntity us1 = new UsuarioEntity();
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
			
			int retorno = usuarioDao.guardar(us1);
		     System.out.println("Response: " + retorno );
			
		}		
    }		
    
}
