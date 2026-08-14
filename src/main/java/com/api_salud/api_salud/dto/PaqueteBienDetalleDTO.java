package com.api_salud.api_salud.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaqueteBienDetalleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idPaqueteDetalle;
    private Integer idProducto;
    private String codigo;
    private String nombre;
    private String nombreComercial;
    private String denominacionPrincipio;
    private String concentracion;
    private String presentacion;
    private String formaFarmaceutica;
    private Integer tipoProducto;
    private Boolean esProductoFarmaceutico;
    private String dosis;
    private String frecuencia;
    private Integer duracionDias;
    private Integer idViaDefault;
    private Integer idUmDosisDefault;
    private BigDecimal cantidadPredefinida;
    private BigDecimal precioPaquete;
    private BigDecimal importePaquete;
    private BigDecimal precioUnitarioTarifario;
    private BigDecimal subtotalEstimadoTarifario;

    public PaqueteBienDetalleDTO() {}

    // Getters y Setters
    public Integer getIdPaqueteDetalle() { return idPaqueteDetalle; }
    public void setIdPaqueteDetalle(Integer idPaqueteDetalle) { this.idPaqueteDetalle = idPaqueteDetalle; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getDenominacionPrincipio() { return denominacionPrincipio; }
    public void setDenominacionPrincipio(String denominacionPrincipio) { this.denominacionPrincipio = denominacionPrincipio; }

    public String getConcentracion() { return concentracion; }
    public void setConcentracion(String concentracion) { this.concentracion = concentracion; }

    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }

    public String getFormaFarmaceutica() { return formaFarmaceutica; }
    public void setFormaFarmaceutica(String formaFarmaceutica) { this.formaFarmaceutica = formaFarmaceutica; }

    public Integer getTipoProducto() { return tipoProducto; }
    public void setTipoProducto(Integer tipoProducto) { this.tipoProducto = tipoProducto; }

    public Boolean getEsProductoFarmaceutico() { return esProductoFarmaceutico; }
    public void setEsProductoFarmaceutico(Boolean esProductoFarmaceutico) { this.esProductoFarmaceutico = esProductoFarmaceutico; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public Integer getDuracionDias() { return duracionDias; }
    public void setDuracionDias(Integer duracionDias) { this.duracionDias = duracionDias; }

    public Integer getIdViaDefault() { return idViaDefault; }
    public void setIdViaDefault(Integer idViaDefault) { this.idViaDefault = idViaDefault; }

    public Integer getIdUmDosisDefault() { return idUmDosisDefault; }
    public void setIdUmDosisDefault(Integer idUmDosisDefault) { this.idUmDosisDefault = idUmDosisDefault; }

    public BigDecimal getCantidadPredefinida() { return cantidadPredefinida; }
    public void setCantidadPredefinida(BigDecimal cantidadPredefinida) { this.cantidadPredefinida = cantidadPredefinida; }

    public BigDecimal getPrecioPaquete() { return precioPaquete; }
    public void setPrecioPaquete(BigDecimal precioPaquete) { this.precioPaquete = precioPaquete; }

    public BigDecimal getImportePaquete() { return importePaquete; }
    public void setImportePaquete(BigDecimal importePaquete) { this.importePaquete = importePaquete; }

    public BigDecimal getPrecioUnitarioTarifario() { return precioUnitarioTarifario; }
    public void setPrecioUnitarioTarifario(BigDecimal precioUnitarioTarifario) { this.precioUnitarioTarifario = precioUnitarioTarifario; }

    public BigDecimal getSubtotalEstimadoTarifario() { return subtotalEstimadoTarifario; }
    public void setSubtotalEstimadoTarifario(BigDecimal subtotalEstimadoTarifario) { this.subtotalEstimadoTarifario = subtotalEstimadoTarifario; }
}