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
public class CatalogoDiagnosticosRepository {

    private final SimpleJdbcCall simpleJdbcCall;

    public CatalogoDiagnosticosRepository(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_buscar_diagnosticos");
    }

    public String ejecutarFnBuscarDiagnosticos(String busqueda, Integer limite, Integer pagina) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_busqueda", busqueda, Types.VARCHAR)
                .addValue("p_limite", limite, Types.INTEGER)
                .addValue("p_pagina", pagina, Types.INTEGER);

        Object result = simpleJdbcCall.executeFunction(Object.class, in);

        if (result instanceof PGobject) {
            return ((PGobject) result).getValue();
        }

        return result != null ? result.toString() : null;
    }
}