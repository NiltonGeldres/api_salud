package com.api_salud.api_salud.mapper;

import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.request.PacienteRequest;
import com.api_salud.api_salud.request.UsuarioRequest;

public interface PacienteMapper {
	
	public PacienteEntity requestToEntity(PacienteRequest request);
    public PacienteEntity usuarioRequestToPacienteEntity(UsuarioRequest request) ;
   
}