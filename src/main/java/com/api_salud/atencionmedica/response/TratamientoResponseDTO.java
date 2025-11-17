package com.api_salud.atencionmedica.response;

import java.time.OffsetDateTime;

/**
 * DTO para el detalle de Tratamientos (igm_atenciones_medicas.atenciones_medicas_tratamientos).
 */
public class TratamientoResponseDTO {

    // id_atencion integer (Clave Foránea)
    private Integer idAtencion;
    // id_tratamiento integer (Clave Compuesta)
    private Integer idTratamiento;
    // id_tipo_tratamiento integer
    private Integer idTipoTratamiento;
    // descripcion character varying(5000)
    private String descripcion;
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public Integer getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Integer idAtencion) { this.idAtencion = idAtencion; }

    public Integer getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(Integer idTratamiento) { this.idTratamiento = idTratamiento; }

    public Integer getIdTipoTratamiento() { return idTipoTratamiento; }
    public void setIdTipoTratamiento(Integer idTipoTratamiento) { this.idTipoTratamiento = idTipoTratamiento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }
}