package com.api_salud.api_salud.dto;


import java.io.Serializable;
import java.math.BigDecimal;

public class ServicioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idProducto;
    private String codigo;
    private String nombre;
    private Integer idPartida;
    private String codMinsa;
    private Integer esCpt;
    private Integer tipoServicio; // Mapeado a idopcs
    private Integer idEstado;
    private String codigoSis;
    private BigDecimal precioVenta;

    public ServicioDTO() {
    }

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

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getCodMinsa() {
        return codMinsa;
    }

    public void setCodMinsa(String codMinsa) {
        this.codMinsa = codMinsa;
    }

    public Integer getEsCpt() {
        return esCpt;
    }

    public void setEsCpt(Integer esCpt) {
        this.esCpt = esCpt;
    }

    public Integer getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(Integer tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getCodigoSis() {
        return codigoSis;
    }

    public void setCodigoSis(String codigoSis) {
        this.codigoSis = codigoSis;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
}
