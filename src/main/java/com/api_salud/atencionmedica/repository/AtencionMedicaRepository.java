package com.api_salud.atencionmedica.repository;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;
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

        private static final String SCHEMA = "igm_atenciones_medicas"; // Asumiendo que es una constante de clase

        private final SimpleJdbcCall insertarCall;
        private final SimpleJdbcCall obtenerPorIdCall;
        private final SimpleJdbcCall listarPorPacienteCall;
        private final SimpleJdbcCall actualizarCall;
        private final SimpleJdbcCall eliminarCall;


        // MÉTODO PRIVADO ESTÁTICO QUE CONTIENE LA LÓGICA DE MAPEO (RowMapper)//
        
        private static RowMapper<AtencionMedica> getAtencionMedicaRowMapper() {
            return (rs, rowNum) -> {
                AtencionMedica am = new AtencionMedica();
                
                // Lógica de mapeo de campos
                am.setIdAtencion(rs.getLong("id_atencion"));
                am.setIdPaciente(rs.getInt("id_paciente"));
                am.setIdCuentaAtencion(rs.getInt("id_cuenta_atencion"));
                am.setIdServicio(rs.getInt("id_servicio"));
                am.setIdMedicoIngreso(rs.getInt("id_medico_ingreso"));
                am.setIdEstadoAtencion(rs.getInt("id_estado_atencion"));
                
                // Mapeo de campos adicionales
                am.setTsIngreso(rs.getObject("ts_ingreso", java.time.OffsetDateTime.class));
                am.setIdUsuarioRegistro(rs.getInt("id_usuario_registro"));
                am.setOrigenRegistroUsuario(rs.getString("origen_registro_usuario"));
                // ... Mapeo de otros campos ...
                
                return am;
            };
        }


        public AtencionMedicaRepository(ProcedureCallConfig config) {
            
            // 1.1. fn_atenciones_medicas_insertar (RETURNS BIGINT)
            this.insertarCall = config.createSimpleJdbcCall("fn_atenciones_medicas_insertar", SCHEMA)
                                      .withReturnValue();

            // 1.2. fn_atenciones_medicas_obtener_por_id (Usa el método estático)
            this.obtenerPorIdCall = config.createSimpleJdbcCall("fn_atenciones_medicas_obtener_por_id", SCHEMA)
                                          .returningResultSet("RESULT_SET", getAtencionMedicaRowMapper()); // LLAMADA AL MÉTODO

            // 1.3. fn_atenciones_medicas_listar_por_paciente (Usa el método estático nuevamente)
            this.listarPorPacienteCall = config.createSimpleJdbcCall("fn_atenciones_medicas_listar_por_paciente", SCHEMA)
                                                .returningResultSet("RESULT_SET", getAtencionMedicaRowMapper()); // LLAMADA AL MÉTODO

            // 1.4. fn_atenciones_medicas_actualizar (RETURNS BOOLEAN)
            // Se añade .withReturnValue() que es necesario para funciones que devuelven algo.
            this.actualizarCall = config.createSimpleJdbcCall("fn_atenciones_medicas_actualizar", SCHEMA)
                                        .withReturnValue();

            // 1.5. sp_atenciones_medicas_eliminar (PROCEDURE)
            this.eliminarCall = config.createSimpleJdbcCall("sp_atenciones_medicas_eliminar", SCHEMA);
       
        

    }
    

    /**
     * Llama a la función de PostgreSQL fn_atenciones_medicas_insertar.
     * @param request DTO con los datos de la nueva atención.
     * @return El ID de la atención recién creada (BIGINT).
     */
    public Long insertar(AtencionMedicaRequest request) {
        
        Map<String, Object> inParams = new HashMap<>();
    	
    	inParams.put("p_id_paciente", request.getIdPaciente());
		inParams.put("p_id_cuenta_atencion", request.getIdCuentaAtencion());
		inParams.put("p_id_servicio", request.getIdServicio());
		inParams.put("p_id_medico_ingreso", request.getIdMedicoIngreso());
		inParams.put("p_id_estado_atencion", request.getIdEstadoAtencion());
		inParams.put("p_ts_ingreso", request.getTsIngreso());
		inParams.put("p_id_usuario_registro", request.getIdUsuarioRegistro());
		inParams.put("p_origen_registro_usuario", request.getOrigenRegistroUsuario());
        
        System.out.println("Llamando a fn_atenciones_medicas_insertar con parámetros: " + inParams);

        // Ejecutamos el Procedimiento Almacenado
        Map<String, Object> out = insertarCall.execute(inParams);
        
        // La función de PostgreSQL retorna BOOLEAN.
        // Usamos SimpleJdbcCall.RETURN_VALUE_NAME para obtener el valor de retorno.
        Object returnValue = out.get("RETURN_VALUE");
        
        // El valor de retorno esperado es un Long (el ID de la nueva atención).
        if (returnValue instanceof Long) {
            return (Long) returnValue;
        } 
        
        // CORRECCIÓN: Si PostgreSQL retorna un Integer (a veces ocurre con tipos BIGINT muy pequeños en ciertos drivers), 
        // lo convertimos a Long. Si el valor es nulo o de otro tipo, retornamos null o 0L.
        if (returnValue instanceof Integer) {
            return ((Integer) returnValue).longValue();
        }
        
        // En caso de retorno inesperado o nulo
        System.err.println("Error: Retorno inesperado de la función de inserción: " + returnValue);
        return null; // O puedes retornar 0L si prefieres un valor por defecto
		
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
     * * @param idAtencion El ID de la atención a actualizar.
     * @param request El DTO con los datos a modificar (AtencionMedicaRequest).
     * @return Retorna true si el SP actualiza correctamente, false en caso contrario.
     */
    public boolean actualizar(Long idAtencion, AtencionMedicaRequest request) {
        
        // Solución del error de Map.of(): Usamos HashMap + put() (Java 8 compatible)
        Map<String, Object> inParams = new HashMap<>();
        
        // Los parámetros del Map deben coincidir con los nombres que espera el SP:
        // "p_id_atencion" (debe ser el primer argumento, es el de la URL/clave)
        inParams.put("p_id_atencion", idAtencion); 
        
        // El resto de los parámetros vienen del Request DTO
        inParams.put("p_id_cuenta_atencion", request.getIdCuentaAtencion());
        inParams.put("p_id_servicio", request.getIdServicio());
        inParams.put("p_id_medico_ingreso", request.getIdMedicoIngreso());
        inParams.put("p_id_estado_atencion", request.getIdEstadoAtencion());
        inParams.put("p_origen_registro_usuario", request.getOrigenRegistroUsuario());
        
        // NOTA: Si el SP espera más parámetros que están en AtencionMedicaRequest
        // pero no fueron incluidos en el ejemplo original, deberían agregarse aquí.

        System.out.println("Llamando a fn_atenciones_medicas_actualizar con parámetros: " + inParams);

        // Ejecutamos el Procedimiento Almacenado
        Map<String, Object> out = actualizarCall.execute(inParams);
        
        // La función de PostgreSQL retorna BOOLEAN.
        // Usamos SimpleJdbcCall.RETURN_VALUE_NAME para obtener el valor de retorno.
        Object returnValue = out.get("RETURN_VALUE");

        if (returnValue instanceof Boolean) {
            return (Boolean) returnValue;
        } else if (returnValue instanceof Integer) {
             // Manejar el caso si retorna 1 o 0 como booleano simulado
            return ((Integer) returnValue) > 0;
        }
        
        // En caso de retorno inesperado o nulo
        return false;
    }   
    

    /**
     * Llama al procedimiento sp_atenciones_medicas_eliminar.
     */
    public void eliminar(Long idAtencion) {
        eliminarCall.execute(idAtencion);
    }
    
    
}    
    
    
    
