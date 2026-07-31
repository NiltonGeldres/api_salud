package com.api_salud.api_salud.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class CatalogoServiciosRepository {

    private final SimpleJdbcCall simpleJdbcCall;

    public CatalogoServiciosRepository(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_buscar_catalogo_servicios");
    }

    public String ejecutarFnBuscarCatalogoServicios(Integer idEntidad, String busqueda, Integer tipoServicio, Integer limite, Integer pagina) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id_entidad", idEntidad, Types.INTEGER)
                .addValue("p_busqueda", busqueda, Types.VARCHAR)
                .addValue("p_tipo_servicio", tipoServicio, Types.INTEGER)
                .addValue("p_limite", limite, Types.INTEGER)
                .addValue("p_pagina", pagina, Types.INTEGER);

        return simpleJdbcCall.executeFunction(String.class, in);
    }
}