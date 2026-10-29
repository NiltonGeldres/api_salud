package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.response.MedicoResponse;

import java.util.List;

import com.api_salud.api_salud.dto.MedicoDTO;
import com.api_salud.api_salud.request.MedicoRequest;
public interface MedicoDao {
	
//	public MedicoResponse medicoEspecialidad(int idEspecialidad);
	
    public int registrarMedico(MedicoRequest req);
//    public List<MedicoDTO> listarMedicosPaginado(int idEntidad, String filtro, String estado, int limit, int offset) ;
    public boolean actualizarMedico(int idMedico, MedicoRequest req) ;
    public boolean darBajaMedico(int idMedico, int idEntidad, String estado);
    public List<MedicoDTO> listarMedicos(int idEntidad, String filtro, String estado, int limit, int offset);
    
	public List<MedicoDTO> medicoEspecialidad(int idEspecialidad);	
	public int  medicoXUsuarioLeer(String usuario) ;
	public int tiempoPromedioAtencion_leer(int idMedico, int idEspecialidad) ;	
	public MedicoResponse medicoXIdMedicosXEspecialidad(int idEspecialidad, int idMedico) ;
	public List<MedicoDTO> medicoEntidad(int idEntidad);			
	
}
