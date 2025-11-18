package com.api_salud.atencionmedica.domain;




import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Clase contenedora de todos los DTOs/Modelos de la Atención Médica y sus detalles.
 * Usamos Lombok (@Data) para simplificar getters, setters, toString, equals y hashCode.
 */
@Data
public class AtencionMedicaModel {

    /**
     * 1. Entidad Maestra: igm_atenciones_medicas.atenciones_medicas
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AtencionMedica {
        private Long idAtencion;
        private Integer idPaciente;
        private Integer idCuentaAtencion;
        private Integer idServicio;
        private Integer idMedicoIngreso;
        private Integer idEstadoAtencion;
        private OffsetDateTime tsIngreso; // Usar OffsetDateTime para timestamp with time zone
        private OffsetDateTime tsActualizacion;
        private Integer idUsuarioRegistro;
        private String origenRegistroUsuario;
        
        // Propiedades para los detalles (no mapeadas directamente a la tabla principal)
        private List<Antecedente> antecedentes;
        private List<Diagnostico> diagnosticos;
        private List<Discapacidad> discapacidades;
        private List<DiscapacidadOtros> discapacidadOtros;
        private List<ExamenFisico> examenesFisicos;
        private List<OrdenMedica> ordenesMedicas;
        private List<Medicacion> medicacion;
        
        
    }

    /**
     * 2. Detalle: igm_atenciones_medicas.atenciones_medicas_antecedentes
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Antecedente {
        private Long idAtencionAntecedente;
        private Long idAtencion; // FK
        private Integer idAntecedente;
        private Integer idTipoAntecedente;
        private String descripcion;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * 3. Detalle: igm_atenciones_medicas.atenciones_medicas_diagnosticos
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Diagnostico {
        private Long idAtencionDiagnostico;
        private Long idAtencion; // FK
        private Integer idDiagnostico;
        private Integer idSubclasificacion;
        private Integer idLab1;
        private Integer idDiagnosticoOrden;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * 4. Detalle: igm_atenciones_medicas.atenciones_medicas_discapacidad
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Discapacidad {
        private Long idAtencionDiscapacidad;
        private Long idAtencion; // FK
        private Integer idDiscapacidad;
        private Integer idGravedadDiscapacidad;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * 5. Detalle: igm_atenciones_medicas.atenciones_medicas_discapacidad_otros
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiscapacidadOtros {
        private Long idAtencionDiscapacidadOtros;
        private Long idAtencion; // FK
        private Integer idTipoActividad;
        private Integer idTiempoDiscapacidadAa; // aa = años
        private Integer idTiempoDiscapacidadMm; // mm = meses
        private Integer idTiempoDiscapacidadDd; // dd = días
        private Integer idTiempoSintrabajarAa;
        private Integer idTiempoSintrabajarMm;
        private Integer idTiempoSintrabajarDd;
        private Integer idAlta;
        private Integer idProductividad;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }

    /**
     * 6. Detalle: igm_atenciones_medicas.atenciones_medicas_examen_fisico
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExamenFisico {
        private Long idAtencionExamenFisico;
        private Long idAtencion; // FK
        private Integer idExamenFisico;
        private Integer idTipoExamenFisico;
        private String descripcion;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
    
    /**
     * 7. Detalle: igm_atenciones_medicas.atenciones_medicas_ordenes_medicas
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrdenMedica {
        private Long idAtencionOrdenMedica;
        private Long idAtencion; // FK
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
     * 8. Detalle: igm_atenciones_medicas.atenciones_medicas_medicacion
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Medicacion {
        private Long idAtencionMedicacion;
        private Long idAtencion; // FK
        private Integer idProducto; // Suponiendo que la medicación está relacionada a un producto
        private Integer idAlmacen;
        private Integer cantidadDosis;
        private Integer idUmDosis; // Unidad de Medida de Dosis
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
     * 8. Detalle: igm_atenciones_medicas.atenciones_medicas_tratamientos
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sintoma {
        private Long idAtencionSintoma;
        private Long idAtencion; // FK
        private Integer idSintoma; // Suponiendo que la medicación está relacionada a un producto
        private Integer idTipoSintoma;
        private Integer descripcion;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
    

    /**
     * 8. Detalle: igm_atenciones_medicas.atenciones_medicas_tratamientos
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tratamiento {
        private Long idAtencionTratamiento;
        private Long idAtencion; // FK
        private Integer idTratamiento; // Suponiendo que la medicación está relacionada a un producto
        private Integer idTipoTratamiento;
        private Integer descripcion;
        private Integer idUsuario;
        private OffsetDateTime tsRegistro;
    }
    
    
    
    
}