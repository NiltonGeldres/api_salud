package com.api_salud.api_salud.repository;

import java.sql.Types;
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

import com.api_salud.api_salud.entity.EspecialidadEntity;
import com.api_salud.api_salud.response.EspecialidadResponse;

@Repository
@Transactional
public class EspecialidadDaoImpl implements EspecialidadDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Objetos pre-configurados para evitar "Metadata Lookup" en cada llamada
    private SimpleJdbcCall callXIdEntidad;
    private SimpleJdbcCall callWeb;
    private SimpleJdbcCall callXIdMedico;

    @PostConstruct
    public void init() {
        // Configuramos la sensibilidad a mayúsculas una sola vez
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        this.callXIdEntidad = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("igm_maestros.especialidad_por_identidad_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_identidad", Types.INTEGER),
                        new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(EspecialidadEntity.class))
                );

        this.callWeb = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("igm_maestros.especialidades_web_todos_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(EspecialidadEntity.class))
                );

        this.callXIdMedico = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("igm_maestros.especialidad_xmedico_leer")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_idmedico", Types.INTEGER),
                        new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(EspecialidadEntity.class))
                );
    }

    @Override
    public EspecialidadResponse xIdEntidad(Long idEntidad) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("p_identidad", idEntidad);
        return ejecutarConsulta(callXIdEntidad, param);
    }

    @Override
    public EspecialidadResponse web() {
        return ejecutarConsulta(callWeb, null);
    }

    @Override
    public EspecialidadResponse xIdMedico(int idMedico) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("p_idmedico", idMedico);
        return ejecutarConsulta(callXIdMedico, param);
    }

    /**
     * Método privado para centralizar la ejecución y evitar repetición de código.
     * El uso de BeanPropertyRowMapper hace que la conversión sea directa y rápida.
     */
    private EspecialidadResponse ejecutarConsulta(SimpleJdbcCall call, SqlParameterSource param) {
        try {
            Map<String, Object> out = (param != null) ? call.execute(param) : call.execute();
            
            if (out != null && out.get("cur") != null) {
                List<EspecialidadEntity> lista = (List<EspecialidadEntity>) out.get("cur");
                EspecialidadResponse response = new EspecialidadResponse();
                response.setEspecialidad(lista);
                return response;
            }
        } catch (Exception e) {
            System.err.println("Error ejecutando SP en EspecialidadDao: " + e.getMessage());
        }
        
        // Devolvemos una respuesta vacía en lugar de null para evitar NullPointerExceptions en el front
        EspecialidadResponse emptyResponse = new EspecialidadResponse();
        emptyResponse.setEspecialidad(Collections.emptyList());
        return emptyResponse;
    }
}



/*
package com.api_salud.api_salud.repository;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import javax.sql.DataSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.query.Procedure;
//import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api_salud.api_salud.entity.EspecialidadEntity;
import com.api_salud.api_salud.response.EspecialidadResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Transactional
public class EspecialidadDaoImpl  implements EspecialidadDao{

//	@Autowired
//	private DataSource  dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcCall simpleJdbcCall;  

	
	@Override
	public EspecialidadResponse xIdEntidad(Long idEntidad) {
		EspecialidadResponse response = null;
	    List<EspecialidadEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.especialidad_por_identidad_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters(
				            		new SqlParameter("p_identidad", Types.INTEGER ),
				            		new SqlOutParameter("cur", Types.OTHER )
				            		);
		SqlParameterSource param = new MapSqlParameterSource()
		.addValue("p_identidad", idEntidad)  ; 
   		Map<String, Object> out = simpleJdbcCall.execute(param);
 	    if (out == null) {
       	   response =null;
        } else {
            List<Object>  list = (List<Object>) out.get("cur") ;
        	   EspecialidadEntity especialidad;
        	   for (Object row : list) {
        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
        		  especialidad = objectMapper.convertValue(row, EspecialidadEntity.class) ;
        	      res.add(especialidad);
        	   }
        	   response = new EspecialidadResponse();
        	   response.setEspecialidad(res);
     	}
		return response;   		
	}

	
	@Override
	public EspecialidadResponse web() {
		EspecialidadResponse response = null;
	    List<EspecialidadEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.especialidades_web_todos_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters(new SqlOutParameter("cur", Types.OTHER ));
   		Map<String, Object> out = simpleJdbcCall.execute();
 	    if (out == null) {
       	   response =null;
        } else {
            List<Object>  list = (List<Object>) out.get("cur") ;
        	   EspecialidadEntity especialidad;
        	   for (Object row : list) {
        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
        		  especialidad = objectMapper.convertValue(row, EspecialidadEntity.class) ;
        	      res.add(especialidad);
        	   }
        	   response = new EspecialidadResponse();
        	   response.setEspecialidad(res);
     	}
		return response;   		
	}

	
	@Override
	public EspecialidadResponse xIdMedico(int idMedico) {
		EspecialidadResponse response = null;
	    List<EspecialidadEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.especialidad_xmedico_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters(
				            		new SqlParameter("p_idmedico", Types.INTEGER ),
				            		new SqlOutParameter("cur", Types.OTHER )
				            		);
		SqlParameterSource param = new MapSqlParameterSource()
		.addValue("p_idmedico", idMedico)  ; 
   		Map<String, Object> out = simpleJdbcCall.execute(param);
 	    if (out == null) {
       	   response =null;
        } else {
            List<Object>  list = (List<Object>) out.get("cur") ;
        	   EspecialidadEntity especialidad;
        	   for (Object row : list) {
        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
        		  especialidad = objectMapper.convertValue(row, EspecialidadEntity.class) ;
        	      res.add(especialidad);
        	   }
        	   response = new EspecialidadResponse();
        	   response.setEspecialidad(res);
     	}
		return response;   		
	}

	
}
*/