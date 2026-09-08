package com.api_salud.api_salud.request;

import com.api_salud.api_salud.request.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtencionMedicaRequest {

	private Long idAtencion;

    // =========================================================================
    // 1. DATOS DE CABECERA Y FILIACIÓN (OBLIGATORIOS SIEMPRE: Borrador y Completo)
    // =========================================================================

  //  @NotNull(message = "Los datos de filiación del paciente (HC y Nombre) son obligatorios.", 
     //        groups = ValidationGroups.BorradorGroup.class)
  //  @Valid
    private AtencionMedicaPacienteRequest paciente; // 🟢 OBLIGATORIO DESDE EL INICIO

    @NotNull(message = "El idCita es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idCita debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idCita;
    
    @NotNull(message = "El idPaciente es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idPaciente debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idPaciente;

    @NotNull(message = "El idCuentaAtencion es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idCuentaAtencion debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idCuentaAtencion;

    @NotNull(message = "El idServicio es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idServicio debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idServicio;

    @NotNull(message = "El idMedicoIngreso es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idMedicoIngreso debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idMedicoIngreso;

    @NotNull(message = "El idEstadoAtencion es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idEstadoAtencion debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idEstadoAtencion;

    @NotNull(message = "El idUsuarioRegistro es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idUsuarioRegistro debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idUsuarioRegistro;

    @NotBlank(message = "El origen del registro es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Size(max = 50, message = "El origen no puede exceder 50 caracteres.", groups = ValidationGroups.BorradorGroup.class)
    private String origenRegistroUsuario;

    @NotBlank(message = "El estado de la firma es obligatorio.", groups = ValidationGroups.BorradorGroup.class)
    @Pattern(regexp = "^(BORRADOR|PENDIENTE|FIRMADO_ELECTRONICO)$", 
             message = "Estado de firma no permitido.", groups = ValidationGroups.BorradorGroup.class)
    private String estadoFirma;

    @NotNull(message = "El idEntidad es mandatorio para aislamiento Multi-tenant.", groups = ValidationGroups.BorradorGroup.class)
    @Min(value = 1, message = "El idEntidad debe ser válido.", groups = ValidationGroups.BorradorGroup.class)
    private Integer idEntidad;

    private Integer idEspecialidad;

    private String rutaPdfFirmado;
    
    // =========================================================================
    // 2. SECCIONES CLÍNICAS OBLIGATORIAS EN ATENCIÓN COMPLETA (CompletoGroup)
    // =========================================================================

    @NotEmpty(message = "Los datos de triaje / signos vitales son obligatorios para cerrar la atención.", 
              groups = ValidationGroups.CompletoGroup.class)
    @Valid
    private List<AtencionMedicaTriajeRequest> triajes; // 🟡 REQUERIDO SOLO EN ATENCIÓN COMPLETA

    @NotEmpty(message = "Los antecedentes son obligatorios.", groups = ValidationGroups.CompletoGroup.class)
    @Valid
    private List<AtencionMedicaAntecedenteRequest> antecedentes;

    @NotEmpty(message = "Los síntomas son obligatorios.", groups = ValidationGroups.CompletoGroup.class)
    @Valid
    private List<AtencionMedicaSintomaRequest> sintomas;

    @NotEmpty(message = "El examen físico es obligatorio.", groups = ValidationGroups.CompletoGroup.class)
    @Valid
    private List<AtencionMedicaExamenFisicoRequest> examenFisico;

    @NotEmpty(message = "Debe registrar obligatoriamente al menos un diagnóstico.", groups = ValidationGroups.CompletoGroup.class)
    @Valid
    private List<AtencionMedicaDiagnosticoRequest> diagnosticos;

    @NotEmpty(message = "El registro de alta/salida es obligatorio.", groups = ValidationGroups.CompletoGroup.class)
    @Valid
    private List<AtencionMedicaAltaRequest> alta;

    // =========================================================================
    // 3. SECCIONES CLÍNICAS OPCIONALES (Pueden quedar vacías en el PDF)
    // =========================================================================

    @Valid
    private List<AtencionMedicaExamenAuxiliarRequest> examenesAuxiliares; 

    @Valid
    private List<AtencionMedicaMedicacionRequest> medicacion;
    

    // --- GETTERS Y SETTERS ---
    public Long getIdAtencion() { return idAtencion; }
    
    
    
    public Integer getIdCita() {
		return idCita;
	}



	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}



	public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }
    
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    
    public Integer getIdCuentaAtencion() { return idCuentaAtencion; }
    public void setIdCuentaAtencion(Integer idCuentaAtencion) { this.idCuentaAtencion = idCuentaAtencion; }
    
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    
    public Integer getIdMedicoIngreso() { return idMedicoIngreso; }
    public void setIdMedicoIngreso(Integer idMedicoIngreso) { this.idMedicoIngreso = idMedicoIngreso; }
    
    public Integer getIdEstadoAtencion() { return idEstadoAtencion; }
    public void setIdEstadoAtencion(Integer idEstadoAtencion) { this.idEstadoAtencion = idEstadoAtencion; }
    
    public Integer getIdUsuarioRegistro() { return idUsuarioRegistro; }
    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) { this.idUsuarioRegistro = idUsuarioRegistro; }
    
    public String getOrigenRegistroUsuario() { return origenRegistroUsuario; }
    public void setOrigenRegistroUsuario(String origenRegistroUsuario) { this.origenRegistroUsuario = origenRegistroUsuario; }
    
    public String getEstadoFirma() { return estadoFirma; }
    public void setEstadoFirma(String estadoFirma) { this.estadoFirma = estadoFirma; }
    
    public Integer getIdEntidad() { return idEntidad; }
    public void setIdEntidad(Integer idEntidad) { this.idEntidad = idEntidad; }
    
    public AtencionMedicaPacienteRequest getPaciente() { return paciente; }
    public void setPaciente(AtencionMedicaPacienteRequest paciente) { this.paciente = paciente; }
    
    public String getRutaPdfFirmado() {	return rutaPdfFirmado;}
	public void setRutaPdfFirmado(String rutaPdfFirmado) {this.rutaPdfFirmado = rutaPdfFirmado;}

	public List<AtencionMedicaAltaRequest> getAlta() { return alta; }
    public void setAlta(List<AtencionMedicaAltaRequest> alta) { this.alta = alta; }
    
    public List<AtencionMedicaTriajeRequest> getTriajes() { return triajes; }
    public void setTriajes(List<AtencionMedicaTriajeRequest> triajes) { this.triajes = triajes; }
    
    public List<AtencionMedicaAntecedenteRequest> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<AtencionMedicaAntecedenteRequest> antecedentes) { this.antecedentes = antecedentes; }
    
    public List<AtencionMedicaSintomaRequest> getSintomas() { return sintomas; }
    public void setSintomas(List<AtencionMedicaSintomaRequest> sintomas) { this.sintomas = sintomas; }
    
    public List<AtencionMedicaExamenFisicoRequest> getExamenFisico() { return examenFisico; }
    public void setExamenFisico(List<AtencionMedicaExamenFisicoRequest> examenFisico) { this.examenFisico = examenFisico; }
    
    public List<AtencionMedicaDiagnosticoRequest> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<AtencionMedicaDiagnosticoRequest> diagnosticos) { this.diagnosticos = diagnosticos; }
    
    public List<AtencionMedicaExamenAuxiliarRequest> getExamenesAuxiliares() { return examenesAuxiliares; }
    public void setExamenesAuxiliares(List<AtencionMedicaExamenAuxiliarRequest> examenesAuxiliares) { this.examenesAuxiliares = examenesAuxiliares; }
    
    public List<AtencionMedicaMedicacionRequest> getMedicacion() { return medicacion; }
    public void setMedicacion(List<AtencionMedicaMedicacionRequest> medicacion) { this.medicacion = medicacion; }

	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

    
}

/*

* 
/
package com.api_salud.api_salud.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtencionMedicaRequest {

    private Long idAtencion;

    @NotNull(message = "El idPaciente es obligatorio.")
    @Min(value = 1, message = "El idPaciente debe ser un identificador numérico válido.")
    private Integer idPaciente;

    @NotNull(message = "El idCuentaAtencion es obligatorio.")
    @Min(value = 1, message = "El idCuentaAtencion debe ser un identificador numérico válido.")
    private Integer idCuentaAtencion;

    @NotNull(message = "El idServicio es obligatorio.")
    @Min(value = 1, message = "El idServicio debe ser un identificador numérico válido.")
    private Integer idServicio;

    @NotNull(message = "El idMedicoIngreso es obligatorio.")
    @Min(value = 1, message = "El idMedicoIngreso debe ser un identificador numérico válido.")
    private Integer idMedicoIngreso;

    @NotNull(message = "El idEstadoAtencion es obligatorio.")
    @Min(value = 1, message = "El idEstadoAtencion debe ser un identificador numérico válido.")
    private Integer idEstadoAtencion;

    @NotNull(message = "El idUsuarioRegistro es obligatorio.")
    @Min(value = 1, message = "El idUsuarioRegistro debe ser un identificador numérico válido.")
    private Integer idUsuarioRegistro;

    @NotBlank(message = "El origen del registro no puede estar vacío.")
    @Size(max = 50, message = "El origen del registro no puede exceder los 50 caracteres.")
    private String origenRegistroUsuario;

    @NotBlank(message = "El estado de la firma es obligatorio.")
    @Pattern(regexp = "^(BORRADOR|PENDIENTE|FIRMADO_ELECTRONICO)$", 
             message = "El estado de la firma no corresponde a los valores permitidos.")
    private String estadoFirma;

    @NotNull(message = "El idEntidad es mandatorio para el aislamiento Multi-tenant.")
    @Min(value = 1, message = "El idEntidad debe ser un identificador numérico válido.")
    private Integer idEntidad;

    // Métricas y soporte de Renderizado PDF HCE
    @NotNull(message = "El objeto paciente de apoyo no puede ser nulo.")
    @Valid
    private AtencionMedicaPacienteRequest paciente; 

    @Valid
//    private AtencionMedicaAltaRequest alta;
    private List<AtencionMedicaAltaRequest> alta;
    
    @Valid
    private List<AtencionMedicaTriajeRequest> triaje;

    @Valid
    private List<AtencionMedicaAntecedenteRequest> antecedentes;

    @Valid
    private List<AtencionMedicaSintomaRequest> sintomas;

    @Valid
    private List<AtencionMedicaExamenFisicoRequest> examenFisico;

    @NotEmpty(message = "La atención debe registrar obligatoriamente al menos un diagnóstico.")
    @Valid
    private List<AtencionMedicaDiagnosticoRequest> diagnosticos;

    @Valid
    private List<AtencionMedicaExamenAuxiliarRequest> examenesAuxiliares;

    @Valid
    private List<AtencionMedicaMedicacionRequest> medicacion;

    // --- GETTERS Y SETTERS ---
    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    public Integer getIdCuentaAtencion() { return idCuentaAtencion; }
    public void setIdCuentaAtencion(Integer idCuentaAtencion) { this.idCuentaAtencion = idCuentaAtencion; }
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    public Integer getIdMedicoIngreso() { return idMedicoIngreso; }
    public void setIdMedicoIngreso(Integer idMedicoIngreso) { this.idMedicoIngreso = idMedicoIngreso; }
    public Integer getIdEstadoAtencion() { return idEstadoAtencion; }
    public void setIdEstadoAtencion(Integer idEstadoAtencion) { this.idEstadoAtencion = idEstadoAtencion; }
    public Integer getIdUsuarioRegistro() { return idUsuarioRegistro; }
    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) { this.idUsuarioRegistro = idUsuarioRegistro; }
    public String getOrigenRegistroUsuario() { return origenRegistroUsuario; }
    public void setOrigenRegistroUsuario(String origenRegistroUsuario) { this.origenRegistroUsuario = origenRegistroUsuario; }
    public String getEstadoFirma() { return estadoFirma; }
    public void setEstadoFirma(String estadoFirma) { this.estadoFirma = estadoFirma; }
    public Integer getIdEntidad() { return idEntidad; }
    public void setIdEntidad(Integer idEntidad) { this.idEntidad = idEntidad; }
    public AtencionMedicaPacienteRequest getPaciente() { return paciente; }
    public void setPaciente(AtencionMedicaPacienteRequest paciente) { this.paciente = paciente; }
	public List<AtencionMedicaAltaRequest> getAlta() {return alta;}
	public void setAlta(List<AtencionMedicaAltaRequest> alta) {	this.alta = alta;}
    public List<AtencionMedicaTriajeRequest> getTriaje() { return triaje; }
    public void setTriaje(List<AtencionMedicaTriajeRequest> triaje) { this.triaje = triaje; }
    public List<AtencionMedicaAntecedenteRequest> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<AtencionMedicaAntecedenteRequest> antecedentes) { this.antecedentes = antecedentes; }
    public List<AtencionMedicaSintomaRequest> getSintomas() { return sintomas; }
    public void setSintomas(List<AtencionMedicaSintomaRequest> sintomas) { this.sintomas = sintomas; }
    public List<AtencionMedicaExamenFisicoRequest> getExamenFisico() { return examenFisico; }
    public void setExamenFisico(List<AtencionMedicaExamenFisicoRequest> examenFisico) { this.examenFisico = examenFisico; }
    public List<AtencionMedicaDiagnosticoRequest> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<AtencionMedicaDiagnosticoRequest> diagnosticos) { this.diagnosticos = diagnosticos; }
    public List<AtencionMedicaExamenAuxiliarRequest> getExamenesAuxiliares() { return examenesAuxiliares; }
    public void setExamenesAuxiliares(List<AtencionMedicaExamenAuxiliarRequest> examenesAuxiliares) { this.examenesAuxiliares = examenesAuxiliares; }
    public List<AtencionMedicaMedicacionRequest> getMedicacion() { return medicacion; }
    public void setMedicacion(List<AtencionMedicaMedicacionRequest> medicacion) { this.medicacion = medicacion; }
}

*/