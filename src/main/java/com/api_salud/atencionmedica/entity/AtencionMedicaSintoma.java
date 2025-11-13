package com.api_salud.atencionmedica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "atenciones_medicas_sintomas", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaSintoma extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionSintoma;

    private Integer idSintoma;
    private Integer idTipoSintoma;
    private String descripcion;
	public Long getIdAtencionSintoma() {
		return idAtencionSintoma;
	}
	public void setIdAtencionSintoma(Long idAtencionSintoma) {
		this.idAtencionSintoma = idAtencionSintoma;
	}
	public Integer getIdSintoma() {
		return idSintoma;
	}
	public void setIdSintoma(Integer idSintoma) {
		this.idSintoma = idSintoma;
	}
	public Integer getIdTipoSintoma() {
		return idTipoSintoma;
	}
	public void setIdTipoSintoma(Integer idTipoSintoma) {
		this.idTipoSintoma = idTipoSintoma;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
}