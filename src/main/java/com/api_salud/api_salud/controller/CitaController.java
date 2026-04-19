package com.api_salud.api_salud.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaMedicoDiariaRequest;
import com.api_salud.api_salud.request.CitaPacientePendientesRequest;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.PacienteCitadoResponse;
import com.api_salud.api_salud.service.CitaService;


@RestController
@RequestMapping("")
public class CitaController {
	
	@Autowired
	CitaService citaService ;


	@GetMapping("/citas/paciente-pendientes")
	public ResponseEntity<List<PacienteCitadoResponse>> listarCitasPendientes(
	        CitaPacientePendientesRequest request) { // Spring mapea ?idMedico=...&fecha=... automáticamente
	    
	    List<PacienteCitadoResponse> response = citaService.citaPacienteListarPendientes(request);
	    
	    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
	}
	
	@GetMapping("/citas/medico-diaria")
	public ResponseEntity<List<PacienteCitadoResponse>> listarCitasDiarias(
	        CitaMedicoDiariaRequest request) { // Spring mapea ?idMedico=...&fecha=... automáticamente
	    
	    List<PacienteCitadoResponse> response = citaService.citaMedicoListarDiaria(request);
	    
	    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
	}
    
	
    @PostMapping("/citaDisponible")
    public ResponseEntity<CitaResponse> disponible(@RequestBody CitaRequest request){
    	CitaResponse response = citaService.citaDisponible(request);
        return new ResponseEntity<CitaResponse>(response, HttpStatus.OK);
    }	
  
    @PostMapping("/crearCita")
    public ResponseEntity<?> crearCita( @RequestBody @Valid CitaFacturacionRequest   request ,  BindingResult bindingResult){
    	
    return null;	
   	}
        
    
}
