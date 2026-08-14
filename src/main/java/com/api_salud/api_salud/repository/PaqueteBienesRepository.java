package com.api_salud.api_salud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class PaqueteBienesRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall fnObtenerDetallePaqueteBienes;

    @PostConstruct
    public void init() {
        this.fnObtenerDetallePaqueteBienes = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_obtener_detalle_paquete_bienes");
    }

    public String obtenerDetallePaqueteBienes(Integer idPaquete, Integer idEntidad) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_id_paquete", idPaquete)
                .addValue("p_id_entidad", idEntidad); // Proviene de TenantContext

        return fnObtenerDetallePaqueteBienes.executeFunction(String.class, params);
    }
}