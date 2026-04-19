package com.api_salud.api_salud.dto;

public class EntidadDto {
	   long idEntidad ;
	   String nombre ;
	   String codigo ;
	   String direccion ;
	   String nombreDistrito ;
	   String logoUrl ;

	   public long getIdEntidad() {
		return idEntidad;
	}
	   public void setIdEntidad(long idEntidad) {
		   this.idEntidad = idEntidad;
	   }
	   public String getNombre() {
		   return nombre;
	   }
	   public void setNombre(String nombre) {
		   this.nombre = nombre;
	   }
	   public String getCodigo() {
		   return codigo;
	   }
	   public void setCodigo(String codigo) {
		   this.codigo = codigo;
	   }
	   public String getDireccion() {
		   return direccion;
	   }
	   public void setDireccion(String direccion) {
		   this.direccion = direccion;
	   }
	   public String getNombreDistrito() {
		   return nombreDistrito;
	   }
	   public void setNombreDistrito(String nombreDistrito) {
		   this.nombreDistrito = nombreDistrito;
	   }
	   public String getLogoUrl() {
		   return logoUrl;
	   }
	   public void setLogoUrl(String logoUrl) {
		   this.logoUrl = logoUrl;
	   }
	   
	   
}
