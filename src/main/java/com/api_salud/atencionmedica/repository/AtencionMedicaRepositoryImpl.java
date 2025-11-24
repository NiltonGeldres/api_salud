// Archivo: com.api_salud.atencionmedica.repository.AtencionMedicaRepositoryImpl.java
package com.api_salud.atencionmedica.repository;

import com.api_salud.atencionmedica.entity.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

@Repository
@Slf4j
public class AtencionMedicaRepositoryImpl implements AtencionMedicaRepository {

    private static final String SCHEMA_NAME = "igm_atenciones_medicas";

    // SimpleJdbcCall instances
    private final SimpleJdbcCall callInsertarCabecera;
    private final SimpleJdbcCall callInsertarAntecedente;
    private final SimpleJdbcCall callInsertarDiagnostico;
    private final SimpleJdbcCall callInsertarDiscapacidad;
    private final SimpleJdbcCall callInsertarDiscapacidadOtros;
    private final SimpleJdbcCall callInsertarExamenFisico;
    private final SimpleJdbcCall callInsertarMedicacion;
    private final SimpleJdbcCall callInsertarOrdenMedica;
    private final SimpleJdbcCall callInsertarSintoma;
    private final SimpleJdbcCall callInsertarTratamiento;

    public AtencionMedicaRepositoryImpl(DataSource dataSource) {
        
        // Inicialización de la CABECERA
        this.callInsertarCabecera = new SimpleJdbcCall(dataSource)
                .withSchemaName(SCHEMA_NAME)
                .withFunctionName("fn_atenciones_medicas_insertar");
        
        // Inicialización de los DETALLES
        this.callInsertarAntecedente = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_antecedentes_insertar");
        this.callInsertarDiagnostico = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_diagnosticos_insertar");
        this.callInsertarDiscapacidad = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_discapacidades_insertar");
        this.callInsertarDiscapacidadOtros = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_discapacidad_otros_insertar");
        this.callInsertarExamenFisico = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_examen_fisico_insertar");
        this.callInsertarMedicacion = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_medicacion_insertar");
        this.callInsertarOrdenMedica = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_ordenes_medicas_insertar");
        this.callInsertarSintoma = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_sintomas_insertar");
        this.callInsertarTratamiento = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME).withFunctionName("fn_tratamientos_insertar");
    }

    /**
     * Lógica de inserción principal, garantizando la transacción atómica.
     */
    @Override
    @Transactional // ¡CRUCIAL! Todas las llamadas a DB en este método están bajo una única transacción.
    public Long insertarAtencionMedicaCompleta(AtencionMedicaEntity entity) {

        // 1. Inserción de la CABECERA y obtención del ID
        Long idAtencion = insertarCabecera(entity);
        log.info("Atención Médica insertada con ID de cabecera: {}", idAtencion);

        if (idAtencion == null) {
             throw new IllegalStateException("El ID de Atención Médica (id_atencion) no fue retornado por la función de cabecera.");
        }
        
        // 2. Inserción de los 9 DETALLES (usando el ID recién creado)
        insertarAntecedentes(entity.getAntecedentes(), idAtencion);
        insertarDiagnosticos(entity.getDiagnosticos(), idAtencion);
        insertarDiscapacidades(entity.getDiscapacidades(), idAtencion);
        insertarDiscapacidadOtros(entity.getDiscapacidadOtros(), idAtencion);
        insertarExamenesFisicos(entity.getExamenesFisicos(), idAtencion);
        insertarMedicacion(entity.getMedicacion(), idAtencion);
        insertarOrdenesMedicas(entity.getOrdenesMedicas(), idAtencion);
        insertarSintomas(entity.getSintomas(), idAtencion);
        insertarTratamientos(entity.getTratamientos(), idAtencion);

        return idAtencion;
    }

    /**
     * Llama a fn_atenciones_medicas_insertar y extrae el BIGINT de retorno.
     */
    private Long insertarCabecera(AtencionMedicaEntity entity) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id_paciente", entity.getIdPaciente())
                .addValue("p_id_cuenta_atencion", entity.getIdCuentaAtencion())
                .addValue("p_id_servicio", entity.getIdServicio())
                .addValue("p_id_medico_ingreso", entity.getIdMedicoIngreso())
                .addValue("p_id_estado_atencion", entity.getIdEstadoAtencion())
                .addValue("p_ts_ingreso", entity.getTsIngreso())
                .addValue("p_id_usuario_registro", entity.getIdUsuarioRegistro())
                .addValue("p_origen_registro_usuario", entity.getOrigenRegistroUsuario());
                
        Map<String, Object> out = callInsertarCabecera.execute(in);

        // PostgreSQL devuelve el valor de retorno en la clave '__return_value'
        return out.containsKey("__return_value") ? ((Number) out.get("__return_value")).longValue() : null;
    }

    // =========================================================================
    // MÉTODOS DE INSERCIÓN DE DETALLES (Se requiere un método por cada tipo de detalle)
    // =========================================================================

    /**
     * Lógica específica para insertar Antecedentes (fn_antecedentes_insertar).
     */
    private void insertarAntecedentes(List<AtencionMedicaAntecedenteEntity> antecedentes, Long idAtencion) {
        if (antecedentes == null || antecedentes.isEmpty()) return;

        for (AtencionMedicaAntecedenteEntity antecedente : antecedentes) {
            
            MapSqlParameterSource in = new MapSqlParameterSource();
            
            // 1. Clave Foránea
            in.addValue("p_id_atencion", idAtencion); 
            
            // 2. Parámetros de negocio
            in.addValue("p_id_antecedente", antecedente.getIdAntecedente());
            in.addValue("p_id_tipo_antecedente", antecedente.getIdTipoAntecedente());
            in.addValue("p_descripcion", antecedente.getDescripcion());
            
            // 3. Trazabilidad (se asume p_id_usuario en la función)
            in.addValue("p_id_usuario", antecedente.getIdUsuario()); 
            
            callInsertarAntecedente.execute(in);
        }
        log.info("{} Antecedentes insertados.", antecedentes.size());
    }
    
    /**
     * Lógica específica para insertar Órdenes Médicas (fn_ordenes_medicas_insertar).
     */
    private void insertarOrdenesMedicas(List<AtencionMedicaOrdenMedicaEntity> ordenesMedicas, Long idAtencion) {
        if (ordenesMedicas == null || ordenesMedicas.isEmpty()) return;

        for (AtencionMedicaOrdenMedicaEntity orden : ordenesMedicas) {
            
            MapSqlParameterSource in = new MapSqlParameterSource();
            
            // 1. Clave Foránea
            in.addValue("p_id_atencion", idAtencion); 
            
            // 2. Parámetros de negocio
            in.addValue("p_id_producto", orden.getIdProducto());
            in.addValue("p_cantidad", orden.getCantidad());
            in.addValue("p_precio", orden.getPrecio());
            in.addValue("p_total", orden.getTotal());
            in.addValue("p_id_diagnostico", orden.getIdDiagnostico());
            in.addValue("p_observacion", orden.getObservacion());
            
            // 3. Trazabilidad
            in.addValue("p_id_usuario", orden.getIdUsuario()); 
            
            callInsertarOrdenMedica.execute(in);
        }
        log.info("{} Órdenes Médicas insertadas.", ordenesMedicas.size());
    }

    // =========================================================================
    // Los siguientes 7 métodos DEBEN ser implementados siguiendo el patrón anterior:
    // =========================================================================
    
    private void insertarDiagnosticos(List<AtencionMedicaDiagnosticoEntity> diagnosticos, Long idAtencion) {
        // Lógica de mapeo para fn_diagnosticos_insertar
        if (diagnosticos == null || diagnosticos.isEmpty()) return;
        // ... iterar y ejecutar SimpleJdbcCall ...
        log.warn("Método insertarDiagnosticos no implementado completamente.");
    }
    
    private void insertarDiscapacidades(List<AtencionMedicaDiscapacidadEntity> discapacidades, Long idAtencion) {
        // Lógica de mapeo para fn_discapacidades_insertar
        if (discapacidades == null || discapacidades.isEmpty()) return;
        // ... iterar y ejecutar SimpleJdbcCall ...
        log.warn("Método insertarDiscapacidades no implementado completamente.");
    }

    private void insertarDiscapacidadOtros(List<AtencionMedicaDiscapacidadOtrosEntity> discapacidadOtros, Long idAtencion) {
        // Lógica de mapeo para fn_discapacidad_otros_insertar
        if (discapacidadOtros == null || discapacidadOtros.isEmpty()) return;
        // ... iterar y ejecutar SimpleJdbcCall ...
        log.warn("Método insertarDiscapacidadOtros no implementado completamente.");
    }

    private void insertarExamenesFisicos(List<AtencionMedicaExamenFisicoEntity> examenesFisicos, Long idAtencion) {
        // Lógica de mapeo para fn_examen_fisico_insertar
        if (examenesFisicos == null || examenesFisicos.isEmpty()) return;
        // ... iterar y ejecutar SimpleJdbcCall ...
        log.warn("Método insertarExamenesFisicos no implementado completamente.");
    }

    private void insertarMedicacion(List<AtencionMedicaMedicacionEntity> medicacion, Long idAtencion) {
        // Lógica de mapeo para fn_medicacion_insertar
        if (medicacion == null || medicacion.isEmpty()) return;
        // ... iterar y ejecutar SimpleJdbcCall ...
        log.warn("Método insertarMedicacion no implementado completamente.");
    }

    private void insertarSintomas(List<AtencionMedicaSintomaEntity> sintomas, Long idAtencion) {
        // Lógica de mapeo para fn_sintomas_insertar
        if (sintomas == null || sintomas.isEmpty()) return;
        // ... iterar y ejecutar SimpleJdbcCall ...
        log.warn("Método insertarSintomas no implementado completamente.");
    }
    
    private void insertarTratamientos(List<AtencionMedicaTratamientoEntity> tratamientos, Long idAtencion) {
        // Lógica de mapeo para fn_tratamientos_insertar
        if (tratamientos == null || tratamientos.isEmpty()) return;
        // ... iterar y ejecutar SimpleJdbcCall ...
        log.warn("Método insertarTratamientos no implementado completamente.");
    }
}