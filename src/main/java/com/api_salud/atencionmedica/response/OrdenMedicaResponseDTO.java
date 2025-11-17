package com.api_salud.atencionmedica.response;

import java.time.OffsetDateTime;

/**
 * DTO para el detalle de Órdenes Médicas (igm_atenciones_medicas.atenciones_medicas_ordenes_medicas).
 */
public class OrdenMedicaResponseDTO {

    // id_atencion integer (Clave Foránea)
    private Integer idAtencion;
    // id_orden_medica integer (Clave Primaria, generada)
    private Integer idOrdenMedica;
    // id_punto_carga integer
    private Integer idPuntoCarga;
    // id_estado integer
    private Integer idEstado;
    // id_producto integer
    private Integer idProducto;
    // cantidad integer
    private Integer cantidad;
    // precio double precision
    private Double precio;
    // total double precision
    private Double total;
    // id_diagnostico integer
    private Integer idDiagnostico;
    // observacion character varying(5000)
    private String observacion;
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public Integer getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Integer idAtencion) { this.idAtencion = idAtencion; }

    public Integer getIdOrdenMedica() { return idOrdenMedica; }
    public void setIdOrdenMedica(Integer idOrdenMedica) { this.idOrdenMedica = idOrdenMedica; }

    public Integer getIdPuntoCarga() { return idPuntoCarga; }
    public void setIdPuntoCarga(Integer idPuntoCarga) { this.idPuntoCarga = idPuntoCarga; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }
}