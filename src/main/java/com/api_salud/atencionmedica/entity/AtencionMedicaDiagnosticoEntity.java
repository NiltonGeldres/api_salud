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
public class AtencionMedicaDiagnosticoEntity {
    int idAtencionDiagnostico;
    int idAtencion;
    int idDiagnostico;
    int idSubclasificacion;
    int idLab1;    
    int idUsuario ;
    int idDiagnosticoOrden ;
    
    String codigoDiagnostico;
    String descripcionDiagnostico;
    String descripcionSubclasificacion;
    String descripcionLab1;
    String descripcionDiagnosticoOrden ;
    
//    int idLab2;    
//    int idLab3;    
//    int idLab4;    
//    int idLab5;    
//    int idLab6;    
  
//    String descripcionLab2;
//    String descripcionLab3;
//    String descripcionLab4;
//    String descripcionLab5;
//    String descripcionLab6;

    public int getIdAtencionDiagnostico() {
        return idAtencionDiagnostico;
    }

    public void setIdAtencionDiagnostico(int idAtencionDiagnostico) {
        this.idAtencionDiagnostico = idAtencionDiagnostico;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdSubclasificacion() {
        return idSubclasificacion;
    }

    public void setIdSubclasificacion(int idSubclasificacion) {
        this.idSubclasificacion = idSubclasificacion;
    }

    public int getIdLab1() {
        return idLab1;
    }

    public void setIdLab1(int idLab1) {
        this.idLab1 = idLab1;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDiagnosticoOrden() {
        return idDiagnosticoOrden;
    }

    public void setIdDiagnosticoOrden(int idDiagnosticoOrden) {
        this.idDiagnosticoOrden = idDiagnosticoOrden;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public String getDescripcionSubclasificacion() {
        return descripcionSubclasificacion;
    }

    public void setDescripcionSubclasificacion(String descripcionSubclasificacion) {
        this.descripcionSubclasificacion = descripcionSubclasificacion;
    }

    public String getDescripcionLab1() {
        return descripcionLab1;
    }

    public void setDescripcionLab1(String descripcionLab1) {
        this.descripcionLab1 = descripcionLab1;
    }

    public String getDescripcionDiagnosticoOrden() {
        return descripcionDiagnosticoOrden;
    }

    public void setDescripcionDiagnosticoOrden(String descripcionDiagnosticoOrden) {
        this.descripcionDiagnosticoOrden = descripcionDiagnosticoOrden;
    }

    public String getCodigoDiagnostico() {
        return codigoDiagnostico;
    }

    public void setCodigoDiagnostico(String codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }


    
}
