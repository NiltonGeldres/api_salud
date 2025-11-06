/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.api_salud.entity;

import javax.validation.constraints.NotNull;

/**
 *
 * @author ngeldres
 */
public class FacturacionServicioDespachoEntity {
	
	@NotNull(message = "El dato idOrden, no puede estar en blanco")
    int idOrden;
	@NotNull(message = "El dato idProducto, no puede estar en blanco")
    int idProducto;
	@NotNull(message = "El dato cantidad, no puede estar en blanco")
    double cantidad;
	@NotNull(message = "El dato precio, no puede estar en blanco")
    double precio;
	@NotNull(message = "El dato total, no puede estar en blanco")
    double total;
	@NotNull(message = "El dato labConfHIS, no puede estar en blanco")
    String labConfHIS;
	@NotNull(message = "El dato GrupoHIS, no puede estar en blanco")
    int GrupoHIS;
	@NotNull(message = "El dato SubGrupoHIS, no puede estar en blanco")
    int  SubGrupoHIS;
	
	public int getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getLabConfHIS() {
		return labConfHIS;
	}
	public void setLabConfHIS(String labConfHIS) {
		this.labConfHIS = labConfHIS;
	}
	public int getGrupoHIS() {
		return GrupoHIS;
	}
	public void setGrupoHIS(int grupoHIS) {
		GrupoHIS = grupoHIS;
	}
	public int getSubGrupoHIS() {
		return SubGrupoHIS;
	}
	public void setSubGrupoHIS(int subGrupoHIS) {
		SubGrupoHIS = subGrupoHIS;
	}

    
        
}
