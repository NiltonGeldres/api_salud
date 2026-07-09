package com.api_salud.api_salud.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.sql.Types;

@Repository
public class AtencionMedicaRepositoryImpl implements AtencionMedicaRepository {

    private final SimpleJdbcCall jdbcCall;
    private final JdbcTemplate jdbcTemplate;

    // Configuración e inyección del DataSource nativo
    public AtencionMedicaRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas") 
                .withFunctionName("fn_guardar_atencion_medica_completa"); 
    }

    @Override
    public Long guardarAtencionMedicaCompleta(String jsonPayload) {
    	System.out.println("JSON ENVIADO   "+jsonPayload);    	
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        // 🔥 Forzamos a Spring a enviar el String con el tipo SQL unificado jsonb
        parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);      

        Map<String, Object> result = jdbcCall.execute(parameterSource);
        Object returnValue = result.get("returnvalue");

        if (returnValue == null) {
            returnValue = result.get("id_atencion"); 
            if (returnValue == null) {
                throw new RuntimeException("La base de datos no retornó ningún ID para la atención médica.");
            }
        }

        return ((Number) returnValue).longValue();
    }

    // =======================================================================
    // 🎯 1. LEER EL JSON DESDE LA TABLA PARA EL PROCESO SEGURO DE FIRMA
    // =======================================================================
    @Override
    public String obtenerJsonAtencionPorId(Long idAtencion) {
        // Ahora solo llamas a la función. Si cambia la BD, el código Java NO se entera.
        String sql = "SELECT igm_atenciones_medicas.fn_obtener_json_atencion(?)";
        
        try {
            return jdbcTemplate.queryForObject(sql, String.class, idAtencion);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // =======================================================================
    // 🎯 2. ACTUALIZAR LA RUTA FÍSICA DEL PDF
    // =======================================================================
    @Override
    public void actualizarRutaPdf(Long idAtencion, String rutaPdf) {
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET ruta_pdf_firmado = ? WHERE id_atencion = ?";
        
        try {
            jdbcTemplate.update(sql, rutaPdf, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la ruta física del PDF en la base de datos: " + e.getMessage(), e);
        }
    }

    // =======================================================================
    // 🎯 3. ACTUALIZAR EL ESTADO DE LA FIRMA A 'FIRMADO_ELECTRONICO'
    // =======================================================================
    @Override
    public void actualizarEstadoFirma(Long idAtencion, String estadoFirma) {
        // Ajusta el nombre exacto de tu columna si se llama 'estado_firma' o similar
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET estado_firma = ? WHERE id_atencion = ?";
        
        try {
            jdbcTemplate.update(sql, estadoFirma, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el estado de la firma en la base de datos: " + e.getMessage(), e);
        }
    }
}