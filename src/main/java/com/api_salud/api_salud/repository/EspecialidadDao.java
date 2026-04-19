package com.api_salud.api_salud.repository;
import com.api_salud.api_salud.response.EspecialidadResponse;
public interface EspecialidadDao {
	
//	public  EspecialidadResponse all();
//	public  EspecialidadResponse webJdbcTemplates();
	public EspecialidadResponse web();
	public EspecialidadResponse xIdMedico(int idMedico) ;
	public EspecialidadResponse xIdEntidad(Long idEntidad) ;
	
}
