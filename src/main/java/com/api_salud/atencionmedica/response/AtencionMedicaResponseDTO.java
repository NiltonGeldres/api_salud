package com.api_salud.atencionmedica.response;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * DTO que encapsula la respuesta completa para la consulta de una
 * Atención Médica (Cabecera y todos sus detalles).
 */
public class AtencionMedicaResponseDTO {

    // --- Cabecera: Campos de igm_atenciones_medicas.atenciones_medicas ---

    // id_atencion integer (Clave Primaria, generada en DB)
    private Integer idAtencion;
    // id_paciente integer
    private Integer idPaciente;
    // id_cuenta_atencion integer
    private Integer idCuentaAtencion;
    // id_servicio integer
    private Integer idServicio;
    // id_medico_ingreso integer
    private Integer idMedicoIngreso;
    // id_estado_atencion integer
    private Integer idEstadoAtencion;
    // ts_ingreso timestamp with time zone
    private OffsetDateTime tsIngreso;
    // id_usuario_registro integer
    private Integer idUsuarioRegistro;
    // origen_registro_usuario character varying(50)
    private String origenRegistroUsuario;
    // ts_registro timestamp with time zone (Generado en DB)
    private OffsetDateTime tsRegistro;
    // ts_actualizacion timestamp with time zone (Generado en DB)
    private OffsetDateTime tsActualizacion;

    // --- Detalles (Listas de DTOs de las Tablas Relacionadas) ---

    private List<AntecedenteResponseDTO> antecedentes;
    private List<DiagnosticoResponseDTO> diagnosticos;
    private List<DiscapacidadResponseDTO> discapacidades;
    private List<DiscapacidadOtrosResponseDTO> discapacidadOtros;
    private List<ExamenFisicoResponseDTO> examenesFisicos;
    private List<OrdenMedicaResponseDTO> ordenesMedicas;
    private List<MedicacionResponseDTO> medicacion;
    private List<SintomaResponseDTO> sintomas;
    private List<TratamientoResponseDTO> tratamientos;

    // --- Constructor, Getters y Setters (omiten por brevedad) ---

    public AtencionMedicaResponseDTO() {}

    public Integer getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Integer idAtencion) { this.idAtencion = idAtencion; }

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

    public OffsetDateTime getTsIngreso() { return tsIngreso; }
    public void setTsIngreso(OffsetDateTime tsIngreso) { this.tsIngreso = tsIngreso; }

    public Integer getIdUsuarioRegistro() { return idUsuarioRegistro; }
    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) { this.idUsuarioRegistro = idUsuarioRegistro; }

    public String getOrigenRegistroUsuario() { return origenRegistroUsuario; }
    public void setOrigenRegistroUsuario(String origenRegistroUsuario) { this.origenRegistroUsuario = origenRegistroUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }

    public OffsetDateTime getTsActualizacion() { return tsActualizacion; }
    public void setTsActualizacion(OffsetDateTime tsActualizacion) { this.tsActualizacion = tsActualizacion; }

    public List<AntecedenteResponseDTO> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<AntecedenteResponseDTO> antecedentes) { this.antecedentes = antecedentes; }

    public List<DiagnosticoResponseDTO> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<DiagnosticoResponseDTO> diagnosticos) { this.diagnosticos = diagnosticos; }

    public List<DiscapacidadResponseDTO> getDiscapacidades() { return discapacidades; }
    public void setDiscapacidades(List<DiscapacidadResponseDTO> discapacidades) { this.discapacidades = discapacidades; }

    public List<DiscapacidadOtrosResponseDTO> getDiscapacidadOtros() { return discapacidadOtros; }
    public void setDiscapacidadOtros(List<DiscapacidadOtrosResponseDTO> discapacidadOtros) { this.discapacidadOtros = discapacidadOtros; }

    public List<ExamenFisicoResponseDTO> getExamenesFisicos() { return examenesFisicos; }
    public void setExamenesFisicos(List<ExamenFisicoResponseDTO> examenesFisicos) { this.examenesFisicos = examenesFisicos; }

    public List<OrdenMedicaResponseDTO> getOrdenesMedicas() { return ordenesMedicas; }
    public void setOrdenesMedicas(List<OrdenMedicaResponseDTO> ordenesMedicas) { this.ordenesMedicas = ordenesMedicas; }

    public List<MedicacionResponseDTO> getMedicacion() { return medicacion; }
    public void setMedicacion(List<MedicacionResponseDTO> medicacion) { this.medicacion = medicacion; }

    public List<SintomaResponseDTO> getSintomas() { return sintomas; }
    public void setSintomas(List<SintomaResponseDTO> sintomas) { this.sintomas = sintomas; }

    public List<TratamientoResponseDTO> getTratamientos() { return tratamientos; }
    public void setTratamientos(List<TratamientoResponseDTO> tratamientos) { this.tratamientos = tratamientos; }
}