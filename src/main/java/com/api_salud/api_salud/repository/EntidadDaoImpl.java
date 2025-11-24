package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.EntidadEntity;
import com.api_salud.api_salud.response.EntidadResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional
public class EntidadDaoImpl implements EntidadDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallEntidad;  

	@Override
	public EntidadResponse xIdMedico(int idMedico) {
		EntidadResponse  response = null;
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
			System.out.println("OUT "+idMedico +"  "+out);
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
	    	   response = new EntidadResponse();
	    	   response.setEntidad(res);
	 	}
		return response;   		
	}
}


