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

import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;


@Repository
@Transactional
public class FacturacionServicioDespachoDaoImpl  implements FacturacionServicioDespachoDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallFacturacionServicioDespacho;  	

	@Override
	public int crearFacturacionServicioDespacho(FacturacionServicioDespachoEntity facturacionServicioDespachoEntity) {
        int response = 1;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallFacturacionServicioDespacho = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_facturacion.facturacion_servicio_despacho_crear")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters(
				 new SqlParameter("p_idorden", Types.INTEGER)					
				,new SqlParameter("p_idproducto", Types.INTEGER)
				,new SqlParameter("p_cantidad", Types.INTEGER)
				,new SqlParameter("p_precio", Types.NUMERIC)
				,new SqlParameter("p_total", Types.NUMERIC)
				,new SqlParameter("p_labconfhis", Types.VARCHAR)
				,new SqlParameter("p_grupohis", Types.INTEGER)
				,new SqlParameter("p_subgrupohis", Types.INTEGER)
			);
	    
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idorden"		, facturacionServicioDespachoEntity.getIdOrden())
				.addValue("p_idproducto"	, facturacionServicioDespachoEntity.getIdProducto())
				.addValue("p_cantidad"		, facturacionServicioDespachoEntity.getCantidad())
				.addValue("p_precio"		, facturacionServicioDespachoEntity.getPrecio())
				.addValue("p_total"			, facturacionServicioDespachoEntity.getTotal())
				.addValue("p_labconfhis"	, facturacionServicioDespachoEntity.getLabConfHIS())
				.addValue("p_grupohis"		, facturacionServicioDespachoEntity.getGrupoHIS())
				.addValue("p_subgrupohis"	, facturacionServicioDespachoEntity.getSubGrupoHIS());
			simpleJdbcCallFacturacionServicioDespacho.execute(param);
		return response;   
   
	}
	

}
