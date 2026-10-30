package com.api_salud.api_salud.repository;



import java.util.List;
import org.springframework.security.core.GrantedAuthority;

import com.api_salud.api_salud.dto.RolDto;
import com.api_salud.api_salud.entity.Role;



public interface RoleDao {
	
	public List<RolDto> buscarPorIdUsuario(int  idUsuario);
	public List<GrantedAuthority> findByUsername(String  username);
	public int create(int  idUsuario);

	// Nuevos métodos para el catálogo de roles
    public List<Role> listarRolesCatalog();
    public Role crearRolCatalog(String nombre);	
}
