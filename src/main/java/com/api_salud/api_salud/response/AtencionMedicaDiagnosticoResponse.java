package com.api_salud.api_salud.response;

public class AtencionMedicaDiagnosticoResponse {
	private Long idDiagnostico;
    private Long idSubclasificacion;
    private String descripcion;
    
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
    
}
