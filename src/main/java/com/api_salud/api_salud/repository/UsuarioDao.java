package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioResponse;


public interface UsuarioDao {

	public Usuario usuarioSave(Usuario request);
	
	public Usuario usuarioLeer(int id_usuario);

	public int xusername_leer(String usuario);
	
	public Usuario usuarioUsernameLeer(String username);
	
	public Usuario usuarioActualizar(Usuario request);

}
