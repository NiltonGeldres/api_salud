package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_sintomas
 */
public class AtencionMedicaSintoma {

    // PK: id_atencion_sintoma bigint
    private Long idAtencionSintoma;

    // FK: id_atencion bigint
    private Long idAtencion;

    // Campos
    // id_sintoma integer
    private Integer idSintoma;
    // id_tipo_sintoma integer
    private Integer idTipoSintoma;
    // descripcion character varying(5000)
    private String descripcion;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaSintoma() {
    }

    // Getters y Setters
    // ...
    public Long getIdAtencionSintoma() {
        return idAtencionSintoma;
    }

    public void setIdAtencionSintoma(Long idAtencionSintoma) {
        this.idAtencionSintoma = idAtencionSintoma;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(Integer idSintoma) {
        this.idSintoma = idSintoma;
    }

    public Integer getIdTipoSintoma() {
        return idTipoSintoma;
    }

    public void setIdTipoSintoma(Integer idTipoSintoma) {
        this.idTipoSintoma = idTipoSintoma;
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