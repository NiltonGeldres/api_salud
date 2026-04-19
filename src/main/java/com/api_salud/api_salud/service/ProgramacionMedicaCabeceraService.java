package com.api_salud.api_salud.service;

import java.util.List;

import com.api_salud.api_salud.dto.ProgramacionMedicaCabeceraDTO;
import com.api_salud.api_salud.entity.ProgramacionMedicaCabeceraEntity;

public interface ProgramacionMedicaCabeceraService {

	public List<ProgramacionMedicaCabeceraDTO>  xMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) ;	
	public int crear(ProgramacionMedicaCabeceraEntity request ) ;
	public int actualizar(ProgramacionMedicaCabeceraEntity programacion) ;	
	public void eliminarXIdProgramacionMedicaCabecera(int idProgramacionMedicaCabecera);	

}
