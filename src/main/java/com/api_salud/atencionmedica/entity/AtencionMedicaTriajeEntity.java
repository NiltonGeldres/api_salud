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
public class AtencionMedicaTriajeEntity {
    int idAtencionTriaje ;
    int idAtencion ;
    int idTipoTriaje ;
    String valorTriaje ;
    String descripcionTriaje;
    String umTriaje;
    int idUsuario ;

    public int getIdAtencionTriaje() {
        return idAtencionTriaje;
    }

    public void setIdAtencionTriaje(int idAtencionTriaje) {
        this.idAtencionTriaje = idAtencionTriaje;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdTipoTriaje() {
        return idTipoTriaje;
    }

    public void setIdTipoTriaje(int idTipoTriaje) {
        this.idTipoTriaje = idTipoTriaje;
    }

    public String getValorTriaje() {
        return valorTriaje;
    }

    public void setValorTriaje(String valorTriaje) {
        this.valorTriaje = valorTriaje;
    }

    public String getDescripcionTriaje() {
        return descripcionTriaje;
    }

    public void setDescripcionTriaje(String descripcionTriaje) {
        this.descripcionTriaje = descripcionTriaje;
    }

    public String getUmTriaje() {
        return umTriaje;
    }

    public void setUmTriaje(String umTriaje) {
        this.umTriaje = umTriaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    

    
}
