package com.api_salud.api_salud.response;

import java.util.List;


import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;

public class ProgramacionMedicaResponse {
	String totalRecord;
	List<ProgramacionMedicaEntity> programacionMedica;
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<ProgramacionMedicaEntity> getProgramacionMedica() {
		return programacionMedica;
	}
	public void setProgramacionMedica(List<ProgramacionMedicaEntity> programacionMedica) {
		this.programacionMedica = programacionMedica;
	}
	
	
	
}
