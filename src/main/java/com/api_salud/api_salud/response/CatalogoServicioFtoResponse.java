package com.api_salud.api_salud.response;

import java.util.List;

import com.api_salud.api_salud.entity.CatalogoServicioFtoEntity;


public class CatalogoServicioFtoResponse {

	String totalRecord;
	List<CatalogoServicioFtoEntity> catalogoServicioFto;
	
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<CatalogoServicioFtoEntity> getCatalogoServicioFto() {
		return catalogoServicioFto;
	}
	public void setCatalogoServicioFto(List<CatalogoServicioFtoEntity> catalogoServicioFto) {
		this.catalogoServicioFto = catalogoServicioFto;
	}
	
	
}
