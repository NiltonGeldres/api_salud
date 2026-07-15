package com.api_salud.api_salud.dto;

import com.api_salud.api_salud.request.*; // Asumiendo que reutilizas tus Request como DTOs de lectura
import com.api_salud.api_salud.response.AtencionMedicaAltaResponse;
import com.api_salud.api_salud.response.AtencionMedicaAntecedenteResponse;
import com.api_salud.api_salud.response.AtencionMedicaDiagnosticoResponse;
import com.api_salud.api_salud.response.AtencionMedicaExamenFisicoResponse;
import com.api_salud.api_salud.response.AtencionMedicaExamenesResponse;
import com.api_salud.api_salud.response.AtencionMedicaMedicacionResponse;
import com.api_salud.api_salud.response.AtencionMedicaPacienteResponse;
import com.api_salud.api_salud.response.AtencionMedicaSintomaResponse;
import com.api_salud.api_salud.response.AtencionMedicaTriajeResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtencionMedicaPdfDTO {
	// --- Cabecera ---
    private String idEntidad;
    private String nombreEntidad;
    private String nombreMedicoIngreso;
    private Integer idMedicoIngreso;
    private String cmpMedico;
    private String nombreServicio;
    private String nombreEspecialidad;
    private Long idAtencion;
    private String estadoFirma;
    private Long getIdMedicoIngreso;
    
    // --- Datos de Paciente (Nuevo objeto response) ---
    private AtencionMedicaPacienteResponse paciente;
    
    // --- Listas de detalle (Ahora usando DTOs de respuesta) ---
    private List<AtencionMedicaTriajeResponse> triajes; // Corresponde al JSON "triaje"
    private List<AtencionMedicaAntecedenteResponse> antecedentes;
    private List<AtencionMedicaSintomaResponse> sintomas;
    private List<AtencionMedicaExamenFisicoResponse> examenFisico;
    private List<AtencionMedicaDiagnosticoResponse> diagnosticos;
    private List<AtencionMedicaExamenesResponse> examenesAuxiliares;
    private List<AtencionMedicaMedicacionResponse> medicacion;
    private List<AtencionMedicaAltaResponse> alta;
	public String getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	public String getNombreMedicoIngreso() {
		return nombreMedicoIngreso;
	}
	public void setNombreMedicoIngreso(String nombreMedicoIngreso) {
		this.nombreMedicoIngreso = nombreMedicoIngreso;
	}
	public Integer getIdMedicoIngreso() {
		return idMedicoIngreso;
	}
	public void setIdMedicoIngreso(Integer idMedicoIngreso) {
		this.idMedicoIngreso = idMedicoIngreso;
	}
	public String getCmpMedico() {
		return cmpMedico;
	}
	public void setCmpMedico(String cmpMedico) {
		this.cmpMedico = cmpMedico;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}
	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}
	public Long getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(Long idAtencion) {
		this.idAtencion = idAtencion;
	}
	public String getEstadoFirma() {
		return estadoFirma;
	}
	public void setEstadoFirma(String estadoFirma) {
		this.estadoFirma = estadoFirma;
	}
	public Long getGetIdMedicoIngreso() {
		return getIdMedicoIngreso;
	}
	public void setGetIdMedicoIngreso(Long getIdMedicoIngreso) {
		this.getIdMedicoIngreso = getIdMedicoIngreso;
	}
	public AtencionMedicaPacienteResponse getPaciente() {
		return paciente;
	}
	public void setPaciente(AtencionMedicaPacienteResponse paciente) {
		this.paciente = paciente;
	}
	public List<AtencionMedicaTriajeResponse> getTriajes() {
		return triajes;
	}
	public void setTriajes(List<AtencionMedicaTriajeResponse> triajes) {
		this.triajes = triajes;
	}
	public List<AtencionMedicaAntecedenteResponse> getAntecedentes() {
		return antecedentes;
	}
	public void setAntecedentes(List<AtencionMedicaAntecedenteResponse> antecedentes) {
		this.antecedentes = antecedentes;
	}
	public List<AtencionMedicaSintomaResponse> getSintomas() {
		return sintomas;
	}
	public void setSintomas(List<AtencionMedicaSintomaResponse> sintomas) {
		this.sintomas = sintomas;
	}
	public List<AtencionMedicaExamenFisicoResponse> getExamenFisico() {
		return examenFisico;
	}
	public void setExamenFisico(List<AtencionMedicaExamenFisicoResponse> examenFisico) {
		this.examenFisico = examenFisico;
	}
	public List<AtencionMedicaDiagnosticoResponse> getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(List<AtencionMedicaDiagnosticoResponse> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public List<AtencionMedicaExamenesResponse> getExamenesAuxiliares() {
		return examenesAuxiliares;
	}
	public void setExamenesAuxiliares(List<AtencionMedicaExamenesResponse> examenesAuxiliares) {
		this.examenesAuxiliares = examenesAuxiliares;
	}
	public List<AtencionMedicaMedicacionResponse> getMedicacion() {
		return medicacion;
	}
	public void setMedicacion(List<AtencionMedicaMedicacionResponse> medicacion) {
		this.medicacion = medicacion;
	}
	public List<AtencionMedicaAltaResponse> getAlta() {
		return alta;
	}
	public void setAlta(List<AtencionMedicaAltaResponse> alta) {
		this.alta = alta;
	}
    
    
	
    
}