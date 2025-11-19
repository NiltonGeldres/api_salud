package com.api_salud.atencionmedica.domain;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * MODELO DE DOMINIO: Representa la Atención Médica en la capa de negocio (Service).
 * El sufijo 'Model' se utiliza para evitar cualquier conflicto con la Entidad (Entity) 
 * y la de transporte (DTO).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtencionMedicaModel { 
    
    // 1. Campos de la Entidad Maestra
    private Long idAtencion;
    private Integer idPaciente;
    private Integer idCuentaAtencion;
    private Integer idServicio;
    private Integer idMedicoIngreso;
    private Integer idEstadoAtencion;
    private OffsetDateTime tsIngreso; 
    private OffsetDateTime tsActualizacion;
    private Integer idUsuarioRegistro;
    private String origenRegistroUsuario;
    
    // 2. Propiedades para los detalles 
    private List<Antecedente> antecedentes;
    private List<Diagnostico> diagnosticos;
    private List<Discapacidad> discapacidades;
    private List<DiscapacidadOtros> discapacidadOtros;
    private List<ExamenFisico> examenesFisicos;
    private List<OrdenMedica> ordenesMedicas;
    private List<Medicacion> medicacion;
    private List<Sintoma> sintomas;
    private List<Tratamiento> tratamientos;

    // ===================================================================================
    // CLASES ANIDADAS DE DETALLE (Modelos de Detalle)
    // ===================================================================================

    /**
     * Detalle: Modelo de Antecedente
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Antecedente {
        private Long idAtencionAntecedente;
        private Long idAtencion; 
        private Integer idAntecedente;
        private Integer idTipoAntecedente;
        private String descripcion;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * Detalle: Modelo de Diagnostico
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Diagnostico {
        private Long idAtencionDiagnostico;
        private Long idAtencion; 
        private Integer idDiagnostico;
        private Integer idSubclasificacion;
        private Integer idLab1;
        private Integer idDiagnosticoOrden;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * Detalle: Modelo de Discapacidad
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Discapacidad {
        private Long idAtencionDiscapacidad;
        private Long idAtencion; 
        private Integer idDiscapacidad;
        private Integer idGravedadDiscapacidad;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * Detalle: Modelo de DiscapacidadOtros
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiscapacidadOtros {
        private Long idAtencionDiscapacidadOtros;
        private Long idAtencion; 
        private Integer idTipoActividad;
        private Integer idTiempoDiscapacidadAnios; 
        private Integer idTiempoDiscapacidadMeses; 
        private Integer idTiempoDiscapacidadDias; 
        private Integer idTiempoSintrabajarAnios;
        private Integer idTiempoSintrabajarMeses;
        private Integer idTiempoSintrabajarDias;
        private Integer idAlta;
        private Integer idProductividad;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * Detalle: Modelo de ExamenFisico
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExamenFisico {
        private Long idAtencionExamenFisico;
        private Long idAtencion; 
        private Integer idExamenFisico;
        private Integer idTipoExamenFisico;
        private String descripcion;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
    
    /**
     * Detalle: Modelo de OrdenMedica
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrdenMedica {
        private Long idAtencionOrdenMedica;
        private Long idAtencion; 
        private Integer idPuntoCarga;
        private Integer idEstado;
        private Integer idProducto;
        private Integer cantidad;
        private Double precio;
        private Double total;
        private Integer idDiagnostico;
        private String observacion;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
    
    /**
     * Detalle: Modelo de Medicacion
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Medicacion {
        private Long idAtencionMedicacion;
        private Long idAtencion; 
        private Integer idProducto; 
        private Integer idAlmacen;
        private Integer cantidadDosis;
        private Integer idUmDosis; 
        private Integer idFrecuenciaDosis;
        private Integer cantidadPeriodo;
        private Integer idViaAdministracion;
        private Integer cantidadTotal;
        private Double precio;
        private Double montoTotal;
        private String indicaciones;
        private Integer idDiagnostico;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
    
    /**
     * Detalle: Modelo de Sintoma
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sintoma {
        private Long idAtencionSintoma;
        private Long idAtencion; 
        private Integer idSintoma; 
        private Integer idTipoSintoma;
        private String descripcion; 
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
    

    /**
     * Detalle: Modelo de Tratamiento
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tratamiento {
        private Long idAtencionTratamiento;
        private Long idAtencion; 
        private Integer idTratamiento; 
        private Integer idTipoTratamiento;
        private String descripcion; 
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
}