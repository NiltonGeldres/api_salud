package com.api_salud.api_salud.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Map;

@Repository
public class AtencionMedicaRepositoryImpl implements AtencionMedicaRepository {

    private final SimpleJdbcCall jdbcCallGuardar;
    private final SimpleJdbcCall jdbcCallFirmar; // <-- Nuevo SimpleJdbcCall para la firma
    private final JdbcTemplate jdbcTemplate;

    // Configuración e inyección del DataSource nativo
    public AtencionMedicaRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        
        // 1. SimpleJdbcCall para guardar atención completa
        this.jdbcCallGuardar = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas") 
                .withFunctionName("fn_guardar_atencion_medica_completa");

        // 2. SimpleJdbcCall para firmar atención (invoca fn_firmar_atencion)
        this.jdbcCallFirmar = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas")
                .withFunctionName("fn_firmar_atencion");
    }

    @Override
    public Long guardarAtencionMedicaCompleta(String jsonPayload) {
        System.out.println("JSON ENVIADO   " + jsonPayload);        
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);      

        Map<String, Object> result = jdbcCallGuardar.execute(parameterSource);
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
    // 🎯 1. LEER EL JSON DESDE LA TABLA
    // =======================================================================
    @Override
    public String obtenerJsonAtencionPorId(Long idAtencion) {
        String sql = "SELECT igm_atenciones_medicas.fn_obtener_json_atencion(?)";
        
        try {
            return jdbcTemplate.queryForObject(sql, String.class, idAtencion);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // =======================================================================
    // 🎯 2. ACTUALIZAR RUTA FÍSICA DEL PDF
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
    // 🎯 3. EJECUTAR FUNCIÓN DE FIRMA COMPLETA USANDO SimpleJdbcCall
    // =======================================================================
    @Override
    public void firmarAtencion(Long idAtencion, String hashFirma, String tipoFirma) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("p_id_atencion", idAtencion);
            parameterSource.addValue("p_hash_firma", hashFirma);
            parameterSource.addValue("p_tipo_firma", tipoFirma != null ? tipoFirma : "TOKEN");

            jdbcCallFirmar.execute(parameterSource);
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar fn_firmar_atencion mediante SimpleJdbcCall: " + e.getMessage(), e);
        }
    }

    // =======================================================================
    // 🎯 4. MÉTODOS DE COMPATIBILIDAD
    // =======================================================================
    @Override
    public void actualizarEstadoFirma(Long idAtencion, String estadoFirma) {
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET estado_firma = ? WHERE id_atencion = ?";
        try {
            jdbcTemplate.update(sql, estadoFirma, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el estado de la firma en la base de datos: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void actualizarHashFirma(Long idAtencion, String hashFirma) {
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET hash_firma_digital = ? WHERE id_atencion = ?";
        try {
            jdbcTemplate.update(sql, hashFirma, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el hash de la firma en la base de datos: " + e.getMessage(), e);
        }
    }    
}


/*package com.api_salud.api_salud.repository;

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
    // 🎯 3. EJECUTAR FUNCIÓN DE FIRMA COMPLETA (Actualiza Columnas + JSONB)
    // =======================================================================
    @Override
    public void firmarAtencion(Long idAtencion, String hashFirma, String tipoFirma) {
        String sql = "SELECT igm_atenciones_medicas.fn_firmar_atencion(?, ?, ?)";
        
        try {
            // Se usa execute con Callback para garantizar la ejecución correcta de la función PL/pgSQL
            jdbcTemplate.execute(sql, (PreparedStatementCallback<Object>) ps -> {
                ps.setLong(1, idAtencion);
                ps.setString(2, hashFirma);
                ps.setString(3, tipoFirma != null ? tipoFirma : "TOKEN");
                return ps.execute();
            });
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar fn_firmar_atencion en la BD: " + e.getMessage(), e);
        }
    }

    // =======================================================================
    // 🎯 4. MÉTODOS DE COMPATIBILIDAD (Sincronizados con la función)
    // =======================================================================
    @Override
    public void actualizarEstadoFirma(Long idAtencion, String estadoFirma) {
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET estado_firma = ? WHERE id_atencion = ?";
        try {
            jdbcTemplate.update(sql, estadoFirma, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el estado de la firma en la base de datos: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void actualizarHashFirma(Long idAtencion, String hashFirma) {
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET hash_firma_digital = ? WHERE id_atencion = ?";
        try {
            jdbcTemplate.update(sql, hashFirma, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el hash de la firma en la base de datos: " + e.getMessage(), e);
        }
    }   
    
}

*/