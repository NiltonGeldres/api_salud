package com.api_salud.api_salud.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.entity.Usuario;
//import com.api_salud.api_salud.entity.UsuarioResponse;
import com.api_salud.api_salud.request.UsuarioRequest;
import com.api_salud.api_salud.response.UsuarioResponse;
import com.api_salud.api_salud.service.UsuarioService;




@RestController
@RequestMapping("")
public class UsuarioController {

		@Autowired
		UsuarioService usuarioService ;
		
		
		@PostMapping("/signin")
		public ResponseEntity<?> signin(@RequestBody UsuarioRequest request) {
		    UsuarioResponse response = usuarioService.usuarioCrear(request);

		    // Si el Service marcó el estado como "False", es un error de negocio (duplicado)
		    if ("False".equals(response.getEstado())) {
		        return ResponseEntity
		                .status(HttpStatus.CONFLICT) // Código 409: Conflicto (duplicado)
		                .body(response); 
		    }

		    // Si todo salió bien, devolvemos 201: Creado
		    return ResponseEntity
		            .status(HttpStatus.CREATED)
		            .body(response);
		}
		
/*
	    @PostMapping("/signin1")
	   public ResponseEntity<Usuario> signin(@RequestBody Usuario request){
	        Usuario response = usuarioService.signin(request);
	        return new ResponseEntity(response, HttpStatus.OK);
	    }*/

	    
	    @PostMapping("/getUsuarioUsername")
	   public ResponseEntity<Usuario> getUsuarioUsernameLeer(@RequestBody Usuario request){
	        Usuario response = usuarioService.usuarioUsernameLeer(request.getUsername());
	        return new ResponseEntity(response, HttpStatus.OK);
	    }

	    @PostMapping("/updateUsuario")
	   public ResponseEntity<Usuario> UsuarioActualizar(@RequestBody Usuario request){
	        Usuario response = usuarioService.usuarioActualizar(request);
	        return new ResponseEntity(response, HttpStatus.OK);
	    }
}
