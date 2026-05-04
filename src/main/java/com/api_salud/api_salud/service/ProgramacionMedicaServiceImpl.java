package com.api_salud.api_salud.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api_salud.api_salud.dto.ProgramacionMedicaCabeceraDTO;
import com.api_salud.api_salud.entity.ProgramacionMedicaCabeceraEntity;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.entity.TurnoDetalleEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.ProgramacionMedicaDao;

import com.api_salud.api_salud.request.ProgramacionMedicaCrearDetalleRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaCrearRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.MedicoResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaDiaResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaMesResponse;
import com.api_salud.api_salud.response.ProgramacionMedicaResponse;

import java.sql.Types;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;

@Service
public class ProgramacionMedicaServiceImpl implements ProgramacionMedicaService{

	@Autowired
	ProgramacionMedicaDao programacionMedicaDao ;

	@Autowired
	ProgramacionMedicaCabeceraService  programacionMedicaCabeceraService ;
	
	@Autowired
	UsuarioService  usuarioService ;
	
	@Autowired
	MedicoService medicoService;
	
	@Autowired
	TurnoDetalleService  turnoDetalleService;
	
	@Autowired
	CitaService  citaService;

	
	@Override
    @Transactional
    public ProgramacionMedicaMesResponse programacionMedicoCrear(ProgramacionMedicaCrearRequest request, int idUsuario) {
        
        // 1. Validación preventiva: si no hay datos, no procesamos nada
        if (request.getProgramacion() == null || request.getProgramacion().isEmpty()) {
            return null;
        }

        // 2. Ejecución de la lógica masiva en la base de datos
        // Delegamos el JSON completo al DAO, que llamará al Procedimiento Almacenado de Reconciliación
        programacionMedicaDao.crearProgramacionMasiva(idUsuario, request.getProgramacion());

        // 3. Recarga inteligente del calendario
        // Extraemos mes y año de la primera fecha enviada para devolver el mes actualizado al Front-end
        try {
            String fechaBase = request.getProgramacion().get(0).getFecha(); // Formato esperado: YYYYMMDD
            int ano = Integer.parseInt(fechaBase.substring(0, 4));
            int mes = Integer.parseInt(fechaBase.substring(4, 6));

            // Retornamos el mes completo ya actualizado
            return null;
//            return programacionMedicoMesLeer(mes, ano, request.getIdMedico(), request.getIdEspecialidad());
            
        } catch (Exception e) {
            // Manejo de error si el formato de fecha del objeto es incorrecto
            throw new RuntimeException("Error al procesar la fecha para recargar el calendario", e);
        }
    }
	
	@Override
	public ProgramacionMedicaResponse medicoFecha(ProgramacionMedicaRequest request) {
		return programacionMedicaDao.programacionMedicoFecha(
					request.getIdMedico()
				  , request.getFecha()
				  , request.getIdEspecialidad()
				
				);

	}

	@Override
	public ProgramacionMedicaMesResponse programacionMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) {
	    // 1. Intento obtener la programación real de la base de datos
	    List<ProgramacionMedicaCabeceraDTO> lista = programacionMedicaCabeceraService.xMedicoMesLeer(mes, ano, idMedico, idEspecialidad);
	    
	    // 2. CONTROL DE VACÍO: Si no hay datos, devolvemos el mes en blanco de inmediato
	    // Esto evita procesar el mapa y el bucle innecesariamente
	    System.out.println("PASO MES LEER ");
	    if (lista == null || lista.isEmpty()) {
	        return programacionMedicoMesBlancoLeer(mes, ano, idMedico, idEspecialidad);
	    }

	    // 3. Si llegamos aquí, hay datos. Procedemos con la lógica optimizada de Mapa (O(1))
//	    int tiempoPromedio = medicoService.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
	    Map<Integer, ProgramacionMedicaCabeceraDTO> mapaProgramacion = new HashMap<>();
	    
	    for (ProgramacionMedicaCabeceraDTO dto : lista) {
	        try {
	            // Extraemos el día: YYYYMMDD -> DD
	            int dia = Integer.parseInt(dto.getFecha().substring(6, 8));
	            mapaProgramacion.put(dia, dto);
	        } catch (Exception e) {
	            // Ignorar errores de formato menores para no romper toda la consulta
	        }
	    }

	    List<ProgramacionMedicaDiaResponse> listaDias = new ArrayList<>(31);
	    LocalDate baseDate = LocalDate.of(ano, mes, 1);
	    int diasEnMes = baseDate.lengthOfMonth();
	    String prefijoFecha = String.format("%04d%02d", ano, mes);

	    for (int day = 1; day <= diasEnMes; day++) {
	        String fechaYYYYMMDD = prefijoFecha + String.format("%02d", day);
	        String dayOfWeekText = getFastDayOfWeek(baseDate.withDayOfMonth(day));
	        
	        ProgramacionMedicaDiaResponse p = new ProgramacionMedicaDiaResponse();
	        p.setId(day);
	        p.setDia(day);
	        p.setDiaSemana(dayOfWeekText);
	        p.setFecha(fechaYYYYMMDD);
//	        p.setTiempoPromedioAtencion(tiempoPromedio);
	        p.setTiempoPromedioAtencion(0);
	        p.setIdMedico(idMedico);
	        p.setIdEspecialidad(idEspecialidad);

	        // Búsqueda instantánea en el mapa
	        ProgramacionMedicaCabeceraDTO entidad = mapaProgramacion.get(day);

	        if (entidad != null) {
	            p.setIdProgramacion(entidad.getIdProgramacionMedicaCabecera());
	            p.setIdTurno(entidad.getIdTurno());
	            p.setColor(entidad.getColor());
	            p.setDescripcionTurno(entidad.getDescripcionTurno());
	            p.setIdServicio(entidad.getIdServicio());
	            p.setCodigoServicio(entidad.getCodigoServicio());
	        } else {
	            // Valores por defecto para días sin programación
	            p.setIdProgramacion(0);
	            p.setIdTurno(0);
	            p.setHoraInicio("");
	            p.setHoraFin("");
	            p.setIdServicio(0);
	            p.setIdDepartamento(0);
	        }
	        listaDias.add(p);
	    }

	    ProgramacionMedicaMesResponse response = new ProgramacionMedicaMesResponse();
	    response.setProgramacionMedicaDiaResponse(listaDias);
	    return response;
	}

	
	@Override		
	public ProgramacionMedicaMesResponse programacionMedicoMesBlancoLeer(int mes, int ano, int idMedico, int idEspecialidad) {
	    
	    // 1. Pre-cálculo de datos fijos
	    ProgramacionMedicaMesResponse response = new ProgramacionMedicaMesResponse();
	    List<ProgramacionMedicaDiaResponse> listaDias = new ArrayList<>(31); // Inicializar con tamaño esperado
	    
	    LocalDate date = LocalDate.of(ano, mes, 1);
	    int diasEnMes = date.lengthOfMonth();
	 //   int tiempoPromedio = medicoService.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
	    
	    // Usar un prefijo para la fecha para evitar formatear el año y mes 31 veces
	    // Resultado esperado: "202605"
	    String prefijoFecha = String.format("%04d%02d", ano, mes);

	    for (int day = 1; day <= diasEnMes; day++) {
	        LocalDate currentDate = date.withDayOfMonth(day);
	        
	        // 2. Optimización de Fecha: Concatenación simple en lugar de formatter.format
	        String fechaYYYYMMDD = prefijoFecha + String.format("%02d", day);
	        
	        // 3. Optimización de Día de Semana: Evitar transformaciones pesadas
	        String dayOfWeekText = getFastDayOfWeek(currentDate);

	        ProgramacionMedicaDiaResponse p = new ProgramacionMedicaDiaResponse();
	        p.setId(day);
	        p.setIdProgramacion(0);
	        p.setDia(day);
	        p.setDiaSemana(dayOfWeekText);
	        p.setFecha(fechaYYYYMMDD);
	        p.setHoraInicio("");
	        p.setHoraFin("");
//	        p.setTiempoPromedioAtencion(tiempoPromedio);
	        p.setTiempoPromedioAtencion(0);
	        p.setIdServicio(0);
	        p.setIdEspecialidad(idEspecialidad);
	        p.setIdDepartamento(0);
	        p.setIdMedico(idMedico);
	        
	        listaDias.add(p);
	    }
	    System.out.println("termino en blanco ");
	    
	    response.setProgramacionMedicaDiaResponse(listaDias);
	    return response;	
	}

	
	private String getFastDayOfWeek(LocalDate date) {
	    switch (date.getDayOfWeek()) {
	        case MONDAY:    return "LU";
	        case TUESDAY:   return "MA";
	        case WEDNESDAY: return "MI";
	        case THURSDAY:  return "JU";
	        case FRIDAY:    return "VI";
	        case SATURDAY:  return "SA";
	        case SUNDAY:    return "DO";
	        default: return "";
	    }
	}
	
    private static String getTwoCharDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase();        
    }	
		
    public static String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

   //Entity
    static class programacionResponse {
		int idProgramacion ;
		public int getIdProgramacion() {
				return idProgramacion;
		}
		public void setIdProgramacion(int idProgramacion) {
				this.idProgramacion = idProgramacion;
		}
	}
	

	
}



/*
@Override
public ProgramacionMedicaMesResponse  programacionMedicoCrear(ProgramacionMedicaCrearRequest request, int idUsuario ) {
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    DateFormat horaFormat = new SimpleDateFormat("HH:mm");
    String fechaRegistro = dateFormat.format(new Date());
    String horaRegistro = horaFormat.format(new Date());
    int idTipoProgramacion =1;
	int idDepartamento = 0;
	int idMedico = request.getIdMedico();
	int idServicio = request.getIdServicio();
	int idTipoServicio = 1;
	
	for (ProgramacionMedicaDiaResponse detalleRequest : request.getProgramacion()) {
		   int idProgramacionMedicaCabeceraActual = detalleRequest.getIdProgramacion();
			// AGREGAR : NO TIENE IDPROGRAMACION  
				if(idProgramacionMedicaCabeceraActual==0)   {
					// AGREGA: SI TIENE TURNO U HORARIO SELECCIONADO 
					if (detalleRequest.getIdTurno() != 0) {	
			          ProgramacionMedicaCabeceraEntity cabecera =new ProgramacionMedicaCabeceraEntity();
			          cabecera.setIdMedico(idMedico);
			          cabecera.setIdEspecialidad(detalleRequest.getIdEspecialidad());
			          cabecera.setIdServicio(detalleRequest.getIdServicio());
			          cabecera.setIdTurno(detalleRequest.getIdTurno());
			          cabecera.setFecha(detalleRequest.getFecha());
			          cabecera.setFechaReg(fechaRegistro);
			          cabecera.setIdUsuario(idUsuario);
			    	   // AGREGAR CABECERA 
			    	   int idProgramacionMedicaCabecera = programacionMedicaCabeceraService.crear(cabecera);
					   if (idProgramacionMedicaCabecera!=0) {
						   // SELECCIONAR TURNOS DEL DIA 
							List<TurnoDetalleEntity> turnos =  turnoDetalleService.xIdTurnoLeer(detalleRequest.getIdTurno());
							// AGREGAR TURNO POR TURNO
							for (TurnoDetalleEntity  t : turnos) {
						          ProgramacionMedicaEntity p =new ProgramacionMedicaEntity();
						      	   p.setIdMedico(idMedico);
						    	   p.setIdDepartamento(idDepartamento);
						    	   p.setIdTipoServicio(idTipoServicio);
						    	   p.setTiempoPromedioAtencion(detalleRequest.getTiempoPromedioAtencion());
//						    	   p.setTiempoPromedioAtencion(tiempoPromedioAtencion);
						    	   p.setIdTipoProgramacion(idTipoProgramacion);
						    	   p.setIdProgramacionMedica(detalleRequest.getIdProgramacion());
						     	   p.setIdEspecialidad(detalleRequest.getIdEspecialidad());
						    	   p.setIdServicio(detalleRequest.getIdServicio());
//						    	   p.setIdServicio(idServicio);
						    	   p.setIdTurno(detalleRequest.getIdTurno());
						    	   p.setFecha(detalleRequest.getFecha());
							       p.setHoraInicio( t.getHoraInicio());
							       p.setHoraFin(t.getHoraFin());
						    	   p.setFechaReg(fechaRegistro);
						    	   p.setDescripcion(detalleRequest.getDescripcionTurno());
						    	   p.setColor(detalleRequest.getColor());
						    	   p.setCitaAdicionalBloqueada("");
						    	   p.setMotivoBloqueoCitaAdicional("");
						    	   p.setBloqueaCitasProgramacion("");
						    	   p.setIdUsuarioAuditoria(idUsuario);        	
						    	   p.setIdProgramacionMedicaCabecera(idProgramacionMedicaCabecera);        	
					        	   int id = programacionMedicaDao.programacionMedicoCrear(p);
							}
					   }		
					}
					
				} else { 	// MODIFICAR :  TIENE IDPROGRAMACION

							// Seleccionar los turnos del dia programados x id Programcion cabecera
							List<ProgramacionMedicaEntity> programacionesTurno =	 programacionMedicaDao.leerXIdProgramacionMedicaCabecera(idProgramacionMedicaCabeceraActual);

							int idProgramacionMedicaEncontrado=0;
							// BUSCAR CITAS: Seleccionar citas que se asignaronen la programacion 
							for (ProgramacionMedicaEntity  e : programacionesTurno) {
								 idProgramacionMedicaEncontrado = citaService.leerXIdProgramacionMedica(e.getIdProgramacionMedica()) ;
							}
							
							//ELIMINAR PROGRAMACIONES: Si no tiene citas creadas o asignadas 
							if(idProgramacionMedicaEncontrado == 0) {
									for (ProgramacionMedicaEntity  e : programacionesTurno) {
										programacionMedicaDao.eliminarXIdProgramacion(e.getIdProgramacionMedica());
									}
									
									if (detalleRequest.getIdTurno() == 0) {
										// ELIMINAR CABECERA:  	
						  				 programacionMedicaCabeceraService.eliminarXIdProgramacionMedicaCabecera(idProgramacionMedicaCabeceraActual);
									} else {
										// MODIFICAR CABECERA: 
											 ProgramacionMedicaCabeceraEntity programacion = new ProgramacionMedicaCabeceraEntity() ;
											 programacion.setIdProgramacionMedicaCabecera(idProgramacionMedicaCabeceraActual);
											 programacion.setIdEspecialidad(detalleRequest.getIdEspecialidad());
											 programacion.setIdServicio(detalleRequest.getIdServicio());
//											 programacion.setIdServicio(idServicio);
											 programacion.setIdTurno(detalleRequest.getIdTurno());
											 programacion.setIdMedico(detalleRequest.getIdMedico());
											 programacion.setFecha(detalleRequest.getFecha());
											 programacion.setFechaReg(fechaRegistro);
											 programacion.setIdUsuario(idUsuario);
						  				 	 programacionMedicaCabeceraService.actualizar(programacion);
						  				 // CREAR PROGRAMACION  	 
													// SELECCIONAR TURNOS DEL DIA 
													List<TurnoDetalleEntity> turnos =  turnoDetalleService.xIdTurnoLeer(detalleRequest.getIdTurno());
													// AGREGAR TURNO POR TURNO
													for (TurnoDetalleEntity  t : turnos) {
												          ProgramacionMedicaEntity p =new ProgramacionMedicaEntity();
												      	   p.setIdMedico(idMedico);
												    	   p.setIdDepartamento(idDepartamento);
												    	   p.setIdTipoServicio(idTipoServicio);
												    	   p.setTiempoPromedioAtencion(detalleRequest.getTiempoPromedioAtencion());
//												    	   p.setTiempoPromedioAtencion(tiempoPromedioAtencion);
												    	   p.setIdTipoProgramacion(idTipoProgramacion);
												    	   p.setIdProgramacionMedica(detalleRequest.getIdProgramacion());
												     	   p.setIdEspecialidad(detalleRequest.getIdEspecialidad());
												    	   p.setIdServicio(detalleRequest.getIdServicio());
												    //	   p.setIdServicio(idServicio);
												    	   p.setIdTurno(detalleRequest.getIdTurno());
												    	   p.setFecha(detalleRequest.getFecha());
													       p.setHoraInicio( t.getHoraInicio());
													       p.setHoraFin(t.getHoraFin());
												    	   p.setFechaReg(fechaRegistro);
												    	   p.setDescripcion(detalleRequest.getDescripcionTurno());
												    	   p.setColor(detalleRequest.getColor());
												    	   p.setCitaAdicionalBloqueada("");
												    	   p.setMotivoBloqueoCitaAdicional("");
												    	   p.setBloqueaCitasProgramacion("");
												    	   p.setIdUsuarioAuditoria(idUsuario);        	
												    	   p.setIdProgramacionMedicaCabecera(idProgramacionMedicaCabeceraActual);        	
											        	   int id = programacionMedicaDao.programacionMedicoCrear(p);
													}
									}	 
					}
					
				
		} 
    }
	
	return null;
}		
*/
/*	
@Override
public ProgramacionMedicaResponse medicoTodos(ProgramacionMedicaRequest request) {
	ProgramacionMedicaResponse retorno = null;
	int idMedico = request.getIdMedico();
	int idEspecialidad = request.getIdEspecialidad();
	if(idMedico>0  &&  idEspecialidad>0) {
		retorno = programacionMedicaDao.programacionMedicoTodos(idMedico, idEspecialidad);
	}
 	return retorno;	
}
*/	

/*
@Override
public ProgramacionMedicaMesResponse  programacionMedicaActualizar(ProgramacionMedicaCrearRequest request, int idUsuario ) {

	return null;
}		
*/


/*	
@Override
public ProgramacionMedicaMesResponse  programacionMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) {
	
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    int tiempoPromedio = medicoService.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
	ProgramacionMedicaMesResponse  response = null;
	List<ProgramacionMedicaCabeceraDTO>  lista =  programacionMedicaCabeceraService.xMedicoMesLeer(mes, ano, idMedico,idEspecialidad);

	List<ProgramacionMedicaDiaResponse>  listaDias = new ArrayList<>();
	if(lista!= null) {
     	LocalDate startDate = LocalDate.of(ano, mes, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        int id=1;
        for (int day = 1; day <= endDate.getDayOfMonth(); day++) {
            LocalDate currentDate = LocalDate.of(ano, mes, day);
            String  fechaYYYYMMDD = currentDate.format(formatter);
	        String dayOfWeekText = removeAccents(getTwoCharDayOfWeek(currentDate));
        	boolean encontrado =false;
            for (ProgramacionMedicaCabeceraDTO entidad : lista) {
                if (Integer.parseInt(entidad.getFecha().substring(6,8))  == day) {
                	ProgramacionMedicaDiaResponse p = new ProgramacionMedicaDiaResponse();
                	p.setId(id);
                	p.setIdProgramacion(entidad.getIdProgramacionMedicaCabecera());
                	p.setDia(Integer.parseInt(entidad.getFecha().substring(6,8)) );
                	p.setDiaSemana(dayOfWeekText.substring(0,2 ));
                	p.setFecha(fechaYYYYMMDD);
                	p.setIdTurno(entidad.getIdTurno());
                	
                	p.setColor(entidad.getColor());
                	p.setDescripcionTurno(entidad.getDescripcionTurno());
                	p.setIdServicio(entidad.getIdServicio());
                	p.setCodigoServicio(entidad.getCodigoServicio());
                	p.setIdEspecialidad(entidad.getIdEspecialidad());
                	p.setIdMedico(entidad.getIdMedico());	                	
                	p.setTiempoPromedioAtencion(tiempoPromedio);
                	
                	listaDias.add(p);
                	encontrado=true;
                	id++;
                }
            }
	            if(encontrado==false) {
                	ProgramacionMedicaDiaResponse p = new ProgramacionMedicaDiaResponse();
                	p.setId(id);
                	p.setIdProgramacion(0);
                	p.setDia(day);
                	p.setDiaSemana(dayOfWeekText.substring(0,2 ));
                	p.setHoraInicio("");
                	p.setHoraFin("");
                	p.setFecha(fechaYYYYMMDD);

                	p.setTiempoPromedioAtencion(tiempoPromedio);
                	p.setIdServicio(0);
                	p.setIdEspecialidad(idEspecialidad);
                	p.setIdDepartamento(0);
                	p.setIdMedico(idMedico);	                	
                	
                	listaDias.add(p);
                	id++;
	            }
             	
            }
        	
	            response = new ProgramacionMedicaMesResponse();
        	response.setProgramacionMedicaDiaResponse(listaDias);
        }
		return response;	
    }	
*/



/*
@Override		
public ProgramacionMedicaMesResponse  programacionMedicoMesBlancoLeer(int mes, int ano, int idMedico, int idEspecialidad) {
	
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	ProgramacionMedicaMesResponse  response = new ProgramacionMedicaMesResponse();
	List<ProgramacionMedicaDiaResponse>  listaDias = new ArrayList<>();
     	LocalDate startDate = LocalDate.of(ano, mes, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        int tiempoPromedio = medicoService.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
        
        int id=1;
        for (int day = 1; day <= endDate.getDayOfMonth(); day++) {
            LocalDate currentDate = LocalDate.of(ano, mes, day);
            String  fechaYYYYMMDD = currentDate.format(formatter);
	        String dayOfWeekText = removeAccents(getTwoCharDayOfWeek(currentDate));
                	ProgramacionMedicaDiaResponse p = new ProgramacionMedicaDiaResponse();
                	p.setId(id);
                	p.setIdProgramacion(0);
                	p.setDia(day);
                	p.setDiaSemana(dayOfWeekText.substring(0,2 ));
                	p.setHoraInicio("");
                	p.setHoraFin("");
                	p.setFecha(fechaYYYYMMDD);
                	
                	p.setTiempoPromedioAtencion(tiempoPromedio);
                	p.setIdServicio(0);
                	p.setIdEspecialidad(idEspecialidad);
                	p.setIdDepartamento(0);
                	p.setIdMedico(idMedico);
                	listaDias.add(p);
                	id++;
        }
        	
       response.setProgramacionMedicaDiaResponse(listaDias);
       return response;	
   }*/