package com.api_salud.api_salud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.dto.MedicoDTO;
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
	

	// --- Nuevos Endpoints CRUD (Registro Atómico, Listado Paginado, Actualización y Baja) ---

// --- Nuevos Endpoints CRUD (Respetando la estructura de rutas directas) ---

    @PostMapping("/medicoRegistrar")
    public ResponseEntity<Object> registrarMedico(@RequestBody MedicoRequest request) {
        try {
            Integer idMedico = medicoService.registrarMedicoCompleto(request);
            return new ResponseEntity<>("Médico registrado exitosamente con ID: " + idMedico, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar médico: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medicosListar/{idEntidad}")
    public ResponseEntity<List<MedicoDTO>> listarMedicosPaginado(
            @PathVariable int idEntidad,
            @RequestParam(defaultValue = "") String filtro,
            @RequestParam(defaultValue = "ACTIVO") String estado,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        
        List<MedicoDTO> medicos = medicoService.listarMedicos(idEntidad, filtro, estado, limit, offset);
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @PutMapping("/medicoActualizar/{idMedico}")
    public ResponseEntity<Object> actualizarMedico(
            @PathVariable int idMedico, 
            @RequestBody MedicoRequest request) {
        try {
            boolean actualizado = medicoService.actualizarMedico(idMedico, request);
            if (actualizado) {
                return new ResponseEntity<>("Médico actualizado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se pudo actualizar el médico", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/medicoBaja/{idMedico}")
    public ResponseEntity<Object> darBajaMedico(
            @PathVariable int idMedico,
            @RequestParam int idEntidad,
            @RequestParam(defaultValue = "INACTIVO") String estado) {
        try {
            boolean dadoDeBaja = medicoService.darBajaMedico(idMedico, idEntidad, estado);
            if (dadoDeBaja) {
                return new ResponseEntity<>("Médico dado de baja exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se pudo procesar la baja del médico", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al dar de baja: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

	@PostMapping("/medicosEntidad")
	   public ResponseEntity<MedicoResponse> medicoEntidad(@RequestBody MedicoRequest request){
	        MedicoResponse response = medicoService.medicoEntidad(request);
	        return new ResponseEntity(response, HttpStatus.OK);
	}
	   
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


	
		
			

	     