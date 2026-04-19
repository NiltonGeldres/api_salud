package com.api_salud.api_salud.response;

import java.util.List;

import com.api_salud.api_salud.entity.CitaSeparadaEntity;


public class CitaSeparadaResponse {
	
	String totalRecord;
	List<CitaSeparadaEntity> citaSeparada;
	
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<CitaSeparadaEntity> getCitaSeparada() {
		return citaSeparada;
	}
	public void setCitaSeparada(List<CitaSeparadaEntity> citaSeparada) {
		this.citaSeparada = citaSeparada;
	}
	
}
