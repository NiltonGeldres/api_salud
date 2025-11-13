package com.api_salud.atencionmedica.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "atenciones_medicas_tratamientos", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaTratamiento extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionTratamiento;

    private Integer idTratamiento;
    private Integer idTipoTratamiento;
    private String descripcion;
	public Long getIdAtencionTratamiento() {
		return idAtencionTratamiento;
	}
	public void setIdAtencionTratamiento(Long idAtencionTratamiento) {
		this.idAtencionTratamiento = idAtencionTratamiento;
	}
	public Integer getIdTratamiento() {
		return idTratamiento;
	}
	public void setIdTratamiento(Integer idTratamiento) {
		this.idTratamiento = idTratamiento;
	}
	public Integer getIdTipoTratamiento() {
		return idTipoTratamiento;
	}
	public void setIdTipoTratamiento(Integer idTipoTratamiento) {
		this.idTipoTratamiento = idTipoTratamiento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
}
