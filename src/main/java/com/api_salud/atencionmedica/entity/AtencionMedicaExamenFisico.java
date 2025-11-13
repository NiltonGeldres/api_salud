package com.api_salud.atencionmedica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "atenciones_medicas_examen_fisico", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaExamenFisico extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionExamenFisico;

    private Integer idExamenFisico;
    private Integer idTipoExamenFisico;
    private String descripcion;
	public Long getIdAtencionExamenFisico() {
		return idAtencionExamenFisico;
	}
	public void setIdAtencionExamenFisico(Long idAtencionExamenFisico) {
		this.idAtencionExamenFisico = idAtencionExamenFisico;
	}
	public Integer getIdExamenFisico() {
		return idExamenFisico;
	}
	public void setIdExamenFisico(Integer idExamenFisico) {
		this.idExamenFisico = idExamenFisico;
	}
	public Integer getIdTipoExamenFisico() {
		return idTipoExamenFisico;
	}
	public void setIdTipoExamenFisico(Integer idTipoExamenFisico) {
		this.idTipoExamenFisico = idTipoExamenFisico;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
}