package com.api_salud.api_salud.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.MedicoResponse;
import com.api_salud.api_salud.service.MedicoService;

@RestController
@RequestMapping("")
public class AtencionMedicaController {
	
	@Autowired
	MedicoService medicoService;

    @PostMapping("/atencionmedica")
	   public ResponseEntity<MedicoResponse> medico(@RequestBody MedicoRequest request){
	        MedicoResponse response = medicoService.medicoEspecialidad(request);
	        return new ResponseEntity(response, HttpStatus.OK);
	    }	

}

