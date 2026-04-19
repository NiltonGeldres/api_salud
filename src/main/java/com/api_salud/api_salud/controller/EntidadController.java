package com.api_salud.api_salud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.request.CitaPacientePendientesRequest;
import com.api_salud.api_salud.request.EntidadRequest;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.EntidadesResponse;
import com.api_salud.api_salud.response.PacienteCitadoResponse;
import com.api_salud.api_salud.service.EntidadService;
import com.api_salud.api_salud.service.MedicoService;


@RestController
@RequestMapping("")
public class EntidadController {
	
	@Autowired
	EntidadService entidadService ;

	
	@Autowired
	MedicoService medicoService ;
	
	@GetMapping("/entidad_por_nombre")
	public ResponseEntity<List<EntidadResponse>> obtenerEntidadesPorNombre(EntidadRequest request) {
	    List<EntidadResponse> response = entidadService.obtenerEntidadesPorNombre(request);
	    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
	}	
	
    @PostMapping("/entidades")
   public ResponseEntity<EntidadesResponse> entidades(@RequestBody entidadRequest request){
    	int idMedico = medicoService.medicoXUsuarioLeer(request.getUsuario());
    	EntidadesResponse response = entidadService.xIdMedico(idMedico);
        return new ResponseEntity<EntidadesResponse>(response, HttpStatus.OK);
    }


    @PostMapping("/entidad")
    public ResponseEntity<EntidadResponse> obtenerEntidadContexto() {
        EntidadResponse response = entidadService.obtenerEntidadDelContexto();
        return new ResponseEntity<>(response, HttpStatus.OK);
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
