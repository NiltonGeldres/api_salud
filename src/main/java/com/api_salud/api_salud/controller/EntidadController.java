package com.api_salud.api_salud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.service.EntidadService;
import com.api_salud.api_salud.service.MedicoService;


@RestController
@RequestMapping("")
public class EntidadController {
	
	@Autowired
	EntidadService entidadService ;

	
	@Autowired
	MedicoService medicoService ;
	
    @PostMapping("/entidades")
   public ResponseEntity<EntidadResponse> entidades(@RequestBody entidadRequest request){
    	int idMedico = medicoService.medicoXUsuarioLeer(request.getUsuario());
    	EntidadResponse response = entidadService.xIdMedico(idMedico);
        return new ResponseEntity<EntidadResponse>(response, HttpStatus.OK);
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
