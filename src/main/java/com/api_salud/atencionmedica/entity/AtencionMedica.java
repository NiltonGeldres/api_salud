package com.api_salud.atencionmedica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import java.time.OffsetDateTime;

/**
 * Entidad principal (Cabecera) para la tabla atenciones_medicas.
 * Usada para mapear los resultados de las funciones SELECT (READ).
 * NOTA: Esta entidad usa javax.persistence (compatible con Spring Boot 2.7.x).
 */
@Entity
@Table(name = "atenciones_medicas", schema = "igm_atenciones_medicas")
@Data
public class AtencionMedica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencion; // Mapea id_atencion

    private Integer idPaciente;
    private Integer idCuentaAtencion;
    private Integer idServicio;
    private Integer idMedicoIngreso;
    private Integer idEstadoAtencion;
    private OffsetDateTime tsIngreso;
    private Integer idUsuarioRegistro;
    private String origenRegistroUsuario;
    private OffsetDateTime tsActualizacion; // Campo de PostgreSQL que se mapea
	public Long getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(Long idAtencion) {
		this.idAtencion = idAtencion;
	}
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Integer getIdCuentaAtencion() {
		return idCuentaAtencion;
	}
	public void setIdCuentaAtencion(Integer idCuentaAtencion) {
		this.idCuentaAtencion = idCuentaAtencion;
	}
	public Integer getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}
	public Integer getIdMedicoIngreso() {
		return idMedicoIngreso;
	}
	public void setIdMedicoIngreso(Integer idMedicoIngreso) {
		this.idMedicoIngreso = idMedicoIngreso;
	}
	public Integer getIdEstadoAtencion() {
		return idEstadoAtencion;
	}
	public void setIdEstadoAtencion(Integer idEstadoAtencion) {
		this.idEstadoAtencion = idEstadoAtencion;
	}
	public OffsetDateTime getTsIngreso() {
		return tsIngreso;
	}
	public void setTsIngreso(OffsetDateTime tsIngreso) {
		this.tsIngreso = tsIngreso;
	}
	public Integer getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}
	public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}
	public String getOrigenRegistroUsuario() {
		return origenRegistroUsuario;
	}
	public void setOrigenRegistroUsuario(String origenRegistroUsuario) {
		this.origenRegistroUsuario = origenRegistroUsuario;
	}
	public OffsetDateTime getTsActualizacion() {
		return tsActualizacion;
	}
	public void setTsActualizacion(OffsetDateTime tsActualizacion) {
		this.tsActualizacion = tsActualizacion;
	}

    
    
}