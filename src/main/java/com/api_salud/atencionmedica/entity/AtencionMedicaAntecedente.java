package com.api_salud.atencionmedica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "atenciones_medicas_antecedentes", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaAntecedente extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionAntecedente;

    private Integer idAntecedente;
    private Integer idTipoAntecedente;
    private String descripcion;
	public Long getIdAtencionAntecedente() {
		return idAtencionAntecedente;
	}
	public void setIdAtencionAntecedente(Long idAtencionAntecedente) {
		this.idAtencionAntecedente = idAtencionAntecedente;
	}
	public Integer getIdAntecedente() {
		return idAntecedente;
	}
	public void setIdAntecedente(Integer idAntecedente) {
		this.idAntecedente = idAntecedente;
	}
	public Integer getIdTipoAntecedente() {
		return idTipoAntecedente;
	}
	public void setIdTipoAntecedente(Integer idTipoAntecedente) {
		this.idTipoAntecedente = idTipoAntecedente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
}