package com.api_salud.api_salud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.EntidadDao;
import com.api_salud.api_salud.request.EntidadRequest;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.EntidadesResponse;
import com.api_salud.api_salud.response.PacienteCitadoResponse;
import com.api_salud.api_salud.context.TenantContext;
import com.api_salud.api_salud.dto.EntidadDto;
import com.api_salud.api_salud.dto.PacienteCitadoDto;

@Service
public class EntidadServiceImpl implements EntidadService {
	@Autowired
	EntidadDao entidadDao ;

	@Override
	public List<EntidadResponse> obtenerEntidadesPorNombre(EntidadRequest request) {
		
	    List<EntidadDto> dtos = entidadDao.obtenerEntidadesPorNombre(request.getNombre());
	    return transformarDtosAResponses(dtos); 
	}
	
	@Override
	public EntidadesResponse xIdMedico(int idMedico) {
		return entidadDao.xIdMedico(idMedico);
	}

    public EntidadResponse obtenerEntidadDelContexto() {

        // 1️⃣ Obtener el id de la entidad desde el contexto (JWT → Filter)
        Long entidadId = TenantContext.getEntidadId();

        if (entidadId == null) {
            throw new RuntimeException("No se encontró la entidad en el contexto");
        }

        // 2️⃣ Buscar la entidad en BD
        EntidadResponse entidad = entidadDao.buscarPorId(entidadId)
            .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));

        // 3️⃣ Armar el DTO de respuesta
        EntidadResponse response = new EntidadResponse();
        response.setIdEntidad(entidad.getIdEntidad());
        response.setNombre(entidad.getNombre());
        response.setDireccion(entidad.getDireccion());
        response.setNombreDistrito(entidad.getNombreDistrito());

        return response;
    }
	
    
	
   private List<EntidadResponse> transformarDtosAResponses(List<EntidadDto> dtos) {
	     List<EntidadResponse> listaFinal = new ArrayList<>();
	     
	     for (EntidadDto dto : dtos) {
	    	 EntidadResponse res = new EntidadResponse();
	    	 
	         res.setIdEntidad(dto.getIdEntidad());
	         res.setNombre(dto.getNombre());
	         res.setCodigo(dto.getCodigo());
	         res.setDireccion(dto.getDireccion());
	         res.setNombreDistrito(dto.getNombreDistrito());
	         res.setLogoUrl(dto.getLogoUrl());
	         
	         listaFinal.add(res);
	     }
	     
	     return listaFinal;
	 }
}
