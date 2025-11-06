package com.api_salud.api_salud.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api_salud.api_salud.service.EmailService;
import com.api_salud.api_salud.service.EmailServiceImpl;

@RestController
@RequestMapping("/correo")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/enviar")
    public String enviarCorreo(@RequestParam String destino, @RequestParam String mensaje) {
    	/*
        try {
            emailService.sendEmail(destino, "Asunto del Correo", mensaje);
            System.out.println("Correo enviado con éxito");
            return "Correo enviado con éxito";
        } catch (Exception e) {
            System.out.println("Error al enviar el correo:"+ e.getMessage());
            return "Error al enviar el correo: " + e.getMessage();
        }*/
    	return null;
    }
}




