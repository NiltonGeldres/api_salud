package com.api_salud.api_salud.service;

import java.util.List;

import com.api_salud.api_salud.dto.MedicoDTO;
import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.MedicoResponse;

public interface  MedicoService {
	public Integer registrarMedicoCompleto(MedicoRequest request) ;
	public List<MedicoDTO> listarMedicosPaginado(int idEntidad, String filtro, String estado, int limit, int offset);
	public boolean actualizarMedico(int idMedico, MedicoRequest request);
	public boolean darBajaMedico(int idMedico, int idEntidad, String estado);
	  
	public MedicoResponse medicoEspecialidad(MedicoRequest request); 	
	public int  medicoXUsuarioLeer(String usuario) ;
	public int tiempoPromedioAtencion_leer(int idMedico, int idEspecialidad) ;	
	public MedicoResponse medicoXIdMedicosXEspecialidad(int idEspecialidad, int idMedico) ;
	public MedicoResponse medicoEntidad(MedicoRequest request) ;

	
}
