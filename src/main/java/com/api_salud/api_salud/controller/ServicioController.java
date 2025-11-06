package com.api_salud.api_salud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.response.ServicioResponse;
import com.api_salud.api_salud.service.MedicoService;
import com.api_salud.api_salud.service.ServicioService;

@RestController
@RequestMapping("")
public class ServicioController {

	@Autowired
	ServicioService servicioService ;
	
	@Autowired
	MedicoService medicoService ;
	
    @PostMapping("/servicios")
   public ResponseEntity<ServicioResponse> servicios(@RequestBody servicioRequest request){
    	int idEntidadEspecialidad = request.getIdEntidadEspecialidad();
    	ServicioResponse response = servicioService.xIdEntidadEspecialidad(idEntidadEspecialidad);
        return new ResponseEntity<ServicioResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/serviciosxentidad")
   public ResponseEntity<ServicioResponse> serviciosxentidad(@RequestBody servicioRequest request){
    	int idEntidad = request.getIdEntidad();
    	ServicioResponse response = servicioService.xIdEntidad(idEntidad);
        return new ResponseEntity<ServicioResponse>(response, HttpStatus.OK);
    }
    
    static class servicioRequest {
    	int idEntidadEspecialidad;
    	int idEntidad;

		public int getIdEntidadEspecialidad() {
			return idEntidadEspecialidad;
		}

		public void setIdEntidadEspecialidad(int idEntidadEspecialidad) {
			this.idEntidadEspecialidad = idEntidadEspecialidad;
		}

   	
		public int getIdEntidad() {
			return idEntidad;
		}

		public void setIdEntidad(int idEntidad) {
			this.idEntidad = idEntidad;
		}

	
    }

}
