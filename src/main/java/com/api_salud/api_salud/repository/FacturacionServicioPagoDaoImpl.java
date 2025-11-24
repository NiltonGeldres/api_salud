package com.api_salud.api_salud.repository;

import java.sql.Types;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;

@Repository
@Transactional
public class FacturacionServicioPagoDaoImpl  implements FacturacionServicioPagoDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallFacturacionServicioPago;  
	
	@Override
	public int crearFacturacionServicioPago(FacturacionServicioPagoEntity facturacionServicioPagoEntity) {
	       System.out.println("***Inicia DaoFacturacionServicioPago: crearFacturacionServicioPago "+facturacionServicioPagoEntity.getIdProducto());            
	        int response = 1;
			jdbcTemplate.setResultsMapCaseInsensitive(true);
		    simpleJdbcCallFacturacionServicioPago = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("igm_facturacion.facturacion_servicio_pago_crear")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
					 new SqlParameter("p_idordenpago", Types.INTEGER)					
					,new SqlParameter("p_idproducto", Types.INTEGER)
					,new SqlParameter("p_cantidad", Types.INTEGER)
					,new SqlParameter("p_precio", Types.NUMERIC)
					,new SqlParameter("p_total", Types.NUMERIC)
				);
		    
				SqlParameterSource param = new MapSqlParameterSource()
					.addValue("p_idordenpago"	, facturacionServicioPagoEntity.getIdOrdenPago())
					.addValue("p_idproducto"	, facturacionServicioPagoEntity.getIdProducto())
					.addValue("p_cantidad"		, facturacionServicioPagoEntity.getCantidad())
					.addValue("p_precio"		, facturacionServicioPagoEntity.getPrecio())
					.addValue("p_total"			, facturacionServicioPagoEntity.getTotal());
				simpleJdbcCallFacturacionServicioPago.execute(param);
	        System.out.println("***Termina DaoFacturacionServicioPago: crearFacturacionServicioPago ");            
			return response;  
	}

}
