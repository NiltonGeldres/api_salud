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
public class AtencionMedicaTratamientoEntity {
    int idAtencion ;
    int idTratamiento;
    String descripcionAtencionTratamiento;
    String descripcionTipoTratamiento;
    int idUsuario ;
    int idTipoTratamiento;

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getDescripcionAtencionTratamiento() {
        return descripcionAtencionTratamiento;
    }

    public void setDescripcionAtencionTratamiento(String descripcionAtencionTratamiento) {
        this.descripcionAtencionTratamiento = descripcionAtencionTratamiento;
    }

    public String getDescripcionTipoTratamiento() {
        return descripcionTipoTratamiento;
    }

    public void setDescripcionTipoTratamiento(String descripcionTipoTratamiento) {
        this.descripcionTipoTratamiento = descripcionTipoTratamiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoTratamiento() {
        return idTipoTratamiento;
    }

    public void setIdTipoTratamiento(int idTipoTratamiento) {
        this.idTipoTratamiento = idTipoTratamiento;
    }

}
