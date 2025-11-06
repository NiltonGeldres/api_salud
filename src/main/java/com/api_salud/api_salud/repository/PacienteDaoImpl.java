package com.api_salud.api_salud.repository;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
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

import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Transactional
public class PacienteDaoImpl implements PacienteDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall  simpleJdbcCallPaciente;

	@Override
	public int crear(
		     String nroHistoriaClinica,
		     String nroDocumento,
		     String apellidoPaterno,
		     String apellidoMaterno,
		     String primerNombre,
		     String segundoNombre,
		     String tercerNombre,
		     String fechaNacimiento,
		     String telefono,
		     String email,
		     String direccionDomicilio,
		     String nombrePadre,
		     String nombreMadre,
		     String observacion,
		     String grupoSanguineo,
		     String factorRh,
		     Integer idTipoSexo,
		     Integer idGradoInstruccion,
		     Integer idEstadoCivil,
		     Integer idDocIdentidad,
		     Integer idTipoOcupacion,
		     Integer nroOrdenHijo,
		     Integer idIdioma,
		     Integer idEtnia,
		     Integer idReligion,
		     Integer idProcedencia,
		     Integer idPaisDomicilio,
		     Integer idPaisProcedencia,
		     Integer idPaisNacimiento,
		     Integer idDepartamentoProcedencia,
		     Integer idDepartamentoDomicilio,
		     Integer idDepartamentoNacimiento,
		     Integer idDistritoProcedencia,
		     Integer idDistritoDomicilio,
		     Integer idDistritoNacimiento,
		     Integer idCentroPobladoProcedencia,
		     Integer idCentroPobladoDomicilio,
		     Integer idCentroPobladoNacimiento,
		     Integer idUsuario
			) {

		System.out.println("*** INICIANDO LA CREACION  :  ");		
	
    	List<CitaSeparadaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallPaciente = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_clientes.paciente_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
			            			new SqlParameter("p_nrohistoriaclinica",    	Types.VARCHAR)
			       				     ,new SqlParameter("p_nrodocumento" ,			Types.VARCHAR)
			       				     ,new SqlParameter("p_apellidopaterno" ,     	Types.VARCHAR)
			       				     ,new SqlParameter("p_apellidomaterno",     	Types.VARCHAR)
			       				     ,new SqlParameter("p_primernombre" ,			Types.VARCHAR)
			       				     ,new SqlParameter("p_segundonombre",			Types.VARCHAR)
			       				     ,new SqlParameter("p_tercernombre",			Types.VARCHAR)
			       				     ,new SqlParameter("p_fechanacimiento",			Types.VARCHAR)
			       				     ,new SqlParameter("p_telefono",				Types.VARCHAR)
			       				     ,new SqlParameter("p_email",					Types.VARCHAR)
			       				     ,new SqlParameter("p_direcciondomicilio", 		Types.VARCHAR)
			       				     ,new SqlParameter("p_nombrepadre"	,			Types.VARCHAR)
			       				     ,new SqlParameter("p_nombremadre"	,			Types.VARCHAR)
			       				     ,new SqlParameter("p_observacion"	,			Types.VARCHAR)
			       				     ,new SqlParameter("p_gruposanguineo"	,      	Types.VARCHAR)
			       				     ,new SqlParameter("p_factorrh",				Types.VARCHAR)
			       				     ,new SqlParameter("p_idtiposexo",					Types.INTEGER )
			       				     ,new SqlParameter("p_idgradoinstruccion",    		Types.INTEGER)
			       				     ,new SqlParameter("p_idestadocivil",				Types.INTEGER)
			       				     ,new SqlParameter("p_iddocidentidad",      		Types.INTEGER)
			       				     ,new SqlParameter("p_idtipoocupacion",      		Types.INTEGER)
			       				     ,new SqlParameter("p_nroordenhijo",				Types.INTEGER)
			       				     ,new SqlParameter("p_ididioma",					Types.INTEGER )
			       				     ,new SqlParameter("p_idetnia",				 		Types.INTEGER )
			       				     ,new SqlParameter("p_idreligion",					Types.INTEGER )
			       				     ,new SqlParameter("p_idprocedencia",				Types.INTEGER )
			       				     ,new SqlParameter("p_idpaisdomicilio"	,       	Types.INTEGER )
			       				     ,new SqlParameter("p_idpaisprocedencia",     		Types.INTEGER )
			       				     ,new SqlParameter("p_idpaisnacimiento"	,       	Types.INTEGER )
			       				     ,new SqlParameter("p_iddepartamentoprocedencia",	Types.INTEGER )
			       				     ,new SqlParameter("p_iddepartamentodomicilio"	,   Types.INTEGER )
			       				     ,new SqlParameter("p_iddepartamentonacimiento",   	Types.INTEGER )
			       				     ,new SqlParameter("p_iddistritoprocedencia",   	Types.INTEGER )
			       				     ,new SqlParameter("p_iddistritodomicilio",      	Types.INTEGER )
			       				     ,new SqlParameter("p_iddistritonacimiento"	,      	Types.INTEGER )
			       				     ,new SqlParameter("p_idcentropobladoprocedencia",  Types.INTEGER )
			       				     ,new SqlParameter("p_idcentropobladodomicilio", 	Types.INTEGER )
			       				     ,new SqlParameter("p_idcentropobladonacimiento", 	Types.INTEGER )				            		
			       				     ,new SqlParameter("p_idusuario", 					Types.INTEGER )				            		
				       				 ,new SqlOutParameter("o_idpaciente", 			Types.INTEGER )
	            					);
		int o_idcitaseparada=0;
	    SqlParameterSource param = new MapSqlParameterSource()
	    	 .addValue("p_nrohistoriaclinica",	    nroHistoriaClinica)
		     .addValue("p_nrodocumento" ,			nroDocumento)
		     .addValue("p_apellidopaterno" ,  		apellidoPaterno)
		     .addValue("p_apellidomaterno",     	apellidoMaterno)
		     .addValue("p_primernombre" ,			primerNombre)
		     .addValue("p_segundonombre",			segundoNombre)
		     .addValue("p_tercernombre",			tercerNombre	)
		     .addValue("p_fechanacimiento",			fechaNacimiento)
		     .addValue("p_telefono",				telefono)
		     .addValue("p_email",					email)
		     .addValue("p_direcciondomicilio", 		direccionDomicilio)
		     .addValue("p_nombrepadre"	,			nombrePadre)
		     .addValue("p_nombremadre"	,			nombreMadre)
		     .addValue("p_observacion"	,			observacion)
		     .addValue("p_gruposanguineo",      	grupoSanguineo)
		     .addValue("p_factorrh",				factorRh)
		     .addValue("p_idtiposexo",				idTipoSexo)
		     .addValue("p_idgradoinstruccion",  	idGradoInstruccion)
		     .addValue("p_idestadocivil",			idEstadoCivil)
		     .addValue("p_iddocidentidad",      	idDocIdentidad)
		     .addValue("p_idtipoocupacion",     	idTipoOcupacion)
		     .addValue("p_nroordenhijo",			nroOrdenHijo)
		     .addValue("p_ididioma",				idIdioma)
		     .addValue("p_idetnia",					idEtnia)
		     .addValue("p_idreligion",				idReligion)
		     .addValue("p_idprocedencia",			idProcedencia)
		     .addValue("p_idpaisdomicilio",     	idPaisDomicilio)
		     .addValue("p_idpaisprocedencia",   	idPaisProcedencia)
		     .addValue("p_idpaisnacimiento",    	idPaisNacimiento)
		     .addValue("p_iddepartamentoprocedencia",	idDepartamentoProcedencia)
		     .addValue("p_iddepartamentodomicilio" ,    idDepartamentoDomicilio )
		     .addValue("p_iddepartamentonacimiento",   	idDepartamentoNacimiento)
		     .addValue("p_iddistritoprocedencia",   	idDistritoProcedencia)
		     .addValue("p_iddistritodomicilio",      	idDistritoDomicilio)
		     .addValue("p_iddistritonacimiento"	,      	idDistritoNacimiento)
		     .addValue("p_idcentropobladoprocedencia",  idCentroPobladoProcedencia)
		     .addValue("p_idcentropobladodomicilio", 	idCentroPobladoDomicilio)
		     .addValue("p_idcentropobladonacimiento", 	idCentroPobladoNacimiento)				            		
	     	 .addValue("p_idusuario", 					idUsuario)	;			            		
		try {
	    	int response = 0;
	   		Map<String, Object> out = simpleJdbcCallPaciente.execute(param);
	 	    if (out != null) {
			   	  response= (int) out.get("o_idpaciente");
	        }
			return  response;	
		} catch (Exception e){
			return  0;
		}		
	}

	@Override
	public PacienteEntity leerNroDocumento(PacienteEntity request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PacienteEntity leerNombres(String  nombres) {
    	System.out.println("****** NOMBRES A BUSCAR :  "+nombres);		
		PacienteEntity response = null;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallPaciente = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_clientes.paciente_buscar_nombres_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_nombres", Types.VARCHAR),
							            		new SqlOutParameter("o_usuario", Types.OTHER) 
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		 .addValue("p_nombres",nombres);
	    
   		Map<String, Object> out =   simpleJdbcCallPaciente.execute(param);
 	    if (out != null)  {
			List<Object> list =  (List<Object>) out.get("o_usuario") ;
 			for (Object row : list) {
	 			PacienteEntity paciente = new PacienteEntity();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			paciente = objectMapper.convertValue(row, PacienteEntity.class) ;
	 			response= paciente;
 			}	
     	}
		return response;
		
	}

	@Override
	public PacienteEntity actualizar(PacienteEntity request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PacienteEntity leerIdUsuario(int idUsuario) {
			PacienteEntity response = null;
			jdbcTemplate.setResultsMapCaseInsensitive(true);
		    simpleJdbcCallPaciente = new SimpleJdbcCall(jdbcTemplate)
					    		.withProcedureName("igm_clientes.paciente_buscar_idusuario_leer")
					            .withoutProcedureColumnMetaDataAccess()
					            .declareParameters( 
					            					new SqlParameter("p_idusuario", Types.INTEGER),
								            		new SqlOutParameter("o_paciente", Types.OTHER) 
					            					);
		    SqlParameterSource param = new MapSqlParameterSource()
		    		 .addValue("p_idusuario",idUsuario);
		    try {
		   		Map<String, Object> out =   simpleJdbcCallPaciente.execute(param);
		 	    if (out != null )  {
		 	    	List<Object> list =  (List<Object>) out.get("o_paciente") ;
			 	   	for (Object row : list) {
				 	   		PacienteEntity paciente = new PacienteEntity();
				 			ObjectMapper objectMapper = new ObjectMapper() ;
				 			paciente = objectMapper.convertValue(row, PacienteEntity.class) ;
				 			response= paciente;
	
					 }	

				return response;
		     	} else {
		     		return null;
		     	}
	 	    
		    } catch (Exception e) {
		    	return null;
		    }
		    
	}

}
