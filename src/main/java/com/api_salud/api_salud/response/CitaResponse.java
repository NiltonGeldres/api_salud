package com.api_salud.api_salud.response;

import java.util.List;




public class CitaResponse {
	String totalRecord;
	List<CitaDisponibleResponse> cita;
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<CitaDisponibleResponse> getCita() {
		return cita;
	}
	public void setCita(List<CitaDisponibleResponse> cita) {
		this.cita = cita;
	}
	
	
	
}
