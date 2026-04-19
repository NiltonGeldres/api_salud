package com.api_salud.api_salud.response;

import java.util.List;


import com.api_salud.api_salud.entity.CitaSeparadaPagoVirtualEntity;

public class CitaSeparadaPagoVirtualResponse {

	
	String totalRecord;
	List<CitaSeparadaPagoVirtualEntity> citaSeparadaPagoVirtual;
	
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<CitaSeparadaPagoVirtualEntity> getCitaSeparadaPagoVirtual() {
		return citaSeparadaPagoVirtual;
	}
	public void setCitaSeparadaPagoVirtual(List<CitaSeparadaPagoVirtualEntity> citaSeparadaPagoVirtual) {
		this.citaSeparadaPagoVirtual = citaSeparadaPagoVirtual;
	}
	
	
	
}
