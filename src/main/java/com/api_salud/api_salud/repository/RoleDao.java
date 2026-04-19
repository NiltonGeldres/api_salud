package com.api_salud.api_salud.repository;



import java.util.List;
import org.springframework.security.core.GrantedAuthority;

import com.api_salud.api_salud.dto.RolDto;



public interface RoleDao {
	
	public List<RolDto> buscarPorIdUsuario(int  idUsuario);
	public List<GrantedAuthority> findByUsername(String  username);
	public int create(int  idUsuario);

}
