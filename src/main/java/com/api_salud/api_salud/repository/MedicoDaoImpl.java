package com.api_salud.api_salud.repository;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api_salud.api_salud.dto.MedicoDTO;
import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.MedicoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
@Transactional
public class MedicoDaoImpl implements MedicoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Objetos pre-configurados en memoria
    private SimpleJdbcCall callMedicoEntidad;
    private SimpleJdbcCall callMedicoEspecialidad;
    private SimpleJdbcCall callMedicoXUsuario;
    private SimpleJdbcCall callTiempoPromedio;
    private SimpleJdbcCall callMedicoXIdEsp;

 // Nuevos Objetos pre-configurados para el CRUD de Médicos
    private SimpleJdbcCall callRegistrarJson;
    private SimpleJdbcCall callActualizarJson;
    private SimpleJdbcCall callBajaJson;
    private SimpleJdbcCall callListarPaginado;
    
    private static final Logger log = LoggerFactory.getLogger(MedicoDaoImpl.class);    

    @Autowired
    public MedicoDaoImpl(DataSource dataSource, ObjectMapper objectMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.objectMapper = objectMapper;
    }
    
    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        

        this.callMedicoEntidad = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withProcedureName("medicos_entidad_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("id_entidad", Types.INTEGER),
                        new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(MedicoDTO.class))
                );

        this.callMedicoEspecialidad = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withProcedureName("medicos_especialidad_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("idEspecialidad", Types.INTEGER),
                        new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(MedicoDTO.class))
                );

        this.callMedicoXUsuario = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withProcedureName("medico_xusuario_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_usuario", Types.VARCHAR),
                        new SqlOutParameter("o_idmedico", Types.INTEGER)
                );

        this.callTiempoPromedio = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withProcedureName("medicoespecialidad_tiempopromedio_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_idmedico", Types.INTEGER),
                        new SqlParameter("p_idespecialidad", Types.INTEGER),
                        new SqlOutParameter("o_tiempopromedioatencion", Types.INTEGER)
                );

        this.callMedicoXIdEsp = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withProcedureName("medicos_xidmedico_xidespecialidad_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_idespecialidad", Types.INTEGER),
                        new SqlParameter("p_idmedico", Types.INTEGER),
                        new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(MedicoDTO.class))
                );
    
// --- Nuevas Funciones CRUD (Registrar, Listar Paginado, Actualizar, Baja) ---
        
     // Todas las funciones maestras bajo el estándar JSON
        this.callRegistrarJson = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_medico_registrar_completo");

        this.callActualizarJson = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_medico_actualizar_json");

        this.callBajaJson = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_medico_baja_json");

        // Listar mantiene el ref_cursor como ya lo tenías configurado
        this.callListarPaginado = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_maestros")
                .withFunctionName("fn_medicos_listar")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_id_entidad", Types.INTEGER),
                        new SqlParameter("p_filtro", Types.VARCHAR),
                        new SqlParameter("p_estado", Types.VARCHAR),
                        new SqlParameter("p_limit", Types.INTEGER),
                        new SqlParameter("p_offset", Types.INTEGER),
                        new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(MedicoDTO.class))
                );
        
    }

@Override
    public int registrarMedico(MedicoRequest req) {
        try {
            // Serializamos el request completo a String JSON
            String jsonPayload = objectMapper.writeValueAsString(req);
            log.info("JSON REGISTRAR MEDICO: {}", jsonPayload);

            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);

            Map<String, Object> result = callRegistrarJson.execute(parameterSource);
            Object jsonResponse = result.get("returnvalue");
            if (jsonResponse == null) {
                jsonResponse = result.get("fn_medico_registrar_completo");
            }

            if (jsonResponse != null) {
                JsonNode rootNode = objectMapper.readTree(jsonResponse.toString());
                boolean success = rootNode.path("success").asBoolean(false);

                if (!success) {
                    String errorMessage = rootNode.path("message").asText("Error desconocido en base de datos.");
                    throw new RuntimeException(errorMessage);
                }

                return rootNode.path("idMedico").asInt(0);
            }

            throw new RuntimeException("La función de base de datos no retornó respuesta.");

        } catch (Exception e) {
            log.error("Error crítico al registrar médico en DAO: ", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean actualizarMedico(int idMedico, MedicoRequest req) {
        try {
            req.setIdMedico(idMedico);
            String jsonPayload = objectMapper.writeValueAsString(req);
            log.info("JSON ACTUALIZAR MEDICO: {}", jsonPayload);

            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);

            Map<String, Object> result = callActualizarJson.execute(parameterSource);
            Object jsonResponse = result.get("returnvalue");
            if (jsonResponse == null) {
                jsonResponse = result.get("fn_medico_actualizar_json");
            }

            if (jsonResponse != null) {
                JsonNode rootNode = objectMapper.readTree(jsonResponse.toString());
                boolean success = rootNode.path("success").asBoolean(false);

                if (!success) {
                    throw new RuntimeException(rootNode.path("message").asText("Error al actualizar."));
                }

                return true;
            }

            return false;
        } catch (Exception e) {
            log.error("Error crítico al actualizar médico en DAO: ", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean darBajaMedico(int idMedico, int idEntidad, String estado) {
        try {
            Map<String, Object> payloadMap = new HashMap<>();
            payloadMap.put("idMedico", idMedico);
            payloadMap.put("idEntidad", idEntidad);
            payloadMap.put("estado", estado);

            String jsonPayload = objectMapper.writeValueAsString(payloadMap);
            log.info("JSON BAJA MEDICO: {}", jsonPayload);

            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("p_payload", jsonPayload, Types.OTHER);

            Map<String, Object> result = callBajaJson.execute(parameterSource);
            Object jsonResponse = result.get("returnvalue");
            if (jsonResponse == null) {
                jsonResponse = result.get("fn_medico_baja_json");
            }

            if (jsonResponse != null) {
                JsonNode rootNode = objectMapper.readTree(jsonResponse.toString());
                boolean success = rootNode.path("success").asBoolean(false);

                if (!success) {
                    throw new RuntimeException(rootNode.path("message").asText("Error al dar de baja."));
                }

                return true;
            }

            return false;
        } catch (Exception e) {
            log.error("Error crítico al dar de baja médico en DAO: ", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<MedicoDTO> listarMedicos(int idEntidad, String filtro, String estado, int limit, int offset) {
        try {
            String sql = "SELECT * FROM igm_maestros.fn_medicos_listar(?, ?, ?, ?, ?)";

            return jdbcTemplate.query(
                    sql,
                    new Object[]{
                            idEntidad,
                            filtro != null ? filtro : "",
                            estado != null ? estado : "ACTIVO",
                            limit,
                            offset
                    },
                    new BeanPropertyRowMapper<>(MedicoDTO.class)
            );

        } catch (Exception e) {
            log.error("Error crítico al listar médicos en DAO: ", e);
            throw new RuntimeException("Error en base de datos al listar: " + e.getMessage());
        }
    }
    
    //--------------------------------------------------
    @Override
    public List<MedicoDTO> medicoEntidad(int idEntidad) {
        try {
            SqlParameterSource param = new MapSqlParameterSource().addValue("id_entidad", idEntidad);
            Map<String, Object> out = callMedicoEntidad.execute(param);
            return (List<MedicoDTO>) out.getOrDefault("cur", Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<MedicoDTO> medicoEspecialidad(int idEspecialidad) {
        try {
            SqlParameterSource param = new MapSqlParameterSource().addValue("idEspecialidad", idEspecialidad);
            Map<String, Object> out = callMedicoEspecialidad.execute(param);
            return (List<MedicoDTO>) out.getOrDefault("cur", Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public int medicoXUsuarioLeer(String usuario) {
        try {
            SqlParameterSource param = new MapSqlParameterSource().addValue("p_usuario", usuario);
            Map<String, Object> out = callMedicoXUsuario.execute(param);
            return (out.get("o_idmedico") != null) ? (int) out.get("o_idmedico") : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int tiempoPromedioAtencion_leer(int idMedico, int idEspecialidad) {
        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("p_idmedico", idMedico)
                    .addValue("p_idespecialidad", idEspecialidad);
            Map<String, Object> out = callTiempoPromedio.execute(param);
            return (out.get("o_tiempopromedioatencion") != null) ? (int) out.get("o_tiempopromedioatencion") : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public MedicoResponse medicoXIdMedicosXEspecialidad(int idEspecialidad, int idMedico) {
        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("p_idespecialidad", idEspecialidad)
                    .addValue("p_idmedico", idMedico);
            Map<String, Object> out = callMedicoXIdEsp.execute(param);
            
            List<MedicoDTO> res = (List<MedicoDTO>) out.getOrDefault("cur", new ArrayList<MedicoDTO>());
            MedicoResponse response = new MedicoResponse();
            response.setMedico(res);
            return response;
        } catch (Exception e) {
            MedicoResponse emptyRes = new MedicoResponse();
            emptyRes.setMedico(Collections.emptyList());
            return emptyRes;
        }
    }
}


