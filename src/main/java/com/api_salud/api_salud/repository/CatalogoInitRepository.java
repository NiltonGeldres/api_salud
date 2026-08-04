package com.api_salud.api_salud.repository;

import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class CatalogoInitRepository {

    private final SimpleJdbcCall simpleJdbcCall;

    public CatalogoInitRepository(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_obtener_catalogos_init");
    }

    public String ejecutarFnObtenerCatalogosInit(Integer idEntidad) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id_entidad", idEntidad, Types.INTEGER);

        Object result = simpleJdbcCall.executeFunction(Object.class, in);

        if (result instanceof PGobject) {
            return ((PGobject) result).getValue();
        }

        return result != null ? result.toString() : null;
    }
}