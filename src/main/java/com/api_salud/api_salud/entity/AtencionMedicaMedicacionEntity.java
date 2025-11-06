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
public class AtencionMedicaMedicacionEntity {
    int idAtencion; 
    int idAlmacenProducto; 
    int idProducto;
    int cantidadDosis;
    int idUmDosis;
    int idFrecuenciaDosis;
    int cantidadPeriodo;
    int idUmPeriodo;
    int idViaAdministracion ;
    int cantidadTotal;
    double precio ;
    double montoTotal ;
    String indicaciones;
    String descripcionAlmacen;
    String descripcionProducto;
    String descripcionDosis;
    String descripcionFrecuenciaDosis;
    String descripcionPeriodo;
    String descripcionViaAdministracion;
    int idUsuario ;
    int idDiagnostico ;
    String nombreDiagnostico;
    String codigoDiagnostico;

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdAlmacenProducto() {
        return idAlmacenProducto;
    }

    public void setIdAlmacenProducto(int idAlmacenProducto) {
        this.idAlmacenProducto = idAlmacenProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadDosis() {
        return cantidadDosis;
    }

    public void setCantidadDosis(int cantidadDosis) {
        this.cantidadDosis = cantidadDosis;
    }

    public int getIdUmDosis() {
        return idUmDosis;
    }

    public void setIdUmDosis(int idUmDosis) {
        this.idUmDosis = idUmDosis;
    }

    public int getIdFrecuenciaDosis() {
        return idFrecuenciaDosis;
    }

    public void setIdFrecuenciaDosis(int idFrecuenciaDosis) {
        this.idFrecuenciaDosis = idFrecuenciaDosis;
    }

    public int getCantidadPeriodo() {
        return cantidadPeriodo;
    }

    public void setCantidadPeriodo(int cantidadPeriodo) {
        this.cantidadPeriodo = cantidadPeriodo;
    }

    public int getIdUmPeriodo() {
        return idUmPeriodo;
    }

    public void setIdUmPeriodo(int idUmPeriodo) {
        this.idUmPeriodo = idUmPeriodo;
    }

    public int getIdViaAdministracion() {
        return idViaAdministracion;
    }

    public void setIdViaAdministracion(int idViaAdministracion) {
        this.idViaAdministracion = idViaAdministracion;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getDescripcionAlmacen() {
        return descripcionAlmacen;
    }

    public void setDescripcionAlmacen(String descripcionAlmacen) {
        this.descripcionAlmacen = descripcionAlmacen;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getDescripcionDosis() {
        return descripcionDosis;
    }

    public void setDescripcionDosis(String descripcionDosis) {
        this.descripcionDosis = descripcionDosis;
    }

    public String getDescripcionFrecuenciaDosis() {
        return descripcionFrecuenciaDosis;
    }

    public void setDescripcionFrecuenciaDosis(String descripcionFrecuenciaDosis) {
        this.descripcionFrecuenciaDosis = descripcionFrecuenciaDosis;
    }

    public String getDescripcionPeriodo() {
        return descripcionPeriodo;
    }

    public void setDescripcionPeriodo(String descripcionPeriodo) {
        this.descripcionPeriodo = descripcionPeriodo;
    }

    public String getDescripcionViaAdministracion() {
        return descripcionViaAdministracion;
    }

    public void setDescripcionViaAdministracion(String descripcionViaAdministracion) {
        this.descripcionViaAdministracion = descripcionViaAdministracion;
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

    public String getNombreDiagnostico() {
        return nombreDiagnostico;
    }

    public void setNombreDiagnostico(String nombreDiagnostico) {
        this.nombreDiagnostico = nombreDiagnostico;
    }

    public String getCodigoDiagnostico() {
        return codigoDiagnostico;
    }

    public void setCodigoDiagnostico(String codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }

    
    

}
