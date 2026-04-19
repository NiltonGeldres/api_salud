package com.api_salud.api_salud.response;

public class EntidadResponse {
    private Long idEntidad;
    private String nombre;
	private String direccion;
	private String nombreDistrito;
	private String codigo ;
	private String logoUrl ;	

    public EntidadResponse() {
        // constructor vacío requerido
    }
    
    public EntidadResponse(Long idEntidad, String nombre, String nombreDistrito, String direccion) {
        
        this.idEntidad = idEntidad;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreDistrito = nombreDistrito;      
    }

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	
}
