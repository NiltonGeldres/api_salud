package com.api_salud.atencionmedica.request;

import java.time.OffsetDateTime;

/**
 * DTO para el detalle de Síntomas (igm_atenciones_medicas.atenciones_medicas_sintomas).
 */
public class SintomaRequestDTO {

    // id_sintoma integer (OBLIGATORIO por clave compuesta)
    private Integer idSintoma;
    // id_tipo_sintoma integer
    private Integer idTipoSintoma;
    // descripcion character varying(5000)
    private String descripcion;
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public Integer getIdSintoma() { return idSintoma; }
    public void setIdSintoma(Integer idSintoma) { this.idSintoma = idSintoma; }

    public Integer getIdTipoSintoma() { return idTipoSintoma; }
    public void setIdTipoSintoma(Integer idTipoSintoma) { this.idTipoSintoma = idTipoSintoma; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }
}