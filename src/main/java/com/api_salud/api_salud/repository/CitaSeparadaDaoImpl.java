package com.api_salud.api_salud.repository;


import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional
public class CitaSeparadaDaoImpl  implements CitaSeparadaDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;  	
	private SimpleJdbcCall  simpleJdbcCallCitaSeparada;
	
	
	@Override
	public int  crearCitaSeparada(
			int idEntidad,
			int idCitaSeparada,
		    String fecha ,
		    String horaInicio ,
		    String horaFin ,
		    int idPaciente ,
		    int idMedico ,
		    int idEspecialidad ,
		    int idServicio ,
		    int idProgramacion ,
		    int idProducto ,
		    String fechaSolicitud,
		    String horaSolicitud,
		    String tipoUsuario,
		    String fechaSeparacion,
		    double precioUnitario,
		    int idUsuario		    
			) {
		
    	int response = 0;
    	List<CitaSeparadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
       SimpleJdbcCall simpleJdbcCallCitaSeparada = new SimpleJdbcCall(jdbcTemplate)		
				    		.withProcedureName("igm_citas.citas_separadas_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		 new SqlParameter("p_identidad",        Types.INTEGER)
				            		,new SqlParameter("p_fecha",      		Types.VARCHAR)
				            		,new SqlParameter("p_horainicio", 		Types.VARCHAR)
				            		,new SqlParameter("p_horafin",    		Types.VARCHAR)
				            		,new SqlParameter("p_idpaciente", 		Types.INTEGER)
				            		,new SqlParameter("p_idmedico",       	Types.INTEGER) 
				            		,new SqlParameter("p_idespecialidad", 	Types.INTEGER)
				            		,new SqlParameter("p_idservicio", 		Types.INTEGER)
				            		,new SqlParameter("p_idprogramacion", 	Types.INTEGER)
				            		,new SqlParameter("p_idproducto", 		Types.INTEGER)
				            		,new SqlParameter("p_fechasolicitud", 	Types.VARCHAR)
				            		,new SqlParameter("p_horasolicitud", 	Types.VARCHAR)
				            		,new SqlParameter("p_tipousuario", 		Types.VARCHAR)
				            		,new SqlParameter("p_fechaseparacion", 	Types.VARCHAR)	
				            		,new SqlParameter("p_preciounitario", 	Types.NUMERIC)	
				            		,new SqlParameter("p_idusuario", 		Types.INTEGER)	
				            		,new SqlOutParameter("o_idcitaseparada", Types.INTEGER )
	            					);
		int o_idcitaseparada=0;
	    SqlParameterSource param = new MapSqlParameterSource()
   				.addValue("p_identidad"			,idEntidad)
   				.addValue("p_fecha"				,fecha)
				.addValue("p_horainicio"		,horaInicio)
				.addValue("p_horafin" 			,horaFin)
				.addValue("p_idpaciente"		,idPaciente)
				.addValue("p_idmedico"			,idMedico)
				.addValue("p_idespecialidad"	,idEspecialidad)
				.addValue("p_idservicio"		,idServicio)
				.addValue("p_idprogramacion"	,idProgramacion)
				.addValue("p_idproducto"		,idProducto)
				.addValue("p_fechasolicitud"	,fechaSolicitud)
				.addValue("p_horasolicitud"		,horaSolicitud)
				.addValue("p_tipousuario"		,tipoUsuario)
				.addValue("p_fechaseparacion"	,fechaSeparacion)
				.addValue("p_preciounitario"	,precioUnitario)
				.addValue("p_idusuario"	,idUsuario);
	    		
	    		
   		Map<String, Object> out = simpleJdbcCallCitaSeparada.execute(param);
 	    if (out != null) {

	   	  response= (int) out.get("o_idcitaseparada");
        }
		return response;
	}
	
	
	@Override
	public CitaSeparadaResponse leerCitaSeparadaXIdpaciente(
			int  idUsuario
			) {
    	CitaSeparadaResponse response = null;
    	List<CitaSeparadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaSeparada = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_separadas_xusuario_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		 new SqlParameter("p_idusuario", Types.INTEGER) 
				            		,new SqlOutParameter("o_citasseparadas", Types.OTHER) 
	            					);

	    SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idusuario"	,idUsuario);
	    		
   		Map<String, Object> out = simpleJdbcCallCitaSeparada.execute(param);
 	    if (out != null) {
 			List<Object>  list =(List<Object>) out.get("o_citasseparadas") ;
 			
 			for (Object row : list) {
	 			CitaSeparadaEntity cita = new CitaSeparadaEntity();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			cita = objectMapper.convertValue(row, CitaSeparadaEntity.class) ;
	 			res.add(cita);
 			}
 			response = new CitaSeparadaResponse();
 			response.setCitaSeparada(res);
        }
 	    
		return response;
	}
	
	@Override
	public CitaSeparadaEntityResponse leerCitaSeparadaXIdCitaSeparada(
			int  idCitaSeparada
			) {
		CitaSeparadaEntityResponse response = null;
    	List<CitaSeparadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaSeparada = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_separadas_x_idcitaceparada_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		 new SqlParameter("p_idcitaseparada", Types.INTEGER) 
				            		,new SqlOutParameter("o_citasseparadas", Types.OTHER) 
	            					);

	    SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idcitaseparada"	,idCitaSeparada);
   		Map<String, Object> out = simpleJdbcCallCitaSeparada.execute(param);
 	    if (out != null) {
 			List<Object> list  = (List<Object>) out.get("o_citasseparadas") ;
 			for (Object row : list) {
	 			CitaSeparadaEntityResponse cita = new CitaSeparadaEntityResponse();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			cita = objectMapper.convertValue(row, CitaSeparadaEntityResponse.class) ;
	 			response=cita;
 			}	
 			
        }
		return response;
	}
	
	@Override
	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualXIdpaciente(
			int  idUsuario
			) {
    	CitaSeparadaResponse response = null;
    	List<CitaSeparadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaSeparada = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_separadas_conpagovirtual_xusuario_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		 new SqlParameter("p_idusuario", Types.INTEGER) 
				            		,new SqlOutParameter("o_citasseparadas", Types.OTHER) 
	            					);

	    SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idusuario"	,idUsuario);
	    		
   		Map<String, Object> out = simpleJdbcCallCitaSeparada.execute(param);
 	    if (out != null) {
 			List<Object>  list =(List<Object>) out.get("o_citasseparadas") ;
 			
 			for (Object row : list) {
	 			CitaSeparadaEntity cita = new CitaSeparadaEntity();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			cita = objectMapper.convertValue(row, CitaSeparadaEntity.class) ;
	 			res.add(cita);
 			}
 			response = new CitaSeparadaResponse();
 			response.setCitaSeparada(res);
        }
		return response;
	}
	
	@Override
	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualConcomprobanteXIdpaciente(
			int  idUsuario
			) {
    	CitaSeparadaResponse response = null;
    	List<CitaSeparadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaSeparada = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_separadas_conpagovirtual_concomprobante_xusuario_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		 new SqlParameter("p_idusuario", Types.INTEGER) 
				            		,new SqlOutParameter("o_citasseparadas", Types.OTHER) 
	            					);

	    SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idusuario"	,idUsuario);
	    		
   		Map<String, Object> out = simpleJdbcCallCitaSeparada.execute(param);
 	    if (out != null) {
 			List<Object>  list =(List<Object>) out.get("o_citasseparadas") ;
 			
 			for (Object row : list) {
	 			CitaSeparadaEntity cita = new CitaSeparadaEntity();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			cita = objectMapper.convertValue(row, CitaSeparadaEntity.class) ;
	 			res.add(cita);
 			}
 			response = new CitaSeparadaResponse();
 			response.setCitaSeparada(res);
        }
 	    
		return response;
	}

	@Override
	public void  actualizaIdCita(int idCitaSeparada, int idCita) {
    	CitaSeparadaResponse response = null;
    	List<CitaSeparadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaSeparada = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_separadas_idcita_actualizar")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		 new SqlParameter("p_idcitaseparada", Types.INTEGER) 
				            		,new SqlParameter("p_idcita", Types.INTEGER) 
	            					);

			try {	
			    SqlParameterSource param = new MapSqlParameterSource()
						.addValue("p_idcitaseparada"	,idCitaSeparada)
			    		.addValue("p_idcita"	 		,idCita);
			    
			    		
		   		Map<String, Object> out = simpleJdbcCallCitaSeparada.execute(param);
		   		
		    } catch (Exception e) {
		        e.printStackTrace(); // Manejo de errores, puedes personalizar según tus necesidades
		    }
 	    
	}
	
}


