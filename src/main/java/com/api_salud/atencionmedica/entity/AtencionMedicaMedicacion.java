package com.api_salud.atencionmedica.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Entity
@Table(name = "atenciones_medicas_medicacion", schema = "igm_atenciones_medicas")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtencionMedicaMedicacion extends BaseDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencionMedicacion;

    private Integer idAlmacen;
    private Integer idProducto;
    private Integer cantidadDosis;
    private Integer idUmDosis; // Unidad de Medida
    private Integer idFrecuenciaDosis;
    private Integer cantidadPeriodo;
    private Integer idViaAdministracion;
    private Integer cantidadTotal;
    private BigDecimal precio; // Mapea DOUBLE PRECISION
    private BigDecimal montoTotal; // Mapea DOUBLE PRECISION
    private String indicaciones;
    private Integer idDiagnostico;
	public Long getIdAtencionMedicacion() {
		return idAtencionMedicacion;
	}
	public void setIdAtencionMedicacion(Long idAtencionMedicacion) {
		this.idAtencionMedicacion = idAtencionMedicacion;
	}
	public Integer getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getCantidadDosis() {
		return cantidadDosis;
	}
	public void setCantidadDosis(Integer cantidadDosis) {
		this.cantidadDosis = cantidadDosis;
	}
	public Integer getIdUmDosis() {
		return idUmDosis;
	}
	public void setIdUmDosis(Integer idUmDosis) {
		this.idUmDosis = idUmDosis;
	}
	public Integer getIdFrecuenciaDosis() {
		return idFrecuenciaDosis;
	}
	public void setIdFrecuenciaDosis(Integer idFrecuenciaDosis) {
		this.idFrecuenciaDosis = idFrecuenciaDosis;
	}
	public Integer getCantidadPeriodo() {
		return cantidadPeriodo;
	}
	public void setCantidadPeriodo(Integer cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}
	public Integer getIdViaAdministracion() {
		return idViaAdministracion;
	}
	public void setIdViaAdministracion(Integer idViaAdministracion) {
		this.idViaAdministracion = idViaAdministracion;
	}
	public Integer getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getIndicaciones() {
		return indicaciones;
	}
	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}
	public Integer getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(Integer idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
    
    
}