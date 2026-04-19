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

import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.response.FacturacionOrdenServicioResponse;



@Repository
@Transactional
public class FacturacionOrdenServicioDaoImpl  implements FacturacionOrdenServicioDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallFacturacionOrdenServicio;  	

    /* 3.   Facturacion  Crear Orden Servicio   */
    public  int  crearFacturacionOrdenServicio(FacturacionOrdenServicioEntity facturacionOrdenServicioEntity)

    {
        int response = 0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallFacturacionOrdenServicio = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_facturacion.facturacion_orden_servicio_crear")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters(
				new SqlOutParameter("p_idorden", Types.INTEGER)					
				,new SqlParameter("p_idpuntocarga", Types.INTEGER)
				,new SqlParameter("p_idpaciente", Types.INTEGER)
				,new SqlParameter("p_idcuentaatencion", Types.INTEGER)
				,new SqlParameter("p_idserviciopaciente", Types.INTEGER)
				,new SqlParameter("p_idtipofinanciamiento", Types.INTEGER)
				,new SqlParameter("p_idfuentefinanciamiento", Types.INTEGER)
				,new SqlParameter("p_fechacreacion", Types.VARCHAR)
				,new SqlParameter("p_idusuario", Types.INTEGER)
				,new SqlParameter("p_fechadespacho", Types.VARCHAR)
				,new SqlParameter("p_idusuariodespacho", Types.INTEGER)
				,new SqlParameter("p_idestadofacturacion", Types.INTEGER)
				,new SqlParameter("p_fechahorarealizacpt", Types.VARCHAR)
			);

    
	    
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idorden"			    , facturacionOrdenServicioEntity.getIdOrden())
				.addValue("p_idpuntocarga"			, facturacionOrdenServicioEntity.getIdPuntoCarga())
				.addValue("p_idpaciente"			, facturacionOrdenServicioEntity.getIdPaciente())
				.addValue("p_idcuentaatencion"		, facturacionOrdenServicioEntity.getIdCuentaAtencion())
				.addValue("p_idserviciopaciente"	, facturacionOrdenServicioEntity.getIdServicioPaciente())
				.addValue("p_idtipofinanciamiento"	, facturacionOrdenServicioEntity.getIdTipoFinanciamiento())
				.addValue("p_idfuentefinanciamiento"	, facturacionOrdenServicioEntity.getIdFuenteFinanciamiento())
				.addValue("p_fechacreacion"			, facturacionOrdenServicioEntity.getFechaCreacion())
				.addValue("p_idusuario"				, facturacionOrdenServicioEntity.getIdUsuario())
				.addValue("p_fechadespacho"			, facturacionOrdenServicioEntity.getFechaDespacho())
				.addValue("p_idusuariodespacho"		, facturacionOrdenServicioEntity.getIdUsuarioDespacho())
				.addValue("p_idestadofacturacion"		, facturacionOrdenServicioEntity.getIdEstadoFacturacion())
				.addValue("p_fechahorarealizacpt"			, facturacionOrdenServicioEntity.getFechaHoraRealizaCpt());

			Map<String, Object> out = simpleJdbcCallFacturacionOrdenServicio.execute(param);
			
 	    if (out != null)  {
	   		 response = (int) out.get("p_idorden");		   		
     	}		
		return response;   
    }        
	
}

