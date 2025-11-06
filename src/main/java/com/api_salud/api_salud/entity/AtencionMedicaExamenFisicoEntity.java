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
public class AtencionMedicaExamenFisicoEntity {
    int idAtencionExamenFisico;
    int idAtencion;
    int idExamenFisico;
    int idTipoExamenFisico;
    String descripcionAtencionExamenFisico;
    String descripcionTipoExamenFisico;
    int idUsuario ;

    public int getIdAtencionExamenFisico() {
        return idAtencionExamenFisico;
    }

    public void setIdAtencionExamenFisico(int idAtencionExamenFisico) {
        this.idAtencionExamenFisico = idAtencionExamenFisico;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdExamenFisico() {
        return idExamenFisico;
    }

    public void setIdExamenFisico(int idExamenFisico) {
        this.idExamenFisico = idExamenFisico;
    }

    public int getIdTipoExamenFisico() {
        return idTipoExamenFisico;
    }

    public void setIdTipoExamenFisico(int idTipoExamenFisico) {
        this.idTipoExamenFisico = idTipoExamenFisico;
    }

    public String getDescripcionAtencionExamenFisico() {
        return descripcionAtencionExamenFisico;
    }

    public void setDescripcionAtencionExamenFisico(String descripcionAtencionExamenFisico) {
        this.descripcionAtencionExamenFisico = descripcionAtencionExamenFisico;
    }

    public String getDescripcionTipoExamenFisico() {
        return descripcionTipoExamenFisico;
    }

    public void setDescripcionTipoExamenFisico(String descripcionTipoExamenFisico) {
        this.descripcionTipoExamenFisico = descripcionTipoExamenFisico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
