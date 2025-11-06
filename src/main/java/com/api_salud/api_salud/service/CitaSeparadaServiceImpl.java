package com.api_salud.api_salud.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioResponse;
import com.api_salud.api_salud.repository.CitaSeparadaDao;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;

@Service
public class CitaSeparadaServiceImpl  implements CitaSeparadaService{
	
	@Autowired 
	CitaSeparadaDao citaSeparadaDao;

	@Autowired 
	CitaService  citaService;
	
	@Autowired 
	UsuarioDao usuarioDao;

	@Autowired 
	EmailService emailService  ;
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private FacturacionService facturacionService;
	
	@Override
	public CitaSeparadaResponse crearCitaSeparada( CitaSeparadaRequest request, int idUsuario) {
		String horaFin  = request.getHoraInicio();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        DateFormat horaFormat = new SimpleDateFormat("HH:mm");
	    String fechaSeparada = dateFormat.format(new Date());
	    String horaSeparada = horaFormat.format(new Date());
	    /* --- Leer el email del usuario -- */
//	    String mail = usuarioDao.usuarioLeer(idUsuario).getEmail();
	    Usuario usuario = usuarioDao.usuarioLeer(idUsuario);
//		CitaSeparadaResponse response = null; 
	   	System.out.println("CITA SEPARADA 1 " );	    
		 int  idCitaSeparada = 0;
	    if(usuario.getEmail() != null) {
//		    response = new CitaSeparadaResponse();
		    /* --- Crear la cita Separada -- */
			idCitaSeparada = citaSeparadaDao.crearCitaSeparada(
					request.getIdCitaSeparada(),
					request.getFecha() ,
					request.getHoraInicio() ,
					horaFin ,
				    request.getIdPaciente() ,
				    request.getIdMedico() ,
				    request.getIdEspecialidad() ,
				    request.getIdServicio() ,
				    request.getIdProgramacion() ,
				    0,
				    fechaSeparada,
				    horaSeparada,
				    request.getTipoUsuario(),
				    fechaSeparada,
				    request.getPrecioUnitario(),
				    idUsuario
				);
			System.out.println("CITA SEPARADA id "+idCitaSeparada);
		    if(idCitaSeparada!=0) {
					CitaSeparadaEntityResponse citaSeparadaEntityResponse = new CitaSeparadaEntityResponse();
					citaSeparadaEntityResponse = citaSeparadaDao.leerCitaSeparadaXIdCitaSeparada(idCitaSeparada)  ;
					System.out.println("citaSeparadaEntityResponse CITA SEPARADA "+citaSeparadaEntityResponse.getIdCitaSeparada());
			    	
			    	//	Usuario usuario = 	usuarioDao.usuarioLeer(idUsuario);
						CitaSeparadaEntity citaSeparada ; //= new CitaSeparadaEntity();
						 	try {
								emailService.sendEmail(
													usuario.getEmail()
													,"Solicitud de cita "
													,citaSeparadaEntityResponse.getNombreMedico()
													,citaSeparadaEntityResponse.getNumeroCuenta()
													,citaSeparadaEntityResponse.getPrecioUnitario()
													,usuario.getPrimer_nombre()+" "+usuario.getApellido_paterno() + " "+usuario.getApellido_materno()
											 );
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
			    		
			    }			
	
		    
	    }
		return null ;
	}	
	
	public CitaSeparadaResponse leerCitaSeparadaXIdPaciente(int idUsuario ) {
		CitaSeparadaResponse response = null; 
	    response = new CitaSeparadaResponse();
		response = citaSeparadaDao.leerCitaSeparadaXIdpaciente(idUsuario);
		return response;
	} ;

	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualXIdPaciente(int idUsuario ) {
		CitaSeparadaResponse response = null; 
	    response = new CitaSeparadaResponse();
		response = citaSeparadaDao.leerCitaSeparadaConPagoVirtualXIdpaciente(idUsuario);
		return response;
	} ;
	
	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualConcomprobanteXIdPaciente(int idUsuario ) {
		CitaSeparadaResponse response = null; 
	    response = new CitaSeparadaResponse();
		response = citaSeparadaDao.leerCitaSeparadaConPagoVirtualConcomprobanteXIdpaciente(idUsuario);
		return response;
	} ;
	
	public CitaSeparadaEntityResponse leerCitaSeparadaXIdCitaSeparada(int idCitaSeparada ) {
		CitaSeparadaEntityResponse response = null; 
//	    response = new CitaSeparadaEntity();
		response = citaSeparadaDao.leerCitaSeparadaXIdCitaSeparada(idCitaSeparada);
		return response;
	} ;
	
	public int confirmarCitaSeparada( CitaFacturacionRequest request) {
		
		int response =0 ;
		
		//citaService.crearCita(request);
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");  
        DateFormat horaFormat = new SimpleDateFormat("hh:mm");  
        String fechaString = dateFormat.format(date);
        String horaString = horaFormat.format(date);
        System.out.println("FECHS  "+fechaString);
        System.out.println("HORA  "+horaString);
        
		// * Crear Paciente * //
		int idUsuario = 0;
		String username= request.getUsuario();
//		Usuario usuario = usuarioService.usuarioUsernameLeer(username);
		Usuario usuario = usuarioService.usuarioLeer(request.getIdUsuario());
		int idPaciente =  pacienteService.crearConUsuario(usuario);
        
		if (idPaciente != 0 ) {
	        request.setIdPaciente(idPaciente);
	        request.setHoraSolicitud(horaString);
	        request.setFechaSolicitud(fechaString);
	        request.setIdUsuario(idUsuario);
			
			CitaEntity c = new CitaEntity();
			c.setIdMedico(request.getIdMedico());
			c.setFecha(request.getFecha());
			c.setHoraInicio(request.getHoraInicio());
		    c.setHoraFin(request.getHoraFin());
			c.setIdEspecialidad(request.getIdEspecialidad());
			c.setIdCita(0);
		    c.setIdPaciente(request.getIdPaciente());
		    c.setIdEstadoCita(1);
		    c.setIdAtencion(0);
		    c.setIdServicio(request.getIdServicio());
		    c.setIdProgramacion(request.getIdProgramacion());
		    c.setFechaSolicitud(request.getFechaSolicitud());
		    c.setHoraSolicitud(request.getHoraSolicitud());
		    c.setEsCitaAdicional(false);
		    c.setIdProducto(request.getIdProducto());
		    c.setIdUsuario(request.getIdUsuario());
		    
		    // Crear Cuenta de Atencion
	 		FacturacionCuentaAtencionEntity fca = new FacturacionCuentaAtencionEntity() ;
	 		fca.setIdCuentaAtencion(0);
	 		fca.setIdPaciente(request.getIdPaciente());
	 		fca.setFechaApertura(fechaString);
	 		fca.setHoraApertura(horaString);
	 		fca.setFechaCierre(fechaString);
	 		fca.setHoraCierre(horaString);
	 		fca.setTotalExonerado(0);
	 		fca.setTotalAsegurado(0);
	 		fca.setTotalPagado(request.getPrecioUnitario());
	 		fca.setIdEstado(2);
	 		fca.setTotalPorPagar(request.getPrecioUnitario());
	 		fca.setIdUsuarioCrea(request.getIdUsuario());
	 		fca.setIdUsuarioModifica(request.getIdUsuario());
	 		fca.setFechaCreacion(fechaString);
	 		fca.setFechaModificacion(fechaString);		
	 		
		    // Orden de servicio
			FacturacionOrdenServicioEntity  fos = new FacturacionOrdenServicioEntity();
			fos.setIdOrden(0) ;
			fos.setIdPuntoCarga(request.getIdPuntoCarga());
			fos.setIdPaciente(request.getIdPaciente());
			fos.setIdCuentaAtencion(0);
			fos.setIdServicioPaciente(request.getIdServicio());
			fos.setIdTipoFinanciamiento(request.getIdTipoFinanciamiento());
			fos.setIdFuenteFinanciamiento(request.getIdFuenteFinanciamiento());
			fos.setFechaCreacion(fechaString);
			fos.setIdUsuario(request.getIdUsuario());
			fos.setFechaDespacho(fechaString);
			fos.setIdUsuarioDespacho(request.getIdUsuario());
			fos.setIdEstadoFacturacion(1);	 		
		    
		    
			// -- Facturacion Servicio Despacho 
			List<FacturacionServicioDespachoEntity> listFacturacionServicioDespacho = new ArrayList<>() ; 
			FacturacionServicioDespachoEntity fosd = new FacturacionServicioDespachoEntity();	
			fosd.setIdOrden(0);
			fosd.setIdProducto(request.getIdProducto());
			fosd.setCantidad(1);
			fosd.setPrecio(request.getPrecioUnitario());
			fosd.setTotal(request.getPrecioUnitario());
			fosd.setLabConfHIS(null);
			fosd.setGrupoHIS(0);
			fosd.setSubGrupoHIS(0);
			listFacturacionServicioDespacho.add(fosd);
	
			// Cabecera de pagos
			FacturacionOrdenServicioPagoEntity fosp = new FacturacionOrdenServicioPagoEntity();
			fosp.setIdOrdenPago(0);
			fosp.setFechaCreacion(fechaString);
			fosp.setIdComprobantePago(0);
			fosp.setIdEstadoFacturacion(1);
			fosp.setIdOrden(1);			
			fosp.setIdOrdenPago(0);			
			fosp.setIdUsuario(idUsuario);
			fosp.setIdUsuarioExonera(idUsuario);
			fosp.setImporteExonerado(0);
					
						
			// -- Facturacion Servicio Pagos
			List<FacturacionServicioPagoEntity> listFacturacionServicioPago = new ArrayList<>() ; 
			FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
			fsp.setIdOrdenPago(0);
			fsp.setCantidad(1);
			fsp.setIdProducto(request.getIdProducto());
			fsp.setPrecio(request.getPrecioUnitario());
			fsp.setTotal(request.getPrecioUnitario());
			listFacturacionServicioPago.add(fsp);	 
		
						// -- Caja Comprobante pago
						
			CajaComprobantePagoEntity ccp = new CajaComprobantePagoEntity();
			ccp.setIdComprobantePago(0);                   
			ccp.setNroSerie("");
			ccp.setNroDocumento("");
			ccp.setRazonSocial("");
			ccp.setRuc("");
			ccp.setSubTotal(request.getPrecioUnitario());
			ccp.setIgv(0);
			ccp.setTotal(request.getPrecioUnitario());
			ccp.setFechaCobranza(fechaString);
			ccp.setTipoCambio(0);
			ccp.setObservaciones("");
			ccp.setIdTipoComprobante(1);
			ccp.setIdCuentaAtencion(0);
			ccp.setIdEstadoComprobante(1);
			ccp.setIdGestionCaja(1);
			ccp.setIdTipoPago(1);
			ccp.setIdTipoOrden(1);
			ccp.setDctos(0);
			ccp.setIdPaciente(idPaciente);
			ccp.setIdCajero(1);
			ccp.setIdTurno(1);
			ccp.setIdCaja(1);
			ccp.setIdFormaPago(1);
			ccp.setIdFarmacia(1);
			ccp.setExoneraciones(0);
			ccp.setAdelantos(0);
			ccp.setIdTipoFinanciamiento(request.getIdTipoFinanciamiento());
			ccp.setDni("");
			ccp.setIdCuentaAtencion(0);
			
			System.out.println("***Inicia Facturacion Digital  ");
			facturacionService.FacturacionDigital(
					fca,fos,listFacturacionServicioDespacho,fosp,listFacturacionServicioPago,ccp
					);
			System.out.println("***Inicia CitaService_crearCita: crearCita ");
			int idCita = citaService.crearCita(c);
			System.out.println("***Termino CitaService_crearCita: crearCita ");
			
			citaSeparadaDao.actualizaIdCita( request.getIdCitaSeparada(), idCita);
			
			response= idCita;
		}  
				
		return response ;
		
	} ;
	
}


