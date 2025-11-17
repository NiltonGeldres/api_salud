package com.api_salud.atencionmedica.response;

import java.time.OffsetDateTime;

/**
 * DTO para el detalle de Antecedentes (igm_atenciones_medicas.atenciones_medicas_antecedentes).
 */
public class AntecedenteResponseDTO {

    // id_atencion integer (Clave Foránea)
    private Integer idAtencion;
    // id_antecedente integer (Clave Compuesta)
    private Integer idAntecedente;
    // id_tipo_antecedente integer
    private Integer idTipoAntecedente;
    // descripcion character varying(5000)
    private String descripcion;
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public Integer getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Integer idAtencion) { this.idAtencion = idAtencion; }

    public Integer getIdAntecedente() { return idAntecedente; }
    public void setIdAntecedente(Integer idAntecedente) { this.idAntecedente = idAntecedente; }

    public Integer getIdTipoAntecedente() { return idTipoAntecedente; }
    public void setIdTipoAntecedente(Integer idTipoAntecedente) { this.idTipoAntecedente = idTipoAntecedente; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }
}