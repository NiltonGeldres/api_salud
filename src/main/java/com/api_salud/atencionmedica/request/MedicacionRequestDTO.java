package com.api_salud.atencionmedica.request;

import java.time.OffsetDateTime;

/**
 * DTO para el detalle de Medicación (igm_atenciones_medicas.atenciones_medicas_medicacion).
 */
public class MedicacionRequestDTO {

    // id_almacen integer
    private Integer idAlmacen;
    // id_producto integer (OBLIGATORIO)
    private Integer idProducto;
    // cantidad_dosis integer
    private Integer cantidadDosis;
    // id_um_dosis integer
    private Integer idUmDosis;
    // id_frecuencia_dosis integer
    private Integer idFrecuenciaDosis;
    // cantidad_periodo integer
    private Integer cantidadPeriodo;
    // id_via_administracion integer
    private Integer idViaAdministracion;
    // cantidad_total integer
    private Integer cantidadTotal;
    // precio double precision
    private Double precio;
    // monto_total double precision
    private Double montoTotal;
    // indicaciones character varying(5000)
    private String indicaciones;
    // id_diagnostico integer
    private Integer idDiagnostico;
    // id_usuario integer
    private Integer idUsuario;
    // ts_registro timestamp with time zone
    private OffsetDateTime tsRegistro;

    // --- Constructor, Getters y Setters ---

    public Integer getIdAlmacen() { return idAlmacen; }
    public void setIdAlmacen(Integer idAlmacen) { this.idAlmacen = idAlmacen; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Integer getCantidadDosis() { return cantidadDosis; }
    public void setCantidadDosis(Integer cantidadDosis) { this.cantidadDosis = cantidadDosis; }

    public Integer getIdUmDosis() { return idUmDosis; }
    public void setIdUmDosis(Integer idUmDosis) { this.idUmDosis = idUmDosis; }

    public Integer getIdFrecuenciaDosis() { return idFrecuenciaDosis; }
    public void setIdFrecuenciaDosis(Integer idFrecuenciaDosis) { this.idFrecuenciaDosis = idFrecuenciaDosis; }

    public Integer getCantidadPeriodo() { return cantidadPeriodo; }
    public void setCantidadPeriodo(Integer cantidadPeriodo) { this.cantidadPeriodo = cantidadPeriodo; }

    public Integer getIdViaAdministracion() { return idViaAdministracion; }
    public void setIdViaAdministracion(Integer idViaAdministracion) { this.idViaAdministracion = idViaAdministracion; }

    public Integer getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(Integer cantidadTotal) { this.cantidadTotal = cantidadTotal; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Double montoTotal) { this.montoTotal = montoTotal; }

    public String getIndicaciones() { return indicaciones; }
    public void setIndicaciones(String indicaciones) { this.indicaciones = indicaciones; }

    public Integer getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Integer idDiagnostico) { this.idDiagnostico = idDiagnostico; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public OffsetDateTime getTsRegistro() { return tsRegistro; }
    public void setTsRegistro(OffsetDateTime tsRegistro) { this.tsRegistro = tsRegistro; }
}