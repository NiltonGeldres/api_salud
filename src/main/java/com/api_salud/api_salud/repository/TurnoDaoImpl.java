package com.api_salud.api_salud.repository;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import com.api_salud.api_salud.entity.TurnoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public class TurnoDaoImpl implements TurnoDao {

	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	private SimpleJdbcCall simpleJdbcCallTurno;  
	

	@Override
	public List<TurnoEntity> turnoLeer() {
		System.out.println("En Turno DAO ");
	    List<TurnoEntity> response =new ArrayList<>();
	    
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallTurno = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.turnos_todos_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters(new SqlOutParameter("cur", Types.OTHER ));
   		Map<String, Object> out = simpleJdbcCallTurno.execute();
        List<Object>  list = (List<Object>) out.get("cur") ;
 	    if (list.isEmpty()) {
       	   response =null;
        } else {
        	   for (Object row : list) {
            	   TurnoEntity turno = new TurnoEntity();
        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
        		  turno = objectMapper.convertValue(row, TurnoEntity.class) ;
        	      response.add(turno);
        	   }
     	}
		return response;   		
	}

	
	

}
