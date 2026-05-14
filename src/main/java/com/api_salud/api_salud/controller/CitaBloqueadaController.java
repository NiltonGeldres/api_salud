package com.api_salud.api_salud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.request.CitaBloqueadaRequest;
import com.api_salud.api_salud.response.CitaBloqueadaResponse;
import com.api_salud.api_salud.service.CitaBloqueadaService;
import com.api_salud.api_salud.service.CitaSeparadaService;
import com.api_salud.api_salud.service.CitaService;
import com.api_salud.api_salud.service.UsuarioService;

@RestController
@RequestMapping("")
public class CitaBloqueadaController {

	@Autowired
	CitaService citaService ;
	
	@Autowired
	CitaBloqueadaService citaBloqueadaService;

	@Autowired
	CitaSeparadaService citaSeparadaService;

	@Autowired
	UsuarioService usuarioService;
    
    @PostMapping("/citaBloquear")
    public ResponseEntity<CitaBloqueadaResponse> citaBloquear(@RequestBody CitaBloqueadaRequest request){
    	CitaBloqueadaResponse response = citaBloqueadaService.crearCitaBloqueada(request);
         return new ResponseEntity<CitaBloqueadaResponse>(response, HttpStatus.OK);
     }
    
    @PostMapping("/eliminarCitaBloqueada")
    public ResponseEntity<Integer> eliminarCitaBloqueada(@RequestBody CitaBloqueadaRequest request){
    	int response = citaBloqueadaService.eliminarCitaBloqueada(request);
         return new ResponseEntity<Integer>(response, HttpStatus.OK);
     }	

    @PostMapping("/eliminarCitaBloqueadaXUsuario")
    public ResponseEntity<Integer> eliminarCitaBloqueadaXUsuario(@RequestBody CitaBloqueadaRequest request){
    	int response = citaBloqueadaService.eliminarCitaBloqueadaXUsuario(request);
         return new ResponseEntity<Integer>(response, HttpStatus.OK);
     }
    
        
   
}