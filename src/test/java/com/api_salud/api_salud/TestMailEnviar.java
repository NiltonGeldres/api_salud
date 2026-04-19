package com.api_salud.api_salud;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.controller.EmailController;
import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.CitaSeparadaDao;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.service.EmailService;
 
@SpringBootTest
public class TestMailEnviar {

	@Autowired 
	CitaSeparadaDao citaSeparadaDao;
	@Autowired
	EmailService emailService ;
	@Autowired 
	UsuarioDao usuarioDao;
	@Test
	void contextLoads() {
		//emailController.enviarCorreo("niltongeldres@hotmail.com", "Mensaje de correo automatizado");
	//	int idCitaSeparada=263 ;
	//	int idUsuario=40;
	//	CitaSeparadaEntityResponse citaSeparadaEntityResponse = new CitaSeparadaEntityResponse();
	//	citaSeparadaEntityResponse = citaSeparadaDao.leerCitaSeparadaXIdCitaSeparada(idCitaSeparada)  ;
		
	 //   Usuario usuario = usuarioDao.usuarioLeer(idUsuario);		
	//	CitaSeparadaEntity citaSeparada ; //= new CitaSeparadaEntity();
	 	try {
	 		
	 		System.out.println("ENVIANDO EMAIL ");
	 		emailService.sendEmail("niltongeldres@hotmail.com", "Correo de prueba", "MEDICO", 10, 70, "NILTON GELDRES");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}


/*
 			emailService.sendEmail(
								usuario.getEmail()
								,"Solicitud de cita "
								,citaSeparadaEntityResponse.getNombreMedico()
								,citaSeparadaEntityResponse.getNumeroCuenta()
								,citaSeparadaEntityResponse.getPrecioUnitario()
								,usuario.getPrimer_nombre()+" "+usuario.getApellido_paterno() + " "+usuario.getApellido_materno()

 */