package com.api_salud.api_salud.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.dto.AtencionPendienteFirmaDTO;
import com.api_salud.api_salud.dto.DocumentoAdjuntoDTO;


import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class AtencionMedicaRepositoryImpl implements AtencionMedicaRepository {

  //  private final SimpleJdbcCall jdbcCallGuardar;
    private final SimpleJdbcCall jdbcCallFirmar; // <-- Nuevo SimpleJdbcCall para la firma
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall jdbcCallGuardarBorrador;
    private final SimpleJdbcCall jdbcCallActualizarBorrador;
    private final SimpleJdbcCall jdbcCallGuardarCompleta;
    private final SimpleJdbcCall jdbcCallActualizarRutaPdf;
    private final SimpleJdbcCall jdbcCallListarPendientesFirma;
    
    // Configuración e inyección del DataSource nativo
    public AtencionMedicaRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
     // 1. SimpleJdbcCall para CREAR borrador
        this.jdbcCallGuardarBorrador = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas") 
                .withFunctionName("fn_guardar_atencion_medica_borrador");

        // 2. SimpleJdbcCall para ACTUALIZAR borrador
        this.jdbcCallActualizarBorrador = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas") 
                .withFunctionName("fn_actualizar_atencion_medica_borrador");

        // 3. SimpleJdbcCall para guardar atención completa (cierre/firma)
        this.jdbcCallGuardarCompleta = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas") 
                .withFunctionName("fn_guardar_atencion_medica_completa");
                
        // 1. SimpleJdbcCall para guardar atención completa
//        this.jdbcCallGuardar = new SimpleJdbcCall(dataSource)
//                .withSchemaName("igm_atenciones_medicas") 
//                .withFunctionName("fn_guardar_atencion_medica_completa");

        // 2. SimpleJdbcCall para firmar atención (invoca fn_firmar_atencion)
        this.jdbcCallFirmar = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas")
                .withFunctionName("fn_firmar_atencion");
        
        this.jdbcCallActualizarRutaPdf = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas")
                .withFunctionName("fn_actualizar_ruta_pdf_borrador");   
        
     // Dentro del constructor AtencionMedicaRepositoryImpl(DataSource dataSource):
        this.jdbcCallListarPendientesFirma = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas")
                .withFunctionName("fn_listar_atenciones_pendientes_firma")
                .returningResultSet("refcursor", BeanPropertyRowMapper.newInstance(AtencionPendienteFirmaDTO.class));        
        
    }

    
    @Override
    public Long guardarAtencionMedicaBorrador(String jsonPayload) {
        System.out.println("JSON CREAR BORRADOR: " + jsonPayload);        
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);      

        Map<String, Object> result = jdbcCallGuardarBorrador.execute(parameterSource);
        Object returnValue = result.get("returnvalue");

        if (returnValue == null) {
            returnValue = result.get("id_atencion"); 
            if (returnValue == null) {
                throw new RuntimeException("La base de datos no retornó un ID válido al guardar borrador.");
            }
        }

        return ((Number) returnValue).longValue();
    }

    @Override
    public void actualizarAtencionMedicaBorrador(Long idAtencion, String jsonPayload) {
        System.out.println("JSON ACTUALIZAR BORRADOR [ID=" + idAtencion + "]: " + jsonPayload);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_id_atencion", idAtencion, Types.BIGINT);
        parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);

        jdbcCallActualizarBorrador.execute(parameterSource);
    }

    @Override
    public Long guardarAtencionMedicaCompleta(String jsonPayload) {
        System.out.println("JSON ATENCION COMPLETA: " + jsonPayload);        
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);      

        Map<String, Object> result = jdbcCallGuardarCompleta.execute(parameterSource);
        Object returnValue = result.get("returnvalue");

        if (returnValue == null) {
            returnValue = result.get("id_atencion"); 
            if (returnValue == null) {
                throw new RuntimeException("La base de datos no retornó ningún ID para la atención médica.");
            }
        }

        return ((Number) returnValue).longValue();
    }   
    
/*    @Override
    public Long guardarAtencionMedicaCompleta1(String jsonPayload) {
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
    }*/

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
/*    @Override
    public void actualizarRutaPdf(Long idAtencion, String rutaPdf) {
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET ruta_pdf_firmado = ? WHERE id_atencion = ?";
        
        try {
            jdbcTemplate.update(sql, rutaPdf, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la ruta física del PDF en la base de datos: " + e.getMessage(), e);
        }
    }
 */   
 // =======================================================================
    // 🎯 2. ACTUALIZAR RUTA FÍSICA DEL PDF Y SINCRONIZAR JSONB VÍA PL/pgSQL
    // =======================================================================
    @Override
    public void actualizarRutasPdf(Long idAtencion, List<DocumentoAdjuntoDTO> documentos) {
        try {
            // Extraer rutas según el tipo de documento generado
            String rutaHistoria = obtenerRutaPorTipo(documentos, "historia");
            String rutaReceta = obtenerRutaPorTipo(documentos, "receta");
            String rutaOrdenes = obtenerRutaPorTipo(documentos, "orden");
            String rutaIndicaciones = obtenerRutaPorTipo(documentos, "indicaciones");

            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("p_id_atencion", idAtencion, Types.BIGINT);
            parameterSource.addValue("p_ruta_historia", rutaHistoria, Types.VARCHAR);
            parameterSource.addValue("p_ruta_receta", rutaReceta, Types.VARCHAR);
            parameterSource.addValue("p_ruta_ordenes", rutaOrdenes, Types.VARCHAR);
            parameterSource.addValue("p_ruta_indicaciones", rutaIndicaciones, Types.VARCHAR);

            jdbcCallActualizarRutaPdf.execute(parameterSource);
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar fn_actualizar_ruta_pdf_borrador en la BD: " + e.getMessage(), e);
        }
    }

    private String obtenerRutaPorTipo(List<DocumentoAdjuntoDTO> documentos, String tipo) {
        return documentos.stream()
                .filter(d -> tipo.equalsIgnoreCase(d.getTipoDocumento()))
                .map(DocumentoAdjuntoDTO::getRutaBorrador)
                .findFirst()
                .orElse(null);
    }    
/*    @Override
    public void actualizarRutaPdf(Long idAtencion, String rutaPdf) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("p_id_atencion", idAtencion, Types.BIGINT);
            parameterSource.addValue("p_ruta_pdf", rutaPdf, Types.VARCHAR);

            jdbcCallActualizarRutaPdf.execute(parameterSource);
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar fn_actualizar_ruta_pdf_borrador en la BD: " + e.getMessage(), e);
        }
    }    */
    

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
    
 // =======================================================================
    // 🎯 5. LISTAR ATENCIONES PENDIENTES DE FIRMA (PDF BORRADOR)
    // =======================================================================
    @Override
    public String listarAtencionesPendientesFirma(Integer idEntidad, Integer idMedico) {
        String sql = "SELECT igm_atenciones_medicas.fn_listar_atenciones_pendientes_firma(?, ?)";
        
        try {
            return jdbcTemplate.queryForObject(sql, String.class, idEntidad, idMedico);
        } catch (EmptyResultDataAccessException e) {
            return "[]";
        }
    }
}


