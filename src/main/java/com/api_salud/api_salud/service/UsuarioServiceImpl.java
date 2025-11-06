package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioResponse;
import com.api_salud.api_salud.repository.UsuarioDao;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioDao dao;
	
	@Override
	public Usuario signin(Usuario request) {
		// TODO Auto-generated method stub
		return dao.usuarioSave(request);
	}


	@Override
	public Integer xusername_leer(String request) {		// TODO Auto-generated method stub
		return dao.xusername_leer(request);
	}
	
		
	@Override
	public Usuario usuarioUsernameLeer(String username) {
		// TODO Auto-generated method stub
		return dao.usuarioUsernameLeer(username);
	}
	
	@Override
	public Usuario usuarioActualizar(Usuario request) {
		// TODO Auto-generated method stub
		return dao.usuarioActualizar(request);
	}

	public Usuario usuarioLeer(int id_usuario) {
		
		return dao.usuarioLeer(id_usuario);
		
	}	

}
