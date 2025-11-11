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
public class AtencionRecetaCabeceraEntity{
    
    int idAtencion;
    int idPuntoCarga;
    String fechaRegistro;
    int idCuentaAtencion;
    int idServicio;
    int idEstado;
    int idMedico;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCuentaAtencion() {
        return idCuentaAtencion;
    }

    public void setIdCuentaAtencion(int idCuentaAtencion) {
        this.idCuentaAtencion = idCuentaAtencion;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
