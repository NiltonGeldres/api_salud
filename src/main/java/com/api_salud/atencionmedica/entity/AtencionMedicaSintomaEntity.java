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
public class AtencionMedicaSintomaEntity {
    int idAtencionSintoma;
    int	idAtencion;
    int	idSintoma;
    int	idTipoSintoma;      
    String descripcionAtencionSintoma ;
    String descripcionTipoSintoma ;
    int idUsuario ;

    public int getIdAtencionSintoma() {
        return idAtencionSintoma;
    }

    public void setIdAtencionSintoma(int idAtencionSintoma) {
        this.idAtencionSintoma = idAtencionSintoma;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(int idSintoma) {
        this.idSintoma = idSintoma;
    }

    public int getIdTipoSintoma() {
        return idTipoSintoma;
    }

    public void setIdTipoSintoma(int idTipoSintoma) {
        this.idTipoSintoma = idTipoSintoma;
    }

    public String getDescripcionAtencionSintoma() {
        return descripcionAtencionSintoma;
    }

    public void setDescripcionAtencionSintoma(String descripcionAtencionSintoma) {
        this.descripcionAtencionSintoma = descripcionAtencionSintoma;
    }

    public String getDescripcionTipoSintoma() {
        return descripcionTipoSintoma;
    }

    public void setDescripcionTipoSintoma(String descripcionTipoSintoma) {
        this.descripcionTipoSintoma = descripcionTipoSintoma;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
