package com.api_salud.api_salud.response;

public class UsuarioDatosGlobalesResponse {
	
      String nombresUsuario;
      String nombreEntidad;
      String email ;
      int numeroCelular;
	  public String getNombresUsuario() {
		  return nombresUsuario;
	  }
	  public void setNombresUsuario(String nombresUsuario) {
		  this.nombresUsuario = nombresUsuario;
	  }
	  public String getNombreEntidad() {
		  return nombreEntidad;
	  }
	  public void setNombreEntidad(String nombreEntidad) {
		  this.nombreEntidad = nombreEntidad;
	  }
	  public String getEmail() {
		  return email;
	  }
	  public void setEmail(String email) {
		  this.email = email;
	  }
	  public int getNumeroCelular() {
		  return numeroCelular;
	  }
	  public void setNumeroCelular(int numeroCelular) {
		  this.numeroCelular = numeroCelular;
	  } 
      
      
}
