/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.atencionmedica.entity;

/**
 *
 * @author administrador
 */
public class AtencionRecetaDetalleEntity {

    int     idAtencion;
    int     idPuntoCarga;
    int     idEstado;
    int     idPlanTrabajo ;
    int     idProducto ;
    int     cantidad ;
    double  precio ;
    double  total ;
    int     saldoRegistroReceta ;
    int     saldoDespachoReceta;
    int     cantidadDespachada ;
    int     idDosisRecetada;
    int     idEstadoDetalle;
    String  motivoAnulacionMedico;
    String  observaciones;
    int  idViaAdministracion ;
    int     idUsuarioAuditoria  ;
    int idUsuario ;

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

    public int getIdPlanTrabajo() {
        return idPlanTrabajo;
    }

    public void setIdPlanTrabajo(int idPlanTrabajo) {
        this.idPlanTrabajo = idPlanTrabajo;
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

    public int getSaldoRegistroReceta() {
        return saldoRegistroReceta;
    }

    public void setSaldoRegistroReceta(int saldoRegistroReceta) {
        this.saldoRegistroReceta = saldoRegistroReceta;
    }

    public int getSaldoDespachoReceta() {
        return saldoDespachoReceta;
    }

    public void setSaldoDespachoReceta(int saldoDespachoReceta) {
        this.saldoDespachoReceta = saldoDespachoReceta;
    }

    public int getCantidadDespachada() {
        return cantidadDespachada;
    }

    public void setCantidadDespachada(int cantidadDespachada) {
        this.cantidadDespachada = cantidadDespachada;
    }

    public int getIdDosisRecetada() {
        return idDosisRecetada;
    }

    public void setIdDosisRecetada(int idDosisRecetada) {
        this.idDosisRecetada = idDosisRecetada;
    }

    public int getIdEstadoDetalle() {
        return idEstadoDetalle;
    }

    public void setIdEstadoDetalle(int idEstadoDetalle) {
        this.idEstadoDetalle = idEstadoDetalle;
    }

    public String getMotivoAnulacionMedico() {
        return motivoAnulacionMedico;
    }

    public void setMotivoAnulacionMedico(String motivoAnulacionMedico) {
        this.motivoAnulacionMedico = motivoAnulacionMedico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdViaAdministracion() {
        return idViaAdministracion;
    }

    public void setIdViaAdministracion(int idViaAdministracion) {
        this.idViaAdministracion = idViaAdministracion;
    }

    public int getIdUsuarioAuditoria() {
        return idUsuarioAuditoria;
    }

    public void setIdUsuarioAuditoria(int idUsuarioAuditoria) {
        this.idUsuarioAuditoria = idUsuarioAuditoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
