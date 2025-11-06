package com.api_salud.api_salud.request;

import javax.persistence.Id;

public class TurnoRequest {
	@Id
	int id_turno;

	public int getId_turno() {
		return id_turno;
	}

	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}
	
	
}
