package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl  implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	

	public void sendEmail(	
							String to, 
							String subject, 
							String nombreMedico,
							int numeroCuenta,
							double precio,
							String nombrePaciente
							) throws MessagingException {

		
	    MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        String  titulo= "Hola "+nombrePaciente;
        String  mensajeBienvenidad= "Hemos recibido tu solicitud, Para finalizar la compra deberas relizar el pago en nuestro numero ";
        String  mensajeFinal= "Luego de realizar el pago deberaas enviarnos el apgo de orden y comprobante a proyectos@igmccorp.com para confirmar la cita";
        
        helper.setTo(to);
        helper.setSubject(subject);
//        helper.setText(body, true);
       
        helper.setFrom("ngeldres@igmccorp.com");
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
		
		
		
	}
 
	
}



/*	
private String setCss() {
	    StringBuffer sb = new StringBuffer();
	    sb.append("<style type=\"text/css\"> ");
	    sb.append("body {");
	    sb.append("  font: normal 11px auto \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; ");
	    sb.append("  color: #4f6b72; ");
	    sb.append("  background: #E6EAE9; ");
	    sb.append("}");
	    sb.append(" a {color: #c75f3e;} ");
	    sb.append("#mytable { width: 500px; padding: 0; margin: 0; } ");
	    sb.append("caption { padding: 0 0 5px 0; width: 500px; ");
	    sb.append(" font: italic 11px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; ");
	    sb.append(" text-align: right; }");
	    sb.append("th { font: bold 11px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; ");
	    sb.append(" color: #4f6b72; border-right: 1px solid #C1DAD7; ");
	    sb.append(" border-bottom: 1px solid #C1DAD7; ");
	    sb.append(" border-top: 1px solid #C1DAD7; ");
	    sb.append(" letter-spacing: 2px; text-transform: uppercase; ");
	    sb.append(" text-align: left; padding: 6px 6px 6px 12px; background: #CAE8EA  no-repeat; }");
	    sb.append(
	        " th.nobg { border-top: 0; border-left: 0; border-right: 1px solid #C1DAD7; background: none; }");
	    sb.append(
	        "td { border-right: 1px solid #C1DAD7; border-bottom: 1px solid #C1DAD7; background: #fff; ");
	    sb.append(" font-size:11px; padding: 6px 6px 6px 12px; color: #4f6b72; }");
	    sb.append("td.alt { background: #F5FAFA; color: #797268; }");
	    sb.append(
	        "th.spec { border-left: 1px solid #C1DAD7; border-top: 0; background: #fff no-repeat; ");
	    sb.append(" font: bold 10px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; }");
	    sb.append(
	        "th.specalt { border-left: 1px solid #C1DAD7; border-top: 0; background: #f5fafa no-repeat; ");
	    sb.append(
	        " font: bold 10px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; color: #797268; }");
	    sb.append("html>body td{ font-size:11px;} ");
	    sb.append("body,td,th { font-family: 宋体, Arial; font-size: 12px; }");
	    sb.append("</style> ");
	    return sb.toString();
	  }	
*/
