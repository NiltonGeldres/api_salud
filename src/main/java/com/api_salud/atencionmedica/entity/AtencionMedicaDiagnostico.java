package com.api_salud.atencionmedica.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "atenciones_medicas_diagnosticos", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaDiagnostico extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionDiagnostico;

    private Integer idDiagnostico;
    private Integer idSubclasificacion;
    private Integer idlab1; // Asumiendo que es id_lab1
    private Integer idDiagnosticoOrder; // Mapea id_diagnostico_orden
	public Long getIdAtencionDiagnostico() {
		return idAtencionDiagnostico;
	}
	public void setIdAtencionDiagnostico(Long idAtencionDiagnostico) {
		this.idAtencionDiagnostico = idAtencionDiagnostico;
	}
	public Integer getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(Integer idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public Integer getIdSubclasificacion() {
		return idSubclasificacion;
	}
	public void setIdSubclasificacion(Integer idSubclasificacion) {
		this.idSubclasificacion = idSubclasificacion;
	}
	public Integer getIdlab1() {
		return idlab1;
	}
	public void setIdlab1(Integer idlab1) {
		this.idlab1 = idlab1;
	}
	public Integer getIdDiagnosticoOrder() {
		return idDiagnosticoOrder;
	}
	public void setIdDiagnosticoOrder(Integer idDiagnosticoOrder) {
		this.idDiagnosticoOrder = idDiagnosticoOrder;
	}
    
    
}