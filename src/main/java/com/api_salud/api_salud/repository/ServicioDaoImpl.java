package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.ServicioEntity;
import com.api_salud.api_salud.response.ServicioResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ServicioDaoImpl implements ServicioDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallServicio;  


	@Override
	public ServicioResponse xIdEntidadEspecialidad(int idEntidadEspecialidad) {
		System.out.println("ID ENTIDAD ESPCILIDAD "+idEntidadEspecialidad);
		ServicioResponse  response = null;
	    List<ServicioEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallServicio = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.servicios_xespecialidades_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters(
				            		new SqlParameter("p_identidadespecialidad", Types.INTEGER ),
				            		new SqlOutParameter("cur", Types.OTHER )
				            		);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_identidadespecialidad", idEntidadEspecialidad)  ; 
	    Map<String, Object> out = simpleJdbcCallServicio.execute(param);
		System.out.println("OUT "+out);
	    if (out == null) {
	   	   response =null;
	    } else {
	        List<Object>  list = (List<Object>) out.get("cur") ;
	        ServicioEntity servicio;
	    	   for (Object row : list) {
	    	   	  ObjectMapper objectMapper = new ObjectMapper() ;
	    		  servicio = objectMapper.convertValue(row, ServicioEntity.class) ;
	    	      res.add(servicio);
	    	   }
	    	   response = new ServicioResponse();
	    	   response.setServicio(res);
	 	}
		return response;   		
	}
	
	@Override
	public ServicioResponse xIdEntidad(int idEntidad) {
		System.out.println("ID ENTIDAD ESPCILIDAD "+idEntidad);
		ServicioResponse  response = null;
	    List<ServicioEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallServicio = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.servicios_xentidad_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters(
				            		new SqlParameter("p_identidad", Types.INTEGER ),
				            		new SqlOutParameter("cur", Types.OTHER )
				            		);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_identidad", idEntidad)  ; 
	    Map<String, Object> out = simpleJdbcCallServicio.execute(param);
		System.out.println("OUT "+out);
	    if (out == null) {
	   	   response =null;
	    } else {
	        List<Object>  list = (List<Object>) out.get("cur") ;
	        ServicioEntity servicio;
	    	   for (Object row : list) {
	    	   	  ObjectMapper objectMapper = new ObjectMapper() ;
	    		  servicio = objectMapper.convertValue(row, ServicioEntity.class) ;
	    	      res.add(servicio);
	    			System.out.println("Lista servicios"+list);
	    	   }
	    	   response = new ServicioResponse();
	    	   response.setServicio(res);
	 	}
		return response;   		
	}
	
}

