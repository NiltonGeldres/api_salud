package com.api_salud.api_salud.response;
public class PacienteCitadoResponse {
	
    private int idCita;       
    private String horaInicio;   
    private String nombres;      
    private int idEspecialidad;
    private String especialidad;
    private String pagado; 
    private String atendido;
    private String fecha;


	private int idPaciente ;
	private int idCuentaAtencion ;
	private int idEstadoAtencion ;
    private String estadoFirma;
    private String horaFin;
    private int idServicio;
    private boolean estadoCita;
    
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
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getPagado() {
		return pagado;
	}
	public void setPagado(String pagado) {
		this.pagado = pagado;
	}
	public String getAtendido() {
		return atendido;
	}
	public void setAtendido(String atendido) {
		this.atendido = atendido;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
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
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public boolean isEstadoCita() {
		return estadoCita;
	}
	public void setEstadoCita(boolean estadoCita) {
		this.estadoCita = estadoCita;
	}
    
    

        
    
    
    
}