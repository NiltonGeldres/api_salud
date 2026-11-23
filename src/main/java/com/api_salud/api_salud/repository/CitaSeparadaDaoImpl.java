package com.api_salud.api_salud.repository;


import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional
public class CitaSeparadaDaoImpl  implements CitaSeparadaDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;  	
	private SimpleJdbcCall  simpleJdbcCallCitaSeparada;
	private SimpleJdbcCall callCrearYLeerJson;

    @Override
    public CitaSeparadaEntityResponse crearCitaSeparada(CitaSeparadaRequest request, int idUsuario) {
    	System.out.println("REQUEST "+
				request.getIdEntidad() 
				+"-- "+request.getFecha()
				+"-- "+request.getHoraInicio()
				+"-- "+request.getHoraFin()
				+"-- "+request.getIdPaciente()
				+"-- "+request.getIdMedico()
				+"-- "+request.getIdEspecialidad()
				+"-- "+request.getIdServicio()
				+"-- "+request.getIdProgramacion()
				+"-- "+request.getIdProducto()
				+"-- "+request.getFechaSolicitud()
				+"-- "+request.getHoraSolicitud()
				+"-- "+request.getTipoUsuario()
				+"-- "+request.getFechaSeparacion()
				+"-- "+request.getPrecioUnitario()
				+"-- "+idUsuario
				+"-- "+request.getIdUsuarioPaciente()
		);

    	// Se agregan casts explícitos a cada parámetro y ::text al final para el jsonb
    	
        String sql = "SELECT igm_citas.f_citas_separadas_crear_y_leer_json(" +
                     "?::integer, " +  // p_id_entidad
                     "?::varchar, " +  // p_fecha
                     "?::varchar, " +  // p_horainicio
                     "?::varchar, " +  // p_horafin
                     "?::integer, " +  // p_idpaciente
                     "?::integer, " +  // p_idmedico
                     "?::integer, " +  // p_idespecialidad
                     "?::integer, " +  // p_idservicio
                     "?::integer, " +  // p_idprogramacion
                     "?::integer, " +  // p_idproducto
                     "?::varchar, " +  // p_fechasolicitud
                     "?::varchar, " +  // p_horasolicitud
                     "?::varchar, " +  // p_tipousuario
                     "?::varchar, " +  // p_fechaseparacion
                     "?::numeric, " +  // p_preciounitario
                     "?::integer, " +  // p_idusuario
                     "?::integer" +   // p_idusuariopaciente
                     ")::text";
        
        try {
            String jsonResultado = jdbcTemplate.queryForObject(sql, String.class,
                  request.getIdEntidad()
                , request.getFecha()
                , request.getHoraInicio()
                , request.getHoraFin()
                , (request.getIdPaciente() != null && request.getIdPaciente() > 0) ? request.getIdPaciente() : null
                , request.getIdMedico()
                , request.getIdEspecialidad()
                , request.getIdServicio()
                , request.getIdProgramacion()
                , request.getIdProducto()
                , request.getFechaSolicitud()
                , request.getHoraSolicitud()
                , request.getTipoUsuario()
                , request.getFechaSeparacion()
                , request.getPrecioUnitario()
                , idUsuario
                , request.getIdUsuarioPaciente()
            );
            System.out.println("REQUEST "+jsonResultado);
            if (jsonResultado != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return objectMapper.readValue(jsonResultado, CitaSeparadaEntityResponse.class);
            }

            return null;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar o procesar la cita en Base de Datos: " + e.getMessage(), e);
        }
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

