package com.api_salud.api_salud.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

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
	public ProgramacionMedicaResponse medicoTodos(ProgramacionMedicaRequest request) {
		ProgramacionMedicaResponse retorno = null;
		int idMedico = request.getIdMedico();
		int idEspecialidad = request.getIdEspecialidad();
		if(idMedico>0  &&  idEspecialidad>0) {
			retorno = programacionMedicaDao.programacionMedicoTodos(idMedico, idEspecialidad);
		}
	 	return retorno;	
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
	   }
	

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
//							    	   p.setTiempoPromedioAtencion(tiempoPromedioAtencion);
							    	   p.setIdTipoProgramacion(idTipoProgramacion);
							    	   p.setIdProgramacionMedica(detalleRequest.getIdProgramacion());
							     	   p.setIdEspecialidad(detalleRequest.getIdEspecialidad());
							    	   p.setIdServicio(detalleRequest.getIdServicio());
//							    	   p.setIdServicio(idServicio);
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
//												 programacion.setIdServicio(idServicio);
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
//													    	   p.setTiempoPromedioAtencion(tiempoPromedioAtencion);
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
		
	
	@Override
	public ProgramacionMedicaMesResponse  programacionMedicaActualizar(ProgramacionMedicaCrearRequest request, int idUsuario ) {
	
		return null;
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

