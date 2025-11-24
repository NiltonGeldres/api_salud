package com.api_salud.api_salud.controller;




import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.service.CitaBloqueadaService;
import com.api_salud.api_salud.service.CitaSeparadaService;
import com.api_salud.api_salud.service.CitaService;
import com.api_salud.api_salud.service.UsuarioService;

@RestController
@RequestMapping("")
public class CitaController {
	

	@Autowired
	CitaService citaService ;
	/*
	@Autowired
	CitaBloqueadaService citaBloqueadaService;

	@Autowired
	CitaSeparadaService citaSeparadaService;

	@Autowired
	UsuarioService usuarioService;
	*/
  
    @PostMapping("/crearCita")
    public ResponseEntity<?> crearCita( @RequestBody @Valid CitaFacturacionRequest   request ,  BindingResult bindingResult){
    	
    	System.out.println("En el Controladooor CITA ");

    return null;	
   	}
        
    
}
