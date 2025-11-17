package com.api_salud.atencionmedica.entity;
import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_diagnosticos
 */
public class AtencionMedicaDiagnostico {

    // PK: id_atencion_diagnostico bigint
    private Long idAtencionDiagnostico;

    // FK: id_atencion bigint
    private Long idAtencion;

    // Campos
    // id_diagnostico integer
    private Integer idDiagnostico;
    // id_subclasificacion integer
    private Integer idSubclasificacion;
    // id_lab1 integer
    private Integer idLab1;
    // id_diagnostico_orden integer
    private Integer idDiagnosticoOrden;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaDiagnostico() {
    }

    // Getters y Setters
    // ...
    public Long getIdAtencionDiagnostico() {
        return idAtencionDiagnostico;
    }

    public void setIdAtencionDiagnostico(Long idAtencionDiagnostico) {
        this.idAtencionDiagnostico = idAtencionDiagnostico;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Integer getIdSubclasificacion() {
        return idSubclasificacion;
    }

    public void setIdSubclasificacion(Integer idSubclasificacion) {
        this.idSubclasificacion = idSubclasificacion;
    }

    public Integer getIdLab1() {
        return idLab1;
    }

    public void setIdLab1(Integer idLab1) {
        this.idLab1 = idLab1;
    }

    public Integer getIdDiagnosticoOrden() {
        return idDiagnosticoOrden;
    }

    public void setIdDiagnosticoOrden(Integer idDiagnosticoOrden) {
        this.idDiagnosticoOrden = idDiagnosticoOrden;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public OffsetDateTime getTsRegistro() {
        return tsRegistro;
    }

    public void setTsRegistro(OffsetDateTime tsRegistro) {
        this.tsRegistro = tsRegistro;
    }
}