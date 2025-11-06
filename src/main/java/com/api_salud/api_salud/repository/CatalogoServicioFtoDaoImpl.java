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
    	System.out.println("Ingreso leerCatalogoServiciosXIdTipoFinanciamiento ");		
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
	    	System.out.println("OUT  "+out.get("cur"));		
   		
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




/*          
System.out.println("Ingreso a Consulta precio de Producto   "+idProducto);            
try {
    cstmtOrdenServicioPagos = conMaestros.prepareCall("{call dbo.catalogo_servicios_xid_tipofinanciamiento_leer("
//    cstmtOrdenServicioPagos = conHis.prepareCall("{call his.dbo.his_catalogo_servicios_xid_tipofinanciamiento_leer("
                        + "?,?)}");  
    cstmtOrdenServicioPagos.setInt(1,idProducto);
    cstmtOrdenServicioPagos.setInt(2,idTipoFinanciamiento);
    rsOrdenServicioPagos = cstmtOrdenServicioPagos.executeQuery(); 
     while (rsOrdenServicioPagos.next()) {
        EntityCatalogoServicioFto.setIdFinanciamientoCatalogo(rsOrdenServicioPagos.getInt(1));
        EntityCatalogoServicioFto.setPrecioUnitario(rsOrdenServicioPagos.getDouble(2));
        EntityCatalogoServicioFto.setIdProducto(rsOrdenServicioPagos.getInt(3));
        EntityCatalogoServicioFto.setIdTipoFinanciamiento(rsOrdenServicioPagos.getInt(4));
        EntityCatalogoServicioFto.setActivo(rsOrdenServicioPagos.getInt(4));
        EntityCatalogoServicioFto.setSeUsaSinPrecio(rsOrdenServicioPagos.getInt(6));
    }
    
    System.out.println("Termino Consulta precio de Producto Financiamiento precios   "+EntityCatalogoServicioFto.getPrecioUnitario());            
} catch (SQLException e) {
    System.out.println("Error Dao Ejecuta proc Consulta e Financiamiento precios"+e.getMessage());            
}    
*/		
