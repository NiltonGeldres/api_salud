package com.api_salud.atencionmedica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "atenciones_medicas_discapacidad_otros", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaDiscapacidadOtros extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionDiscapacidadOtros;

    private Integer idTipoActividad;
    private Integer idTiempoDiscapacidadAa; // Años
    private Integer idTiempoDiscapacidadMm; // Meses
    private Integer idTiempoDiscapacidadDd; // Días
    private Integer idTiempoSintrabajarAa;
    private Integer idTiempoSintrabajarMm;
    private Integer idTiempoSintrabajarDd;
    private Integer idAlta;
    private Integer idProductividad;
	public Long getIdAtencionDiscapacidadOtros() {
		return idAtencionDiscapacidadOtros;
	}
	public void setIdAtencionDiscapacidadOtros(Long idAtencionDiscapacidadOtros) {
		this.idAtencionDiscapacidadOtros = idAtencionDiscapacidadOtros;
	}
	public Integer getIdTipoActividad() {
		return idTipoActividad;
	}
	public void setIdTipoActividad(Integer idTipoActividad) {
		this.idTipoActividad = idTipoActividad;
	}
	public Integer getIdTiempoDiscapacidadAa() {
		return idTiempoDiscapacidadAa;
	}
	public void setIdTiempoDiscapacidadAa(Integer idTiempoDiscapacidadAa) {
		this.idTiempoDiscapacidadAa = idTiempoDiscapacidadAa;
	}
	public Integer getIdTiempoDiscapacidadMm() {
		return idTiempoDiscapacidadMm;
	}
	public void setIdTiempoDiscapacidadMm(Integer idTiempoDiscapacidadMm) {
		this.idTiempoDiscapacidadMm = idTiempoDiscapacidadMm;
	}
	public Integer getIdTiempoDiscapacidadDd() {
		return idTiempoDiscapacidadDd;
	}
	public void setIdTiempoDiscapacidadDd(Integer idTiempoDiscapacidadDd) {
		this.idTiempoDiscapacidadDd = idTiempoDiscapacidadDd;
	}
	public Integer getIdTiempoSintrabajarAa() {
		return idTiempoSintrabajarAa;
	}
	public void setIdTiempoSintrabajarAa(Integer idTiempoSintrabajarAa) {
		this.idTiempoSintrabajarAa = idTiempoSintrabajarAa;
	}
	public Integer getIdTiempoSintrabajarMm() {
		return idTiempoSintrabajarMm;
	}
	public void setIdTiempoSintrabajarMm(Integer idTiempoSintrabajarMm) {
		this.idTiempoSintrabajarMm = idTiempoSintrabajarMm;
	}
	public Integer getIdTiempoSintrabajarDd() {
		return idTiempoSintrabajarDd;
	}
	public void setIdTiempoSintrabajarDd(Integer idTiempoSintrabajarDd) {
		this.idTiempoSintrabajarDd = idTiempoSintrabajarDd;
	}
	public Integer getIdAlta() {
		return idAlta;
	}
	public void setIdAlta(Integer idAlta) {
		this.idAlta = idAlta;
	}
	public Integer getIdProductividad() {
		return idProductividad;
	}
	public void setIdProductividad(Integer idProductividad) {
		this.idProductividad = idProductividad;
	}
    
    
}
