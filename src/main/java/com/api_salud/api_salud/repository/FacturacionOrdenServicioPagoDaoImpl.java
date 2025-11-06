package com.api_salud.api_salud.repository;

import java.sql.Types;
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

import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;


@Repository
@Transactional
public class FacturacionOrdenServicioPagoDaoImpl  implements FacturacionOrdenServicioPagoDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallFacturacionOrdenServicioPagos;  	
	
	@Override
	public int crearFacturacionOrdenServicioPago(
			FacturacionOrdenServicioPagoEntity facturacionOrdenServicioPagosEntity) {
		
        System.out.println("***Inicia DaoFacturacionOrdenServicioPagos: crearFacturacionOrdenServicioPagos ");            
        int response = 0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallFacturacionOrdenServicioPagos = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_facturacion.facturacion_orden_servicio_pagos_crear")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters(
			  new SqlOutParameter("p_idordenpago"			, Types.INTEGER)					
				,new SqlParameter("p_idcomprobantepago"		, Types.INTEGER)
				,new SqlParameter("p_idorden"				, Types.INTEGER)
				,new SqlParameter("p_importeexonerado"		, Types.NUMERIC)
				,new SqlParameter("p_fechacreacion"			, Types.VARCHAR)
				,new SqlParameter("p_idusuario"				, Types.INTEGER)
				,new SqlParameter("p_idestadofacturacion"	, Types.INTEGER)
				,new SqlParameter("p_idusuarioexonera"		, Types.INTEGER)
			);
		
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idordenpago"			    , facturacionOrdenServicioPagosEntity.getIdOrdenPago())
				.addValue("p_idcomprobantepago"			, facturacionOrdenServicioPagosEntity.getIdComprobantePago())
				.addValue("p_idorden"			    	, facturacionOrdenServicioPagosEntity.getIdOrden())
				.addValue("p_importeexonerado"			, facturacionOrdenServicioPagosEntity.getImporteExonerado())
				.addValue("p_fechacreacion"				, facturacionOrdenServicioPagosEntity.getFechaCreacion())
				.addValue("p_idusuario"					, facturacionOrdenServicioPagosEntity.getIdUsuario())
				.addValue("p_idestadofacturacion"		, facturacionOrdenServicioPagosEntity.getIdEstadoFacturacion())
				.addValue("p_idusuarioexonera"			, facturacionOrdenServicioPagosEntity.getIdUsuarioExonera());

			Map<String, Object> out = simpleJdbcCallFacturacionOrdenServicioPagos.execute(param);
			
 	    if (out != null)  {
	   		System.out.println("Orden Servicio Pago  "+ out.get("p_idordenpago"));
	   		 response = (int) out.get("p_idordenpago");		   		
     	}		
        System.out.println("***Termina DaoFacturacionOrdenServicioPagos: crearFacturacionOrdenServicioPagos  ");            
		return response;   
	}

	@Override
	public int actualizaIdComprobantePago(int idOrdenPago, int idComprobantePago) {
        System.out.println("***Inicia DaoFacturacionOrdenServicioPagos: crearFacturacionOrdenServicioPagos ");            
        int response = 0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallFacturacionOrdenServicioPagos = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_facturacion.facturacion_orden_servicio_pagos_actualizar_idcomprobante")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters(
				new SqlParameter("p_idordenpago"			, Types.INTEGER)
				,new SqlParameter("p_idcomprobantepago"		, Types.INTEGER)
			);
		
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idordenpago"			    , idOrdenPago)
				.addValue("p_idcomprobantepago"			, idComprobantePago);

			Map<String, Object> out = simpleJdbcCallFacturacionOrdenServicioPagos.execute(param);
			
 	    if (out != null)  {
	   		System.out.println("Orden Servicio Pago  ");
	   		 response = 1;		   		
     	}		
        System.out.println("***Termina DaoFacturacionOrdenServicioPagos: crearFacturacionOrdenServicioPagos  ");            
		return response;   
	}

}
