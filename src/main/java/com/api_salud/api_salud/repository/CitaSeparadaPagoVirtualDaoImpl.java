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
import com.api_salud.api_salud.entity.CitaSeparadaPagadaEntity;
import com.api_salud.api_salud.entity.CitaSeparadaPagoVirtualEntity;
import com.api_salud.api_salud.response.CitaSeparadaPagoVirtualResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Transactional
public class CitaSeparadaPagoVirtualDaoImpl implements  CitaSeparadaPagoVirtualDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallCitaSeparadaPagoVirtual;
	
	public CitaSeparadaPagoVirtualResponse crearCitaSeparadaPagoVirtual(
		    int idCitaSeparadaPagoVirtual ,
		    int idComprobantePago,
		    int idCitaSeparada ,
		    String fecha ,
		    String nroOperacion, 
		    String correo ,
		    String celular ,
		    Double monto ,
		    int idTipoOperacion ,
		    String origen ,
		    String destino ,
		    String entidadDestino ,
		    int idUsuario
		    
			) {
		
    	CitaSeparadaPagoVirtualResponse response = null;
    	List<CitaSeparadaPagoVirtualEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaSeparadaPagoVirtual = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_separadas_pago_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		 new SqlOutParameter("o_idcitaseparadapagovirtual", Types.INTEGER )
				            		,new SqlParameter("p_idcomprobantepago", 			Types.INTEGER)
				            		,new SqlParameter("p_idcitaseparada",    			Types.INTEGER)
				            		,new SqlParameter("p_fecha", 						Types.VARCHAR)
				            		,new SqlParameter("p_nrooperacion",       			Types.VARCHAR) 
				            		,new SqlParameter("p_correo", 						Types.VARCHAR)
				            		,new SqlParameter("p_celular", 						Types.VARCHAR)
				            		,new SqlParameter("p_monto", 						Types.NUMERIC)
				            		,new SqlParameter("p_idtipooperacion", 				Types.INTEGER)
				            		,new SqlParameter("p_origen", 						Types.VARCHAR)
				            		,new SqlParameter("p_destino", 						Types.VARCHAR)
				            		,new SqlParameter("p_entidaddestino", 				Types.VARCHAR)
				            		,new SqlParameter("p_idusuario", 					Types.INTEGER)	
	            					);
		int o_idcitaseparadapagovirtual = 0;
	    SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idcomprobantepago"			,idComprobantePago)
				.addValue("p_idcitaseparada" 			,idCitaSeparada)
				.addValue("p_fecha"						,fecha)
				.addValue("p_nrooperacion"				,nroOperacion)
				.addValue("p_correo"					,correo)
				.addValue("p_celular"					,celular)
				.addValue("p_monto"						,monto)
				.addValue("p_idtipooperacion"			,idTipoOperacion)
				.addValue("p_origen"					,origen)
				.addValue("p_destino"					,destino)
				.addValue("p_entidaddestino"			,entidadDestino)
				.addValue("p_idusuario"					,idUsuario);
	    		
		System.out.println(" En CitaSeparadaPagoVirtualResponse Paso sqlparameter" );
   		Map<String, Object> out = simpleJdbcCallCitaSeparadaPagoVirtual.execute(param);
     	  System.out.println("OUT "+out );
   		
 	    if (out != null) {
 	      response= new CitaSeparadaPagoVirtualResponse();
	   	  o_idcitaseparadapagovirtual = (int) out.get("o_idcitaseparadapagovirtual");
 		  CitaSeparadaPagoVirtualEntity cita = new CitaSeparadaPagoVirtualEntity();
       	  cita.setIdCitaSeparadaPagoVirtual(o_idcitaseparadapagovirtual);  
       	  System.out.println(" ID  CitaSeparadaPagoVirtualResponse "+ o_idcitaseparadapagovirtual );
  	      res.add(cita);
          response.setCitaSeparadaPagoVirtual(res);
        }
		return response;
	}

	@Override
	public List<CitaSeparadaPagadaEntity> leerCitaSeparadaPagadaXMedico(int idMedico) {
		
    	List<CitaSeparadaPagadaEntity> response = null;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCitaSeparadaPagoVirtual = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_separadas_pagadas_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		new SqlParameter("p_idmedico",	Types.INTEGER)
				            		, new SqlOutParameter("o_citas", Types.OTHER )
	            					);
		
	    SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idmedico"					,idMedico);
	    		
   		Map<String, Object> out = simpleJdbcCallCitaSeparadaPagoVirtual.execute(param);
 	    if (out != null)  {
 	    	response = new ArrayList<>(); 	    	
 	            List<Object>  list = (List<Object>) out.get("o_citas") ;
        	   for (Object row : list) {
        		   CitaSeparadaPagadaEntity citaSeparadaPagadaEntity;
        	   	 ObjectMapper objectMapper = new ObjectMapper() ;
        	   	 citaSeparadaPagadaEntity = objectMapper.convertValue(row, CitaSeparadaPagadaEntity.class) ;
        	     response.add(citaSeparadaPagadaEntity);
        	     System.out.println(citaSeparadaPagadaEntity.getNombres());
        	   }
    	}		
		return response; 
	}
		

	
	
	

}
