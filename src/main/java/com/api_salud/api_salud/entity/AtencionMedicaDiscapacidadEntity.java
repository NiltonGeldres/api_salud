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
public class AtencionMedicaDiscapacidadEntity {
int idAtencion;
int idDiscapacidad;
int idGravedadDiscapacidad;
String descripcionDiscapacidad;
String descripcionGravedadDiscapacidad;
int idUsuario ;

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdDiscapacidad() {
        return idDiscapacidad;
    }

    public void setIdDiscapacidad(int idDiscapacidad) {
        this.idDiscapacidad = idDiscapacidad;
    }

    public int getIdGravedadDiscapacidad() {
        return idGravedadDiscapacidad;
    }

    public void setIdGravedadDiscapacidad(int idGravedadDiscapacidad) {
        this.idGravedadDiscapacidad = idGravedadDiscapacidad;
    }

    public String getDescripcionDiscapacidad() {
        return descripcionDiscapacidad;
    }

    public void setDescripcionDiscapacidad(String descripcionDiscapacidad) {
        this.descripcionDiscapacidad = descripcionDiscapacidad;
    }

    public String getDescripcionGravedadDiscapacidad() {
        return descripcionGravedadDiscapacidad;
    }

    public void setDescripcionGravedadDiscapacidad(String descripcionGravedadDiscapacidad) {
        this.descripcionGravedadDiscapacidad = descripcionGravedadDiscapacidad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
