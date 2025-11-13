package com.api_salud.atencionmedica.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * Configuración de la capa de acceso a datos para utilizar SimpleJdbcCall.
 * Proporciona el JdbcTemplate necesario y el DataSource.
 */
@Configuration
public class ProcedureCallConfig {

    private final DataSource dataSource;

    public ProcedureCallConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Bean genérico para la creación de SimpleJdbcCall.
     * @param procedureName Nombre de la función o procedimiento de la base de datos.
     * @param schemaName Nombre del esquema donde reside el procedimiento.
     * @return Una instancia de SimpleJdbcCall.
     */
    public SimpleJdbcCall createSimpleJdbcCall(String procedureName, String schemaName) {
        SimpleJdbcCall call = new SimpleJdbcCall(dataSource)
                .withSchemaName(schemaName)
                .withProcedureName(procedureName);
        return call;
    }
}