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
public class AtencionMedicaExamenesEntity {

    int idAtencionPlanTrabajo ;            
    int idAtencion ;
    int idPuntoCarga        ;
    int idEstado            ;
    int idProducto ;
    int cantidad ;
    double  precio ;
    double  total ;
    String  observaciones;
    int idPedido    ;
    int idComprobantePagoPedido   ;
    String descripcionProducto;
    String descripcionPuntoCarga;
    int idUsuario ;
    int idDiagnostico ;
    String descripcionDiagnostico;
    String codigoDiagnostico;
    String descripcionServicio;
        
    public int getIdAtencionPlanTrabajo() {
        return idAtencionPlanTrabajo;
    }

    public void setIdAtencionPlanTrabajo(int idAtencionPlanTrabajo) {
        this.idAtencionPlanTrabajo = idAtencionPlanTrabajo;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdPuntoCarga() {
        return idPuntoCarga;
    }

    public void setIdPuntoCarga(int idPuntoCarga) {
        this.idPuntoCarga = idPuntoCarga;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdComprobantePagoPedido() {
        return idComprobantePagoPedido;
    }

    public void setIdComprobantePagoPedido(int idComprobantePagoPedido) {
        this.idComprobantePagoPedido = idComprobantePagoPedido;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getDescripcionPuntoCarga() {
        return descripcionPuntoCarga;
    }

    public void setDescripcionPuntoCarga(String descripcionPuntoCarga) {
        this.descripcionPuntoCarga = descripcionPuntoCarga;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public String getCodigoDiagnostico() {
        return codigoDiagnostico;
    }

    public void setCodigoDiagnostico(String codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }
    
    
}
