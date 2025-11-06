package com.api_salud.api_salud.service;


import java.util.List;


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
//public class UsuarioDetailsServiceImpl implements UserDetailsService{
public class UsuarioDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepo repo;

	@Autowired
	private RoleDao role;
	
//	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
		System.out.println("Consultando usuario "+ username);
		Usuario us = repo.findByUsername(username);
		System.out.println("Consultando rol");
		List<GrantedAuthority> roles = role.findByUsername(username);
		System.out.println("termino");
		
		UserDetails  userDet = new  User(us.getUsername(), us.getPassword() , roles);
		
		return userDet ;
	}
}
