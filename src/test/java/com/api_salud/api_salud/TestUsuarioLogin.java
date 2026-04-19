package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.repository.UsuarioRepo;
import com.api_salud.api_salud.response.TokenInfo;
import com.api_salud.api_salud.service.JwtUtilService;

@SpringBootTest
public class TestUsuarioLogin {

	  @Autowired
	  private AuthenticationManager authenticationManager;
	
		@Autowired
		UsuarioRepo dao;

		  @Autowired
		  private JwtUtilService jwtUtilService;

		  @Autowired
		  UserDetailsService usuarioDetailsService;		  
		  
		@Test
		void contextLoads() {
			
		    System.out.println("Iniciando autenticacion");
		    /*Usuario 1*/
/*		    
			authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken
					( "ngeldres", "09800710")
			  );
*/			
		    /*Usuario 2*/
			authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken
					( "ngeldres", "1234")
			  );
			
		    System.out.println("Authenticado");
			/*  
		    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername("ngeldres");
		    System.out.println("Userdetails:   "+ userDetails.toString());
		    
		    final String jwt = jwtUtilService.generateToken(userDetails,"",0);
		    TokenInfo tokenInfo = new TokenInfo(jwt);
		    System.out.println( "Token:  "+ResponseEntity.ok(tokenInfo));
		 */   
			
		    

		}	

}


