package com.api_salud.api_salud.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api_salud.api_salud.request.AuthenticationReq;
import com.api_salud.api_salud.response.TokenInfo;
import com.api_salud.api_salud.service.AuthService;
import com.api_salud.api_salud.service.JwtUtilService;

@RestController
@RequestMapping("")
public class AuthController {
	

	  @Autowired
	  UserDetailsService usuarioDetailsService;

	    @Autowired
	    private AuthService authService; // Creamos un servicio para orquestar esto

	    @PostMapping("/auth")
	    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authReq) {
	        TokenInfo tokenInfo = authService.login(authReq.getUser(), authReq.getPassword());
	        return ResponseEntity.ok(tokenInfo);
	    }
	  

	  
}
