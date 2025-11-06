package com.api_salud.api_salud.service;

import javax.mail.MessagingException;

public interface  EmailService {
	
	public void sendEmail(
			String to, 
			String subject, 
			String nombreMedico,
			int  numeroCuenta,
			double precio,
			String nombrePaciente

						)  throws MessagingException;	
	
}
