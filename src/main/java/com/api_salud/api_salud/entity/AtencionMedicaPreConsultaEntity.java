/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.api_salud.entity;

import java.util.ArrayList;

/**
 *
 * @author ngeldres
 */
public class AtencionMedicaPreConsultaEntity {
   int idAtencion;
    int idUsuario ;
    String origenRegistroUsuario;    
    String fechaRegistro;
    String horaRegistro;
    ArrayList<AtencionMedicaTriajeEntity>               detalleTriajes ;
    
    
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getOrigenRegistroUsuario() {
		return origenRegistroUsuario;
	}
	public void setOrigenRegistroUsuario(String origenRegistroUsuario) {
		this.origenRegistroUsuario = origenRegistroUsuario;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getHoraRegistro() {
		return horaRegistro;
	}
	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
	}
	public ArrayList<AtencionMedicaTriajeEntity> getDetalleTriajes() {
		return detalleTriajes;
	}
	public void setDetalleTriajes(ArrayList<AtencionMedicaTriajeEntity> detalleTriajes) {
		this.detalleTriajes = detalleTriajes;
	}

    
    
    
}
