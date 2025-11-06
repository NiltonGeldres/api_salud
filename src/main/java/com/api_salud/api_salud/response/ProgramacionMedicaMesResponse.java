package com.api_salud.api_salud.response;

import java.util.List;

public class ProgramacionMedicaMesResponse {
	int totalRegistros;
	int numeroPagina ;
	List<ProgramacionMedicaDiaResponse>  ProgramacionMedicaDiaResponse ;
	
	public int getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public int getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	public List<ProgramacionMedicaDiaResponse> getProgramacionMedicaDiaResponse() {
		return ProgramacionMedicaDiaResponse;
	}
	public void setProgramacionMedicaDiaResponse(List<ProgramacionMedicaDiaResponse> programacionMedicaDiaResponse) {
		ProgramacionMedicaDiaResponse = programacionMedicaDiaResponse;
	}
	
	

}
