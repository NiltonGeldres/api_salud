package com.api_salud.api_salud.repository;

import java.sql.Types;
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

@Repository
@Transactional
public class EmpleadoDaoImpl implements EmpleadoDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall simpleJdbcCallEmpleado;
	
	@Override
	public int xIdMedico(int idMedico) {
			int response ;
			jdbcTemplate.setResultsMapCaseInsensitive(true);
		    simpleJdbcCallEmpleado = new SimpleJdbcCall(jdbcTemplate)
					    		.withProcedureName("igm_maestros.empleado_xidmedico_leer")
					            .withoutProcedureColumnMetaDataAccess()
					            .declareParameters( 
					            					new SqlParameter("p_idmedico", Types.INTEGER),
					            				    new SqlOutParameter("o_id_entidad", Types.INTEGER)				            					
					            					);
		    SqlParameterSource param = new MapSqlParameterSource()
		    		.addValue("p_idmedico", idMedico);	    
	   		Map<String, Object> out = simpleJdbcCallEmpleado.execute(param);
   
	 	    if (out != null && out.get("o_id_entidad")!= null)  {
		   		return  (int) out.get("o_id_entidad");		   		
	     	}
	 	    return 0;
		}
		
	

}

