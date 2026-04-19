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

import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;




@Repository
@Transactional
public class FacturacionCuentaAtencionDaoImpl  implements FacturacionCuentaAtencionDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallFacturacionCuentaAtencion;  
	
	
	@Override
	/* 2.    Crear Cuenta Atencion */
	public int  crearFacturacionCuentaAtencion(FacturacionCuentaAtencionEntity  facturacionCuentaAtencionEntity){
			int  response = 20;
			jdbcTemplate.setResultsMapCaseInsensitive(true);
		    simpleJdbcCallFacturacionCuentaAtencion = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("igm_facturacion.facturacion_cuentas_atencion_crear")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters( 
					new SqlOutParameter("p_idcuentaatencion", Types.INTEGER)
					,new SqlParameter("p_idpaciente", Types.INTEGER)
					,new SqlParameter("p_fechaapertura", Types.VARCHAR)
					,new SqlParameter("p_horaapertura", Types.VARCHAR)
					,new SqlParameter("p_fechacierre", Types.VARCHAR)
					,new SqlParameter("p_horacierre", Types.VARCHAR)
					,new SqlParameter("p_totalexonerado", Types.NUMERIC)
					,new SqlParameter("p_totalasegurado", Types.NUMERIC)
					,new SqlParameter("p_totalpagado", Types.NUMERIC)
					,new SqlParameter("p_idestado", Types.INTEGER)
					,new SqlParameter("p_totalporpagar", Types.NUMERIC)
					,new SqlParameter("p_idusuariocrea", Types.INTEGER)
					,new SqlParameter("p_idusuariomodifica", Types.INTEGER)
					,new SqlParameter("p_fechacreacion", Types.VARCHAR)
					,new SqlParameter("p_fechamodificacion", Types.VARCHAR)
				);
				SqlParameterSource param = new MapSqlParameterSource()
					.addValue("p_idcuentaatencion"	, facturacionCuentaAtencionEntity.getIdCuentaAtencion())
					.addValue("p_idpaciente"		, facturacionCuentaAtencionEntity.getIdPaciente())
					.addValue("p_fechaapertura"		, facturacionCuentaAtencionEntity.getFechaApertura())
					.addValue("P_horaapertura"		, facturacionCuentaAtencionEntity.getHoraApertura())
					.addValue("p_fechacierre"		, facturacionCuentaAtencionEntity.getFechaCierre())
					.addValue("p_horacierre"		, facturacionCuentaAtencionEntity.getHoraCierre())
					.addValue("p_totalexonerado"	, facturacionCuentaAtencionEntity.getTotalExonerado())
					.addValue("p_totalasegurado"	, facturacionCuentaAtencionEntity.getTotalAsegurado())
					.addValue("p_totalpagado"		, facturacionCuentaAtencionEntity.getTotalPagado())
					.addValue("p_idestado"			, facturacionCuentaAtencionEntity.getIdEstado())
					.addValue("P_totalporpagar"		, facturacionCuentaAtencionEntity.getTotalPorPagar())
					.addValue("p_idusuariocrea"		, facturacionCuentaAtencionEntity.getIdUsuarioCrea())
					.addValue("p_idusuariomodifica"	, facturacionCuentaAtencionEntity.getIdUsuarioModifica())
		    		.addValue("p_fechacreacion"		, facturacionCuentaAtencionEntity.getFechaCreacion())
		    		.addValue("p_fechamodificacion"	, facturacionCuentaAtencionEntity.getFechaModificacion());
				Map<String, Object> out = simpleJdbcCallFacturacionCuentaAtencion.execute(param);
				
	 	    if (out != null)  {
		   		response = (int) out.get("p_idcuentaatencion");		   		
		   		
	     	}		
			return response;
		}			
	
}


