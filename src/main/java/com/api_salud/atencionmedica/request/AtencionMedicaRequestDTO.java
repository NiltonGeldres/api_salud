package com.api_salud.atencionmedica.request;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * DTO que encapsula la solicitud completa para el registro de una nueva
 * Atención Médica (Cabecera y todos sus detalles).
 *
 * NOTA: Los campos que son auto-generados (id_atencion, ts_actualizacion, ts_registro)
 * no se incluyen en el DTO de inserción, ya que se manejarán en el backend/base de datos.
 */
public class AtencionMedicaRequestDTO {

    // --- Cabecera: Campos de igm_atenciones_medicas.atenciones_medicas ---

    // id_paciente integer (OBLIGATORIO)
    private Integer idPaciente;
    // id_cuenta_atencion integer (OBLIGATORIO)
    private Integer idCuentaAtencion;
    // id_servicio integer (OBLIGATORIO)
    private Integer idServicio;
    // id_medico_ingreso integer (OBLIGATORIO)
    private Integer idMedicoIngreso;
    // id_estado_atencion integer (OBLIGATORIO)
    private Integer idEstadoAtencion;
    // ts_ingreso timestamp with time zone (OBLIGATORIO)
    private OffsetDateTime tsIngreso;
    // id_usuario_registro integer (OBLIGATORIO)
    private Integer idUsuarioRegistro;
    // origen_registro_usuario character varying(50) (OBLIGATORIO)
    private String origenRegistroUsuario;

    // --- Detalles (Listas de DTOs de las Tablas Relacionadas) ---

    // 1. Antecedentes (atenciones_medicas_antecedentes)
    private List<AntecedenteRequestDTO> antecedentes;

    // 2. Diagnósticos (atenciones_medicas_diagnosticos)
    private List<DiagnosticoRequestDTO> diagnosticos;

    // 3. Discapacidad (atenciones_medicas_discapacidad)
    private List<DiscapacidadRequestDTO> discapacidades;

    // 4. Discapacidad Otros (atenciones_medicas_discapacidad_otros)
    // Nota: Esta tabla parece tener una relación 1:1 con Atenciones_Medicas (no tiene clave compuesta),
    // pero la definimos como lista por si la lógica de negocio requiere múltiples entradas.
    private List<DiscapacidadOtrosRequestDTO> discapacidadOtros;

    // 5. Examen Físico (atenciones_medicas_examen_fisico)
    private List<ExamenFisicoRequestDTO> examenesFisicos;

    // 6. Órdenes Médicas (atenciones_medicas_ordenes_medicas)
    private List<OrdenMedicaRequestDTO> ordenesMedicas;

    // 7. Medicación (atenciones_medicas_medicacion)
    private List<MedicacionRequestDTO> medicacion;

    // 8. Síntomas (atenciones_medicas_sintomas)
    private List<SintomaRequestDTO> sintomas;

    // 9. Tratamientos (atenciones_medicas_tratamientos)
    private List<TratamientoRequestDTO> tratamientos;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaRequestDTO() {}

    // Getters y Setters para todos los campos de cabecera y detalle (omiten por brevedad)

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

    public List<AntecedenteRequestDTO> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<AntecedenteRequestDTO> antecedentes) { this.antecedentes = antecedentes; }

    public List<DiagnosticoRequestDTO> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<DiagnosticoRequestDTO> diagnosticos) { this.diagnosticos = diagnosticos; }

    public List<DiscapacidadRequestDTO> getDiscapacidades() { return discapacidades; }
    public void setDiscapacidades(List<DiscapacidadRequestDTO> discapacidades) { this.discapacidades = discapacidades; }

    public List<DiscapacidadOtrosRequestDTO> getDiscapacidadOtros() { return discapacidadOtros; }
    public void setDiscapacidadOtros(List<DiscapacidadOtrosRequestDTO> discapacidadOtros) { this.discapacidadOtros = discapacidadOtros; }

    public List<ExamenFisicoRequestDTO> getExamenesFisicos() { return examenesFisicos; }
    public void setExamenesFisicos(List<ExamenFisicoRequestDTO> examenesFisicos) { this.examenesFisicos = examenesFisicos; }

    public List<OrdenMedicaRequestDTO> getOrdenesMedicas() { return ordenesMedicas; }
    public void setOrdenesMedicas(List<OrdenMedicaRequestDTO> ordenesMedicas) { this.ordenesMedicas = ordenesMedicas; }

    public List<MedicacionRequestDTO> getMedicacion() { return medicacion; }
    public void setMedicacion(List<MedicacionRequestDTO> medicacion) { this.medicacion = medicacion; }

    public List<SintomaRequestDTO> getSintomas() { return sintomas; }
    public void setSintomas(List<SintomaRequestDTO> sintomas) { this.sintomas = sintomas; }

    public List<TratamientoRequestDTO> getTratamientos() { return tratamientos; }
    public void setTratamientos(List<TratamientoRequestDTO> tratamientos) { this.tratamientos = tratamientos; }
}