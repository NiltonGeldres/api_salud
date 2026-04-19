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

import com.api_salud.api_salud.entity.CatalogoServicioFtoEntity;
import com.api_salud.api_salud.response.CatalogoServicioFtoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Transactional
public class CatalogoServicioFtoDaoImpl  implements CatalogoServicioFtoDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallCatalogo;  
	
	/*
	 leerCatalogoServiciosXIdTipoFinanciamiento, permite leer los todos los precios para un producto segun su plan
	 * */
	@Override
	public CatalogoServicioFtoResponse leerCatalogoServiciosXIdTipoFinanciamiento(int idProducto,int idTipoFinanciamiento) {
		
        CatalogoServicioFtoResponse response = null;
	    List<CatalogoServicioFtoEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallCatalogo = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_maestros.catalogo_servicios_xid_tipofinanciamiento_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("idProducto", Types.INTEGER)
				            					,new SqlParameter("idTipoFinanciamiento", Types.INTEGER)
				            					,new SqlOutParameter("cur", Types.OTHER )				            					
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("idProducto", idProducto)	    
				.addValue("idTipoFinanciamiento", idTipoFinanciamiento);    
   		Map<String, Object> out = simpleJdbcCallCatalogo.execute(param);
   		
 	    if (out != null)  {
 	            List<Object>  list = (List<Object>) out.get("cur") ;
        	   for (Object row : list) {
        		 CatalogoServicioFtoEntity catalogoServicioFto;
        	   	 ObjectMapper objectMapper = new ObjectMapper() ;
        	   	 catalogoServicioFto = objectMapper.convertValue(row, CatalogoServicioFtoEntity.class) ;
        	     res.add(catalogoServicioFto);
        	   }
        	   response = new CatalogoServicioFtoResponse();
        	   response.setCatalogoServicioFto(res);
     	}		
		return response; 
	}
}




