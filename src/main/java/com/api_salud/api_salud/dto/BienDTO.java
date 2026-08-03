package com.api_salud.api_salud.dto; // Ajusta el paquete según corresponda

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BienDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private BigDecimal precioVenta;
    private Integer idViaDefault;
    private Integer idUmDosisDefault;
    private BigDecimal precioDistribucion;

    public BienDTO() {
    }

    // Getters y Setters
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDenominacionPrincipio() {
        return denominacionPrincipio;
    }

    public void setDenominacionPrincipio(String denominacionPrincipio) {
        this.denominacionPrincipio = denominacionPrincipio;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public Integer getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(Integer tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Boolean getEsProductoFarmaceutico() {
        return esProductoFarmaceutico;
    }

    public void setEsProductoFarmaceutico(Boolean esProductoFarmaceutico) {
        this.esProductoFarmaceutico = esProductoFarmaceutico;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getIdViaDefault() {
        return idViaDefault;
    }

    public void setIdViaDefault(Integer idViaDefault) {
        this.idViaDefault = idViaDefault;
    }

    public Integer getIdUmDosisDefault() {
        return idUmDosisDefault;
    }

    public void setIdUmDosisDefault(Integer idUmDosisDefault) {
        this.idUmDosisDefault = idUmDosisDefault;
    }

    public BigDecimal getPrecioDistribucion() {
        return precioDistribucion;
    }

    public void setPrecioDistribucion(BigDecimal precioDistribucion) {
        this.precioDistribucion = precioDistribucion;
    }
}