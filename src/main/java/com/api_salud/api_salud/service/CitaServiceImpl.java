package com.api_salud.api_salud.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;
import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.CajaComprobantePagoDao;
import com.api_salud.api_salud.repository.CitaDao;
import com.api_salud.api_salud.repository.FacturacionCuentaAtencionDao;
import com.api_salud.api_salud.repository.FacturacionOrdenServicioDao;
import com.api_salud.api_salud.repository.FacturacionOrdenServicioPagoDao;
import com.api_salud.api_salud.repository.FacturacionServicioDespachoDao;
import com.api_salud.api_salud.repository.FacturacionServicioPagoDao;
import com.api_salud.api_salud.repository.PacienteDao;
import com.api_salud.api_salud.repository.UsuarioDao;

import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.request.FacturacionDetalleRequest;
import com.api_salud.api_salud.request.FacturacionRequest;
import com.api_salud.api_salud.response.CitaResponse;

@Service
public class CitaServiceImpl  implements  CitaService{
	@Autowired
	private CitaDao citaDao;


	
	
	@Override
	public CitaResponse citaDisponible(CitaRequest request) {
		
		return citaDao.citasDisponiblesDia(
			request.getIdMedico(),
	        request.getFecha(),
	        request.getIdEspecialidad()
        );
	}

	@Override
	public int crearCita(CitaEntity c) { 
			//CitaResponse response = new CitaResponse(); 
		return citaDao.crearCita(c);
	}

	@Override
	public CitaResponse leerCita(int idCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int leerXIdProgramacionMedica(int idProgramacionMedica) {
		return citaDao.leerXIdProgramacionMedica(idProgramacionMedica);
	}

	
	
	
}

/*
 * 
 			
			FacturacionRequest facturacion = new FacturacionRequest();
			facturacion.setFechaCreacion(fechaString);
			facturacion.setFecha(fechaString);
			facturacion.setHora(horaString);
			facturacion.setIdCuentaAtencion(0);
			facturacion.setIdFuenteFinanciamiento(request.getIdFuenteFinanciamiento());
			facturacion.setIdUsuario(idUsuario);
			facturacion.setIdPaciente(idPaciente);
			
			facturacion.setIdEspecialidad(request.getIdEspecialidad());
			facturacion.setIdServicio(request.getIdServicio());
			facturacion.setIdServicioPaciente(request.getIdServicio());
			facturacion.setIdPuntoCarga(0);
			
			facturacion.setFechaApertura(fechaString);
			facturacion.setHoraApertura(horaString);
			facturacion.setFechaCierre(fechaString);
			facturacion.setHoraCierre(horaString);
			facturacion.setFechaModificacion(fechaString);

			facturacion.setTotalPagado(request.getPrecioUnitario());
			facturacion.setTotalPorPagar(request.getPrecioUnitario());
			facturacion.setTotalAsegurado(0);
			facturacion.setTotalExonerado(0);
			
			facturacion.setIdEstado(0);
			facturacion.setIdUsuarioCrea(idUsuario) ;
			facturacion.setIdUsuarioModifica(idUsuario) ;
			facturacion.setIdUsuarioAuditoria(idUsuario) ;
			
			List<FacturacionDetalleRequest> listFacturacionDetalle = new ArrayList<>();
			FacturacionDetalleRequest  fdr = new FacturacionDetalleRequest() ;
		    fdr.setIdProducto(request.getIdProducto());
		    fdr.setPrecioUnitario(request.getPrecioUnitario());
			fdr.setCantidad(1);
			listFacturacionDetalle.add(fdr);
			
 * 
 * 
 * */



/*
 		// ---Facturacion Cuenta Atencion 
		int idCuentaAtencion = facturacionCuentaAtencionService.crearFacturacionCuentaAtencion(request.getFacturacionCuentaAtencionEntity());
		if(idCuentaAtencion!=0) {
			FacturacionOrdenServicioEntity  fos = new FacturacionOrdenServicioEntity();
			fos = request.getFacturacionOrdenServicioEntity();
			fos.setIdCuentaAtencion(idCuentaAtencion);
			System.out.println("***Inicio CitaService_crearCita:  facturacionOrdenServicio");
			// --- Facturacion Orden Servicio  
			int idOrdenServicio = facturacionOrdenServicioDao.crearFacturacionOrdenServicio(fos);
			if(idOrdenServicio!=0) {
				List<FacturacionServicioDespachoEntity> listFacturacionServicioDespachoEntity = request.getListFacturacionServicioDespachoEntity() ; 
			     for ( int i=0; i<listFacturacionServicioDespachoEntity.size();i++){
						FacturacionServicioDespachoEntity fosd = new FacturacionServicioDespachoEntity();
						fosd = listFacturacionServicioDespachoEntity.get(i);
						fosd.setIdOrden(idOrdenServicio);
						// -- Facturacion Servicio Despacho 
						facturacionServicioDespachoDao.crearFacturacionServicioDespacho(fosd);
			     }
			}
						
			// -- Facturacion Orden Servicio Pagos 
			FacturacionOrdenServicioPagoEntity  fosp = new FacturacionOrdenServicioPagoEntity();
			fosp = request.getFacturacionOrdenServicioPagoEntity();
			fosp.setIdOrden(idOrdenServicio);			
			int idOrdenServicioPago = facturacionOrdenServicioPagoDao.crearFacturacionOrdenServicioPago(fosp);
			if(idOrdenServicioPago!=0) {
				
				// -- Facturacion Servicio Pagos
				List<FacturacionServicioPagoEntity> listFacturacionServicioPagoEntity = request.getListFacturacionServicioPagoEntity() ; 
			     for ( int i=0; i<listFacturacionServicioPagoEntity.size();i++){
						FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
						fsp = listFacturacionServicioPagoEntity.get(i);
						fsp.setIdOrdenPago(idOrdenServicioPago);
						facturacionServicioPagoDao.crearFacturacionServicioPago(fsp);
			     }
			}
			
			// -- Caja Comprobante pago
			CajaComprobantePagoEntity  ccp = new CajaComprobantePagoEntity();
			ccp = request.getCajaComprobantePagoEntity();
			ccp.setIdCuentaAtencion(idCuentaAtencion);			
			int idCajaComprobantePago = cajaComprobantePagoDao.crearCajaComprobantePago(ccp);
			if( idCajaComprobantePago!=0) {
				System.out.println("***Termino Compraobante pago:   " );
				facturacionOrdenServicioPagoDao.actualizaIdComprobantePago(idOrdenServicioPago, idCajaComprobantePago);
			}
			System.out.println("***Termino CitaService_crearCita :facturacionOrdenServicio " );
//		 int idOrden  = 	facturacionOrdenServicioDao.crearFacturacionOrdenServicio(request.getFacturacionOrdenServicioEntity());
		}
		
		System.out.println("***Inicia CitaService_crearCita: crearCita ");
		int idCita = citaDao.crearCita(request.getCitaEntity());
		System.out.println("***Termino CitaService_crearCita: crearCita ");
 
/*		
PacienteEntity pacienteEncontrado  =  pacienteDao.leerIdUsuario(usuario.getId_usuario()); //Buscar paciente con id usuario
if(pacienteEncontrado == null ) {   		//Si no tiene HC, crear   
	String nombresPacienteCrear =  usuario.getApellido_paterno() +" "+usuario.getApellido_materno()+" "+usuario.getPrimer_nombre()+" "+usuario.getSegundo_nombre();
	PacienteEntity pacienteDuplicado = pacienteDao.leerNombres(nombresPacienteCrear);// consulta si nombre ya existe
	if(pacienteDuplicado ==null) { //No esta duplicado, crear
		int idPaciente =pacienteDao.crear(  
						   usuario.getNumero_documento() 
						  ,usuario.getNumero_documento() 
						  ,usuario.getApellido_paterno() 
						  ,usuario.getApellido_materno() 
						  ,usuario.getPrimer_nombre() 
						  ,usuario.getSegundo_nombre() 
						  ,"" //tercer nombre
						  ,"" // fecha nacimiento 
						  ,usuario.getNumero_celular()
						  ,usuario.getEmail() 
						  , ""  , ""  , "" , "" , "" , ""
						  , null,null,null,null,null,null,null , null, null, null, null
						  , null , null, null, null, null, null, null, null, null , null, null
						  , usuario.getId_usuario()
						);
		System.out.println("***PACIENTE CREADO :  "+idPaciente);		
	} else {
		String nombresPaciente = pacienteDuplicado.getApellidoPaterno()
				+pacienteDuplicado.getApellidoMaterno()
				+pacienteDuplicado.getPrimerNombre()
				+pacienteDuplicado.getSegundoNombre()
				+pacienteDuplicado.getTercerNombre();
		
		return null;
	} 
} 
*/


/*
 	@Autowired
	private FacturacionCuentaAtencionService facturacionCuentaAtencionService;
	@Autowired
	private FacturacionOrdenServicioDao facturacionOrdenServicioDao;
	@Autowired
	private FacturacionServicioDespachoDao facturacionServicioDespachoDao;
	@Autowired
	private FacturacionOrdenServicioPagoDao facturacionOrdenServicioPagoDao;
	@Autowired
	private FacturacionServicioPagoDao facturacionServicioPagoDao;
	@Autowired
	private CajaComprobantePagoDao cajaComprobantePagoDao;

 * */
 

