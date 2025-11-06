package com.api_salud.api_salud.response;

import java.util.List;

import com.api_salud.api_salud.entity.EntidadEntity;

public class EntidadResponse {
	String totalRecord;
	List<EntidadEntity> entidad;
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<EntidadEntity> getEntidad() {
		return entidad;
	}
	public void setEntidad(List<EntidadEntity> entidad) {
		this.entidad = entidad;
	}
	
	
	

}
