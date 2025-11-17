package com.api_salud.atencionmedica.request;

import java.time.OffsetDateTime;

/**
 * DTO para el detalle de Discapacidad (igm_atenciones_medicas.atenciones_medicas_discapacidad).
 */
public class DiscapacidadRequestDTO {

    // id_discapacidad integer (OBLIGATORIO por clave compuesta)
    private Integer idDiscapacidad;
    // id_gravedad_discapacidad integer
    private Integer idGravedadDiscapacidad;
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public Integer getIdDiscapacidad() { return idDiscapacidad; }
    public void setIdDiscapacidad(Integer idDiscapacidad) { this.idDiscapacidad = idDiscapacidad; }

    public Integer getIdGravedadDiscapacidad() { return idGravedadDiscapacidad; }
    public void setIdGravedadDiscapacidad(Integer idGravedadDiscapacidad) { this.idGravedadDiscapacidad = idGravedadDiscapacidad; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }
}