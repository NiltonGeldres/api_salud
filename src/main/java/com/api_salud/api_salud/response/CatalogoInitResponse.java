package com.api_salud.api_salud.response;

import java.util.List;

public class CatalogoInitResponse {

    private List<TriajeDTO> catalogoTriajes;
    private List<TipoDiagnosticoDTO> catalogoTipoDiagnostico;
    private List<ViaAdministracionDTO> catalogoViasAdministracion;
    private List<PaqueteMedicacionDTO> catalogoPaquetesMedicacion;
    private List<PaqueteExamenDTO> catalogoPaquetesExamenes;

    public CatalogoInitResponse() {
    }

    public List<TriajeDTO> getCatalogoTriajes() {
        return catalogoTriajes;
    }

    public void setCatalogoTriajes(List<TriajeDTO> catalogoTriajes) {
        this.catalogoTriajes = catalogoTriajes;
    }

    public List<TipoDiagnosticoDTO> getCatalogoTipoDiagnostico() {
        return catalogoTipoDiagnostico;
    }

    public void setCatalogoTipoDiagnostico(List<TipoDiagnosticoDTO> catalogoTipoDiagnostico) {
        this.catalogoTipoDiagnostico = catalogoTipoDiagnostico;
    }

    public List<ViaAdministracionDTO> getCatalogoViasAdministracion() {
        return catalogoViasAdministracion;
    }

    public void setCatalogoViasAdministracion(List<ViaAdministracionDTO> catalogoViasAdministracion) {
        this.catalogoViasAdministracion = catalogoViasAdministracion;
    }

    public List<PaqueteMedicacionDTO> getCatalogoPaquetesMedicacion() {
        return catalogoPaquetesMedicacion;
    }

    public void setCatalogoPaquetesMedicacion(List<PaqueteMedicacionDTO> catalogoPaquetesMedicacion) {
        this.catalogoPaquetesMedicacion = catalogoPaquetesMedicacion;
    }

    public List<PaqueteExamenDTO> getCatalogoPaquetesExamenes() {
        return catalogoPaquetesExamenes;
    }

    public void setCatalogoPaquetesExamenes(List<PaqueteExamenDTO> catalogoPaquetesExamenes) {
        this.catalogoPaquetesExamenes = catalogoPaquetesExamenes;
    }

    // --- INNER DTOS ---

    public static class TriajeDTO {
        private Integer idTriaje;
        private String nombreTriaje;
        private String um;
        private Integer prioridad;

        public Integer getIdTriaje() { return idTriaje; }
        public void setIdTriaje(Integer idTriaje) { this.idTriaje = idTriaje; }
        public String getNombreTriaje() { return nombreTriaje; }
        public void setNombreTriaje(String nombreTriaje) { this.nombreTriaje = nombreTriaje; }
        public String getUm() { return um; }
        public void setUm(String um) { this.um = um; }
        public Integer getPrioridad() { return prioridad; }
        public void setPrioridad(Integer prioridad) { this.prioridad = prioridad; }
    }

    public static class TipoDiagnosticoDTO {
        private Integer idDiagnosticoSubclasificacion;
        private String codigo;
        private String descripcion;
        private Integer idDiagnosticoClasificacion;
        private Integer idTipoServicio;

        public Integer getIdDiagnosticoSubclasificacion() { return idDiagnosticoSubclasificacion; }
        public void setIdDiagnosticoSubclasificacion(Integer idDiagnosticoSubclasificacion) { this.idDiagnosticoSubclasificacion = idDiagnosticoSubclasificacion; }
        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public Integer getIdDiagnosticoClasificacion() { return idDiagnosticoClasificacion; }
        public void setIdDiagnosticoClasificacion(Integer idDiagnosticoClasificacion) { this.idDiagnosticoClasificacion = idDiagnosticoClasificacion; }
        public Integer getIdTipoServicio() { return idTipoServicio; }
        public void setIdTipoServicio(Integer idTipoServicio) { this.idTipoServicio = idTipoServicio; }
    }

    public static class ViaAdministracionDTO {
        private Integer idViaAdministracion;
        private String nombreViaAdministracion;
        private String grupoClasificacion;

        public Integer getIdViaAdministracion() { return idViaAdministracion; }
        public void setIdViaAdministracion(Integer idViaAdministracion) { this.idViaAdministracion = idViaAdministracion; }
        public String getNombreViaAdministracion() { return nombreViaAdministracion; }
        public void setNombreViaAdministracion(String nombreViaAdministracion) { this.nombreViaAdministracion = nombreViaAdministracion; }
        public String getGrupoClasificacion() { return grupoClasificacion; }
        public void setGrupoClasificacion(String grupoClasificacion) { this.grupoClasificacion = grupoClasificacion; }
    }

    public static class PaqueteMedicacionDTO {
        private String id;
        private Integer idPaqueteMedicacion;
        private String nombrePaquete;
        private String descripcion;
        private List<MedicamentoAsociadoDTO> medicamentosAsociados;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public Integer getIdPaqueteMedicacion() { return idPaqueteMedicacion; }
        public void setIdPaqueteMedicacion(Integer idPaqueteMedicacion) { this.idPaqueteMedicacion = idPaqueteMedicacion; }
        public String getNombrePaquete() { return nombrePaquete; }
        public void setNombrePaquete(String nombrePaquete) { this.nombrePaquete = nombrePaquete; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public List<MedicamentoAsociadoDTO> getMedicamentosAsociados() { return medicamentosAsociados; }
        public void setMedicamentosAsociados(List<MedicamentoAsociadoDTO> medicamentosAsociados) { this.medicamentosAsociados = medicamentosAsociados; }
    }

    public static class MedicamentoAsociadoDTO {
        private String id;
        private Integer idPaqueteMedicacionDetalle;
        private Integer idProducto;
        private String descripcion;
        private String dosis;
        private String frecuencia;
        private String periodo;
        private Integer cantidad;
        private String via;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public Integer getIdPaqueteMedicacionDetalle() { return idPaqueteMedicacionDetalle; }
        public void setIdPaqueteMedicacionDetalle(Integer idPaqueteMedicacionDetalle) { this.idPaqueteMedicacionDetalle = idPaqueteMedicacionDetalle; }
        public Integer getIdProducto() { return idProducto; }
        public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public String getDosis() { return dosis; }
        public void setDosis(String dosis) { this.dosis = dosis; }
        public String getFrecuencia() { return frecuencia; }
        public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
        public String getPeriodo() { return periodo; }
        public void setPeriodo(String periodo) { this.periodo = periodo; }
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
        public String getVia() { return via; }
        public void setVia(String via) { this.via = via; }
    }

    public static class PaqueteExamenDTO {
        private String id;
        private Integer idPaqueteExamen;
        private String nombrePaquete;
        private String descripcion;
        private List<ExamenAsociadoDTO> examenesAsociados;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public Integer getIdPaqueteExamen() { return idPaqueteExamen; }
        public void setIdPaqueteExamen(Integer idPaqueteExamen) { this.idPaqueteExamen = idPaqueteExamen; }
        public String getNombrePaquete() { return nombrePaquete; }
        public void setNombrePaquete(String nombrePaquete) { this.nombrePaquete = nombrePaquete; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public List<ExamenAsociadoDTO> getExamenesAsociados() { return examenesAsociados; }
        public void setExamenesAsociados(List<ExamenAsociadoDTO> examenesAsociados) { this.examenesAsociados = examenesAsociados; }
    }

    public static class ExamenAsociadoDTO {
        private String id;
        private Integer idPaqueteExamenDetalle;
        private String label;
        private String codigoProcedimiento;
        private String codMinsa;
        private Integer orden;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public Integer getIdPaqueteExamenDetalle() { return idPaqueteExamenDetalle; }
        public void setIdPaqueteExamenDetalle(Integer idPaqueteExamenDetalle) { this.idPaqueteExamenDetalle = idPaqueteExamenDetalle; }
        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }
        public String getCodigoProcedimiento() { return codigoProcedimiento; }
        public void setCodigoProcedimiento(String codigoProcedimiento) { this.codigoProcedimiento = codigoProcedimiento; }
        public String getCodMinsa() { return codMinsa; }
        public void setCodMinsa(String codMinsa) { this.codMinsa = codMinsa; }
        public Integer getOrden() { return orden; }
        public void setOrden(Integer orden) { this.orden = orden; }
    }
}