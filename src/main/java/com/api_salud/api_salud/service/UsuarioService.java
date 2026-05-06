package com.api_salud.api_salud.service;

import com.api_salud.api_salud.dto.UsuarioDto;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioEntity;

import com.api_salud.api_salud.request.UsuarioRequest;
import com.api_salud.api_salud.response.UsuarioDatosGlobalesResponse;
import com.api_salud.api_salud.response.UsuarioResponse;

public interface UsuarioService {
	
    public UsuarioResponse usuarioCrear(UsuarioRequest request);

	public Usuario signin(Usuario request);

	public Integer xusername_leer(String request);

	public Usuario usuarioUsernameLeer(String userNamet) ;

	public Usuario usuarioActualizar(Usuario request) ;	

	public Usuario usuarioLeer(int id_usuario) ;

	UsuarioDatosGlobalesResponse usuarioDatosGlobales(int idUsuario);

}
