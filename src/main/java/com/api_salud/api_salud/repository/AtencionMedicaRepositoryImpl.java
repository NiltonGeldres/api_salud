package com.api_salud.api_salud.repository;


import com.api_salud.api_salud.repository.AtencionMedicaRepository;
import org.springframework.jdbc.core.JdbcTemplate; // 🎯 Agregamos JdbcTemplate para el UPDATE rápido
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class AtencionMedicaRepositoryImpl implements AtencionMedicaRepository {

    private final SimpleJdbcCall jdbcCall;
    private final JdbcTemplate jdbcTemplate; // 🎯 Declaramos la herramienta para updates planos

    // Configuración e inyección del DataSource nativo
    public AtencionMedicaRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource); // Inicializamos el JdbcTemplate
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withSchemaName("igm_atenciones_medicas") 
                .withFunctionName("fn_guardar_atencion_medica_completa"); 
    }

    @Override
    public Long guardarAtencionMedicaCompleta(String jsonPayload) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_payload", jsonPayload);

        Map<String, Object> result = jdbcCall.execute(parameterSource);
        Object returnValue = result.get("returnvalue");

        if (returnValue == null) {
            throw new RuntimeException("La base de datos no retornó ningún ID para la atención médica.");
        }

        return ((Number) returnValue).longValue();
    }

    // =======================================================================
    // 🎯 EL MÉTODO QUE FALTA AGREGAR PARA QUE NO DE ERROR EL SERVICE
    // =======================================================================
 // =======================================================================
    // 🎯 MÉTODO CORREGIDO CON EL ESQUEMA Y TABLA REAL DE POSTGRESQL
    // =======================================================================
    @Override
    public void actualizarRutaPdf(Long idAtencion, String rutaPdf) {
        // 🔄 CORRECCIÓN: Esquema 'atenciones_medicas' y tabla 'atenciones_medicas' (o cabecera_atencion si la renombraste)
        String sql = "UPDATE igm_atenciones_medicas.atenciones_medicas SET ruta_pdf_firmado = ? WHERE id_atencion = ?";
        
        try {
            jdbcTemplate.update(sql, rutaPdf, idAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la ruta física del PDF en la base de datos: " + e.getMessage(), e);
        }
    }
}