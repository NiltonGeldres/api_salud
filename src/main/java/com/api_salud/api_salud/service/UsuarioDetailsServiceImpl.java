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
import com.api_salud.api_salud.repository.UsuarioDao;
//import com.api_salud.api_salud.repository.UsuarioRepo;


@Service
//public class UsuarioDetailsServiceImpl implements UserDetailsService{
public class UsuarioDetailsServiceImpl implements UserDetailsService{

//	@Autowired
//	private UsuarioRepo repo;

//	@Autowired
//	private RoleDao role;
	
	@Autowired
    private UsuarioDao usuarioDao; // Cambiamos de UsuarioRepo a UsuarioDao

    @Autowired
    private RoleDao roleDao; // Cambiamos a roleDao para ser consistentes	
	
//	@SuppressWarnings("unused")
/*	@Override
	public UserDetails loadUserByUsername1(String username)  throws UsernameNotFoundException{
		Usuario us = repo.findByUsername(username);
		List<GrantedAuthority> roles = role.findByUsername(username);
		UserDetails  userDet = new  User(us.getUsername(), us.getPassword() , roles);
		
		return userDet ;
	}*/
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Usamos el método que ya tienes en tu UsuarioServiceImpl/Dao
        Usuario us = usuarioDao.usuarioUsernameLeer(username);
        
        if (us == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        List<GrantedAuthority> roles = roleDao.findByUsername(username);
        
        // Retornamos el User de Spring Security
        return new User(us.getUsername(), us.getPassword(), roles);
    }	
	
}
