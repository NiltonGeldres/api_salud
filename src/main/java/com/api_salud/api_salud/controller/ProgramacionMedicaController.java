package com.api_salud.api_salud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.dto.ProgramacionMedicaDTO;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.ProgramacionMedicaCrearRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.ProgramacionMedicaDiaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaMesResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.api_salud.api_salud.service.MedicoService;
import com.api_salud.api_salud.service.ProgramacionMedicaService;
import com.api_salud.api_salud.service.UsuarioService;

@RestController
@RequestMapping("")
public class ProgramacionMedicaController {

	@Autowired
	ProgramacionMedicaService programacionMedicaService ;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	MedicoService medicoService;	

	
    @PostMapping("/programacionMedicoTodos")
   public ResponseEntity<ProgramacionMedicaResponse> medicoTodos(@RequestBody ProgramacionMedicaRequest request){
        ProgramacionMedicaResponse response =  programacionMedicaService.medicoTodos(request);
        return new ResponseEntity<ProgramacionMedicaResponse>(response, HttpStatus.OK);
        		
    }
    
    
    @PostMapping("/programacionMedicoMes")
	public ResponseEntity<?> programacionMedicoMesLeer(@RequestBody ProgramacionMedicaDTO  request) {

		int idMedico = 0;
		String usuario= request.getUsuario();
		 idMedico = medicoService.medicoXUsuarioLeer(usuario);    	
		
		/*ProgramacionMedicaMesResponse response =  programacionMedicaService.programacionMedicoMesLeer(
        		request.getMes(),
        		request.getAnio(),
        		idMedico,
        		request.getIdEspecialidad()
        		);*/
		
		ProgramacionMedicaMesResponse response =  programacionMedicaService.programacionMedicoMesLeer(
	 		request.getMes(),
	 		request.getAnio(),
	 		request.getIdMedico(),
	 		request.getIdEspecialidad()
 		);
	
		if (response!= null)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
		return null;	
	}

    @PostMapping("/programacionMedicoMesBlanco")
	public ResponseEntity<?> programacionMedicoMesBlancoLeer(@RequestBody   ProgramacionMedicaDTO request) {

		int idMedico = 0;
		String usuario= request.getUsuario();
		 idMedico = medicoService.medicoXUsuarioLeer(usuario);    	
		
		ProgramacionMedicaMesResponse response =  programacionMedicaService.programacionMedicoMesBlancoLeer(
        		request.getMes(),
        		request.getAnio(),
        		idMedico,
        		request.getIdEspecialidad()
        		);
		if (response!= null)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
		return null;	
	}
	
    @PostMapping("/programacionMedicaCrear")
	public ResponseEntity<?> programacionMedicaCrear(@RequestBody ProgramacionMedicaCrearRequest request) {
    	int   idUsuario = usuarioService.xusername_leer(request.getUsuario());  
		ProgramacionMedicaMesResponse response =  programacionMedicaService.programacionMedicoCrear(request,  idUsuario);
		if (response!= null)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
		return null;	
	}
	

    
	  static class medicoMesRequest  {
		int mes;
		int anio;
		String  usuario;
		int idEspecialidad;
		int idMedico;
		int idServicio;
		
		public int getMes() {
			return mes;
		}
		public void setMes(int mes) {
			this.mes = mes;
		}
		public int getAnio() {
			return anio;
		}
		public void setAnio(int anio) {
			this.anio = anio;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public int getIdEspecialidad() {
			return idEspecialidad;
		}
		public void setIdEspecialidad(int idEspecialidad) {
			this.idEspecialidad = idEspecialidad;
		}
		public int getIdMedico() {
			return idMedico;
		}
		public void setIdMedico(int idMedico) {
			this.idMedico = idMedico;
		}
		public int getIdServicio() {
			return idServicio;
		}
		public void setIdServicio(int idServicio) {
			this.idServicio = idServicio;
		}
		
		
	}    

}


