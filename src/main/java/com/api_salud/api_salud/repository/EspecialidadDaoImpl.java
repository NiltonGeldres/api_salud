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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
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
    	System.out.println("Ingreso xIdMedico  "+ idMedico);
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
    	System.out.println("OUT  "+out);
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

/*
public static <T> T toBean(Map<String, Object> beanPropMap, Class<T> type) {
    try {
        T beanInstance = type.getConstructor().newInstance();
        for (Object k : beanPropMap.keySet()) {
            String key = (String) k;
            Object value = beanPropMap.get(k);
            if (value != null) {
                try {
                    java.lang.reflect.Field field = type.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(beanInstance, value);
                    field.setAccessible(false);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
        return beanInstance;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
*/	


/*
 
 		jdbcTemplate = new JdbcTemplate(dataSource);	
		System.out.println(jdbcTemplate);		
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.especialidades_web_todos_leer")
//				            .returningResultSet("esp",BeanPropertyRowMapper.newInstance(EspecialidadEntity.class));
				            .withoutProcedureColumnMetaDataAccess().declareParameters(new SqlOutParameter("cur", Types.OTHER));
//				    		.withoutProcedureColumnMetaDataAccess().declareParameters(new SqlOutParameter("esp", BeanPropertyRowMapper.newInstance(EspecialidadEntity.class));
   		System.out.println("****ANTES DE EXECUTE");
//   		Map<String, Object> out = simpleJdbcCall.execute();
 		Map<String, Object> out = simpleJdbcCall.execute();
  		System.out.println("****DESPUES DE EXECUTE");
  	//	listEspecialidad = new ArrayList<>();
	    	       if (out == null) {
	    	     	   System.out.println("****VACIO");
	    	     	   return response;
//	    	            return (EspecialidadResponse) Collections.emptyList();
	    	        } else {

		    	     		List<EspecialidadEntity> listEspecialidad = new ArrayList<>(); 
		    	     //		listEspecialidad = (List<EspecialidadEntity>) out.entrySet();
		    	     		
		    	     		for (Entry<String, Object> entry : out.entrySet()) {
		    	     		    System.out.println("Lista 1 "+entry.getKey() + " : " + entry.getValue().toString());
		    	                System.out.println("Lista 2 "+entry.getValue());
	    	     		
		    	     		}		    	     		
	
	    	                System.out.println("OUT GET  "+out.get("cur"));
	
		    	        	
	
		    	        	
	    	        	
		    	            
		    	     //		listEspecialidad = toBean(out, EspecialidadEntity.class);
		    	     		listEspecialidad.add(x);
			    	        response = new EspecialidadResponse();
			    	        response.setEspecialidad(listEspecialidad);
	    	        

 * */



/*	    	        	
	for(Entry<String, Object>  entrada: out.entrySet()) {
        System.out.println("Lista completa getValue  "+entrada.getValue());
         listEspecialidad =   (List<EspecialidadEntity>) entrada.getValue();
            System.out.println("Item 1 "  +listEspecialidad.get(1).getDescripcionEspecialidad());
	};	    	        	
	response = new EspecialidadResponse();
	response.setEspecialidad(listEspecialidad);
	
		    	        	
    	
    	
    	for(Map.Entry<String,EspecialidadEntity> entrada: out.entrySet()) {
            System.out.println("Lista completa getValue  "+entrada.getValue());
             listEspecialidad =   entrada.getValue();
                System.out.println("Item 1 "  +listEspecialidad.get(1).getDescripcionEspecialidad());
    	};	    	        	
    	response = new EspecialidadResponse();
    	response.setEspecialidad(listEspecialidad);
/*	    	        	
		listEspecialidad  = new ArrayList<>();
    	out.forEach((key, value) -> {
    		EspecialidadEntity e = new EspecialidadEntity();
//    		e.setDescripcionEspecialidad(value);
//    		e.setIdEspecialidad(Integer.parseInt(key));
    	    System.out.println("Key : " + key + " Value : " + value);
    	    System.out.println("Key : " + key );
    	    System.out.println(" Value : " + value.idespecialidad);
//    	    System.out.println("Especialidad "+ e.getIdEspecialidad());
    		//listEspecialidad.add(e);  
    	});
*/	    	        	
    	
//        System.out.println("Value"+  entry.getValue());
        
//  		List<EspecialidadEntity> listEspecialidad = (List<EspecialidadEntity>) out;
//  		response.setEspecialidad(listEspecialidad);
//  	   return response;
        //return (EspecialidadResponse) out.get("cur");
//    }    		

//}
	
	
//    MapSqlParameterSource params = new MapSqlParameterSource("cur", null);				    	    
		    		
		    		
		    		
//			            .returningResultSet("cur",BeanPropertyRowMapper
//		            						.newInstance(EspecialidadEntity.class));


//		SqlParameterSource parameter = new MapSqlParameterSource();
//        new SqlOutParameter("cur", Types.OTHER)	