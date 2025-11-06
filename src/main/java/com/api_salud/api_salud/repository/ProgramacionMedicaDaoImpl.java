package com.api_salud.api_salud.repository;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaMesResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.api_salud.api_salud.service.CitaService;
import com.fasterxml.jackson.databind.ObjectMapper;



@Repository
@Transactional
public class ProgramacionMedicaDaoImpl  implements ProgramacionMedicaDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallProg;

	
	@Autowired
	CitaService citaService;

		
	@Override
	public ProgramacionMedicaResponse  programacionMedicoTodos(int idMedico, int idEspecialidad) {
		ProgramacionMedicaResponse response = null;
	    List<ProgramacionMedicaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProg = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_por_idmedico_idespecialidad_web_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_idmedico", Types.INTEGER)
				            					,new SqlParameter("p_idespecialidad", Types.INTEGER)
				            					,new SqlOutParameter("cur", Types.OTHER )				            					
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idmedico", idMedico)	    
				.addValue("p_idespecialidad", idEspecialidad);    
   		Map<String, Object> out = simpleJdbcCallProg.execute(param);
 	    if (out != null) {
 	    	response= new ProgramacionMedicaResponse();
            List<Object>  list = (List<Object>) out.get("cur") ;
        	   for (Object row : list) {
             	  ProgramacionMedicaEntity prog = new ProgramacionMedicaEntity();
        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
        		  prog = objectMapper.convertValue(row, ProgramacionMedicaEntity.class) ;
        		  
        		  //------- consultar si fecha programada tiene cupos 
					CitaRequest c = new CitaRequest();
					c.setFecha(prog.getFecha());
					c.setIdEspecialidad(idEspecialidad);
					c.setIdMedico(idMedico);
			    	CitaResponse cf = citaService.citaDisponible(c); // llamar citas de programacion
			    	if (!cf.getCita().isEmpty()) {
			    		res.add(prog);
			    	}
			    	//-------        		  
        	   }
     	      response.setProgramacionMedica(res);
        	   
     	}		
		return response;
		
}

	
		
	@Override
	public ProgramacionMedicaResponse programacionMedicoFecha(int idMedico, String fecha, int idEspecialidad) {
		System.out.println("INGRESO A  programacionMedicoFecha... "+idMedico+idEspecialidad+fecha);
		ProgramacionMedicaResponse response = null;
	    List<ProgramacionMedicaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProg = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_por_fecha_idmedico_idespecialidad_web_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_idmedico", Types.INTEGER)
				            					,new SqlParameter("p_fecha", Types.VARCHAR)
				            					,new SqlParameter("p_idespecialidad", Types.INTEGER)
				            					,new SqlOutParameter("cur", Types.OTHER )				            					
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idmedico", idMedico)	    
	    		.addValue("p_fecha", fecha)	    
				.addValue("p_idespecialidad", idEspecialidad);    
   		Map<String, Object> out = simpleJdbcCallProg.execute(param);
		System.out.println("INGRESO A  programacionMedicoFecha... " +out);
   		
 	    if (out != null) {
 	    	response= new ProgramacionMedicaResponse();
            List<Object>  list = (List<Object>) out.get("cur") ;
        	   for (Object row : list) {
             	  ProgramacionMedicaEntity prog = new ProgramacionMedicaEntity();
        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
        		  prog = objectMapper.convertValue(row, ProgramacionMedicaEntity.class) ;
		    	  res.add(prog);
        		  
        	   }
     	      response.setProgramacionMedica(res);
        	   
     	}		
		return response;
		
		
		
	}
		
	
	@Override
	public List<ProgramacionMedicaEntity>  programacionMedicoMesLeer(int mes, int ano, int idMedico) {
		System.out.println("programacionMedicoMesLeer:  "+mes+" "+ano+" "+idMedico);
		
		List<ProgramacionMedicaEntity> response = null;
	    List<ProgramacionMedicaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProg = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_xmedico_xmes_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					 new SqlParameter("p_mes",      Types.INTEGER)
				            					,new SqlParameter("p_anio",     Types.INTEGER)
				            					,new SqlParameter("p_idmedico", Types.INTEGER)
				            					,new SqlOutParameter("cur",     Types.OTHER )				            					
				            					);
   		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_mes", mes)	    
	    		.addValue("p_anio", ano)	    
				.addValue("p_idmedico", idMedico);    
   		Map<String, Object> out = simpleJdbcCallProg.execute(param);
		List<Object>  list =(List<Object>) out.get("cur") ;
 	    if (!list.isEmpty()) {
// 			List<Object>  list =(List<Object>) out.get("cur") ;
 			for (Object row : list) {
 				ProgramacionMedicaEntity cita = new ProgramacionMedicaEntity();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			cita = objectMapper.convertValue(row, ProgramacionMedicaEntity.class) ;
	 			res.add(cita);
 			}
 			response =res;
 	    } else	{
 	    	response = null;
     	}		
		return response;
}
	
	@Override
	public int   programacionMedicoCrear(ProgramacionMedicaEntity programacion) {
		int response=0 ;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProg = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					 new SqlParameter("p_idespecialidad",      	Types.INTEGER)
				            					,new SqlParameter("p_idturno",     			Types.INTEGER)
				            					,new SqlParameter("p_color", 				Types.VARCHAR)
				            					,new SqlParameter("p_idservicio", 			Types.INTEGER)
				            					,new SqlParameter("p_idmedico", 			Types.INTEGER)
				            					,new SqlParameter("p_iddepartamento", 		Types.INTEGER)
				            					,new SqlParameter("p_idtiposervicio", 		Types.INTEGER)
				            					,new SqlParameter("p_fecha", 				Types.VARCHAR)
				            					,new SqlParameter("p_horainicio", 			Types.VARCHAR)
				            					,new SqlParameter("p_horafin", 				Types.VARCHAR)
				            					,new SqlParameter("p_descripcion", 			Types.VARCHAR)
				            					,new SqlParameter("p_idtipoprogramacion",	Types.INTEGER)
				            					,new SqlParameter("p_fechareg", 			Types.VARCHAR)
				            					,new SqlParameter("p_tiempopromedioatencion", Types.INTEGER)
				            					,new SqlParameter("p_citaadicionalbloqueada", Types.VARCHAR)
				            					,new SqlParameter("p_motivobloqueocitaadicional", Types.VARCHAR)
				            					,new SqlParameter("p_bloqueacitasprogramacion", Types.VARCHAR)
				            					,new SqlParameter("p_idusuarioauditoria", Types.INTEGER)
				            					,new SqlParameter("p_idprogramacionmedica_cabecera", Types.INTEGER)
				            					,new SqlOutParameter("o_idprogramacion",     Types.INTEGER)				            					
				            					);
		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idespecialidad", programacion.getIdEspecialidad())	    
	    		.addValue("p_idturno", programacion.getIdTurno())	    
	    		.addValue("p_color", programacion.getColor())	    
	    		.addValue("p_idservicio", programacion.getIdServicio())	    
	    		.addValue("p_idmedico", programacion.getIdMedico())	    
	    		.addValue("p_iddepartamento", programacion.getIdDepartamento())	    
	    		.addValue("p_idtiposervicio", programacion.getIdTipoServicio())	    
	    		.addValue("p_fecha", programacion.getFecha())	    
	    		.addValue("p_horainicio", programacion.getHoraInicio())	    
	    		.addValue("p_horafin", programacion.getHoraFin())	    
	    		.addValue("p_descripcion", programacion.getDescripcion())	    
	    		.addValue("p_idtipoprogramacion", programacion.getIdTipoProgramacion())	    
	    		.addValue("p_fechareg", programacion.getFechaReg())	    
	    		.addValue("p_tiempopromedioatencion", programacion.getTiempoPromedioAtencion())	    
	    		.addValue("p_citaadicionalbloqueada", programacion.getCitaAdicionalBloqueada())	    
	    		.addValue("p_motivobloqueocitaadicional", programacion.getMotivoBloqueoCitaAdicional())	    
	    		.addValue("p_bloqueacitasprogramacion", programacion.getBloqueaCitasProgramacion())	    
	    		.addValue("p_idusuarioauditoria", programacion.getIdUsuarioAuditoria())
	    		.addValue("p_idprogramacionmedica_cabecera", programacion.getIdProgramacionMedicaCabecera());
	    
   		Map<String, Object> out = simpleJdbcCallProg.execute(param);
		//List<Object>  list =(List<Object>) out.get("o_idprogramacion") ;
   	  	int idProgramacion= (int) out.get("o_idprogramacion");
		
 	    if (idProgramacion != 0) {
	   	  response= idProgramacion;
        }
		return response;		
	};
	
	@Override
	public int   programacionMedicaActualizar(ProgramacionMedicaEntity programacion) {
		int response=0 ;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProg = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					 new SqlParameter("p_idespecialidad",      	Types.INTEGER)
				            					,new SqlParameter("p_idturno",     			Types.INTEGER)
				            					,new SqlParameter("p_color", 				Types.VARCHAR)
				            					,new SqlParameter("p_idservicio", 			Types.INTEGER)
				            					,new SqlParameter("p_idmedico", 			Types.INTEGER)
				            					,new SqlParameter("p_iddepartamento", 		Types.INTEGER)
				            					,new SqlParameter("p_idtiposervicio", 		Types.INTEGER)
				            					,new SqlParameter("p_fecha", 				Types.VARCHAR)
				            					,new SqlParameter("p_horainicio", 			Types.VARCHAR)
				            					,new SqlParameter("p_horafin", 				Types.VARCHAR)
				            					,new SqlParameter("p_descripcion", 			Types.VARCHAR)
				            					,new SqlParameter("p_idtipoprogramacion",	Types.INTEGER)
				            					,new SqlParameter("p_fechareg", 			Types.VARCHAR)
				            					,new SqlParameter("p_tiempopromedioatencion", Types.INTEGER)
				            					,new SqlParameter("p_citaadicionalbloqueada", Types.VARCHAR)
				            					,new SqlParameter("p_motivobloqueocitaadicional", Types.VARCHAR)
				            					,new SqlParameter("p_bloqueacitasprogramacion", Types.VARCHAR)
				            					,new SqlParameter("p_idusuarioauditoria", Types.INTEGER)
				            					,new SqlOutParameter("o_idprogramacion",     Types.INTEGER)				            					
				            					);
		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idespecialidad", programacion.getIdEspecialidad())	    
	    		.addValue("p_idturno", programacion.getIdTurno())	    
	    		.addValue("p_color", programacion.getColor())	    
	    		.addValue("p_idservicio", programacion.getIdServicio())	    
	    		.addValue("p_idmedico", programacion.getIdMedico())	    
	    		.addValue("p_iddepartamento", programacion.getIdDepartamento())	    
	    		.addValue("p_idtiposervicio", programacion.getIdTipoServicio())	    
	    		.addValue("p_fecha", programacion.getFecha())	    
	    		.addValue("p_horainicio", programacion.getHoraInicio())	    
	    		.addValue("p_horafin", programacion.getHoraFin())	    
	    		.addValue("p_descripcion", programacion.getDescripcion())	    
	    		.addValue("p_idtipoprogramacion", programacion.getIdTipoProgramacion())	    
	    		.addValue("p_fechareg", programacion.getFechaReg())	    
	    		.addValue("p_tiempopromedioatencion", programacion.getTiempoPromedioAtencion())	    
	    		.addValue("p_citaadicionalbloqueada", programacion.getCitaAdicionalBloqueada())	    
	    		.addValue("p_motivobloqueocitaadicional", programacion.getMotivoBloqueoCitaAdicional())	    
	    		.addValue("p_bloqueacitasprogramacion", programacion.getBloqueaCitasProgramacion())	    
	    		.addValue("p_idusuarioauditoria", programacion.getIdUsuarioAuditoria());
	    
   		Map<String, Object> out = simpleJdbcCallProg.execute(param);
		//List<Object>  list =(List<Object>) out.get("o_idprogramacion") ;
   	  	int idProgramacion= (int) out.get("o_idprogramacion");
		
 	    if (idProgramacion != 0) {
	   	  response= idProgramacion;
        }
		return response;		
	};
	

	@Override
	public List<ProgramacionMedicaEntity>  leerXIdProgramacionMedicaCabecera(int  idProgramacionMedicaCabecera) {
		
		List<ProgramacionMedicaEntity> response = null;
	    List<ProgramacionMedicaEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProg = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_xidprogramacion_cabecera_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					 new SqlParameter("p_idprogramacionmedica_cabecera",      Types.INTEGER)
				            					,new SqlOutParameter("cur",     Types.OTHER )				            					
				            					);
   		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idprogramacionmedica_cabecera", idProgramacionMedicaCabecera)   
;    
   		Map<String, Object> out = simpleJdbcCallProg.execute(param);
   		System.out.println("OUT  turnos "+out);
		List<Object>  list =(List<Object>) out.get("cur") ;
 	    if (!list.isEmpty()) {
 			for (Object row : list) {
 				ProgramacionMedicaEntity programaciones = new ProgramacionMedicaEntity(); 				
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			programaciones = objectMapper.convertValue(row,ProgramacionMedicaEntity.class) ;
	 			res.add(programaciones);
 			}
 			response =res;
 	    } else	{
 	    	response = null;
     	}		
		return response;
	}
	
	
	public void eliminarXIdProgramacion(int idProgramacionMedica) {
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProg = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_xidprogramacion_eliminar")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( new SqlParameter("p_idprogramacionmedica", Types.INTEGER));
	    SqlParameterSource param = new MapSqlParameterSource().addValue("p_idprogramacionmedica", idProgramacionMedica);
   		Map<String, Object> out = simpleJdbcCallProg.execute(param);
	}
	

	
}





