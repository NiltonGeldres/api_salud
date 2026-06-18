package com.api_salud.api_salud.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtencionMedicaRequest {

    private Long idAtencion;

    @NotNull(message = "El idPaciente es obligatorio.")
    @Min(value = 1, message = "El idPaciente debe ser un ID válido.")
    private Integer idPaciente;

    @NotNull(message = "El idCuentaAtencion es obligatorio.")
    @Min(value = 1, message = "El idCuentaAtencion debe ser un ID válido.")
    private Integer idCuentaAtencion;

    @NotNull(message = "El idServicio es obligatorio.")
    @Min(value = 1, message = "El idServicio debe ser un ID válido.")
    private Integer idServicio;

    @NotNull(message = "El idEstadoAtencion es obligatorio.")
    private Integer idEstadoAtencion; 

    @NotNull(message = "El estadoFirma es obligatorio.")
    @Pattern(regexp = "^(BORRADOR|FIRMADO_ELECTRONICO)$", message = "El estado de la firma solo permite: BORRADOR o FIRMADO_ELECTRONICO")
    private String estadoFirma;

    private OffsetDateTime tsIngreso;
    
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "El origen del usuario contiene caracteres no permitidos.")
    private String origenRegistroUsuario;

    private String rutaPdfFirmado;
    private String rutaImagenFirma;
    private String hashDocumento;
    
    // 🔄 CORRECCIÓN 1: Nombre alineado exactamente a la clave JSON que busca el SP y la columna DDL
    @NotNull(message = "El idMedicoIngreso es obligatorio.")
    private Integer idMedicoIngreso;
    
    // 🔄 CORRECCIÓN 2: Validación añadida para proteger el esquema Multi-tenant desde Java
    @NotNull(message = "El idEntidad es obligatorio.")
    private Integer idEntidad;
    
    @NotNull(message = "El idUsuarioRegistro es obligatorio.")
    private Integer idUsuarioRegistro;
    
    // Listas internas validadas en cascada profunda
    private List<@Valid AtencionMedicaAntecedenteRequest> antecedentes;
    private List<@Valid AtencionMedicaSintomaRequest> sintomas;
    private List<@Valid AtencionMedicaExamenFisicoRequest> examenFisico;
    private List<@Valid AtencionMedicaTriajeRequest> triaje;
    private List<@Valid AtencionMedicaDiagnosticoRequest> diagnosticos;
    private List<@Valid AtencionMedicaExamenAuxiliarRequest> examenesAuxiliares;
    private List<@Valid AtencionMedicaMedicacionRequest> medicacion;
    
    @Valid
    private AtencionMedicaAltaRequest alta;

    // --- GETTERS Y SETTERS TRADICIONALES ---
    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }

    public Integer getIdCuentaAtencion() { return idCuentaAtencion; }
    public void setIdCuentaAtencion(Integer idCuentaAtencion) { this.idCuentaAtencion = idCuentaAtencion; }

    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }

    public Integer getIdEstadoAtencion() { return idEstadoAtencion; }
    public void setIdEstadoAtencion(Integer idEstadoAtencion) { this.idEstadoAtencion = idEstadoAtencion; }

    public String getEstadoFirma() { return estadoFirma; }
    public void setEstadoFirma(String estadoFirma) { this.estadoFirma = estadoFirma; }

    public OffsetDateTime getTsIngreso() { return tsIngreso; }
    public void setTsIngreso(OffsetDateTime tsIngreso) { this.tsIngreso = tsIngreso; }

    public String getOrigenRegistroUsuario() { return origenRegistroUsuario; }
    public void setOrigenRegistroUsuario(String origenRegistroUsuario) { this.origenRegistroUsuario = origenRegistroUsuario; }

    public String getRutaPdfFirmado() { return rutaPdfFirmado; }
    public void setRutaPdfFirmado(String rutaPdfFirmado) { this.rutaPdfFirmado = rutaPdfFirmado; }

    public String getRutaImagenFirma() { return rutaImagenFirma; }
    public void setRutaImagenFirma(String rutaImagenFirma) { this.rutaImagenFirma = rutaImagenFirma; }

    public String getHashDocumento() { return hashDocumento; }
    public void setHashDocumento(String hashDocumento) { this.hashDocumento = hashDocumento; }

    // 🔄 CORRECCIÓN GET/SET: idMedicoIngreso
    public Integer getIdMedicoIngreso() { return idMedicoIngreso; }
    public void setIdMedicoIngreso(Integer idMedicoIngreso) { this.idMedicoIngreso = idMedicoIngreso; }
    
    public Integer getIdEntidad() { return idEntidad; }
    public void setIdEntidad(Integer idEntidad) { this.idEntidad = idEntidad; }

    public Integer getIdUsuarioRegistro() { return idUsuarioRegistro; }
    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) { this.idUsuarioRegistro = idUsuarioRegistro; }

    public List<AtencionMedicaAntecedenteRequest> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<AtencionMedicaAntecedenteRequest> antecedentes) { this.antecedentes = antecedentes; }

    public List<AtencionMedicaSintomaRequest> getSintomas() { return sintomas; }
    public void setSintomas(List<AtencionMedicaSintomaRequest> sintomas) { this.sintomas = sintomas; }

    public List<AtencionMedicaExamenFisicoRequest> getExamenFisico() { return examenFisico; }
    public void setExamenFisico(List<AtencionMedicaExamenFisicoRequest> examenFisico) { this.examenFisico = examenFisico; }

    public List<AtencionMedicaTriajeRequest> getTriaje() { return triaje; }
    public void setTriaje(List<AtencionMedicaTriajeRequest> triaje) { this.triaje = triaje; }

    public List<AtencionMedicaDiagnosticoRequest> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<AtencionMedicaDiagnosticoRequest> diagnosticos) { this.diagnosticos = diagnosticos; }

    public List<AtencionMedicaExamenAuxiliarRequest> getExamenesAuxiliares() { return examenesAuxiliares; }
    public void setExamenesAuxiliares(List<AtencionMedicaExamenAuxiliarRequest> examenesAuxiliares) { this.examenesAuxiliares = examenesAuxiliares; }

    public List<AtencionMedicaMedicacionRequest> getMedicacion() { return medicacion; }
    public void setMedicacion(List<AtencionMedicaMedicacionRequest> medicacion) { this.medicacion = medicacion; }

    public AtencionMedicaAltaRequest getAlta() { return alta; }
    public void setAlta(AtencionMedicaAltaRequest alta) { this.alta = alta; }
}