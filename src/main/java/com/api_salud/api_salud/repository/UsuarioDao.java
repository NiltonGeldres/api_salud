package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.dto.UsuarioDto;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioEntity;

 

public interface UsuarioDao {

	public String validarRegistroCompleto(UsuarioEntity usuario) ;
	
	public UsuarioDto buscarPorIdUsuario(int idUsuario) ;
	
	public int guardar(UsuarioEntity request);
	
//	public Usuario usuarioSave(UsuarioEntity request);
	public Usuario usuarioSave(Usuario request);
	
	public Usuario usuarioLeer(int id_usuario);

	public int xusername_leer(String usuario);
	
	public Usuario usuarioUsernameLeer(String username);
	
	public Usuario usuarioActualizar(Usuario request);

}
