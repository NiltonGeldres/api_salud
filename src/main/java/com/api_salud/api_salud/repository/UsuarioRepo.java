package com.api_salud.api_salud.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.Usuario;



@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer>  {

	Usuario findByUsername(String username);
/*
   @Query(value = "{call save_usuario(:username, :password, :email)}", nativeQuery = true)
	Usuario  saveUsuario(
	            @Param("username")String username,
	            @Param("password")String password,
	            @Param("email") String email 
	    );	
*/
}


