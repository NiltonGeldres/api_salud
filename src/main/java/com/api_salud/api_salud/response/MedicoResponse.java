package com.api_salud.api_salud.response;

import java.util.List;

import com.api_salud.api_salud.dto.MedicoDTO;


public class MedicoResponse{

	String totalRecord;
	List<MedicoDTO> medico;
	
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<MedicoDTO> getMedico() {
		return medico;
	}
	public void setMedico(List<MedicoDTO> medico) {
		this.medico = medico;
	}
	
	
    	
	
}
