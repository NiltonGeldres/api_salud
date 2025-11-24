package com.api_salud.api_salud.repository;


import com.api_salud.api_salud.entity.Usuario;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * Contrato para el acceso a datos del Usuario, implementado por JDBC.
 */

public interface UsuarioRepo {
	
    /**
     * Busca un usuario por su nombre de usuario (utilizado para la autenticación).
     * @param nombreUsuario el nombre de usuario a buscar.
     * @return Un Optional que contiene el Usuario si se encuentra.
     */
	Optional<Usuario> findByUsername(String nombreUsuario);
	
	// Si necesitas la función saveUsuario, también debe definirse aquí sin anotaciones JPA.
	// int saveUsuario(String username, String password, String email);
}


/*
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.Usuario;



@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer>  {
	Usuario findByUsername(String username);
*/	
/*
   @Query(value = "{call save_usuario(:username, :password, :email)}", nativeQuery = true)
	Usuario  saveUsuario(
	            @Param("username")String username,
	            @Param("password")String password,
	            @Param("email") String email 
	    );	
*/
//}


