package com.api_salud.api_salud.response;

import java.util.List;

import com.api_salud.api_salud.entity.EspecialidadEntity;


public class EspecialidadResponse {
		String totalRecord;
		List<EspecialidadEntity> especialidad;
		
		public String getTotalRecord() {
			return totalRecord;
		}
		public void setTotalRecord(String totalRecord) {
			this.totalRecord = totalRecord;
		}
		public List<EspecialidadEntity> getEspecialidad() {
			return especialidad;
		}
		public void setEspecialidad(List<EspecialidadEntity> especialidad) {
			this.especialidad = especialidad;
		}
	
	    
	    

}
