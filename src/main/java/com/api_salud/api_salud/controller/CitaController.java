package com.api_salud.api_salud.controller;



import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.entity.CitaSeparadaPagadaEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;

import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.service.CitaBloqueadaService;
import com.api_salud.api_salud.service.CitaSeparadaService;
import com.api_salud.api_salud.service.CitaService;
import com.api_salud.api_salud.service.UsuarioService;

@RestController
@RequestMapping("")
public class CitaController {
	

	@Autowired
	CitaService citaService ;
	
	@Autowired
	CitaBloqueadaService citaBloqueadaService;

	@Autowired
	CitaSeparadaService citaSeparadaService;

	@Autowired
	UsuarioService usuarioService;
	
    @PostMapping("/citaDisponible")
    public ResponseEntity<CitaResponse> disponible(@RequestBody CitaRequest request){
    	CitaResponse response = citaService.citaDisponible(request);
        return new ResponseEntity<CitaResponse>(response, HttpStatus.OK);
    }	
  
    @PostMapping("/crearCita")
    public ResponseEntity<?> crearCita( @RequestBody @Valid CitaFacturacionRequest   request ,  BindingResult bindingResult){
    	
    	System.out.println("En el Controladooor CITA ");
   		/*if (bindingResult.hasErrors()) { 
            return  ResponseEntity
            		.badRequest()
            		.body(
            				bindingResult.getAllErrors() 
            				.stream() 
            				.map(ObjectError::getDefaultMessage)
            				.collect(Collectors.joining())
            		);
   		}*/
  //  	System.out.println("Si errores  ");
//	    CitaResponse response = null; 
 //   	response = citaService.crearCita(request);
  //  	if(response != null) 
  //          return new ResponseEntity<CitaResponse>(response, HttpStatus.OK);
  //      else    
  //          return new ResponseEntity<CitaResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    return null;	
   	}
        
    
}
