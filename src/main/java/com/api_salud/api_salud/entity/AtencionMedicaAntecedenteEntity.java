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
public class AtencionMedicaAntecedenteEntity {
    int	idAtencionAntecedente;
    String descripcionAtencionAntecedente;
    int	idAtencion;
    int	idAntecedente;
    int	idTipoAntecedente;
    String descripcionTipoAntecedente;

    public int getIdAtencionAntecedente() {
        return idAtencionAntecedente;
    }

    public void setIdAtencionAntecedente(int idAtencionAntecedente) {
        this.idAtencionAntecedente = idAtencionAntecedente;
    }

    public String getDescripcionAtencionAntecedente() {
        return descripcionAtencionAntecedente;
    }

    public void setDescripcionAtencionAntecedente(String descripcionAtencionAntecedente) {
        this.descripcionAtencionAntecedente = descripcionAtencionAntecedente;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(int idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public int getIdTipoAntecedente() {
        return idTipoAntecedente;
    }

    public void setIdTipoAntecedente(int idTipoAntecedente) {
        this.idTipoAntecedente = idTipoAntecedente;
    }

    public String getDescripcionTipoAntecedente() {
        return descripcionTipoAntecedente;
    }

    public void setDescripcionTipoAntecedente(String descripcionTipoAntecedente) {
        this.descripcionTipoAntecedente = descripcionTipoAntecedente;
    }
    
}
