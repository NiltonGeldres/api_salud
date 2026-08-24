package com.api_salud.api_salud.dto;

public class PacienteCitadoDto {

		private int idPaciente ;
		private int idCita ;
	    private String horaInicio;
		private int idCuentaAtencion ;
		private int idEstadoAtencion ;
	    private String estadoFirma;
	    private String horaFin;
	    private String nombres;
	    private int idEspecialidad;
	    private int idServicio;
	    private String especialidad;
	    private boolean estadoCita;
	    private String fecha;
	    
	    
		public int getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}
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
		public int getIdCuentaAtencion() {
			return idCuentaAtencion;
		}
		public void setIdCuentaAtencion(int idCuentaAtencion) {
			this.idCuentaAtencion = idCuentaAtencion;
		}
		public int getIdEstadoAtencion() {
			return idEstadoAtencion;
		}
		public void setIdEstadoAtencion(int idEstadoAtencion) {
			this.idEstadoAtencion = idEstadoAtencion;
		}
		public String getEstadoFirma() {
			return estadoFirma;
		}
		public void setEstadoFirma(String estadoFirma) {
			this.estadoFirma = estadoFirma;
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
		public int getIdEspecialidad() {
			return idEspecialidad;
		}
		public void setIdEspecialidad(int idEspecialidad) {
			this.idEspecialidad = idEspecialidad;
		}
		public int getIdServicio() {
			return idServicio;
		}
		public void setIdServicio(int idServicio) {
			this.idServicio = idServicio;
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
