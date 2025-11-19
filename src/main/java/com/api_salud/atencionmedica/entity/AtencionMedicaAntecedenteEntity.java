package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_antecedentes
 */
public class AtencionMedicaAntecedenteEntity {

    // PK: id_atencion_antecedente bigint
    private Long idAtencionAntecedente;

    // FK: id_atencion bigint (Clave de la atención médica)
    private Long idAtencion;

    // Campos obligatorios y opcionales
    // id_antecedente integer
    private Integer idAntecedente;
    // id_tipo_antecedente integer
    private Integer idTipoAntecedente;
    // descripcion character varying(5000)
    private String descripcion;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone (default now())
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaAntecedenteEntity() {
    }

    // Getters y Setters (se omiten por brevedad en este ejemplo,
    // pero deben ser incluidos en una implementación real)
    // ...
    public Long getIdAtencionAntecedente() {
        return idAtencionAntecedente;
    }

    public void setIdAtencionAntecedente(Long idAtencionAntecedente) {
        this.idAtencionAntecedente = idAtencionAntecedente;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(Integer idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public Integer getIdTipoAntecedente() {
        return idTipoAntecedente;
    }

    public void setIdTipoAntecedente(Integer idTipoAntecedente) {
        this.idTipoAntecedente = idTipoAntecedente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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