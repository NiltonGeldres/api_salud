package com.api_salud.api_salud.entity;


public class CitadoEntity {
	
		int id;
		String nroHistoriaClinica;
		String nombres;
		String servicio;
		String horaSolicitud;
		String turno;	
		String estado;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNroHistoriaClinica() {
			return nroHistoriaClinica;
		}
		public void setNroHistoriaClinica(String nroHistoriaClinica) {
			this.nroHistoriaClinica = nroHistoriaClinica;
		}
		public String getNombres() {
			return nombres;
		}
		public void setNombres(String nombres) {
			this.nombres = nombres;
		}
		public String getServicio() {
			return servicio;
		}
		public void setServicio(String servicio) {
			this.servicio = servicio;
		}
		public String getHoraSolicitud() {
			return horaSolicitud;
		}
		public void setHoraSolicitud(String horaSolicitud) {
			this.horaSolicitud = horaSolicitud;
		}
		public String getTurno() {
			return turno;
		}
		public void setTurno(String turno) {
			this.turno = turno;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
	

}
