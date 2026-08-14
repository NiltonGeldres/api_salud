package com.api_salud.api_salud.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaqueteServicioDetalleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idPaqueteDetalle;
    private Integer idProducto;
    private String codigo;
    private String nombre;
    private Boolean esCpt;
    private String codMinsa;
    private String codigoSis;
    private String nombreMinsa;
    private String indicacionesPreparacion;
    private BigDecimal cantidadPredefinida;
    private BigDecimal precioPaquete;
    private BigDecimal importePaquete;
    private BigDecimal precioVentaTarifario;
    private BigDecimal subtotalEstimadoTarifario;

    public PaqueteServicioDetalleDTO() {}

    // Getters y Setters
    public Integer getIdPaqueteDetalle() { return idPaqueteDetalle; }
    public void setIdPaqueteDetalle(Integer idPaqueteDetalle) { this.idPaqueteDetalle = idPaqueteDetalle; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getEsCpt() { return esCpt; }
    public void setEsCpt(Boolean esCpt) { this.esCpt = esCpt; }

    public String getCodMinsa() { return codMinsa; }
    public void setCodMinsa(String codMinsa) { this.codMinsa = codMinsa; }

    public String getCodigoSis() { return codigoSis; }
    public void setCodigoSis(String codigoSis) { this.codigoSis = codigoSis; }

    public String getNombreMinsa() { return nombreMinsa; }
    public void setNombreMinsa(String nombreMinsa) { this.nombreMinsa = nombreMinsa; }

    public String getIndicacionesPreparacion() { return indicacionesPreparacion; }
    public void setIndicacionesPreparacion(String indicacionesPreparacion) { this.indicacionesPreparacion = indicacionesPreparacion; }

    public BigDecimal getCantidadPredefinida() { return cantidadPredefinida; }
    public void setCantidadPredefinida(BigDecimal cantidadPredefinida) { this.cantidadPredefinida = cantidadPredefinida; }

    public BigDecimal getPrecioPaquete() { return precioPaquete; }
    public void setPrecioPaquete(BigDecimal precioPaquete) { this.precioPaquete = precioPaquete; }

    public BigDecimal getImportePaquete() { return importePaquete; }
    public void setImportePaquete(BigDecimal importePaquete) { this.importePaquete = importePaquete; }

    public BigDecimal getPrecioVentaTarifario() { return precioVentaTarifario; }
    public void setPrecioVentaTarifario(BigDecimal precioVentaTarifario) { this.precioVentaTarifario = precioVentaTarifario; }

    public BigDecimal getSubtotalEstimadoTarifario() { return subtotalEstimadoTarifario; }
    public void setSubtotalEstimadoTarifario(BigDecimal subtotalEstimadoTarifario) { this.subtotalEstimadoTarifario = subtotalEstimadoTarifario; }
}