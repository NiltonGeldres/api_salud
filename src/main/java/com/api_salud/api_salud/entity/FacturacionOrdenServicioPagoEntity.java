/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.api_salud.entity;

/**
 *
 * @author ngeldres
 */
public class FacturacionOrdenServicioPagoEntity {
    int idOrdenPago;
    int idComprobantePago;
    int idOrden;
    double ImporteExonerado;
    String FechaCreacion;
    int idUsuario;
    int idEstadoFacturacion;
    int idUsuarioExonera;

    public int getIdOrdenPago() {
        return idOrdenPago;
    }

    public void setIdOrdenPago(int idOrdenPago) {
        this.idOrdenPago = idOrdenPago;
    }

    public int getIdComprobantePago() {
        return idComprobantePago;
    }

    public void setIdComprobantePago(int idComprobantePago) {
        this.idComprobantePago = idComprobantePago;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public double getImporteExonerado() {
        return ImporteExonerado;
    }

    public void setImporteExonerado(double ImporteExonerado) {
        this.ImporteExonerado = ImporteExonerado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEstadoFacturacion() {
        return idEstadoFacturacion;
    }

    public void setIdEstadoFacturacion(int idEstadoFacturacion) {
        this.idEstadoFacturacion = idEstadoFacturacion;
    }

    public int getIdUsuarioExonera() {
        return idUsuarioExonera;
    }

    public void setIdUsuarioExonera(int idUsuarioExonera) {
        this.idUsuarioExonera = idUsuarioExonera;
    }
    
}
