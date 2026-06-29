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