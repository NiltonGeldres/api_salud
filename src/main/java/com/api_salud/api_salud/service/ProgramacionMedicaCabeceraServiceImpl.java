package com.api_salud.api_salud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.ProgramacionMedicaCabeceraEntity;
import com.api_salud.api_salud.repository.ProgramacionMedicaCabeceraDao;
@Service
public class ProgramacionMedicaCabeceraServiceImpl implements ProgramacionMedicaCabeceraService{
	@Autowired
	ProgramacionMedicaCabeceraDao programacionMedicaCabeceraDao;
	
	@Override
	public List<ProgramacionMedicaCabeceraEntity> xMedicoMesLeer(int mes, int ano, int idMedico) {
		
		return programacionMedicaCabeceraDao.xMedicoMesLeer(mes, ano, idMedico);
	}

	@Override
	public int crear(ProgramacionMedicaCabeceraEntity request) {
		return programacionMedicaCabeceraDao.crear(request);
	}

	@Override
	public int actualizar(ProgramacionMedicaCabeceraEntity programacion) {
		return programacionMedicaCabeceraDao.actualizar(programacion);
	}
	
	public void eliminarXIdProgramacionMedicaCabecera(int idProgramacionMedicaCabecera) {
		 programacionMedicaCabeceraDao.eliminarXIdProgramacionMedicaCabecera(idProgramacionMedicaCabecera);
	}	
	

}
