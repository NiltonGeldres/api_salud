package com.api_salud.api_salud.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api_salud.api_salud.dto.UsuarioDto;
import com.api_salud.api_salud.entity.UsuarioEntity;
import com.api_salud.api_salud.request.UsuarioRequest;
import com.api_salud.api_salud.response.UsuarioResponse;

@Component
public class UsuarioMapper {

    // Convierte lo que viene del Frontend (Request) a lo que entiende la DB (Entity)
    public UsuarioEntity requestToEntity(UsuarioRequest request) {
        if (request == null) return null;
        UsuarioEntity entity = new UsuarioEntity();
        BeanUtils.copyProperties(request, entity); // Copia campos con nombres iguales
        return entity;
    }

    // Convierte lo que sale de la DB (Entity/Dto interno) a la respuesta final (Response)
    public UsuarioResponse dtoToResponse(UsuarioDto dto) {
        if (dto == null) return null;

        UsuarioResponse response = new UsuarioResponse();
        
        // Copiamos idUsuario, username, email y estado
        BeanUtils.copyProperties(dto, response);
        
        // RECOMENDACIÓN DE SEGURIDAD: 
        // Aunque el Response tiene el campo password, nunca lo enviamos de vuelta.
        response.setPassword("********"); 
        
        return response;
    }
}