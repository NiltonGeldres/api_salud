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

import com.api_salud.api_salud.entity.ProgramacionMedicaCabeceraEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Transactional
public class ProgramacionMedicaCabeceraDaoImpl implements ProgramacionMedicaCabeceraDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallProgCab;
	
	@Override
	public List<ProgramacionMedicaCabeceraEntity> xMedicoMesLeer(int mes, int ano, int idMedico) {
		System.out.println("programacionMedicoCabecera xMedicoMesLeer:  "+mes+" "+ano+" "+idMedico);
		
		List<ProgramacionMedicaCabeceraEntity> response = null;
	    List<ProgramacionMedicaCabeceraEntity> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_cabecera_xmedico_xmes_leer")
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
			Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
			System.out.println("programacionMedicoCabecera xMedicoMesLeer:  "+out);
		List<Object>  list =(List<Object>) out.get("cur") ;
		    if (!list.isEmpty()) {
				for (Object row : list) {
					ProgramacionMedicaCabeceraEntity cita = new ProgramacionMedicaCabeceraEntity();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			cita = objectMapper.convertValue(row, ProgramacionMedicaCabeceraEntity.class) ;
	 			res.add(cita);
				}
				response =res;
		    } else	{
		    	response = null;
	 	}		
		return response;
	}

	@Override
	public int crear(ProgramacionMedicaCabeceraEntity programacion) {
	/*	System.out.println("en Crear Cabecera  "+
	    		programacion.getIdEspecialidad()+"--"+	    
	    		programacion.getIdServicio()+"--"+	    
	    		programacion.getIdTurno()+"--"+	    
	    		programacion.getFecha()+"--"+	    
	    		programacion.getFechaReg()+"--"+	    
	    		programacion.getIdUsuario());
*/
				
		int response=0 ;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_cabecera_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					 new SqlParameter("p_idespecialidad",      	Types.INTEGER)
				            					,new SqlParameter("p_idservicio",   		Types.INTEGER)
				            					,new SqlParameter("p_idturno",     			Types.INTEGER)
				            					,new SqlParameter("p_idmedico", 			Types.INTEGER)
				            					,new SqlParameter("p_fecha", 				Types.VARCHAR)
				            					,new SqlParameter("p_fechareg", 			Types.VARCHAR)
				            					,new SqlParameter("p_idusuario",			Types.INTEGER)
				            					,new SqlOutParameter("o_idprogramacioncabecera",    Types.INTEGER)				            					
				            					);
		
		
		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idespecialidad", programacion.getIdEspecialidad())	    
	    		.addValue("p_idservicio", programacion.getIdServicio())	    
	    		.addValue("p_idturno", programacion.getIdTurno())	    
	    		.addValue("p_idmedico", programacion.getIdMedico())	    
	    		.addValue("p_fecha", programacion.getFecha())	    
	    		.addValue("p_fechareg", programacion.getFechaReg())	    
	    		.addValue("p_idusuario", programacion.getIdUsuario());
	    
			Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
		  	int idProgramacionCabecera= (int) out.get("o_idprogramacioncabecera");
		    if (idProgramacionCabecera != 0) {
		    	response= idProgramacionCabecera;
		    }	
		return response;		
	}

	@Override
	public int actualizar(ProgramacionMedicaCabeceraEntity programacion) {
		System.out.println("***ID PROGRAMACIONCABECERA   "
				+"--"+programacion.getIdProgramacionMedicaCabecera()
				+"--"+programacion.getIdEspecialidad()	
				+"--"+programacion.getIdServicio()
				+"--"+programacion.getIdTurno()
				+"--"+programacion.getIdMedico()
				+"--"+programacion.getFecha()
				+"--"+programacion.getFechaReg()
				+"--"+programacion.getIdUsuario()
				);		
		int response=0 ;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_cabecera_actualizar")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
	            					 new SqlParameter("p_idprogramacionmedica_cabecera",      	Types.INTEGER)
	            					,new SqlParameter("p_idespecialidad",      	Types.INTEGER)
	            					,new SqlParameter("p_idservicio",   		Types.INTEGER)
	            					,new SqlParameter("p_idturno",     			Types.INTEGER)
	            					,new SqlParameter("p_idmedico", 			Types.INTEGER)
	            					,new SqlParameter("p_fecha", 				Types.VARCHAR)
	            					,new SqlParameter("p_fechareg", 			Types.VARCHAR)
	            					,new SqlParameter("p_idusuario",			Types.INTEGER)
            					);
		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idprogramacionmedica_cabecera", programacion.getIdProgramacionMedicaCabecera())	    
	    		.addValue("p_idespecialidad", programacion.getIdEspecialidad())	    
	    		.addValue("p_idservicio", programacion.getIdServicio())	    
	    		.addValue("p_idturno", programacion.getIdTurno())	    
	    		.addValue("p_idmedico", programacion.getIdMedico())	    
	    		.addValue("p_fecha", programacion.getFecha())	    
	    		.addValue("p_fechareg", programacion.getFechaReg())	    
	    		.addValue("p_idusuario", programacion.getIdUsuario());
	    
			Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
		//List<Object>  list =(List<Object>) out.get("o_idprogramacion") ;
//		  	int idProgramacionCabecera= (int) out.get("o_idprogramacion_cabecera");
		
	//	    if (idProgramacionCabecera != 0) {
	  // 	  response= idProgramacionCabecera;
	   // }
		return response;		

	}

	public void eliminarXIdProgramacionMedicaCabecera(int idProgramacionMedicaCabecera) {
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_cabecera_eliminar")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( new SqlParameter("p_idprogramacionmedica_cabecera", Types.INTEGER));
	    SqlParameterSource param = new MapSqlParameterSource().addValue("p_idprogramacionmedica_cabecera",idProgramacionMedicaCabecera);
   		Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
	}
	

	
}
