package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.TurnoDetalleEntity;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional
public class TurnoDetalleDaoImpl  implements TurnoDetalleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallTurnoDetalle;  

	@Override
	public List<TurnoDetalleEntity> xIdTurnoLeer(int idTurno) {
	    List<TurnoDetalleEntity> response =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallTurnoDetalle = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.turnosdetalle_x_idtunro_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            			new SqlParameter("p_idturno", Types.INTEGER ), 
				            			new SqlOutParameter("cur", Types.OTHER )
				            );
	    SqlParameterSource param = new MapSqlParameterSource()
	    		 .addValue("p_idturno",idTurno);
		Map<String, Object> out = simpleJdbcCallTurnoDetalle.execute(param);
	    List<Object>  list = (List<Object>) out.get("cur") ;
       if (list.isEmpty()) {
	   	   response =null;
	    } else {
	    	   for (Object row : list) {
	        	   TurnoDetalleEntity turnoDetalle = new TurnoDetalleEntity();
	    	   	  ObjectMapper objectMapper = new ObjectMapper() ;
	    		  turnoDetalle = objectMapper.convertValue(row, TurnoDetalleEntity.class) ;
	    	      response.add(turnoDetalle);
	    	   }
	 	}
		return response;   		
	}


}


