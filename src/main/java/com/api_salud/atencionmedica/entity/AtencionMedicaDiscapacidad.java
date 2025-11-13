package com.api_salud.atencionmedica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "atenciones_medicas_discapacidad", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaDiscapacidad extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionDiscapacidad;

    private Integer idDiscapacidad;
    private Integer idGravedadDiscapacidad;
	public Long getIdAtencionDiscapacidad() {
		return idAtencionDiscapacidad;
	}
	public void setIdAtencionDiscapacidad(Long idAtencionDiscapacidad) {
		this.idAtencionDiscapacidad = idAtencionDiscapacidad;
	}
	public Integer getIdDiscapacidad() {
		return idDiscapacidad;
	}
	public void setIdDiscapacidad(Integer idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}
	public Integer getIdGravedadDiscapacidad() {
		return idGravedadDiscapacidad;
	}
	public void setIdGravedadDiscapacidad(Integer idGravedadDiscapacidad) {
		this.idGravedadDiscapacidad = idGravedadDiscapacidad;
	}
    
    
}