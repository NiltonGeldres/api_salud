package com.api_salud.api_salud.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioResponse;
import com.api_salud.api_salud.service.UsuarioService;




@RestController
@RequestMapping("")
public class UsuarioController {

		@Autowired
		UsuarioService usuarioService ;
		
	    @PostMapping("/signin")
	   public ResponseEntity<Usuario> signin(@RequestBody Usuario request){
	        Usuario response = usuarioService.signin(request);
	        return new ResponseEntity(response, HttpStatus.OK);
	    }

	    @PostMapping("/getUsuarioUsername")
	   public ResponseEntity<Usuario> getUsuarioUsernameLeer(@RequestBody Usuario request){
	    	System.out.println("USERNAME"+request.getUsername());
	        Usuario response = usuarioService.usuarioUsernameLeer(request.getUsername());
	        return new ResponseEntity(response, HttpStatus.OK);
	    }

	    @PostMapping("/updateUsuario")
	   public ResponseEntity<Usuario> UsuarioActualizar(@RequestBody Usuario request){
	    	System.out.println("USERNAME "+request.getUsername());
	        Usuario response = usuarioService.usuarioActualizar(request);
	        return new ResponseEntity(response, HttpStatus.OK);
	    }
	    
	    
}
