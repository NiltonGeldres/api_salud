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
import com.api_salud.api_salud.service.JwtUtilService;

@RestController
@RequestMapping("")
public class AuthController {
	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Autowired
	  UserDetailsService usuarioDetailsService;

	  @Autowired
	  private JwtUtilService jwtUtilService;
	  
	  @PostMapping("/auth")
	  public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
		  System.out.println("authenticate Usuario "+authenticationReq.getUser());
		  System.out.println("authenticate password "+authenticationReq.getPassword());
		  authenticationManager.authenticate(
			   new UsernamePasswordAuthenticationToken(
					   authenticationReq.getUser(),
					   authenticationReq.getPassword()
				)
		  );
		  
		System.out.println("Paso authenticationManager   ");
	    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(authenticationReq.getUser());
	    System.out.println("Userdetails:   "+ userDetails.toString());
	    
	    final String jwt = jwtUtilService.generateToken(userDetails);
	    TokenInfo tokenInfo = new TokenInfo(jwt);
	    System.out.println( "Token:  "+ResponseEntity.ok(tokenInfo));	    
	    return ResponseEntity.ok(tokenInfo);
	  }	  

}
