/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.atencionmedica.entity;

/**
 *
 * @author ngeldres
 */
public class AtencionMedicaDiscapacidadOtrosEntity {
    int idAtencion;
    int idTipoActividad;
    int tiempoDiscapacidadAA;
    int tiempoDiscapacidadMM;
    int tiempoDiscapacidadDD;
    int tiempoSinTrabajarAA;
    int tiempoSinTrabajarMM;
    int tiempoSinTrabajarDD;
    int idAlta;
    int idProductividad;
    String descripcionTipoActividad;
    String descripcionAlta;
    String DescripcionProductividad;
   int idUsuario ;


    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public int getTiempoDiscapacidadAA() {
        return tiempoDiscapacidadAA;
    }

    public void setTiempoDiscapacidadAA(int tiempoDiscapacidadAA) {
        this.tiempoDiscapacidadAA = tiempoDiscapacidadAA;
    }

    public int getTiempoDiscapacidadMM() {
        return tiempoDiscapacidadMM;
    }

    public void setTiempoDiscapacidadMM(int tiempoDiscapacidadMM) {
        this.tiempoDiscapacidadMM = tiempoDiscapacidadMM;
    }

    public int getTiempoDiscapacidadDD() {
        return tiempoDiscapacidadDD;
    }

    public void setTiempoDiscapacidadDD(int tiempoDiscapacidadDD) {
        this.tiempoDiscapacidadDD = tiempoDiscapacidadDD;
    }

    public int getTiempoSinTrabajarAA() {
        return tiempoSinTrabajarAA;
    }

    public void setTiempoSinTrabajarAA(int tiempoSinTrabajarAA) {
        this.tiempoSinTrabajarAA = tiempoSinTrabajarAA;
    }

    public int getTiempoSinTrabajarMM() {
        return tiempoSinTrabajarMM;
    }

    public void setTiempoSinTrabajarMM(int tiempoSinTrabajarMM) {
        this.tiempoSinTrabajarMM = tiempoSinTrabajarMM;
    }

    public int getTiempoSinTrabajarDD() {
        return tiempoSinTrabajarDD;
    }

    public void setTiempoSinTrabajarDD(int tiempoSinTrabajarDD) {
        this.tiempoSinTrabajarDD = tiempoSinTrabajarDD;
    }

    public int getIdAlta() {
        return idAlta;
    }

    public void setIdAlta(int idAlta) {
        this.idAlta = idAlta;
    }

    public int getIdProductividad() {
        return idProductividad;
    }

    public void setIdProductividad(int idProductividad) {
        this.idProductividad = idProductividad;
    }

    public String getDescripcionTipoActividad() {
        return descripcionTipoActividad;
    }

    public void setDescripcionTipoActividad(String descripcionTipoActividad) {
        this.descripcionTipoActividad = descripcionTipoActividad;
    }

    public String getDescripcionAlta() {
        return descripcionAlta;
    }

    public void setDescripcionAlta(String descripcionAlta) {
        this.descripcionAlta = descripcionAlta;
    }

    public String getDescripcionProductividad() {
        return DescripcionProductividad;
    }

    public void setDescripcionProductividad(String DescripcionProductividad) {
        this.DescripcionProductividad = DescripcionProductividad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
