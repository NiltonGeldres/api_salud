package com.api_salud.api_salud.response;

import java.util.List;


import com.api_salud.api_salud.entity.MedicoEntity;

public class MedicoResponse{

	String totalRecord;
	List<MedicoEntity> medico;
	
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<MedicoEntity> getMedico() {
		return medico;
	}
	public void setMedico(List<MedicoEntity> medico) {
		this.medico = medico;
	}
	
    	
	
}
