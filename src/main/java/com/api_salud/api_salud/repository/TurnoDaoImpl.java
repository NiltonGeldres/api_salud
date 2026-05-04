package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.context.TenantContext;
import com.api_salud.api_salud.entity.TurnoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TurnoDaoImpl implements TurnoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Instancia única para evitar overhead de creación de objetos
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private SimpleJdbcCall simpleJdbcCallTurno;

    @PostConstruct
    public void init() {
        // Configuración global de JdbcTemplate
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        // Inicialización única del procedimiento
        this.simpleJdbcCallTurno = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_programacion")
                .withProcedureName("turnos_todos_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_id_entidad", Types.INTEGER),
                        new SqlOutParameter("cur", Types.OTHER)
                );
    }

    @Override
    public List<TurnoEntity> turnoLeer() {
        Long entidadId = TenantContext.getEntidadId();
        
        // Validación preventiva para evitar NullPointerException
        if (entidadId == null) {
            return new ArrayList<>();
        }

        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("p_id_entidad", entidadId.intValue());

            Map<String, Object> out = simpleJdbcCallTurno.execute(param);
            
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> list = (List<Map<String, Object>>) out.get("cur");

            if (list == null || list.isEmpty()) {
                return new ArrayList<>();
            }

            // Mapeo optimizado de la lista
            List<TurnoEntity> response = new ArrayList<>(list.size());
            for (Map<String, Object> row : list) {
                // Usamos la instancia estática OBJECT_MAPPER
                response.add(OBJECT_MAPPER.convertValue(row, TurnoEntity.class));
            }
            
            return response;

        } catch (Exception e) {
            // Log de error específico para diagnóstico
            throw new RuntimeException("Error al ejecutar turnos_todos_leer", e);
        }
    }
}
