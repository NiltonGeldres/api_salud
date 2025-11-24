package com.api_salud.atencionmedica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    // Nota: Es mejor usar las propiedades de application.properties, 
    // pero si la AutoConfiguración falla, este bean asegura que se crea el JavaMailSender.
    
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        // ** Reemplazar con las propiedades de tu servidor de correo **
        mailSender.setHost("smtp.gmail.com"); 
        mailSender.setPort(587); 
        mailSender.setUsername("tu_correo@gmail.com"); 
        mailSender.setPassword("tu_contraseña_o_token_de_aplicacion"); 

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false"); // Cambiar a true si quieres ver la comunicación SMTP

        return mailSender;
    }
}