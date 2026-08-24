package com.api_salud.api_salud.response;
public class PacienteCitadoResponse {
	
	private int idPaciente ;
	private String nroHistoriaClinica ;
    private int idCita;       
    private String horaInicio;   
    private String nombres;      
    private int idEspecialidad;
    private String especialidad;
    private String fecha;
    
	private String pagado; 
    private int idCuentaAtencion ;
    private String horaFin;
    private int idServicio;
    
    private Integer estadoCita;
    private Integer idAtencion;
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNroHistoriaClinica() {
		return nroHistoriaClinica;
	}
	public void setNroHistoriaClinica(String nroHistoriaClinica) {
		this.nroHistoriaClinica = nroHistoriaClinica;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPagado() {
		return pagado;
	}
	public void setPagado(String pagado) {
		this.pagado = pagado;
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
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public Integer getEstadoCita() {
		return estadoCita;
	}
	public void setEstadoCita(Integer estadoCita) {
		this.estadoCita = estadoCita;
	}
	public Integer getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(Integer idAtencion) {
		this.idAtencion = idAtencion;
	}

//    private String atendido;
//    private String estadoFirma;
//	private int idEstadoAtencion ;
    
    
    
   
    
    
}