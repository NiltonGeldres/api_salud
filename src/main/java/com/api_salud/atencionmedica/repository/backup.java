package com.api_salud.atencionmedica.repository;


import com.api_salud.atencionmedica.domain.AtencionMedicaModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

/**
 * Implementación del repositorio de Atenciones Médicas usando SimpleJdbcCall
 * para interactuar con los procedimientos almacenados en PostgreSQL (esquema igm_atenciones_medicas).
 * * NOTA: El prefijo de los parámetros 'p_' de los SPs es manejado automáticamente
 * al mapear las propiedades del DTO con SimpleJdbcCall.
 */
@Repository
public class AtencionMedicaRepositoryImpl implements AtencionMedicaRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String SCHEMA = "igm_atenciones_medicas";

    // SimpleJdbcCall para la entidad maestra
    private SimpleJdbcCall insertarAtencionMedicaCall;
    private SimpleJdbcCall actualizarAtencionMedicaCall;
    private SimpleJdbcCall obtenerAtencionMedicaPorIdCall;
    private SimpleJdbcCall listarAtencionesMedicasPorPacienteCall;
    
    // SimpleJdbcCall para entidades detalle
    private Map<String, SimpleJdbcCall> detalleInsertCalls = new HashMap<>();
    private Map<String, SimpleJdbcCall> detalleUpdateCalls = new HashMap<>();
    private Map<String, SimpleJdbcCall> detalleListCalls = new HashMap<>();
    private Map<String, SimpleJdbcCall> detalleDeleteCalls = new HashMap<>(); // Asumimos SPs de eliminación

    @Autowired
    public AtencionMedicaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Inicialización de SimpleJdbcCall para todos los SPs.
     */
    @PostConstruct
    public void init() {
        // --- 1. Entidad Maestra: Atencion Medica ---
        insertarAtencionMedicaCall = new SimpleJdbcCall(jdbcTemplate)
            .withSchemaName(SCHEMA)
            .withFunctionName("fn_atenciones_medicas_insertar");

        actualizarAtencionMedicaCall = new SimpleJdbcCall(jdbcTemplate)
            .withSchemaName(SCHEMA)
            .withFunctionName("fn_atenciones_medicas_actualizar");
            
        // Configuración para mapear la salida a la clase AtencionMedica
        obtenerAtencionMedicaPorIdCall = new SimpleJdbcCall(jdbcTemplate)
            .withSchemaName(SCHEMA)
            .withFunctionName("fn_atenciones_medicas_obtener_por_id")
            .returningResultSet("ATENCIONES_MEDICAS_RESULT", (RowMapper) (rs, rowNum) -> {
                 // Usamos BeanPropertyRowMapper, asumiendo que los nombres de columna (snake_case)
                 // son mapeados a los campos del DTO (camelCase) correctamente (comportamiento por defecto en Spring).
                 return new org.springframework.jdbc.core.BeanPropertyRowMapper<>(AtencionMedica.class).mapRow(rs, rowNum);
             });

        listarAtencionesMedicasPorPacienteCall = new SimpleJdbcCall(jdbcTemplate)
            .withSchemaName(SCHEMA)
            .withFunctionName("fn_atenciones_medicas_listar_por_paciente")
            .returningResultSet("ATENCIONES_MEDICAS_RESULT", (RowMapper) (rs, rowNum) -> 
                 new org.springframework.jdbc.core.BeanPropertyRowMapper<>(AtencionMedica.class).mapRow(rs, rowNum)
             );

        // --- 2. Entidades Detalle ---
        // Definición de configuraciones para reutilizar
        Map<String, Class<?>> detalleConfigs = new HashMap<>();
        detalleConfigs.put("antecedentes", Antecedente.class);
        detalleConfigs.put("diagnosticos", Diagnostico.class);
        detalleConfigs.put("discapacidad", Discapacidad.class);
        detalleConfigs.put("discapacidad_otros", DiscapacidadOtros.class);
        detalleConfigs.put("examen_fisico", ExamenFisico.class);
        detalleConfigs.put("ordenes_medicas", OrdenMedica.class);
        detalleConfigs.put("medicacion", Medicacion.class);

        // Inicializar SimpleJdbcCall para Insertar y Listar detalles
        for (Map.Entry<String, Class<?>> entry : detalleConfigs.entrySet()) {
            String name = entry.getKey();
            Class<?> modelClass = entry.getValue();

            // Insertar
            detalleInsertCalls.put(name, new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName(SCHEMA)
                .withFunctionName("fn_" + name + "_insertar"));
            
            // Actualizar
            detalleUpdateCalls.put(name, new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName(SCHEMA)
                .withFunctionName("fn_" + name + "_actualizar"));

            // Listar
            detalleListCalls.put(name, new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName(SCHEMA)
                .withFunctionName("fn_" + name + "_listar_por_atencion")
                .returningResultSet(name.toUpperCase() + "_RESULT", (RowMapper) (rs, rowNum) -> 
                    new org.springframework.jdbc.core.BeanPropertyRowMapper<>(modelClass).mapRow(rs, rowNum)
                ));

             // Eliminar (Asumido)
            detalleDeleteCalls.put(name, new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName(SCHEMA)
                .withFunctionName("fn_" + name + "_eliminar")); // Se asume fn_{detalle}_eliminar
        }
    }

    // =========================================================================
    // IMPLEMENTACIÓN DE LA ENTIDAD MAESTRA (ATENCION MEDICA)
    // =========================================================================

    @Override
    public Long insertarAtencionMedica(AtencionMedica atencion) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            // Los nombres de parámetros deben coincidir con los nombres de la función SP
            // p_id_paciente, p_id_cuenta_atencion, p_id_servicio, p_id_medico_ingreso, p_id_estado_atencion, 
            // p_ts_ingreso, p_id_usuario_registro, p_origen_registro_usuario
            .addValue("p_id_paciente", atencion.getIdPaciente())
            .addValue("p_id_cuenta_atencion", atencion.getIdCuentaAtencion())
            .addValue("p_id_servicio", atencion.getIdServicio())
            .addValue("p_id_medico_ingreso", atencion.getIdMedicoIngreso())
            .addValue("p_id_estado_atencion", atencion.getIdEstadoAtencion())
            .addValue("p_ts_ingreso", atencion.getTsIngreso())
            .addValue("p_id_usuario_registro", atencion.getIdUsuarioRegistro())
            .addValue("p_origen_registro_usuario", atencion.getOrigenRegistroUsuario());

        Map<String, Object> out = insertarAtencionMedicaCall.execute(params);
        // La función retorna un BIGINT (id_atencion)
        return (Long) out.get("RETURN_VALUE");
    }

    @Override
    public Boolean actualizarAtencionMedica(AtencionMedica atencion) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            // p_id_atencion, p_id_cuenta_atencion, p_id_servicio, p_id_medico_ingreso, 
            // p_id_estado_atencion, p_origen_registro_usuario
            .addValue("p_id_atencion", atencion.getIdAtencion())
            .addValue("p_id_cuenta_atencion", atencion.getIdCuentaAtencion())
            .addValue("p_id_servicio", atencion.getIdServicio())
            .addValue("p_id_medico_ingreso", atencion.getIdMedicoIngreso())
            .addValue("p_id_estado_atencion", atencion.getIdEstadoAtencion())
            .addValue("p_origen_registro_usuario", atencion.getOrigenRegistroUsuario());

        Map<String, Object> out = actualizarAtencionMedicaCall.execute(params);
        // La función retorna un BOOLEAN
        return out.get("RETURN_VALUE") != null ? (Boolean) out.get("RETURN_VALUE") : false;
    }

    @Override
    public Optional<AtencionMedica> obtenerAtencionMedicaPorId(Long idAtencion) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_id_atencion", idAtencion);

        Map<String, Object> out = obtenerAtencionMedicaPorIdCall.execute(params);
        
        @SuppressWarnings("unchecked")
        List<AtencionMedica> results = (List<AtencionMedica>) out.get("ATENCIONES_MEDICAS_RESULT");
        
        return results != null && !results.isEmpty() ? Optional.of(results.get(0)) : Optional.empty();
    }

    @Override
    public List<AtencionMedica> listarAtencionesMedicasPorPaciente(Integer idPaciente) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_id_paciente", idPaciente);

        Map<String, Object> out = listarAtencionesMedicasPorPacienteCall.execute(params);
        
        @SuppressWarnings("unchecked")
        List<AtencionMedica> results = (List<AtencionMedica>) out.get("ATENCIONES_MEDICAS_RESULT");
        
        return results != null ? results : Collections.emptyList();
    }


    // =========================================================================
    // IMPLEMENTACIÓN DE LAS ENTIDADES DETALLE
    // Se utiliza un enfoque de fábrica con Lambdas para reutilizar la lógica de CRUD.
    // =========================================================================

    /**
     * Función genérica para insertar entidades detalle.
     * @param detalle Objeto DTO del detalle.
     * @param spName Nombre de la función SP (e.g., "antecedentes").
     * @return ID generado (BIGINT).
     */
    private <T> Long insertarDetalleGenerico(T detalle, String spName) {
        SimpleJdbcCall call = detalleInsertCalls.get(spName);
        if (call == null) {
            throw new IllegalArgumentException("SP de inserción no configurado para: " + spName);
        }
        
        // SimpleJdbcCall puede mapear propiedades del objeto directamente si siguen la convención
        // (por ejemplo, idAtencion a p_id_atencion), pero para mayor seguridad,
        // usamos BeanPropertySqlParameterSource o mapeamos manualmente.
        // Aquí utilizaremos MapSqlParameterSource mapeando los DTOs a Map.
        MapSqlParameterSource params = new MapSqlParameterSource();
        
        // Mapeo manual (simplificado para los detalles)
        // NOTA: En un proyecto real, se usaría un conversor/mapper más robusto.
        if (detalle instanceof Antecedente) {
            Antecedente a = (Antecedente) detalle;
            params.addValue("p_id_atencion", a.getIdAtencion())
                  .addValue("p_id_antecedente", a.getIdAntecedente())
                  .addValue("p_id_tipo_antecedente", a.getIdTipoAntecedente())
                  .addValue("p_descripcion", a.getDescripcion())
                  .addValue("p_id_usuario", a.getIdUsuario());
        } else if (detalle instanceof Diagnostico) {
            Diagnostico d = (Diagnostico) detalle;
            params.addValue("p_id_atencion", d.getIdAtencion())
                  .addValue("p_id_diagnostico", d.getIdDiagnostico())
                  .addValue("p_id_subclasificacion", d.getIdSubclasificacion())
                  .addValue("p_id_lab1", d.getIdLab1())
                  .addValue("p_id_diagnostico_orden", d.getIdDiagnosticoOrden())
                  .addValue("p_id_usuario", d.getIdUsuario());
        } else if (detalle instanceof Discapacidad) {
            Discapacidad d = (Discapacidad) detalle;
            params.addValue("p_id_atencion", d.getIdAtencion())
                  .addValue("p_id_discapacidad", d.getIdDiscapacidad())
                  .addValue("p_id_gravedad_discapacidad", d.getIdGravedadDiscapacidad())
                  .addValue("p_id_usuario", d.getIdUsuario());
        } else if (detalle instanceof DiscapacidadOtros) {
            DiscapacidadOtros d = (DiscapacidadOtros) detalle;
            params.addValue("p_id_atencion", d.getIdAtencion())
                  .addValue("p_id_tipo_actividad", d.getIdTipoActividad())
                  .addValue("p_id_tiempo_discapacidad_aa", d.getIdTiempoDiscapacidadAa())
                  .addValue("p_id_tiempo_discapacidad_mm", d.getIdTiempoDiscapacidadMm())
                  .addValue("p_id_tiempo_discapacidad_dd", d.getIdTiempoDiscapacidadDd())
                  .addValue("p_id_tiempo_sintrabajar_aa", d.getIdTiempoSintrabajarAa())
                  .addValue("p_id_tiempo_sintrabajar_mm", d.getIdTiempoSintrabajarMm())
                  .addValue("p_id_tiempo_sintrabajar_dd", d.getIdTiempoSintrabajarDd())
                  .addValue("p_id_alta", d.getIdAlta())
                  .addValue("p_id_productividad", d.getIdProductividad())
                  .addValue("p_id_usuario", d.getIdUsuario());
        } else if (detalle instanceof ExamenFisico) {
             ExamenFisico e = (ExamenFisico) detalle;
             params.addValue("p_id_atencion", e.getIdAtencion())
                   .addValue("p_id_examen_fisico", e.getIdExamenFisico())
                   .addValue("p_id_tipo_examen_fisico", e.getIdTipoExamenFisico())
                   .addValue("p_descripcion", e.getDescripcion())
                   .addValue("p_id_usuario", e.getIdUsuario());
        } else if (detalle instanceof OrdenMedica) {
             OrdenMedica e = (OrdenMedica) detalle;
             params.addValue("p_id_atencion", e.getIdAtencion())
                   .addValue("p_id_punto_carga", e.getIdPuntoCarga())
                   .addValue("p_id_estado", e.getIdEstado())
                   .addValue("p_id_producto", e.getIdProducto())
                   .addValue("p_cantidad", e.getCantidad())
                   .addValue("p_precio", e.getPrecio())
                   .addValue("p_total", e.getTotal())
                   .addValue("p_id_diagnostico", e.getIdDiagnostico())
                   .addValue("p_observacion", e.getObservacion())
                   .addValue("p_id_usuario", e.getIdUsuario());
        } else if (detalle instanceof Medicacion) {
             Medicacion m = (Medicacion) detalle;
             // Suponemos que la función de insertar medicación acepta un ID de producto
             params.addValue("p_id_atencion", m.getIdAtencion())
                   .addValue("p_id_producto", m.getIdProducto()) 
                   .addValue("p_id_almacen", m.getIdAlmacen())
                   .addValue("p_cantidad_dosis", m.getCantidadDosis())
                   .addValue("p_id_um_dosis", m.getIdUmDosis())
                   .addValue("p_id_frecuencia_dosis", m.getIdFrecuenciaDosis())
                   .addValue("p_cantidad_periodo", m.getCantidadPeriodo())
                   .addValue("p_id_via_administracion", m.getIdViaAdministracion())
                   .addValue("p_cantidad_total", m.getCantidadTotal())
                   .addValue("p_precio", m.getPrecio())
                   .addValue("p_monto_total", m.getMontoTotal())
                   .addValue("p_indicaciones", m.getIndicaciones())
                   .addValue("p_id_diagnostico", m.getIdDiagnostico())
                   .addValue("p_id_usuario", m.getIdUsuario());
        } else {
            throw new IllegalArgumentException("Tipo de detalle no reconocido para inserción.");
        }
        
        Map<String, Object> out = call.execute(params);
        return (Long) out.get("RETURN_VALUE");
    }
    
    /**
     * Función genérica para actualizar entidades detalle.
     * @param detalle Objeto DTO del detalle.
     * @param spName Nombre de la función SP (e.g., "antecedentes").
     * @return Verdadero si se actualizó, falso si no.
     */
    private <T> Boolean actualizarDetalleGenerico(T detalle, String spName) {
        SimpleJdbcCall call = detalleUpdateCalls.get(spName);
        if (call == null) {
            throw new IllegalArgumentException("SP de actualización no configurado para: " + spName);
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        
        if (detalle instanceof Antecedente) {
            Antecedente a = (Antecedente) detalle;
            params.addValue("p_id_atencion_antecedente", a.getIdAtencionAntecedente())
                  .addValue("p_id_tipo_antecedente", a.getIdTipoAntecedente())
                  .addValue("p_descripcion", a.getDescripcion())
                  .addValue("p_id_usuario", a.getIdUsuario());
        } else if (detalle instanceof Diagnostico) {
            Diagnostico d = (Diagnostico) detalle;
            params.addValue("p_id_atencion_diagnostico", d.getIdAtencionDiagnostico())
                  .addValue("p_id_subclasificacion", d.getIdSubclasificacion())
                  .addValue("p_id_lab1", d.getIdLab1())
                  .addValue("p_id_diagnostico_orden", d.getIdDiagnosticoOrden())
                  .addValue("p_id_usuario", d.getIdUsuario());
        } else if (detalle instanceof Discapacidad) {
            Discapacidad d = (Discapacidad) detalle;
            params.addValue("p_id_atencion_discapacidad", d.getIdAtencionDiscapacidad())
                  .addValue("p_id_gravedad_discapacidad", d.getIdGravedadDiscapacidad())
                  .addValue("p_id_usuario", d.getIdUsuario());
        } else if (detalle instanceof DiscapacidadOtros) {
            DiscapacidadOtros d = (DiscapacidadOtros) detalle;
            params.addValue("p_id_atencion_discapacidad_otros", d.getIdAtencionDiscapacidadOtros())
                  .addValue("p_id_tipo_actividad", d.getIdTipoActividad())
                  .addValue("p_id_tiempo_discapacidad_aa", d.getIdTiempoDiscapacidadAa())
                  .addValue("p_id_tiempo_discapacidad_mm", d.getIdTiempoDiscapacidadMm())
                  .addValue("p_id_tiempo_discapacidad_dd", d.getIdTiempoDiscapacidadDd())
                  .addValue("p_id_tiempo_sintrabajar_aa", d.getIdTiempoSintrabajarAa())
                  .addValue("p_id_tiempo_sintrabajar_mm", d.getIdTiempoSintrabajarMm())
                  .addValue("p_id_tiempo_sintrabajar_dd", d.getIdTiempoSintrabajarDd())
                  .addValue("p_id_alta", d.getIdAlta())
                  .addValue("p_id_productividad", d.getIdProductividad())
                  .addValue("p_id_usuario", d.getIdUsuario());
        } else if (detalle instanceof ExamenFisico) {
            ExamenFisico e = (ExamenFisico) detalle;
            params.addValue("p_id_atencion_examen_fisico", e.getIdAtencionExamenFisico())
                  .addValue("p_id_tipo_examen_fisico", e.getIdTipoExamenFisico())
                  .addValue("p_descripcion", e.getDescripcion())
                  .addValue("p_id_usuario", e.getIdUsuario());
        } else if (detalle instanceof OrdenMedica) {
            OrdenMedica e = (OrdenMedica) detalle;
            params.addValue("p_id_atencion_examen", e.getIdAtencionOrdenMedica())
                  .addValue("p_id_punto_carga", e.getIdPuntoCarga())
                  .addValue("p_id_estado", e.getIdEstado())
                  .addValue("p_id_producto", e.getIdProducto())
                  .addValue("p_cantidad", e.getCantidad())
                  .addValue("p_precio", e.getPrecio())
                  .addValue("p_total", e.getTotal())
                  .addValue("p_id_diagnostico", e.getIdDiagnostico())
                  .addValue("p_observacion", e.getObservacion())
                  .addValue("p_id_usuario", e.getIdUsuario());
        } else if (detalle instanceof Medicacion) {
            Medicacion m = (Medicacion) detalle;
            params.addValue("p_id_atencion_medicacion", m.getIdAtencionMedicacion())
                  .addValue("p_id_almacen", m.getIdAlmacen())
                  .addValue("p_cantidad_dosis", m.getCantidadDosis())
                  .addValue("p_id_um_dosis", m.getIdUmDosis())
                  .addValue("p_id_frecuencia_dosis", m.getIdFrecuenciaDosis())
                  .addValue("p_cantidad_periodo", m.getCantidadPeriodo())
                  .addValue("p_id_via_administracion", m.getIdViaAdministracion())
                  .addValue("p_cantidad_total", m.getCantidadTotal())
                  .addValue("p_precio", m.getPrecio())
                  .addValue("p_monto_total", m.getMontoTotal())
                  .addValue("p_indicaciones", m.getIndicaciones())
                  .addValue("p_id_diagnostico", m.getIdDiagnostico())
                  .addValue("p_id_usuario", m.getIdUsuario());
        } else {
            throw new IllegalArgumentException("Tipo de detalle no reconocido para actualización.");
        }
        
        Map<String, Object> out = call.execute(params);
        return out.get("RETURN_VALUE") != null ? (Boolean) out.get("RETURN_VALUE") : false;
    }

    /**
     * Función genérica para listar entidades detalle.
     * @param idAtencion ID de la atención médica maestra.
     * @param spName Nombre de la función SP (e.g., "antecedentes").
     * @param resultName Nombre del result set definido en init (e.g., "ANTECEDENTES_RESULT").
     * @return Lista de objetos DTO del detalle.
     */
    private <T> List<T> listarDetalleGenerico(Long idAtencion, String spName, String resultName) {
        SimpleJdbcCall call = detalleListCalls.get(spName);
        if (call == null) {
            throw new IllegalArgumentException("SP de listado no configurado para: " + spName);
        }

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_id_atencion", idAtencion);
        
        Map<String, Object> out = call.execute(params);
        
        @SuppressWarnings("unchecked")
        List<T> results = (List<T>) out.get(resultName);
        
        return results != null ? results : Collections.emptyList();
    }
    
    /**
     * Función genérica para eliminar entidades detalle.
     * @param idDetalle ID del detalle a eliminar.
     * @param spName Nombre de la función SP (e.g., "antecedentes").
     * @return Verdadero si se eliminó, falso si no.
     */
    @Override
    public Boolean eliminarDetalle(Long idDetalle, String spName) {
        SimpleJdbcCall call = detalleDeleteCalls.get(spName);
        if (call == null) {
            System.err.println("Advertencia: SP de eliminación no configurado para: " + spName + ". No se puede eliminar.");
            return false;
        }

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_id_atencion_" + spName, idDetalle); // Asumiendo p_id_atencion_{detalle}
        
        Map<String, Object> out = call.execute(params);
        return out.get("RETURN_VALUE") != null ? (Boolean) out.get("RETURN_VALUE") : false;
    }
    
    // --- Implementación de los detalles usando las funciones genéricas ---

    @Override
    public Long insertarAntecedente(Antecedente antecedente) {
        return insertarDetalleGenerico(antecedente, "antecedentes");
    }

    @Override
    public Boolean actualizarAntecedente(Antecedente antecedente) {
        return actualizarDetalleGenerico(antecedente, "antecedentes");
    }

    @Override
    public List<Antecedente> listarAntecedentesPorAtencion(Long idAtencion) {
        return listarDetalleGenerico(idAtencion, "antecedentes", "ANTECEDENTES_RESULT");
    }

    @Override
    public Long insertarDiagnostico(Diagnostico diagnostico) {
        return insertarDetalleGenerico(diagnostico, "diagnosticos");
    }

    @Override
    public Boolean actualizarDiagnostico(Diagnostico diagnostico) {
        return actualizarDetalleGenerico(diagnostico, "diagnosticos");
    }

    @Override
    public List<Diagnostico> listarDiagnosticosPorAtencion(Long idAtencion) {
        return listarDetalleGenerico(idAtencion, "diagnosticos", "DIAGNOSTICOS_RESULT");
    }

    @Override
    public Long insertarDiscapacidad(Discapacidad discapacidad) {
        return insertarDetalleGenerico(discapacidad, "discapacidad");
    }

    @Override
    public Boolean actualizarDiscapacidad(Discapacidad discapacidad) {
        return actualizarDetalleGenerico(discapacidad, "discapacidad");
    }

    @Override
    public List<Discapacidad> listarDiscapacidadesPorAtencion(Long idAtencion) {
        return listarDetalleGenerico(idAtencion, "discapacidad", "DISCAPACIDAD_RESULT");
    }

    @Override
    public Long insertarDiscapacidadOtros(DiscapacidadOtros otros) {
        return insertarDetalleGenerico(otros, "discapacidad_otros");
    }

    @Override
    public Boolean actualizarDiscapacidadOtros(DiscapacidadOtros otros) {
        return actualizarDetalleGenerico(otros, "discapacidad_otros");
    }

    @Override
    public List<DiscapacidadOtros> listarDiscapacidadesOtrosPorAtencion(Long idAtencion) {
        return listarDetalleGenerico(idAtencion, "discapacidad_otros", "DISCAPACIDAD_OTROS_RESULT");
    }

    @Override
    public Long insertarExamenFisico(ExamenFisico examenFisico) {
        return insertarDetalleGenerico(examenFisico, "examen_fisico");
    }

    @Override
    public Boolean actualizarExamenFisico(ExamenFisico examenFisico) {
        return actualizarDetalleGenerico(examenFisico, "examen_fisico");
    }

    @Override
    public List<ExamenFisico> listarExamenesFisicosPorAtencion(Long idAtencion) {
        return listarDetalleGenerico(idAtencion, "examen_fisico", "EXAMEN_FISICO_RESULT");
    }

    @Override
    public Long insertarExamen(OrdenMedica ordenMedica) {
        return insertarDetalleGenerico(ordenMedica, "examenes");
    }

    @Override
    public Boolean actualizarExamen(OrdenMedica ordenMedica) {
        return actualizarDetalleGenerico(ordenMedica, "examenes");
    }

    @Override
    public List<OrdenMedica> listarOrdenesMedicasPorAtencion(Long idAtencion) {
        return listarDetalleGenerico(idAtencion, "ordenesMedicas", "EXAMENES_RESULT");
    }

    @Override
    public Long insertarMedicacion(Medicacion medicacion) {
        return insertarDetalleGenerico(medicacion, "medicacion");
    }

    @Override
    public Boolean actualizarMedicacion(Medicacion medicacion) {
        return actualizarDetalleGenerico(medicacion, "medicacion");
    }

    @Override
    public List<Medicacion> listarMedicacionPorAtencion(Long idAtencion) {
        return listarDetalleGenerico(idAtencion, "medicacion", "MEDICACION_RESULT");
    }
}