package com.api_salud.api_salud.service;

import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.request.PacienteRequest;
import com.api_salud.api_salud.request.UsuarioRequest;

public interface PacienteService {

	public int crearDesdeRegistro(UsuarioRequest request);
    public int pacienteCrear(PacienteRequest request) ;
	public int crear(PacienteEntity request) ;
	public PacienteEntity leerNroDocumento(PacienteEntity request) ;
	public PacienteEntity leerNombres(String  request) ;
	public PacienteEntity actualizar(PacienteEntity request) ;

	public int crearConUsuario(Usuario usuario) ;
	
	public int leerIdPacientexIdUsuario(int  idusuario) ;
	
}
