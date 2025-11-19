package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;

/**
 * Entidad que mapea la tabla igm_atenciones_medicas.atenciones_medicas_ordenes_medicas
 */
public class AtencionMedicaOrdenMedicaEntity {

    // PK: id_atencion_orden_medica bigint
    private Long idAtencionOrdenMedica;

    // FK: id_atencion bigint
    private Long idAtencion;

    // Campos obligatorios y opcionales
    // id_punto_carga integer
    private Integer idPuntoCarga;
    // id_estado integer
    private Integer idEstado;
    // id_producto integer
    private Integer idProducto;
    // cantidad integer
    private Integer cantidad;
    // precio double precision -> Double
    private Double precio;
    // total double precision -> Double
    private Double total;
    // id_diagnostico integer
    private Integer idDiagnostico;
    // observacion character varying(5000)
    private String observacion;

    // Trazabilidad
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public AtencionMedicaOrdenMedicaEntity() {
    }

    // Getters y Setters
    // ...
    public Long getIdAtencionOrdenMedica() {
        return idAtencionOrdenMedica;
    }

    public void setIdAtencionOrdenMedica(Long idAtencionOrdenMedica) {
        this.idAtencionOrdenMedica = idAtencionOrdenMedica;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdPuntoCarga() {
        return idPuntoCarga;
    }

    public void setIdPuntoCarga(Integer idPuntoCarga) {
        this.idPuntoCarga = idPuntoCarga;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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