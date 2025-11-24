package com.api_salud.api_salud.service;


import java.util.List;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.RoleDao;
import com.api_salud.api_salud.repository.UsuarioRepo; // Ahora es la interfaz sin JPA

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepo repo; // Usará la nueva implementación UsuarioRepoImpl (por inyección de dependencia)
	
	@Autowired
	private RoleDao role;
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario)  throws UsernameNotFoundException{
		System.out.println("Consultando usuario "+ nombreUsuario);
		
        // Usamos findByNombreUsuario (de la nueva interfaz)
		Optional<Usuario> optionalUs = repo.findByUsername(nombreUsuario); 
        
        if (!optionalUs.isPresent()) {
            System.out.println("Usuario NO encontrado: " + nombreUsuario);
            throw new UsernameNotFoundException("Usuario no encontrado: " + nombreUsuario);
        }
        
        Usuario us = optionalUs.get();
        
		System.out.println("Consultando rol ------=====> loadUserByUsername");
		List<GrantedAuthority> roles = role.findByUsername(nombreUsuario); // Pasamos el nombreUsuario
		System.out.println("termino");
		
		// Utilizamos el nombre de usuario y el password de la entidad Usuario
		UserDetails  userDet = new  User(us.getUsername(), us.getPassword() , roles);
		
		return userDet ;
	}
}


/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.RoleDao;
import com.api_salud.api_salud.repository.UsuarioRepo;


@Service

public class UsuarioDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepo repo;

	@Autowired
	private RoleDao role;
	
	@Override
	public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
		System.out.println("Consultando usuario "+ username);
		Usuario us = repo.findByUsername(username);
		System.out.println("Consultando rol ------=====> loadUserByUsername");
		System.out.println(" ------=====> loadUserByUsername");
		System.out.println("------=====> loadUserByUsername");
		System.out.println(" SI SE USAAAAAAA ------=====> loadUserByUsername");
		List<GrantedAuthority> roles = role.findByUsername(username);
		System.out.println("termino");
		
		UserDetails  userDet = new  User(us.getUsername(), us.getPassword() , roles);
		
		return userDet ;
	}
}
*/