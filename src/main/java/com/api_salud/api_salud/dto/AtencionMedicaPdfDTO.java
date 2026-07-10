package com.api_salud.api_salud.dto;

import com.api_salud.api_salud.request.*; // Asumiendo que reutilizas tus Request como DTOs de lectura
import com.api_salud.api_salud.response.AltaResponse;
import com.api_salud.api_salud.response.AntecedenteResponse;
import com.api_salud.api_salud.response.DiagnosticoResponse;
import com.api_salud.api_salud.response.ExamenAuxiliarResponse;
import com.api_salud.api_salud.response.ExamenFisicoResponse;
import com.api_salud.api_salud.response.MedicacionResponse;
import com.api_salud.api_salud.response.PacienteResponse;
import com.api_salud.api_salud.response.SintomaResponse;
import com.api_salud.api_salud.response.TriajeResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtencionMedicaPdfDTO {
	// --- Cabecera ---
    private String nombreEntidad;
    private String nombreMedico;
    private String cmpMedico;
    private String nombreServicio;
    private Long idAtencion;
    private String estadoFirma;
    private Long getIdMedicoIngreso;
    
    // --- Datos de Paciente (Nuevo objeto response) ---
    private PacienteResponse paciente;
    
    // --- Listas de detalle (Ahora usando DTOs de respuesta) ---
    private List<TriajeResponse> triaje; // Corresponde al JSON "triaje"
    private List<AntecedenteResponse> antecedentes;
    private List<SintomaResponse> sintomas;
    private List<ExamenFisicoResponse> examenFisico;
    private List<DiagnosticoResponse> diagnosticos;
    private List<ExamenAuxiliarResponse> examenesAuxiliares;
    private List<MedicacionResponse> medicacion;
    private List<AltaResponse> alta;
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
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
	public PacienteResponse getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteResponse paciente) {
		this.paciente = paciente;
	}
	public List<TriajeResponse> getTriaje() {
		return triaje;
	}
	public void setTriaje(List<TriajeResponse> triaje) {
		this.triaje = triaje;
	}
	public List<AntecedenteResponse> getAntecedentes() {
		return antecedentes;
	}
	public void setAntecedentes(List<AntecedenteResponse> antecedentes) {
		this.antecedentes = antecedentes;
	}
	public List<SintomaResponse> getSintomas() {
		return sintomas;
	}
	public void setSintomas(List<SintomaResponse> sintomas) {
		this.sintomas = sintomas;
	}
	public List<ExamenFisicoResponse> getExamenFisico() {
		return examenFisico;
	}
	public void setExamenFisico(List<ExamenFisicoResponse> examenFisico) {
		this.examenFisico = examenFisico;
	}
	public List<DiagnosticoResponse> getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(List<DiagnosticoResponse> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public List<ExamenAuxiliarResponse> getExamenesAuxiliares() {
		return examenesAuxiliares;
	}
	public void setExamenesAuxiliares(List<ExamenAuxiliarResponse> examenesAuxiliares) {
		this.examenesAuxiliares = examenesAuxiliares;
	}
	public List<MedicacionResponse> getMedicacion() {
		return medicacion;
	}
	public void setMedicacion(List<MedicacionResponse> medicacion) {
		this.medicacion = medicacion;
	}
	public List<AltaResponse> getAlta() {
		return alta;
	}
	public void setAlta(List<AltaResponse> alta) {
		this.alta = alta;
	}
	public Long getGetIdMedicoIngreso() {
		return getIdMedicoIngreso;
	}
	public void setGetIdMedicoIngreso(Long getIdMedicoIngreso) {
		this.getIdMedicoIngreso = getIdMedicoIngreso;
	}	
 
    
}