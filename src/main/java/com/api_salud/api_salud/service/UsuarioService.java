package com.api_salud.api_salud.service;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioResponse;

public interface UsuarioService {
	
	public Usuario signin(Usuario request);

	public Integer xusername_leer(String request);

	public Usuario usuarioUsernameLeer(String userNamet) ;

	public Usuario usuarioActualizar(Usuario request) ;	

	public Usuario usuarioLeer(int id_usuario) ;

}
