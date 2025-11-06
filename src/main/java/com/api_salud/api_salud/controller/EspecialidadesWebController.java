package com.api_salud.api_salud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.controller.EntidadController.entidadRequest;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.EspecialidadResponse;

import com.api_salud.api_salud.response.TurnoResponse;
import com.api_salud.api_salud.service.EspecialidadService;
import com.api_salud.api_salud.service.MedicoService;
import com.api_salud.api_salud.service.TurnoService;

@RestController
@RequestMapping("")
public class EspecialidadesWebController {
	
	
		@Autowired
		EspecialidadService especialidadService ;
		
		@Autowired
		MedicoService medicoService ;
		
	    @PostMapping("/especialidad")
	   public ResponseEntity<EspecialidadResponse> especialidad(){
	    	System.out.println("Ingreso a especialidad ");
	        EspecialidadResponse response = especialidadService.web();
	        return new ResponseEntity<EspecialidadResponse>(response, HttpStatus.OK);
	    }
			
	    @PostMapping("/especialidadMedico")
	   public ResponseEntity<EspecialidadResponse> especialidadMedico(@RequestBody entidadRequest request){
	    	int idMedico = medicoService.medicoXUsuarioLeer(request.getUsuario());
	    	System.out.println("Ingreso a especialidad ");
	        EspecialidadResponse response = especialidadService.xIdMedico(idMedico);
	        return new ResponseEntity<EspecialidadResponse>(response, HttpStatus.OK);
	    }
			

	    static class entidadRequest {
	    	String usuario;

	    	public String getUsuario() {
	    		return usuario;
	    	}

	    	public void setUsuario(String usuario) {
	    		this.usuario = usuario;
	    	}


	    	}
	             

}


