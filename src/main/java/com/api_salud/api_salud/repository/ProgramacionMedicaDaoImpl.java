package com.api_salud.api_salud.repository;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

import com.api_salud.api_salud.entity.CitaBloqueadaEntity;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.response.CitaDisponibleResponse;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;



@Repository
@Transactional
public class ProgramacionMedicaDaoImpl  implements ProgramacionMedicaDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallProg;
	private SimpleJdbcCall simpleJdbcCallCita;  

	
//	@Autowired
//	CitaService citaService;

	@Autowired
	private CitaBloqueadaDao citaBloqueadaDao;

//	@Autowired
//	ProgramacionMedicaDao programacionMedicaDao;  
	
	
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
//			    	CitaResponse cf = citaService.citaDisponible(c); // llamar citas de programacion
			    	CitaResponse cf = citasDisponiblesDia(idMedico, null, idEspecialidad); // llamar citas de programacion
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
	
	
	
	
// -------------------------------------------

		
	@Override
	public CitaResponse citasDisponiblesDia(int idMedico, String fecha, int idEspecialidad ) {
		CitaResponse response = new CitaResponse();
		//Obtener la Programacion Medica solo hora de inicio y fin 
		ProgramacionMedicaResponse programacionMedica = null;  
//		programacionMedica = programacionMedicaDao.programacionMedicoFecha(idMedico,fecha,idEspecialidad);
		programacionMedica = programacionMedicoFecha(idMedico,fecha,idEspecialidad);
		
		// Recorrer programaciones del dia sin tomar la hora final de programacion
		List<CitaDisponibleResponse> citaProgramadaDia = new ArrayList<>();
		List<String> citasProgramadasDiaTodas = new ArrayList<>();
		for(ProgramacionMedicaEntity p : programacionMedica.getProgramacionMedica()) {
			citasProgramadasDiaTodas = crearCitasProgramadasDiaTodas(p.getFecha(),p.getHoraInicio(),p.getHoraFin());
			for (String  element : citasProgramadasDiaTodas) {
				System.out.println("SERVICIO "+p.getNombreServicio());
				if (!element.equals( p.getHoraFin())) {   //no exeder cupos no tomar hora de fin de programacion
					CitaDisponibleResponse e =new CitaDisponibleResponse();
					e.setIdProgramacion(p.getIdProgramacionMedica());
					e.setIdServicio(p.getIdServicio());
					e.setNombreServicio(p.getNombreServicio());
					e.setHoraInicio(element);
					citaProgramadaDia.add(e);
				}
			}			
		}
	  
		
		//Obtener Citas No disponibles o ya registradas 
		List<String> citaRegistrada = new ArrayList<>();
		citaRegistrada = citasNoDisponibleDia(idMedico,fecha,idEspecialidad);
		
		//Obtener Citas Bloqueadas
		List<String>  citaBloqueada = new ArrayList<>(); 
		List<CitaBloqueadaEntity> citaBloqueadaRes =	citaBloqueadaDao.leerCitaBloqueada(idMedico,fecha);
  	    for (CitaBloqueadaEntity r : citaBloqueadaRes) {  
    			citaBloqueada.add(r.getHoraInicio()); 
  	    };
  
	
		List<CitaDisponibleResponse> sinRegistrar = new ArrayList<>();
		List<CitaDisponibleResponse> sinBloquear = new ArrayList<>();
  	    
		//Quitando los que ya estan registrados 
		if (!citaRegistrada.isEmpty() ) {

            for(CitaDisponibleResponse citaProgramada:  citaProgramadaDia) {
            	boolean isExists = citaRegistrada.contains(citaProgramada.getHoraInicio());
            	if(!isExists) {
                    sinRegistrar.add(citaProgramada);
      				 System.out.println("AGREGADA "+citaProgramada.getHoraInicio());
                    
            	}
		    }
			 			 
		}  else {
            for(CitaDisponibleResponse citaProgramada:  citaProgramadaDia) {
                    sinRegistrar.add(citaProgramada);
            }
		} 
		
		//Quitando los que estan bloqueados 
		 if (!citaBloqueada.isEmpty() ) {
	            for (CitaDisponibleResponse citaSinRegistrar : sinRegistrar) {
	            	boolean isExists1 = citaBloqueada.contains(citaSinRegistrar.getHoraInicio());
	            	if(!isExists1) {
	            		sinBloquear.add(citaSinRegistrar);	            		
	            	}
	            }
		 } else {
	            for (CitaDisponibleResponse citaSinRegistrar : sinRegistrar) {
	                    sinBloquear.add(citaSinRegistrar);
	            }
		 }	
		System.out.println("PASO citaBloqueada	");
		response.setCita(sinBloquear);
		return response;
	}
	

	
	
	
	
	/* METODOS  UTILIS NO INTERFAZ*/
	
	//1	
		@Override
		public List<String> crearCitasProgramadasDiaTodas(String  fecha, String horaInicio,String horaFin) {
			String DEFAULT_PATTERN = "yyyymmdd HH:mm:ss";		
			DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
			List<String>  cupos  = new ArrayList<>();
			Date dateInicio,dateFin;
				try {
					dateInicio = formatter.parse(fecha +" "+horaInicio+":00");
					dateFin = formatter.parse(fecha +" "+horaFin+":00");
					Calendar calendarInicio = Calendar.getInstance();
					Calendar calendarFin = Calendar.getInstance();
					calendarInicio.setTime(dateInicio); //tuFechaBase es un Date;
					calendarFin.setTime(dateFin); //tuFechaBase es un Date;
					int x =1;
					while (x != 0) {
						String h = String.format("%02d",calendarInicio.get(Calendar.HOUR_OF_DAY));
						String m = String.format("%02d",calendarInicio.get(Calendar.MINUTE));
						cupos.add( h+":"+m);
						x = calendarInicio.getTime().compareTo(calendarFin.getTime());
						calendarInicio.add(Calendar.MINUTE, 15); 
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}		
			return  cupos ;
		}

	//2
		@Override
		public List<String> citasNoDisponibleDia(int idMedico, String fecha, int idEspecialidad) {
			List<String> citaAsignada = new ArrayList<>();
			CitaResponse data =	asignadas( idMedico, fecha , idEspecialidad );
			for (CitaDisponibleResponse  element : data.getCita()) {
				citaAsignada.add(element.getHoraInicio());
			}
			
			System.out.println("CITA SEPARADA HORA ");
			CitaResponse dataSeparada =	leerCitaSeparada( idMedico, fecha , idEspecialidad );
			for (CitaDisponibleResponse  element1 : dataSeparada.getCita()) {
				System.out.println("CITA SEPARADA HORA "+element1.getHoraInicio());
				citaAsignada.add(element1.getHoraInicio());
			}
			

			
			return citaAsignada;
		}
	//3
		
		public CitaResponse asignadas(int idMedico, String fecha, int idEspecialidad) {
			CitaResponse response = null;
	    	System.out.println("INGRESO A asignadas "+idEspecialidad +" "+fecha+" "+idMedico);		
		    List<CitaDisponibleResponse> res =new ArrayList<>();
			jdbcTemplate.setResultsMapCaseInsensitive(true);
		    simpleJdbcCallCita = new SimpleJdbcCall(jdbcTemplate)
					    		.withProcedureName("igm_citas.citas_medico_idmedico_fecha_idespecialidad_web_leer")
					            .withoutProcedureColumnMetaDataAccess()
					            .declareParameters( 
					            					new SqlParameter("idMedico", Types.INTEGER)
					            					,new SqlParameter("fecha", Types.VARCHAR)
					            					,new SqlParameter("idEspecialidad", Types.INTEGER)
					            					,new SqlOutParameter("cur", Types.OTHER )				            					
					            					);
		    SqlParameterSource param = new MapSqlParameterSource()
		    		.addValue("idMedico", idMedico)	    
					.addValue("fecha", fecha)    
					.addValue("idEspecialidad", idEspecialidad);	    
	   		Map<String, Object> out = simpleJdbcCallCita.execute(param);
	 	    if (out == null) { response =null;
	        } else {
	            List<Object>  list = (List<Object>) out.get("cur") ;
	        	   for (Object row : list) {
	             	  CitaDisponibleResponse cita;
	        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
	        		  cita = objectMapper.convertValue(row, CitaDisponibleResponse.class) ;
	        	      res.add(cita);
	        	   }
	        	   response = new CitaResponse();
	        	   response.setCita(res);
	     	}		
			return response;
		}

		// Lista todas las citas ya registradas  al cliente
		public CitaResponse leerCitaSeparada(int idMedico, String fecha, int idEspecialidad) {
		    	System.out.println("Ingreso leerCitaSeparada "+idEspecialidad +" "+fecha+" "+idMedico);		
				CitaResponse response = null;
			    List<CitaDisponibleResponse> res =new ArrayList<>();
				jdbcTemplate.setResultsMapCaseInsensitive(true);
			    simpleJdbcCallCita = new SimpleJdbcCall(jdbcTemplate)
						    		.withProcedureName("igm_citas.citas_separadas_idmedico_fecha_idespecialidad_web_leer")
						            .withoutProcedureColumnMetaDataAccess()
						            .declareParameters( 
						            					new SqlParameter("idMedico", Types.INTEGER)
						            					,new SqlParameter("fecha", Types.VARCHAR)
						            					,new SqlParameter("idEspecialidad", Types.INTEGER)
						            					,new SqlOutParameter("cur", Types.OTHER )				            					
						            					);
			    SqlParameterSource param = new MapSqlParameterSource()
			    		.addValue("idMedico", idMedico)	    
						.addValue("fecha", fecha)    
						.addValue("idEspecialidad", idEspecialidad);	    
		   		Map<String, Object> out = simpleJdbcCallCita.execute(param);
		 	    if (out == null) { response =null;
//		    	System.out.println("Lista VAcia");		
		        } else {
		            List<Object>  list = (List<Object>) out.get("cur") ;
		        	   for (Object row : list) {
		             	  CitaDisponibleResponse cita;
		        	   	  ObjectMapper objectMapper = new ObjectMapper() ;
		        		  cita = objectMapper.convertValue(row, CitaDisponibleResponse.class) ;
		        	      res.add(cita);
		        	   }
		        	   response = new CitaResponse();
		        	   response.setCita(res);
		     	}		
				return response;
			}
				
		
		
	
	
}





