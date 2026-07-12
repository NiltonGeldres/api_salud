package com.api_salud.api_salud.response;

public class AtencionMedicaDiagnosticoResponse {
	private Long idDiagnostico;
    private Long idSubclasificacion;
    private String descripcion;
    private String nombreSubclasificacion;
    private Long  idDiagnosticoOrden;
    	
    
	public Long getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(Long idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public Long getIdSubclasificacion() {
		return idSubclasificacion;
	}
	public void setIdSubclasificacion(Long idSubclasificacion) {
		this.idSubclasificacion = idSubclasificacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreSubclasificacion() {
		return nombreSubclasificacion;
	}
	public void setNombreSubclasificacion(String nombreSubclasificacion) {
		this.nombreSubclasificacion = nombreSubclasificacion;
	}
	public Long getIdDiagnosticoOrden() {
		return idDiagnosticoOrden;
	}
	public void setIdDiagnosticoOrden(Long idDiagnosticoOrden) {
		this.idDiagnosticoOrden = idDiagnosticoOrden;
	}
    
}
