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
        System.out.println("***Inicia DaoFacturacionOrdenServicio: crearFacturacionOrdenServicio ");            
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
	   		System.out.println("Orden Servicio   "+ out.get("p_idorden"));
	   		 response = (int) out.get("p_idorden");		   		
     	}		
        System.out.println("***Termina DaoFacturacionOrdenServicio: crearFacturacionOrdenServicio ");            
		return response;   
    }        
	
}

/*	
	int idPuntoCarga
	,int idPaciente
	,int idCuentaAtencion
	,int idServicioPaciente
	,double idTipoFinanciamiento
	,double idFuenteFinanciamiento
	,String fechaCreacion
	,int idUsuario
	,String fechaDespacho
	,int idUsuarioDespacho
	,int idEstadoFacturacion
	,String fechaHoraRealizaCpt
	,int idUsuarioAuditoria
)*/
/*
 
         
        
        
        
        
        //////////////////////////////////////////////////////
        
//        int idCuentaAtencion= -1;
        int estadoFacturacion = 1;
        int fuenteFinanciamiento=0;
        int idOrden=0;
        int idTipoFinanciamiento= 0;
        int idPuntoCarga = 0;
  //      int idCuentaAtencion = 0;
//                    idCuentaAtencion = ejecutarProCrearFacturacionCuentaAtencion(beanCita);
                    FacturacionOrdenServicioBean beanOrdenServicio = new FacturacionOrdenServicioBean();
                    beanOrdenServicio.setFechaCreacion(beanCita.getFechaSolicitud());
                    beanOrdenServicio.setFechaDespacho(beanCita.getFechaSolicitud());
                    beanOrdenServicio.setFechaHoraRealizaCpt(null);
                    beanOrdenServicio.setIdCuentaAtencion(idCuentaAtencion);
                    beanOrdenServicio.setIdEstadoFacturacion(estadoFacturacion);
                    beanOrdenServicio.setIdFuenteFinanciamiento(fuenteFinanciamiento);
                    beanOrdenServicio.setIdOrden(idOrden);
                    beanOrdenServicio.setIdPaciente(beanCita.getIdPaciente());
                    beanOrdenServicio.setIdPuntoCarga(idPuntoCarga);
                    beanOrdenServicio.setIdServicioPaciente(beanCita.getIdServicio());
                    beanOrdenServicio.setIdTipoFinanciamiento(idTipoFinanciamiento);
                    beanOrdenServicio.setIdUsuario(beanCita.getIdPaciente());
                    beanOrdenServicio.setIdUsuarioDespacho(beanCita.getIdPaciente());
        
        System.out.println("Ingreso a Crea Orden Servicio");            
        try {
            cstmtOrdenServicio = conFac.prepareCall("{call facturacion_orden_servicio_crear("
                                + "?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?)}");  
            cstmtOrdenServicio.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmtOrdenServicio.setInt(2,beanOrdenServicio.getIdPuntoCarga());
            cstmtOrdenServicio.setInt(3,beanOrdenServicio.getIdPaciente());
            cstmtOrdenServicio.setInt(4,beanOrdenServicio.getIdCuentaAtencion());
            cstmtOrdenServicio.setInt(5,beanOrdenServicio.getIdServicioPaciente());
            cstmtOrdenServicio.setInt(6,beanOrdenServicio.getIdTipoFinanciamiento());
            cstmtOrdenServicio.setInt(7,beanOrdenServicio.getIdFuenteFinanciamiento());
            cstmtOrdenServicio.setString(8,beanOrdenServicio.getFechaCreacion());
            cstmtOrdenServicio.setInt(9,beanOrdenServicio.getIdUsuario());
            cstmtOrdenServicio.setString(10,beanOrdenServicio.getFechaDespacho());
            cstmtOrdenServicio.setInt(11,beanOrdenServicio.getIdUsuarioDespacho());
            cstmtOrdenServicio.setInt(12,beanOrdenServicio.getIdEstadoFacturacion());
            cstmtOrdenServicio.setString(13,beanOrdenServicio.getFechaCreacion());
            cstmtOrdenServicio.setInt(14,beanOrdenServicio.getIdPaciente());
            cstmtOrdenServicio.execute(); 
            
            idCuentaAtencion = cstmtOrdenServicio.getInt(1);
            System.out.println("Termino Correcto ");            
        } catch (SQLException e) {
            idCuentaAtencion = 0;
            System.out.println("Error Dao Ejecuta proc Crear Orden Servicio"+e.getMessage());            
       }    
        return idCuentaAtencion ;
    } 

 
 * */




/*			double totalPorPagar
,int idEstado
,double totalPagado
,double totalAsegurado
,double totalExonerado
,String horaCierre
,String fechaCierre
,String horaApertura
,String fechaApertura
,int idPaciente
,int idCuentaAtencion
,String fechaCreacion
,int idUsuarioAuditoria*/
