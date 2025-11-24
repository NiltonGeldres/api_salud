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

import com.api_salud.api_salud.entity.ProgramacionMedicaCabeceraEntity;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.entity.TurnoDetalleEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.ProgramacionMedicaDao;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaCrearDetalleRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaCrearRequest;
import com.api_salud.api_salud.request.ProgramacionMedicaRequest;
import com.api_salud.api_salud.response.CitaResponse;
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
		System.out.println();
		return programacionMedicaDao.programacionMedicoFecha(
					request.getIdMedico()
				  , request.getFecha()
				  , request.getIdEspecialidad()
				
				);

	}
	
	
	@Override
	public CitaResponse citaDisponible(CitaRequest request) {
		
		return programacionMedicaDao.citasDisponiblesDia(
			request.getIdMedico(),
	        request.getFecha(),
	        request.getIdEspecialidad()
        );
	}
	
	
/*	
	@Override
	public ProgramacionMedicaMesResponse  programacionMedicoMesLeer1(int mes, int ano, int idMedico, int idEspecialidad) {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int tiempoPromedio = medicoService.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
		ProgramacionMedicaMesResponse  response = null;
//		List<ProgramacionMedicaEntity>  lista =  programacionMedicaDao.programacionMedicoMesLeer(
		List<ProgramacionMedicaCabeceraEntity>  lista =  programacionMedicaCabeceraService.xMedicoMesLeer(mes, ano, idMedico);

		System.out.print("LISTA =   "+lista);
		List<ProgramacionMedicaDiaResponse>  listaDias = new ArrayList<>();
		if(lista!= null) {
	     	LocalDate startDate = LocalDate.of(ano, mes, 1);
	        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
	        int id=1;
	        for (int day = 1; day <= endDate.getDayOfMonth(); day++) {
	            LocalDate currentDate = LocalDate.of(ano, mes, day);
	            String  fechaYYYYMMDD = currentDate.format(formatter);
		        String dayOfWeekText = removeAccents(getTwoCharDayOfWeek(currentDate));
		        System.out.println(fechaYYYYMMDD+"----"+currentDate+" -- "+dayOfWeekText);
            	boolean encontrado =false;
	            for (ProgramacionMedicaEntity entidad : lista) {
	                if (Integer.parseInt(entidad.getFecha().substring(6,8))  == day) {
	                	ProgramacionMedicaDiaResponse p = new ProgramacionMedicaDiaResponse();
	                	p.setId(id);
	                	p.setIdProgramacion(entidad.getIdProgramacionMedica());
	                	p.setDia(Integer.parseInt(entidad.getFecha().substring(6,8)) );
	                	p.setDiaSemana(dayOfWeekText.substring(0,2 ));
	                	p.setHoraInicio(entidad.getHoraInicio());
	                	p.setHoraFin(entidad.getHoraFin());
	                	p.setFecha(fechaYYYYMMDD);
	                	
	                	p.setColor(entidad.getColor());
	                	p.setDescripcionTurno(entidad.getDescripcion());
	                	p.setTiempoPromedioAtencion(entidad.getTiempoPromedioAtencion());
	                	p.setIdServicio(entidad.getIdServicio());
	                	p.setIdEspecialidad(entidad.getIdEspecialidad());
	                	p.setIdDepartamento(entidad.getIdDepartamento());
	                	p.setIdMedico(entidad.getIdMedico());	                	
	                	
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
	@Override
	public ProgramacionMedicaMesResponse  programacionMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int tiempoPromedio = medicoService.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
		ProgramacionMedicaMesResponse  response = null;
//		List<ProgramacionMedicaEntity>  lista =  programacionMedicaDao.programacionMedicoMesLeer(
		List<ProgramacionMedicaCabeceraEntity>  lista =  programacionMedicaCabeceraService.xMedicoMesLeer(mes, ano, idMedico);

		System.out.print("LISTA =   "+lista);
		List<ProgramacionMedicaDiaResponse>  listaDias = new ArrayList<>();
		if(lista!= null) {
	     	LocalDate startDate = LocalDate.of(ano, mes, 1);
	        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
	        int id=1;
	        for (int day = 1; day <= endDate.getDayOfMonth(); day++) {
	            LocalDate currentDate = LocalDate.of(ano, mes, day);
	            String  fechaYYYYMMDD = currentDate.format(formatter);
		        String dayOfWeekText = removeAccents(getTwoCharDayOfWeek(currentDate));
		        System.out.println(fechaYYYYMMDD+"----"+currentDate+" -- "+dayOfWeekText);
            	boolean encontrado =false;
	            for (ProgramacionMedicaCabeceraEntity entidad : lista) {
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
	                	p.setIdEspecialidad(entidad.getIdEspecialidad());
	                	p.setIdMedico(entidad.getIdMedico());	                	
	                	
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
		System.out.println("programacionMedicoMesBlancoLeer "+"   "+idMedico+"--"+idEspecialidad);
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		ProgramacionMedicaMesResponse  response = new ProgramacionMedicaMesResponse();
		List<ProgramacionMedicaDiaResponse>  listaDias = new ArrayList<>();
	     	LocalDate startDate = LocalDate.of(ano, mes, 1);
	        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
	        int tiempoPromedio = medicoService.tiempoPromedioAtencion_leer(idMedico, idEspecialidad);
			System.out.println("programacionMedicoMesBlancoLeer tiempoPromedio "+tiempoPromedio);
	        
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
		int idTipoServicio = 1;
		int tiempoPromedioAtencion =15 ;
		
		for (ProgramacionMedicaDiaResponse detalleRequest : request.getProgramacion()) {
				System.out.println("IDturno  " +detalleRequest.getIdTurno());
				if(detalleRequest.getIdTurno() != 0) {
			          ProgramacionMedicaCabeceraEntity pc =new ProgramacionMedicaCabeceraEntity();
			      	   pc.setIdMedico(idMedico);
			     	   pc.setIdEspecialidad(detalleRequest.getIdEspecialidad());
			    	   pc.setIdServicio(detalleRequest.getIdServicio());
			    	   pc.setIdTurno(detalleRequest.getIdTurno());
			    	   pc.setFecha(detalleRequest.getFecha());
			    	   pc.setFechaReg(fechaRegistro);
			    	   int idProgramacionMedicaCabecera = programacionMedicaCabeceraService.crear(pc);
						System.out.println("en Crear Programacion ID cbecera  "+ idProgramacionMedicaCabecera);
						if (idProgramacionMedicaCabecera!=0) {
							List<TurnoDetalleEntity> turnos =  turnoDetalleService.xIdTurnoLeer(detalleRequest.getIdTurno());
							for (TurnoDetalleEntity  t : turnos) {
							//	System.out.println("IDturnoDetalle  " +idTurno+"---- "+ t.getHoraInicio());
						          ProgramacionMedicaEntity p =new ProgramacionMedicaEntity();
						      	   p.setIdMedico(idMedico);
						    	   p.setIdDepartamento(idDepartamento);
						    	   p.setIdTipoServicio(idTipoServicio);
						    	   p.setTiempoPromedioAtencion(tiempoPromedioAtencion);
						    	   p.setIdTipoProgramacion(idTipoProgramacion);
						
						    	   p.setIdProgramacionMedica(detalleRequest.getIdProgramacion());
						     	   p.setIdEspecialidad(detalleRequest.getIdEspecialidad());
						    	   p.setIdServicio(detalleRequest.getIdServicio());
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
						        	
						    	   if(detalleRequest.getIdProgramacion()==0) {
						        	   int id = programacionMedicaDao.programacionMedicoCrear(p);
						    	   }
							}
						}	
				}	
        }
		
		
		//return programacionMedicaDao.programacionMedicoCrear(request);
		
		return null;
    }		
	
	*/
	

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
		int tiempoPromedioAtencion =15 ;
	   System.out.println("IDSERVICIO  "+idServicio);				   
		
		for (ProgramacionMedicaDiaResponse detalleRequest : request.getProgramacion()) {
			   int idProgramacionMedicaCabeceraActual = detalleRequest.getIdProgramacion();
			   System.out.println("DIa  "+detalleRequest.getDia()+ " ==>  "+idProgramacionMedicaCabeceraActual);				   
				// AGREGAR : NO TIENE IDPROGRAMACION  
					if(idProgramacionMedicaCabeceraActual==0)   {
						   System.out.println("AGREGAR ");											
						// AGREGA: SI TIENE TURNO U HORARIO SELECCIONADO 
						if (detalleRequest.getIdTurno() != 0) {	
				          ProgramacionMedicaCabeceraEntity cabecera =new ProgramacionMedicaCabeceraEntity();
				          cabecera.setIdMedico(idMedico);
				          cabecera.setIdEspecialidad(detalleRequest.getIdEspecialidad());
				          cabecera.setIdServicio(idServicio);
				          cabecera.setIdTurno(detalleRequest.getIdTurno());
				          cabecera.setFecha(detalleRequest.getFecha());
				          cabecera.setFechaReg(fechaRegistro);
				          cabecera.setIdUsuario(idUsuario);
				    	   // AGREGAR CABECERA 
				    	   int idProgramacionMedicaCabecera = programacionMedicaCabeceraService.crear(cabecera);
				    	   System.out.println("idProgramacionMedicaCabecera "+ idProgramacionMedicaCabecera);
						   if (idProgramacionMedicaCabecera!=0) {
							   // SELECCIONAR TURNOS DEL DIA 
							   System.out.println("TURNO SELECCIONADO "+detalleRequest.getIdTurno());
								List<TurnoDetalleEntity> turnos =  turnoDetalleService.xIdTurnoLeer(detalleRequest.getIdTurno());
								   System.out.println("turnos");
								// AGREGAR TURNO POR TURNO
								for (TurnoDetalleEntity  t : turnos) {
							          ProgramacionMedicaEntity p =new ProgramacionMedicaEntity();
							      	   p.setIdMedico(idMedico);
							    	   p.setIdDepartamento(idDepartamento);
							    	   p.setIdTipoServicio(idTipoServicio);
							    	   p.setTiempoPromedioAtencion(tiempoPromedioAtencion);
							    	   p.setIdTipoProgramacion(idTipoProgramacion);
							    	   p.setIdProgramacionMedica(detalleRequest.getIdProgramacion());
							     	   p.setIdEspecialidad(detalleRequest.getIdEspecialidad());
							    	   p.setIdServicio(idServicio);
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
									   System.out.println("Antes programacionMedicoCrear ");
						        	   int id = programacionMedicaDao.programacionMedicoCrear(p);
									   System.out.println("Despues programacionMedicoCrear ID:  "+id);
								}
						   }		
						}
						
					} else { 	// MODIFICAR :  TIENE IDPROGRAMACION
						   System.out.println("MODIFICAR " +idProgramacionMedicaCabeceraActual);											
	
								// Seleccionar los turnos del dia programados x id Programcion cabecera
								List<ProgramacionMedicaEntity> programacionesTurno =	 programacionMedicaDao.leerXIdProgramacionMedicaCabecera(idProgramacionMedicaCabeceraActual);
							   System.out.println("Modificar programacionesTurno "+ programacionesTurno);
	
								int idProgramacionMedicaEncontrado=0;
								// BUSCAR CITAS: Seleccionar citas que se asignaronen la programacion 
								for (ProgramacionMedicaEntity  e : programacionesTurno) {
									   System.out.println("Buscar citas ");
									 idProgramacionMedicaEncontrado = citaService.leerXIdProgramacionMedica(e.getIdProgramacionMedica()) ;
									   System.out.println("Salir  citas ");
								}
							   System.out.println("Modificar programacionesMedicaEncontrado en Citas  "+idProgramacionMedicaEncontrado);
								
								//ELIMINAR PROGRAMACIONES: Si no tiene citas creadas o asignadas 
							   System.out.println("MODIFICAR : idProgramacionMedicaEncontrada  "+idProgramacionMedicaEncontrado);
								if(idProgramacionMedicaEncontrado == 0) {
										for (ProgramacionMedicaEntity  e : programacionesTurno) {
										   System.out.println("Eliminar   "+e.getIdProgramacionMedica());
											programacionMedicaDao.eliminarXIdProgramacion(e.getIdProgramacionMedica());
										   System.out.println("Eliminado ");
										}
										
									    System.out.println("Eliminar Cabecera  "+detalleRequest.getIdTurno());
										if (detalleRequest.getIdTurno() == 0) {
											// ELIMINAR CABECERA:  	
							  				 programacionMedicaCabeceraService.eliminarXIdProgramacionMedicaCabecera(idProgramacionMedicaCabeceraActual);
											System.out.println("Modificar Eliminar cabecera ");
										} else {
											// MODIFICAR CABECERA: 
											   System.out.println("Actualizo  cabecera "+idUsuario);
												 ProgramacionMedicaCabeceraEntity programacion = new ProgramacionMedicaCabeceraEntity() ;
												 programacion.setIdProgramacionMedicaCabecera(idProgramacionMedicaCabeceraActual);
												 programacion.setIdEspecialidad(detalleRequest.getIdEspecialidad());
												 programacion.setIdServicio(idServicio);
												 programacion.setIdTurno(detalleRequest.getIdTurno());
												 programacion.setIdMedico(detalleRequest.getIdMedico());
												 programacion.setFecha(detalleRequest.getFecha());
												 programacion.setFechaReg(fechaRegistro);
												 programacion.setIdUsuario(idUsuario);
							  				 	 programacionMedicaCabeceraService.actualizar(programacion);
												   System.out.println("Actualizo  cabecera ");
							  				 // CREAR PROGRAMACION  	 
														// SELECCIONAR TURNOS DEL DIA 
														List<TurnoDetalleEntity> turnos =  turnoDetalleService.xIdTurnoLeer(detalleRequest.getIdTurno());
														   System.out.println("Selecciono turnos ");
														// AGREGAR TURNO POR TURNO
														for (TurnoDetalleEntity  t : turnos) {
													          ProgramacionMedicaEntity p =new ProgramacionMedicaEntity();
													      	   p.setIdMedico(idMedico);
													    	   p.setIdDepartamento(idDepartamento);
													    	   p.setIdTipoServicio(idTipoServicio);
													    	   p.setTiempoPromedioAtencion(tiempoPromedioAtencion);
													    	   p.setIdTipoProgramacion(idTipoProgramacion);
													    	   p.setIdProgramacionMedica(detalleRequest.getIdProgramacion());
													     	   p.setIdEspecialidad(detalleRequest.getIdEspecialidad());
													    	   p.setIdServicio(idServicio);
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
															   System.out.println("Antes modificar  programacionMedicoCrear MOD");
												        	   int id = programacionMedicaDao.programacionMedicoCrear(p);
															   System.out.println("Despues Modificar programacionMedicoCrear MOD ID "+id);
														}
										}	 
						}
						
					
			} 
        }
		
		
		//return programacionMedicaDao.programacionMedicoCrear(request);
		
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
