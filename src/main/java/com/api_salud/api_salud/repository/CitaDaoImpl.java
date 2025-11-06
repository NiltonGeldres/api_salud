package com.api_salud.api_salud.repository;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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


import com.api_salud.api_salud.entity.CitaBloqueadaEntity;
import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.entity.EspecialidadEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.MedicoEntity;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;

import com.api_salud.api_salud.response.CitaBloqueadaResponse;
import com.api_salud.api_salud.response.CitaDisponibleResponse;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;
import com.api_salud.api_salud.response.MedicoResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;
import com.api_salud.api_salud.service.CitaService;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
0 CitaProgramada 
1 CitaDisponible
2 CitaBloqueada
3 CitaSeparada
4 CitaRegistrada
*/

@Repository
@Transactional
public class CitaDaoImpl implements CitaDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall  simpleJdbcCallCita;  
	
	@Autowired
	ProgramacionMedicaDao programacionMedicaDao;  
	
	@Autowired	
	FacturacionDao facturacionDao;  
	
	@Autowired
	CitaBloqueadaDao citaBloqueadaDao;

	
	
	@Override
	public int crearCita(CitaEntity citaEntity) {
    	System.out.println(", Inicio DAO crearCita " + citaEntity.getIdPaciente());
		
    	int  response = 0;
    	List<CitaDisponibleResponse> res =new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCita = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            		   new SqlOutParameter("p_idcita"			,Types.INTEGER)
				            		  ,new SqlParameter("p_fecha"				,Types.VARCHAR)
				            		  ,new SqlParameter("p_horainicio"			,Types.VARCHAR)
				            		  ,new SqlParameter("p_horafin"				,Types.VARCHAR)
				            		  ,new SqlParameter("p_idpaciente"			,Types.INTEGER)
				            		  ,new SqlParameter("p_idestadocita"		,Types.INTEGER)
				            		  ,new SqlParameter("p_idatencion"			,Types.INTEGER)
				            		  ,new SqlParameter("p_idmedico"			,Types.INTEGER)
				            		  ,new SqlParameter("p_idespecialidad"		,Types.INTEGER)
				            		  ,new SqlParameter("p_idservicio"			,Types.INTEGER)
				            		  ,new SqlParameter("p_idprogramacion"		,Types.INTEGER)
				            		  ,new SqlParameter("p_idproducto"			,Types.INTEGER)
				            		  ,new SqlParameter("p_fechasolicitud"		,Types.VARCHAR)
				            		  ,new SqlParameter("p_horasolicitud"		,Types.VARCHAR)
				            		  ,new SqlParameter("p_escitaadicional"		,Types.BOOLEAN)
				            		  ,new SqlParameter("p_idusuario"			,Types.INTEGER)
	            					);
		int o_idcita=0;
	    SqlParameterSource param = new MapSqlParameterSource()
	    			.addValue("p_idcita"				,citaEntity.getIdCita())
			    	.addValue("p_fecha"					,citaEntity.getFecha())
					.addValue("p_horainicio"			,citaEntity.getHoraInicio())
				    .addValue("p_horafin"				,citaEntity.getHoraFin())
				    .addValue("p_idpaciente"			,citaEntity.getIdPaciente())
				    .addValue("p_idestadocita"			,citaEntity.getIdEstadoCita())
				    .addValue("p_idatencion"			,citaEntity.getIdAtencion())
			    	.addValue("p_idmedico"				,citaEntity.getIdMedico())
					.addValue("p_idespecialidad"		,citaEntity.getIdEspecialidad())
				    .addValue("p_idservicio"			,citaEntity.getIdServicio())
				    .addValue("p_idprogramacion"		,citaEntity.getIdProgramacion())
				    .addValue("p_idproducto"			,citaEntity.getIdProducto())
				    .addValue("p_fechasolicitud"		,citaEntity.getFechaSolicitud())
				    .addValue("p_horasolicitud"			,citaEntity.getHoraSolicitud())
				    .addValue("p_escitaadicional"		,citaEntity.isEsCitaAdicional())
	    			.addValue("p_idusuario"				,citaEntity.getIdUsuario());
	    		
   		Map<String, Object> out = simpleJdbcCallCita.execute(param);
 	    if (out != null) {
 	    	response= (int) out.get("p_idcita");
        }
    	System.out.println(", Termino DAO crearCita id " + response+ "  ");
		return response;
	}

	
	
	@Override
	public CitaResponse citasDisponiblesDia(int idMedico, String fecha, int idEspecialidad ) {
		CitaResponse response = new CitaResponse();
		//Obtener la Programacion Medica solo hora de inicio y fin 
		ProgramacionMedicaResponse programacionMedica = null;  
		programacionMedica = programacionMedicaDao.programacionMedicoFecha(idMedico,fecha,idEspecialidad);
		
		/* Recorrer programaciones del dia sin tomar la hora final de programacion*/
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
	
/*1*/	
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

/*2*/
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
/*3*/
	
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
//	    	System.out.println("Lista VAcia");		
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
			
	
	
/* -----------------------------------------------------------------------------*/
	@Override
	public int  leerXIdProgramacionMedica(int  idProgramacionMedica) {
   		System.out.println("idProgramacion "+idProgramacionMedica);
		int response = 0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallCita = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_citas.citas_xidprogramacionmedica_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					 new SqlParameter("p_idprogramacion",   Types.INTEGER)
				            					,new SqlOutParameter("o_idprogramacion",Types.INTEGER )				            					
				            					);
	    SqlParameterSource param = new MapSqlParameterSource().addValue("p_idprogramacion", idProgramacionMedica);
   		Map<String, Object> out = simpleJdbcCallCita.execute(param);
   		System.out.println(out);
		response = (int ) out.get("o_idprogramacion") ;
		
		return response;
	}
}




