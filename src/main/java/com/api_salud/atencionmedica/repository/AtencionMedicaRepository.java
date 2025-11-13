package com.api_salud.atencionmedica.repository;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.atencionmedica.config.ProcedureCallConfig;
import com.api_salud.atencionmedica.entity.AtencionMedica;
import com.api_salud.atencionmedica.request.AtencionMedicaRequest;

/**
 * Repositorio que interactúa con la tabla atenciones_medicas
 * utilizando SimpleJdbcCall para invocar las funciones y procedimientos almacenados.
 */
@Repository
public class AtencionMedicaRepository {

    private static final String SCHEMA = "igm_atenciones_medicas";

    private final SimpleJdbcCall insertarCall;
    private final SimpleJdbcCall obtenerPorIdCall;
    private final SimpleJdbcCall listarPorPacienteCall;
    private final SimpleJdbcCall actualizarCall;
    private final SimpleJdbcCall eliminarCall;

    public AtencionMedicaRepository(ProcedureCallConfig config) {
        // 1.1. fn_atenciones_medicas_insertar (RETURNS BIGINT)
        this.insertarCall = config.createSimpleJdbcCall("fn_atenciones_medicas_insertar", SCHEMA)
                                  .withReturnValue();

        // 1.2. fn_atenciones_medicas_obtener_por_id (RETURNS SETOF atenciones_medicas)
        this.obtenerPorIdCall = config.createSimpleJdbcCall("fn_atenciones_medicas_obtener_por_id", SCHEMA)
                                      .returningResultSet("RESULT_SET", (rs, rowNum) -> {
                                          AtencionMedica am = new AtencionMedica();
                                          am.setIdAtencion(rs.getLong("id_atencion"));
                                          am.setIdPaciente(rs.getInt("id_paciente"));
                                          am.setIdCuentaAtencion(rs.getInt("id_cuenta_atencion"));
                                          am.setIdServicio(rs.getInt("id_servicio"));
                                          am.setIdMedicoIngreso(rs.getInt("id_medico_ingreso"));
                                          am.setIdEstadoAtencion(rs.getInt("id_estado_atencion"));
                                          // Se asume mapeo automático de OffsetDateTime
                                          return am;
                                      });

        // 1.3. fn_atenciones_medicas_listar_por_paciente (RETURNS SETOF atenciones_medicas)
        this.listarPorPacienteCall = config.createSimpleJdbcCall("fn_atenciones_medicas_listar_por_paciente", SCHEMA)
                                            .returningResultSet("RESULT_SET", obtenerPorIdCall.getRowMapper());

        // 1.4. fn_atenciones_medicas_actualizar (RETURNS BOOLEAN)
        this.actualizarCall = config.createSimpleJdbcCall("fn_atenciones_medicas_actualizar", SCHEMA);

        // 1.5. sp_atenciones_medicas_eliminar (PROCEDURE)
        this.eliminarCall = config.createSimpleJdbcCall("sp_atenciones_medicas_eliminar", SCHEMA);
    }

    /**
     * Llama a la función de PostgreSQL fn_atenciones_medicas_insertar.
     * @param request DTO con los datos de la nueva atención.
     * @return El ID de la atención recién creada (BIGINT).
     */
    public Long insertar(AtencionMedicaRequest request) {
        Map<String, Object> inParams = Map.of(
            "p_id_paciente", request.getIdPaciente(),
            "p_id_cuenta_atencion", request.getIdCuentaAtencion(),
            "p_id_servicio", request.getIdServicio(),
            "p_id_medico_ingreso", request.getIdMedicoIngreso(),
            "p_id_estado_atencion", request.getIdEstadoAtencion(),
            "p_ts_ingreso", request.getTsIngreso(),
            "p_id_usuario_registro", request.getIdUsuarioRegistro(),
            "p_origen_registro_usuario", request.getOrigenRegistroUsuario()
        );

        Map<String, Object> out = insertarCall.execute(inParams);
        // El resultado se espera en el primer valor de salida, que es el ID (BIGINT)
        return (Long) out.get(SimpleJdbcCall.RETURN_VALUE_NAME);
    }

    /**
     * Llama a la función fn_atenciones_medicas_obtener_por_id.
     */
    public Optional<AtencionMedica> obtenerPorId(Long idAtencion) {
        Map<String, Object> out = obtenerPorIdCall.execute(idAtencion);
        
        @SuppressWarnings("unchecked")
        List<AtencionMedica> resultados = (List<AtencionMedica>) out.get("RESULT_SET");
        
        return resultados != null && !resultados.isEmpty() ? Optional.of(resultados.get(0)) : Optional.empty();
    }

    /**
     * Llama a la función fn_atenciones_medicas_listar_por_paciente.
     */
    public List<AtencionMedica> listarPorPaciente(Integer idPaciente) {
        Map<String, Object> out = listarPorPacienteCall.execute(idPaciente);

        @SuppressWarnings("unchecked")
        List<AtencionMedica> resultados = (List<AtencionMedica>) out.get("RESULT_SET");

        return resultados != null ? resultados : Collections.emptyList();
    }

    /**
     * Llama a la función fn_atenciones_medicas_actualizar.
     */
    public boolean actualizar(Long idAtencion, AtencionMedicaRequest request) {
        // NOTA: Se usan los valores por defecto NULL en el SP si el campo del DTO es nulo.
        Map<String, Object> inParams = Map.of(
            "p_id_atencion", idAtencion,
            "p_id_cuenta_atencion", request.getIdCuentaAtencion(),
            "p_id_servicio", request.getIdServicio(),
            "p_id_medico_ingreso", request.getIdMedicoIngreso(),
            "p_id_estado_atencion", request.getIdEstadoAtencion(),
            "p_origen_registro_usuario", request.getOrigenRegistroUsuario()
        );

        Map<String, Object> out = actualizarCall.execute(inParams);
        // La función de PostgreSQL retorna BOOLEAN.
        return (Boolean) out.get(SimpleJdbcCall.RETURN_VALUE_NAME);
    }

    /**
     * Llama al procedimiento sp_atenciones_medicas_eliminar.
     */
    public void eliminar(Long idAtencion) {
        eliminarCall.execute(idAtencion);
    }