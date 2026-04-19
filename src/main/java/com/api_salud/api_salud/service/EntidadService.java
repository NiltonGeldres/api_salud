package com.api_salud.api_salud.service;

import java.util.List;

import com.api_salud.api_salud.request.EntidadRequest;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.EntidadesResponse;

public interface EntidadService {
	public List<EntidadResponse> obtenerEntidadesPorNombre(EntidadRequest request);
	public EntidadesResponse xIdMedico(int idMedico);
    public EntidadResponse obtenerEntidadDelContexto() ;

}
