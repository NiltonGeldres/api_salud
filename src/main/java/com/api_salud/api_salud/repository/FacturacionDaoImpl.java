package com.api_salud.api_salud.repository;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;

import com.api_salud.api_salud.response.FacturacionResponse;


@Repository
@Transactional
//@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FacturacionDaoImpl implements FacturacionDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallFacturacion;  
	
	@Override
	/* 2.    Crear Cuenta Atencion */
	  public FacturacionResponse crearFacturacionCuentaAtencion(
		double totalPorPagar, int idEstado, double totalPagado, double totalAsegurado, double totalExonerado,
		String horaCierre, String fechaCierre, String horaApertura, String fechaApertura, int idPaciente, 
		int idCuentaAtencion,String fechaCreacion,int idUsuarioAuditoria)		
		{
		  
			FacturacionResponse response = null;
		    List<FacturacionCuentaAtencionEntity> res =new ArrayList<>();
			jdbcTemplate.setResultsMapCaseInsensitive(true);
		    simpleJdbcCallFacturacion = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("igm_facturacion.facturacion_cuentas_atencion_crear")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters( 
					new SqlParameter("totalPorPagar", Types.NUMERIC)
					,new SqlParameter("idEstado", Types.INTEGER)
					,new SqlParameter("totalPagado", Types.NUMERIC)
					,new SqlParameter("totalAsegurado", Types.NUMERIC)
					,new SqlParameter("totalExonerado", Types.NUMERIC)
					,new SqlParameter("horaCierre", Types.VARCHAR)
					,new SqlParameter("fechaCierre", Types.VARCHAR)
					,new SqlParameter("horaApertura", Types.VARCHAR)
					,new SqlParameter("fechaApertura", Types.VARCHAR)
					,new SqlParameter("idPaciente", Types.INTEGER)
					,new SqlOutParameter("idCuentaAtencion", Types.INTEGER)
					,new SqlParameter("fechaCreacion", Types.VARCHAR)
					,new SqlParameter("idUsuarioAuditoria", Types.INTEGER)
				);
		    
				SqlParameterSource param = new MapSqlParameterSource()
					.addValue("totalPorPagar", totalPorPagar)
					.addValue("idEstado", idEstado)
					.addValue("totalPagado", totalPagado)
					.addValue("totalAsegurado", totalAsegurado)
					.addValue("totalExonerado", totalExonerado)
					.addValue("horaCierre", horaCierre)
					.addValue("fechaCierre", fechaCierre)
					.addValue("horaApertura",horaApertura)
					.addValue("fechaApertura", fechaApertura)
					.addValue("idPaciente", idPaciente)
					.addValue("fechaCreacion", fechaCreacion)
					.addValue("idUsuarioAuditoria", idUsuarioAuditoria);
				Map<String, Object> out = simpleJdbcCallFacturacion.execute(param);
	 	    if (out != null)  {
		   		System.out.println("Cuenta atencion"+ out.get("idCuentaAtencion"));
	     	}		
			return response;
		}			
	
}
	
	  
/*
	  int idCuentaAtencion = 0;
    System.out.println("Ingreso a ejecutarProCrearFacturacionCuentaAtencion");            
    try {
        cstmtCuentaAtencion = conFac.prepareCall("{call facturacion_cuentas_atencion_crear("
                            + "?,?,?,?,?,?,?,?,?,?,"
                            + "?,?,?)}");  
        cstmtCuentaAtencion.setNull(1,java.sql.Types.INTEGER);
        cstmtCuentaAtencion.setInt(2,1);
        cstmtCuentaAtencion.setNull(3,java.sql.Types.INTEGER);
        cstmtCuentaAtencion.setNull(4,java.sql.Types.INTEGER);
        cstmtCuentaAtencion.setNull(5,java.sql.Types.INTEGER);
        cstmtCuentaAtencion.setString(6,EntityCita.getHoraInicio());
        cstmtCuentaAtencion.setString(7,EntityCita.getFecha());
        cstmtCuentaAtencion.setString(8,EntityCita.getHoraInicio());
        cstmtCuentaAtencion.setString(9,EntityCita.getFecha());
        cstmtCuentaAtencion.setInt(10,EntityCita.getIdPaciente());
        cstmtCuentaAtencion.registerOutParameter(11, java.sql.Types.INTEGER);
        cstmtCuentaAtencion.setString(12,EntityCita.getFechaSolicitud());
        cstmtCuentaAtencion.setInt(13,10);
        cstmtCuentaAtencion.execute(); 0
        idCuentaAtencion = cstmtCuentaAtencion.getInt(11);
        System.out.println("Termino Correcto");            
    } catch (SQLException e) {
        idCuentaAtencion = 0;
        System.out.println("Error Dao Crea Cuenta Atencion  "+e.getMessage());            
   }    
    return idCuentaAtencion ;
} 
*/	
	

/*
System.out.println("Ingreso crearFacturacionCuentaAtencion "
		+ " totalPorPagar "+ totalPorPagar
		+ " idEstado "+idEstado
		+ " totalPagado "+ totalPagado
		+ " totalAsegurado "+totalAsegurado
		+ " totalExonerado "+totalExonerado
		+ " horaCierre "+horaCierre
		+ " fechaCierre "+fechaCierre
		+ " horaApertura "+horaApertura
		+ " fechaApertura "+fechaApertura
		+ " idPaciente "+idPaciente
		+ " idCuentaAtencion "+idCuentaAtencion
		+ " fechaCreacion "+fechaCreacion
		+ " idUsuarioAuditoria "+idUsuarioAuditoria);		
		*/