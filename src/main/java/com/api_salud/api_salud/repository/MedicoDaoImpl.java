package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

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
import com.api_salud.api_salud.response.MedicoResponse;

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
    }

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


