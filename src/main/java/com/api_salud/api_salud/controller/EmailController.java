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
    	return null;
    }
}




