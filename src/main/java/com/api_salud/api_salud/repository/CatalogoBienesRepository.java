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
public class CatalogoBienesRepository {

    private final SimpleJdbcCall fnBuscarCatalogoBienes;
    private final SimpleJdbcCall fnObtenerDetallePaqueteBienes;

    public CatalogoBienesRepository(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        this.fnBuscarCatalogoBienes = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_buscar_catalogo_bienes");

        this.fnObtenerDetallePaqueteBienes = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_obtener_detalle_paquete_bienes");
    }

    public String ejecutarFnBuscarCatalogoBienes(Integer idEntidad, String busqueda, Integer tipoProducto, Integer limite, Integer pagina) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id_entidad", idEntidad, Types.INTEGER)
                .addValue("p_busqueda", busqueda, Types.VARCHAR)
                .addValue("p_tipo_producto", tipoProducto, Types.INTEGER)
                .addValue("p_limite", limite, Types.INTEGER)
                .addValue("p_pagina", pagina, Types.INTEGER);

        Object result = fnBuscarCatalogoBienes.executeFunction(Object.class, in);
        return extraerJsonString(result);
    }

    public String obtenerDetallePaqueteBienes(Integer idPaquete, Integer idEntidad) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id_paquete", idPaquete, Types.INTEGER)
                .addValue("p_id_entidad", idEntidad, Types.INTEGER);

        Object result = fnObtenerDetallePaqueteBienes.executeFunction(Object.class, in);
        return extraerJsonString(result);
    }

    private String extraerJsonString(Object result) {
        if (result instanceof PGobject) {
            return ((PGobject) result).getValue();
        } else if (result != null) {
            return result.toString();
        }
        return null;
    }
}