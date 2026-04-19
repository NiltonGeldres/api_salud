package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.dto.EntidadDto;
import com.api_salud.api_salud.entity.EntidadEntity;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.EntidadesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional
public class EntidadDaoImpl implements EntidadDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallEntidad;  

	
	
	@Override
	public List<EntidadDto> obtenerEntidadesPorNombre(String nombre) {
	    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withSchemaName("igm_maestros")
	            .withProcedureName("entidades_obtener_por_nombre")
	            .withoutProcedureColumnMetaDataAccess()
	            .declareParameters(
	                new SqlParameter("p_nombre", Types.VARCHAR),
	                new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(EntidadDto.class))
	            );

	    SqlParameterSource in = new MapSqlParameterSource()
	            .addValue("p_nombre", nombre)
	            .addValue("cur", "cur_entidades"); 
	    try {
	        Map<String, Object> out = call.execute(in);
	        return (List<EntidadDto>) out.get("cur");
	        
	    } catch (Exception e) {
	        System.err.println("Error en entidades_obtener_por_nombre: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}
	
	@Override
	public EntidadesResponse xIdMedico(int idMedico) {
		EntidadesResponse  response = null;
	    List<EntidadEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallEntidad = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.entidades_xmedico_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters(
				            		new SqlParameter("p_idmedico", Types.INTEGER ),
				            		new SqlOutParameter("cur", Types.OTHER )
				            		);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idmedico", idMedico)  ; 
	    
			Map<String, Object> out = simpleJdbcCallEntidad.execute(param);
		    if (out == null) {
	   	   response =null;
	    } else {
	        List<Object>  list = (List<Object>) out.get("cur") ;
	    	   EntidadEntity entidad;
	    	   for (Object row : list) {
	    	   	  ObjectMapper objectMapper = new ObjectMapper() ;
	    		  entidad = objectMapper.convertValue(row, EntidadEntity.class) ;
	    	      res.add(entidad);
	    	   }
	    	   response = new EntidadesResponse();
	    	   response.setEntidad(res);
	 	}
		return response;   		
	}
	
	public Optional<EntidadResponse> buscarPorId(Long idEntidad) {
	    jdbcTemplate.setResultsMapCaseInsensitive(true);

//	    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
	  	    simpleJdbcCallEntidad = new SimpleJdbcCall(jdbcTemplate)
	        .withProcedureName("igm_maestros.entidades_x_id_entidad_leer")
	        .withoutProcedureColumnMetaDataAccess()
	        .declareParameters(
	            new SqlParameter("p_identidad", Types.INTEGER),
	            new SqlOutParameter("cur", Types.OTHER)
	        );

	    SqlParameterSource param = new MapSqlParameterSource()
	        .addValue("p_identidad", idEntidad);

	    Map<String, Object> out = simpleJdbcCallEntidad.execute(param);

	    if (out != null && out.get("cur") != null) {

	        List<Map<String, Object>> list = (List<Map<String, Object>>) out.get("cur");

	        if (!list.isEmpty()) {
	            Map<String, Object> row = list.get(0);

	            EntidadResponse entidad = new EntidadResponse();
	            entidad.setIdEntidad(((Number) row.get("idEntidad")).longValue());
	            entidad.setNombre((String) row.get("nombre"));
	            entidad.setDireccion((String) row.get("direccion"));
	            entidad.setNombreDistrito(((String) row.get("nombreDistrito")));

	            return Optional.of(entidad);
	        }
	    }

	    return Optional.empty();
	}
}



/*
 * 
    EntidadResponse response = null;
    List<EntidadEntity> res = new ArrayList<>();

    jdbcTemplate.setResultsMapCaseInsensitive(true);

    simpleJdbcCallEntidad = new SimpleJdbcCall(jdbcTemplate)
        .withProcedureName("igm_maestros.entidades_x_id_entidad_leer")
        .withoutProcedureColumnMetaDataAccess()
        .declareParameters(
            new SqlParameter("p_identidad", Types.INTEGER),
            new SqlOutParameter("cur", Types.OTHER)
        );

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("p_identidad", idEntidad);

    Map<String, Object> out = simpleJdbcCallEntidad.execute(param);

    if (out != null) {
        List<Object> list = (List<Object>) out.get("cur");

        for (Object row : list) {
            ObjectMapper objectMapper = new ObjectMapper();
            EntidadEntity entidad = objectMapper.convertValue(row, EntidadEntity.class);
            res.add(entidad);
        }

        response = new EntidadResponse();
        response.setEntidad(res);
    }

    return response;

 * */
