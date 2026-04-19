package com.api_salud.api_salud.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.api_salud.api_salud.entity.CitaBloqueadaEntity;

import com.api_salud.api_salud.response.CitaBloqueadaResponse;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.FacturacionOrdenServicioResponse;
import com.api_salud.api_salud.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional
public class CitaBloqueadaDaoImpl  implements CitaBloqueadaDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;  	
	private SimpleJdbcCall  simpleJdbcCallCitaBloqueada;
	UsuarioService usuario1Service;		

	@Override
	public CitaBloqueadaResponse crearCitaBloqueada(
 		    int idcitabloqueada,
		    int idusuario,
		    String  fecha,
		    String horainicio ,
		    String horafin ,
		    String fechabloqueo ,
		    String horabloqueo ,
		    int idmedico,
		    String  tipousuario, 
		    String  usuario, 
		    int  idEntidad 
			) {

        CitaBloqueadaResponse response = null;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallCitaBloqueada = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_citas.citas_bloqueadas_crear")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters( 
				new SqlParameter("identidad", Types.INTEGER)
				,new SqlParameter("idusuario", Types.INTEGER)
				,new SqlParameter("fecha", Types.VARCHAR)
				,new SqlParameter("horainicio", Types.VARCHAR)
				,new SqlParameter("horafin", Types.VARCHAR)
				,new SqlParameter("fechabloqueo", Types.VARCHAR)
				,new SqlParameter("horabloqueo", Types.VARCHAR)
				,new SqlParameter("idmedico", Types.INTEGER)
				,new SqlParameter("tipousuario", Types.VARCHAR)
				,new SqlOutParameter("idcitabloqueada", Types.INTEGER)
				);
	    
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("identidad", idEntidad)
				.addValue("idusuario", idusuario)
				.addValue("fecha", fecha)
				.addValue("horainicio", horainicio)
				.addValue("horafin", horafin)
				.addValue("fechabloqueo", fechabloqueo)
				.addValue("horabloqueo", horabloqueo)
				.addValue("idmedico", idmedico)
				.addValue("tipousuario",tipousuario)
				.addValue("idcitabloqueada", idcitabloqueada);

			Map<String, Object> out = simpleJdbcCallCitaBloqueada.execute(param);
			
 	    if (out != null)  {
	   		int id = (int) out.get("idcitabloqueada");		   		
	   		response = new CitaBloqueadaResponse();
	   		response.setIdCitaBloqueada(id);
     	}
		return response;   
	}
	
	@Override
	public int  eliminarCitaBloqueada(int idCitabloqueada) {
        int response =0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallCitaBloqueada = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_citas.citas_bloqueadas_eliminar")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters( 
				new SqlParameter("idCitaBloqueada", Types.INTEGER)
				,new SqlOutParameter("o_idcitabloqueada", Types.INTEGER)
				);
	    
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("idcitabloqueada", idCitabloqueada);

			Map<String, Object> out = simpleJdbcCallCitaBloqueada.execute(param);
			
 	    if (out != null)  {
	 	    if (out.get("o_idcitabloqueada") != null)  {
		   		int id = (int) out.get("o_idcitabloqueada");		   		
		   		response = id;
	     	}
     	}
		return response;   
	}

	@Override
	public int  eliminarCitaBloqueadaXUsuario(int idusuario) {
		int response =0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallCitaBloqueada = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_citas.citas_bloqueadas_xusuario_eliminar")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters( 
				new SqlParameter("i_idusuario", Types.INTEGER)
				);
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("i_idusuario", idusuario);
			Map<String, Object> out = simpleJdbcCallCitaBloqueada.execute(param);
	 	    if (out == null)  {
	     	}
			return response;    
	}

	// Lista todas las citas bloqueadas 
	@Override
	public List<CitaBloqueadaEntity> leerCitaBloqueada(int idMedico, String fecha) {
		List<CitaBloqueadaEntity> response = null;
	    List<CitaBloqueadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaBloqueada = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_bloqueadas_xmedico_fecha_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_idmedico", Types.INTEGER)
				            					,new SqlParameter("p_fecha", Types.VARCHAR)
				            					,new SqlOutParameter("o_cur", Types.OTHER )				            					
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idmedico", idMedico)	    
				.addValue("p_fecha", fecha);    
   		Map<String, Object> out = simpleJdbcCallCitaBloqueada.execute(param);
 	    if (out == null) { response =null;
        } else {
            List<Object>  list = (List<Object>) out.get("o_cur") ;
        	   for (Object row : list) {
             	  CitaBloqueadaEntity cita;
        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
        		  cita = objectMapper.convertValue(row, CitaBloqueadaEntity.class) ;
        	      res.add(cita);
        	   }
     	}		
	 	   for (CitaBloqueadaEntity row : res) {
		  	   }	  
 	    
		return res;
	}

	
	

}
