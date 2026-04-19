package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.Map;

//import javax.transaction.Transactional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;


@Repository
@Transactional
public class CajaComprobantePagoDaoImpl  implements CajaComprobantePagoDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallCajaComprobantePago;  
	
	@Override
	public int crearCajaComprobantePago(CajaComprobantePagoEntity cajaComprobantePagoEntity) {
        int response = 0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCajaComprobantePago = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("igm_facturacion.caja_comprobantes_pago_crear")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters(
				  new SqlOutParameter("p_idcomprobantepago" 	, Types.INTEGER)
					,new SqlParameter("p_nroserie" 				, Types.VARCHAR)
					,new SqlParameter("p_nrodocumento"			, Types.VARCHAR)
					,new SqlParameter("p_razonsocial"			, Types.VARCHAR)
					,new SqlParameter("p_ruc"					, Types.VARCHAR)
					,new SqlParameter("p_subtotal"				, Types.NUMERIC)
					,new SqlParameter("p_igv"					, Types.NUMERIC)
					,new SqlParameter("p_total"					, Types.NUMERIC)
					,new SqlParameter("p_fechacobranza"			, Types.VARCHAR)
					,new SqlParameter("p_tipocambio"			, Types.NUMERIC)
					,new SqlParameter("p_observaciones"			, Types.VARCHAR)
					,new SqlParameter("p_idtipocomprobante" 	, Types.INTEGER)
					,new SqlParameter("p_idcuentaatencion" 		, Types.INTEGER)
					,new SqlParameter("p_idestadocomprobante" 	, Types.INTEGER)
					,new SqlParameter("p_idgestioncaja" 		, Types.INTEGER)
					,new SqlParameter("p_idtipopago" 			, Types.INTEGER)
					,new SqlParameter("p_idtipoorden" 			, Types.INTEGER)
					,new SqlParameter("p_dctos"					, Types.NUMERIC)
					,new SqlParameter("p_idpaciente" 			, Types.INTEGER)
					,new SqlParameter("p_idcajero" 				, Types.INTEGER)
					,new SqlParameter("p_idturno" 				, Types.INTEGER)
					,new SqlParameter("p_idcaja" 				, Types.INTEGER)
					,new SqlParameter("p_idformapago" 			, Types.INTEGER)
					,new SqlParameter("p_idfarmacia" 			, Types.INTEGER)
					,new SqlParameter("p_exoneraciones" 		, Types.NUMERIC)
					,new SqlParameter("p_adelantos"				, Types.NUMERIC)
					,new SqlParameter("p_idtipofinanciamiento" 	, Types.INTEGER)
					,new SqlParameter("p_dni"					, Types.VARCHAR)
			);
				
		
			SqlParameterSource param = new MapSqlParameterSource()
				.addValue("p_idcomprobantepago"		, cajaComprobantePagoEntity.getIdComprobantePago())                   
				.addValue("p_nroserie"				, cajaComprobantePagoEntity.getNroSerie())
				.addValue("p_nrodocumento"			, cajaComprobantePagoEntity.getNroDocumento())
				.addValue("p_razonsocial"			, cajaComprobantePagoEntity.getRazonSocial())
				.addValue("p_ruc"					, cajaComprobantePagoEntity.getRuc())
				.addValue("p_subtotal"				, cajaComprobantePagoEntity.getSubTotal())
				.addValue("p_igv"					, cajaComprobantePagoEntity.getIgv())
				.addValue("p_total"					, cajaComprobantePagoEntity.getTotal())
				.addValue("p_fechacobranza"			, cajaComprobantePagoEntity.getFechaCobranza())
				.addValue("p_tipocambio"			, cajaComprobantePagoEntity.getTipoCambio())
				.addValue("p_observaciones"			, cajaComprobantePagoEntity.getObservaciones())
				.addValue("p_idtipocomprobante"		, cajaComprobantePagoEntity.getIdTipoComprobante())
				.addValue("p_idcuentaatencion"		, cajaComprobantePagoEntity.getIdCuentaAtencion())
				.addValue("p_idestadocomprobante"	, cajaComprobantePagoEntity.getIdEstadoComprobante())
				.addValue("p_idgestioncaja"			, cajaComprobantePagoEntity.getIdGestionCaja())
				.addValue("p_idtipopago"			, cajaComprobantePagoEntity.getIdTipoPago())
				.addValue("p_idtipoorden"			, cajaComprobantePagoEntity.getIdTipoOrden())
				.addValue("p_dctos"					, cajaComprobantePagoEntity.getDctos())
				.addValue("p_idpaciente"			, cajaComprobantePagoEntity.getIdPaciente())
				.addValue("p_idcajero"				, cajaComprobantePagoEntity.getIdCajero())
				.addValue("p_idturno"				, cajaComprobantePagoEntity.getIdTurno())
				.addValue("p_idcaja"				, cajaComprobantePagoEntity.getIdCaja())
				.addValue("p_idformapago"			, cajaComprobantePagoEntity.getIdFormaPago())
				.addValue("p_idfarmacia"			, cajaComprobantePagoEntity.getIdFarmacia())
				.addValue("p_exoneraciones"			, cajaComprobantePagoEntity.getExoneraciones())
				.addValue("p_adelantos"				, cajaComprobantePagoEntity.getAdelantos())
				.addValue("p_idtipofinanciamiento"	, cajaComprobantePagoEntity.getIdTipoFinanciamiento())
				.addValue("p_dni"					, cajaComprobantePagoEntity.getDni())
				;
					

			Map<String, Object> out = simpleJdbcCallCajaComprobantePago.execute(param);
			
 	    if (out != null)  {
	   		 response = (int) out.get("p_idcomprobantepago");		   		
     	}		
		return response;   
	}

}
