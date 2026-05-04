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

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.dto.PacienteCitadoDto;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	MedicoDao medicoDao;
	
	@Override
	public List<PacienteCitadoDto> citaPacienteListarPendientes(int idPaciente, String fecha) {
	    // Configuramos la llamada al procedimiento de PostgreSQL
	    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withSchemaName("igm_citas")
	            .withProcedureName("cita_paciente_listar_pendientes")
	            .withoutProcedureColumnMetaDataAccess()
	            .declareParameters(
	                new SqlParameter("p_idpaciente", Types.INTEGER),
	                new SqlParameter("p_fecha", Types.VARCHAR),
	                new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(PacienteCitadoDto.class))
	            );

	    // Parámetros de entrada
	    SqlParameterSource in = new MapSqlParameterSource()
	            .addValue("p_idpaciente", idPaciente)
	            .addValue("p_fecha", fecha)
	            .addValue("cur", "cur_pacientes"); // Nombre del cursor interno en Postgres

	    // Ejecución
	    Map<String, Object> out = call.execute(in);
       
	    // Retornamos la lista casteada
	    return (List<PacienteCitadoDto>) out.get("cur");
	}
	
	@Override
	public List<PacienteCitadoDto> citaMedicoListarDiaria(int idMedico, String fecha) {
	    // Configuramos la llamada al procedimiento de PostgreSQL
	    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withSchemaName("igm_citas")
	            .withProcedureName("cita_medico_listar_diaria")
	            .withoutProcedureColumnMetaDataAccess()
	            .declareParameters(
	                new SqlParameter("p_idmedico", Types.INTEGER),
	                new SqlParameter("p_fecha", Types.VARCHAR),
	                new SqlOutParameter("cur", Types.REF_CURSOR, new BeanPropertyRowMapper<>(PacienteCitadoDto.class))
	            );

	    // Parámetros de entrada
	    SqlParameterSource in = new MapSqlParameterSource()
	            .addValue("p_idmedico", idMedico)
	            .addValue("p_fecha", fecha)
	            .addValue("cur", "cur_pacientes"); // Nombre del cursor interno en Postgres

	    // Ejecución
	    Map<String, Object> out = call.execute(in);
       
	    // Retornamos la lista casteada
	    return (List<PacienteCitadoDto>) out.get("cur");
	}
	
	
	
	@Override
	public int crearCita(CitaEntity citaEntity) {
		
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
		return response;
	}

	
	
	@Override
	public CitaResponse citasDisponiblesDia(int idMedico, String fecha, int idEspecialidad ) {
		int tiempoPromedioAtencion = medicoDao.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
		CitaResponse response = new CitaResponse();
		ProgramacionMedicaResponse programacionMedica = null;  
		//-------------------------------------------------------- 
		//  OBTENER LA PROGRAMACION MEDICA SOLO PROCESA HORA DE INICIO Y FIN 
		programacionMedica = programacionMedicaDao.programacionMedicoFecha(idMedico,fecha,idEspecialidad);
		//-------------------------------------------------------- 
		
		/* Recorrer programaciones del dia sin tomar la hora final de programacion*/
		List<CitaDisponibleResponse> cuposProgramadaDia = new ArrayList<>();
		List<String> cuposProgramadasDiaTodas = new ArrayList<>();
		for(ProgramacionMedicaEntity p : programacionMedica.getProgramacionMedica()) {
			cuposProgramadasDiaTodas = crearCuposProgramadasDiaTodas(p.getFecha(),p.getHoraInicio(),p.getHoraFin(),tiempoPromedioAtencion);
			for (String  element : cuposProgramadasDiaTodas) {
				if (!element.equals( p.getHoraFin())) {   //no exeder cupos no tomar hora de fin de programacion
					CitaDisponibleResponse e =new CitaDisponibleResponse();
					e.setIdProgramacion(p.getIdProgramacionMedica());
					e.setIdServicio(p.getIdServicio());
					e.setNombreServicio(p.getNombreServicio());
					e.setHoraInicio(element);
					cuposProgramadaDia.add(e);
				}
			}			
		}
	  
		
		//Obtener Citas No disponibles o ya registradas 
		List<String> citaRegistrada = new ArrayList<>();
		citaRegistrada = cuposNoDisponibleDia(idMedico,fecha,idEspecialidad);
		
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

            for(CitaDisponibleResponse citaProgramada:  cuposProgramadaDia) {
            	boolean isExists = citaRegistrada.contains(citaProgramada.getHoraInicio());
            	
            	if(!isExists) {
                    sinRegistrar.add(citaProgramada);
                    
            	}
		    }
			 			 
		}  else {
            for(CitaDisponibleResponse citaProgramada:  cuposProgramadaDia) {
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
		response.setCita(sinBloquear);
		return response;
	}
	

/*1*/
	
	@Override
	public List<String> crearCuposProgramadasDiaTodas(String fecha, String horaInicio, String horaFin, int tiempoPromedioAtencion) {
	    // 1. Usar DateTimeFormatter (H:mm es más flexible que concatenar ceros manualmente)
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    List<String> cupos = new ArrayList<>();

	    try {
	        // 2. Usar LocalTime para manejar solo horas (la fecha no es necesaria para generar los slots)
	        LocalTime inicio = LocalTime.parse(horaInicio);
	        LocalTime fin = LocalTime.parse(horaFin);

	        // 3. Validación de seguridad para evitar bucles infinitos
	        if (tiempoPromedioAtencion <= 0) return cupos;

	        // 4. Lógica limpia: mientras la hora actual no supere el fin
	        LocalTime actual = inicio;
	        while (!actual.isAfter(fin)) {
	            cupos.add(actual.format(formatter));
	            
	            // 5. Avanzar el tiempo
	            actual = actual.plusMinutes(tiempoPromedioAtencion);
	            
	            // Evitar que el siguiente slot sea exactamente igual al fin si no quieres que se pase
	            if (actual.isAfter(fin)) break;
	        }

	    } catch (Exception e) {
	        // En lugar de e.printStackTrace(), usa un logger profesional
	        //log.error("Error al generar cupos: " + e.getMessage());
	    }

	    return cupos;
	}
	
/*2*/
	@Override
	public List<String> cuposNoDisponibleDia(int idMedico, String fecha, int idEspecialidad) {
		List<String> citaAsignada = new ArrayList<>();
		CitaResponse citasAsignadas =	obtenerCitasAsignadasDia( idMedico, fecha , idEspecialidad );
		for (CitaDisponibleResponse  element : citasAsignadas.getCita()) {
			citaAsignada.add(element.getHoraInicio());
		}
		
		CitaResponse citasSeparada =	obtenerCitaSeparada( idMedico, fecha , idEspecialidad );
		for (CitaDisponibleResponse  element1 : citasSeparada.getCita()) {
			citaAsignada.add(element1.getHoraInicio());
		}
		

		
		return citaAsignada;
	}
/*3*/
	
	public CitaResponse obtenerCitasAsignadasDia(int idMedico, String fecha, int idEspecialidad) {
		CitaResponse response = null;
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
	public CitaResponse obtenerCitaSeparada(int idMedico, String fecha, int idEspecialidad) {
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
		response = (int ) out.get("o_idprogramacion") ;
		return response;
	}
}




