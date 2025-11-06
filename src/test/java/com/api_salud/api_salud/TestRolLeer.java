package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.api_salud.api_salud.repository.RoleDao;
import com.api_salud.api_salud.repository.UsuarioRepo;
import com.api_salud.api_salud.response.TokenInfo;
import com.api_salud.api_salud.service.JwtUtilService;

import java.util.List;

@SpringBootTest
public class TestRolLeer {

	@Autowired
	RoleDao roleDao;

	@Test
	void contextLoads() {
	List<GrantedAuthority> x = 	roleDao.findByUsername("ngeldres");
	System.out.println(x);		
	    
	 for (GrantedAuthority row : x) {
		  System.out.println("RESPONSE HORA CITA BLOQUEADA "+ row.getAuthority());
	   }	  
		  

	}	
}



