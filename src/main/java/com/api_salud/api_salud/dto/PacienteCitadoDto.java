package com.api_salud.api_salud.dto;

public class PacienteCitadoDto {

		private int idCita ;
		private String nroHistoriaClinica ;
		private int idPaciente ;
	    private String horaInicio;
		private int idCuentaAtencion ;
	    private String horaFin;
	    private String nombres;
	    private int idEspecialidad;
	    private int idServicio;
	    private String especialidad;
	    private Integer estadoCita;
	    private Integer idAtencion;
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
		public String getNroHistoriaClinica() {
			return nroHistoriaClinica;
		}
		public void setNroHistoriaClinica(String nroHistoriaClinica) {
			this.nroHistoriaClinica = nroHistoriaClinica;
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
		public Integer getEstadoCita() {
			return estadoCita;
		}
		public void setEstadoCita(Integer estadoCita) {
			this.estadoCita = estadoCita;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public Integer getIdAtencion() {
			return idAtencion;
		}
		public void setIdAtencion(Integer idAtencion) {
			this.idAtencion = idAtencion;
		}


		
}
