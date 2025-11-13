package com.api_salud.atencionmedica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal; // Uso de BigDecimal para precisión monetaria

@Entity
@Table(name = "atenciones_medicas_examenes", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaExamen extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionExamen;

    private Integer idPuntoCarga;
    private Integer idEstado;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precio; // Mapea DOUBLE PRECISION
    private BigDecimal total;  // Mapea DOUBLE PRECISION
    private Integer idDiagnostico;
    private String observacion;
	public Long getIdAtencionExamen() {
		return idAtencionExamen;
	}
	public void setIdAtencionExamen(Long idAtencionExamen) {
		this.idAtencionExamen = idAtencionExamen;
	}
	public Integer getIdPuntoCarga() {
		return idPuntoCarga;
	}
	public void setIdPuntoCarga(Integer idPuntoCarga) {
		this.idPuntoCarga = idPuntoCarga;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Integer getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(Integer idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
    
    
}