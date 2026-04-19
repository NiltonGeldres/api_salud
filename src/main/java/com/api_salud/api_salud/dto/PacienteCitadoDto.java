package com.api_salud.api_salud.dto;

public class PacienteCitadoDto {

		private int idCita ;
	    private String horaInicio;
	    private String horaFin;
	    private String nombres;
	    private String especialidad;
	    private boolean estadoCita;
	    private String fecha;

	    
		public int getIdCita() {
			return idCita;
		}
		public void setIdCita(int idCita) {
			this.idCita = idCita;
		}
		public String getHoraInicio() {
			return horaInicio;
		}
		public void setHoraInicio(String horaInicio) {
			this.horaInicio = horaInicio;
		}
		public String getHoraFin() {
			return horaFin;
		}
		public void setHoraFin(String horaFin) {
			this.horaFin = horaFin;
		}
		public String getNombres() {
			return nombres;
		}
		public void setNombres(String nombres) {
			this.nombres = nombres;
		}
		public String getEspecialidad() {
			return especialidad;
		}
		public void setEspecialidad(String especialidad) {
			this.especialidad = especialidad;
		}
		public boolean getEstadoCita() {
			return estadoCita;
		}
		public void setEstadoCita(boolean estadoCita) {
			this.estadoCita = estadoCita;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}


}
