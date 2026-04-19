package com.api_salud.api_salud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.EspecialidadResponse;
import com.api_salud.api_salud.response.MedicoResponse;
import com.api_salud.api_salud.service.EspecialidadService;
import com.api_salud.api_salud.service.MedicoService;

@RestController
@RequestMapping("")
public class MedicoController {
	@Autowired
	MedicoService medicoService;

    @PostMapping("/medicosEspecialidad")
	   public ResponseEntity<MedicoResponse> medicoEspecialidad(@RequestBody MedicoRequest request){
	        MedicoResponse response = medicoService.medicoEspecialidad(request);
	        return new ResponseEntity(response, HttpStatus.OK);
	    }
    
    @PostMapping("/medicoEspecialidad")
	   public ResponseEntity<MedicoResponse> medicoXIdMedicoXIdEspecialidad(@RequestBody MedicoRequest request){
    	   int idMedico = medicoService.medicoXUsuarioLeer(request.getUsuario());
    	    
	        MedicoResponse response = medicoService.medicoXIdMedicosXEspecialidad(request.getIdEspecialidad(), idMedico);
	        return new ResponseEntity(response, HttpStatus.OK);
	    }
}


	
		
			

	     