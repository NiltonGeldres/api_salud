package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl  implements EmailService {

	//@Autowired
	//private JavaMailSender javaMailSender;
	

	public void sendEmail(	
							String to, 
							String subject, 
							String nombreMedico,
							int numeroCuenta,
							double precio,
							String nombrePaciente
							) throws MessagingException {
/*
		System.out.println("MAIL A ENVIAR"+to);
		
	    MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        String  titulo= "Hola "+nombrePaciente;
        String  mensajeBienvenidad= "Hemos recibido tu solicitud, Para finalizar la compra deberas relizar el pago en nuestro numero ";
        String  mensajeFinal= "Luego de realizar el pago deberaas enviarnos el apgo de orden y comprobante a proyectos@igmccorp.com para confirmar la cita";
        
        helper.setTo(to);
        helper.setSubject(subject);
//        helper.setText(body, true);
       
        helper.setFrom("igmccorporation@hotmail.com");
        helper.setText(
         "<html><head>"
        + "</head>"
        + 		"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"GBK\" /> "
        + "<body>"
        + "" 
        +"<br>"
        + titulo 
        +"<br>"
        +"<br>"
        +  mensajeBienvenidad
        +"<br>"
        +"<br>"
        +  "<b> Nombre: </b> "+nombreMedico
        +"<br>"
        +  "<b>Numero: </b> "+numeroCuenta
        +"<br>"
        +  "<b>Precio: </b> "+precio
        +"<br>"
        +"<br>"
        +  mensajeFinal
        + "</body></html>",
        true);        
        
        javaMailSender.send(message);
		
	*/	
		
	}
 
	
}

