package com.api_salud.api_salud.entity;

/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name = "citas_separadas")*/
public class CitaSeparadaEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)

	int idCitaSeparada;
	
//	@Column(name = "fecha")
//@NotNull(message="fecha: no ha sido ingresada")
 //   @NotEmpty	
    String fecha ;
	
//	@Column(name = "horainicio")
//	@NotNull(message="fecha: no ha sido ingresada")
    String horaInicio ;

//	@Column(name = "horafin")
//    @NotNull
    String horaFin ;
    
//	@Column(name = "idpaciente")
 //   @Min(value=1, message="idPaciente: no ha sido ingresado")    
    int idPaciente ;
	
//	@Column(name = "idmedico")
 //   @Min(value=1, message="idMedico: no ha sido ingresado")    
    int idMedico ;
    
//	@Column(name = "idespecialidad")
    //@Min(value=1, message="idEspecialidad: no ha sido ingresado")    
    int idEspecialidad ;
    
	//@Column(name = "idprogramacion")
    //@Min(value=1, message="idServicio: no ha sido ingresado")    
    int idServicio ;
    
	//@Column(name = "idprogramacion")
    //@Min(value=1, message="idProgramacion: no ha sido ingresado")    
    int idProgramacion ;
    
	//@Column(name = "idproducto")
    //@Min(value=1, message="idProdicto: no ha sido ingresado")    
    int idProducto ;
    
	//@Column(name = "idsolicitud")
    String fechaSolicitud;
	//@Column(name = "horasolicitud")
    String horaSolicitud;
	//@Column(name = "tipousuario")
    String tipoUsuario;
	//@Column(name = "fechaseparacion")
    String fechaSeparacion;
    String nombreMedico;
    String nombreEspecialidad;
    String nombreServicio;
    int precioUnitario;
    int idCita;
   
	public int getIdCitaSeparada() {
		return idCitaSeparada;
	}
	public void setIdCitaSeparada(int idCitaSeparada) {
		this.idCitaSeparada = idCitaSeparada;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
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
	public int getIdProgramacion() {
		return idProgramacion;
	}
	public void setIdProgramacion(int idProgramacion) {
		this.idProgramacion = idProgramacion;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getHoraSolicitud() {
		return horaSolicitud;
	}
	public void setHoraSolicitud(String horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getFechaSeparacion() {
		return fechaSeparacion;
	}
	public void setFechaSeparacion(String fechaSeparacion) {
		this.fechaSeparacion = fechaSeparacion;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}
	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public int getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getIdCita() {
		return idCita;
	}
	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	
    
    

}
