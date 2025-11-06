package com.api_salud.api_salud.repository;



import java.util.List;
import org.springframework.security.core.GrantedAuthority;



public interface RoleDao {
	
	public List<GrantedAuthority> findByUsername(String  username);
	public int create(int  idUsuario);

}
