package com.api_salud.api_salud.service;

import com.api_salud.api_salud.response.EspecialidadResponse;


public interface EspecialidadService {
	public EspecialidadResponse web();
	public EspecialidadResponse web1(); 
	public EspecialidadResponse xIdMedico(int idMedico) ;
	
}
