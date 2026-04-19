package com.api_salud.api_salud.repository;

import java.util.List;
import java.util.Optional;

import com.api_salud.api_salud.dto.EntidadDto;
import com.api_salud.api_salud.entity.EntidadEntity;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.EntidadesResponse;

public interface  EntidadDao {

	public List<EntidadDto> obtenerEntidadesPorNombre(String nombre); 	
	public EntidadesResponse   xIdMedico(int idMedicos);
	public Optional<EntidadResponse> buscarPorId(Long idEntidad);

}
