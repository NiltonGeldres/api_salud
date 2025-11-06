package com.api_salud.api_salud.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;


@Service
public class FacturacionServiceImpl  implements FacturacionService {
	@Autowired
	private FacturacionCuentaAtencionService facturacionCuentaAtencionService;
	@Autowired
	private FacturacionOrdenServicioService facturacionOrdenServicioService;
	@Autowired
	private FacturacionServicioDespachoService facturacionServicioDespachoService;
	@Autowired
	private FacturacionOrdenServicioPagoService facturacionOrdenServicioPagoService;
	@Autowired
	private FacturacionServicioPagoService facturacionServicioPagoService;
	@Autowired
	private CajaComprobantePagoService cajaComprobantePagoService;
	
	
	@Override
	public String FacturacionDigital(
			FacturacionCuentaAtencionEntity fca,
			FacturacionOrdenServicioEntity  fos ,
			List<FacturacionServicioDespachoEntity> listFacturacionServicioDespacho,
			FacturacionOrdenServicioPagoEntity fosp,
			List<FacturacionServicioPagoEntity>  listFacturacionServicioPago,
			CajaComprobantePagoEntity ccp			
	) {
		int idCuentaAtencion=0;
		// 1. Crear cuenta de atencion
		System.out.println("***Inicio FacturacionService:  facturacionCuentaAtencion");
		idCuentaAtencion = facturacionCuentaAtencionService.crearFacturacionCuentaAtencion(fca);
		System.out.println("***Termina FacturacionService: :  facturacionCuentaAtencion: "+idCuentaAtencion);
		if(idCuentaAtencion!=0) {
			fca.setIdCuentaAtencion(idCuentaAtencion);
			fos.setIdCuentaAtencion(fca.getIdCuentaAtencion());
				// 2. Crear Orden de Servicio
				System.out.println("***Inicio FacturacionService:  facturacionOrdenServicio");
				int idOrdenServicio = facturacionOrdenServicioService.crearFacturacionOrdenServicio(fos);
				System.out.println("***Termina FacturacionService:  facturacionOrdenServicio "+idOrdenServicio);				
				if(idOrdenServicio!=0) {
				     for ( int i=0; i<listFacturacionServicioDespacho.size();i++){
							FacturacionServicioDespachoEntity fosd = new FacturacionServicioDespachoEntity();	
							fosd = listFacturacionServicioDespacho.get(i);;
							fosd.setIdOrden(idOrdenServicio);
							// 3. Crar Facturacion Servicio Despacho 
							System.out.println("***Inicio FacturacionService:  FacturacionServicioDespacho");
							facturacionServicioDespachoService.crearFacturacionServicioDespacho(fosd);
							System.out.println("***Termina FacturacionService:  FacturacionServicioDespacho "+idOrdenServicio);
				     }
				}
			
				// 4. Crear Cabecera de Pagos
				fosp.setIdOrden(idOrdenServicio);			
				System.out.println("***Inicio FacturacionService::  FacturacionOrdenServicioPago");
				int idOrdenServicioPago = facturacionOrdenServicioPagoService.crearFacturacionOrdenServicioPago(fosp);
				System.out.println("***Termina FacturacionService::  FacturacionOrdenServicioPago"+idOrdenServicioPago);
				if(idOrdenServicioPago!=0) {
						// 5. Crear Facturacion Servicio Pagos
					     for ( int i=0; i<listFacturacionServicioPago.size();i++){
							    FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
								fsp = listFacturacionServicioPago.get(i);
								fsp.setIdOrdenPago(idOrdenServicioPago);
								System.out.println("***Inicio FacturacionService:  FacturacionServicioPago");
								facturacionServicioPagoService.crearFacturacionServicioPago(fsp);
								System.out.println("***Termina FacturacionService::  FacturacionServicioPago "+idOrdenServicioPago);
					     }
				     
				     
						// 6. Crear Caja Comprobante Pago
						ccp.setIdCuentaAtencion(idCuentaAtencion);
						int idCajaComprobantePago = cajaComprobantePagoService.crearCajaComprobantePago(ccp);
						System.out.println("***Inicio CitaService_crearCita:  ComprobantePago");
						facturacionOrdenServicioPagoService.actualizaIdComprobantePago(idOrdenServicioPago, idCajaComprobantePago);
						System.out.println("***Termino CitaService_crearCita :facturacionOrdenServicio " );
				     
				     
				}	
			
		}		
		return null;
		
	}
}	



/* -------------------------------------------------------------------------------------
 
 		
 		FacturacionCuentaAtencionEntity fca = new FacturacionCuentaAtencionEntity() ;
 		fca.setIdCuentaAtencion(0);
 		fca.setIdPaciente(request.getIdPaciente());
 		fca.setFechaApertura(request.getFecha());
 		fca.setHoraApertura(request.getHoraApertura());
 		fca.setFechaCierre(request.getFecha());
 		fca.setHoraCierre(request.getHora());
 		fca.setTotalExonerado(request.getTotalExonerado());
 		fca.setTotalAsegurado(request.getTotalAsegurado());
 		fca.setTotalPagado(request.getTotalPagado());
 		fca.setIdEstado(request.getIdEstado());
 		fca.setTotalPorPagar(request.getTotalPorPagar());
 		fca.setIdUsuarioCrea(request.getIdUsuarioCrea());
 		fca.setIdUsuarioModifica(request.getIdUsuarioModifica());
 		fca.setFechaCreacion(request.getFechaCreacion());
 		fca.setFechaModificacion(request.getFechaModificacion());		

		
		
		
		// ---Facturacion Cuenta Atencion 
//		int idCuentaAtencion = facturacionCuentaAtencionService.crearFacturacionCuentaAtencion(request.getFacturacionCuentaAtencionEntity());
		int idCuentaAtencion = facturacionCuentaAtencionService.crearFacturacionCuentaAtencion(fca);
		if(idCuentaAtencion!=0) {
			request.setIdCuentaAtencion(idCuentaAtencion);
		    // Orden de servicio
				FacturacionOrdenServicioEntity  fos = new FacturacionOrdenServicioEntity();
				fos.setIdOrden(0) ;
				fos.setIdPuntoCarga(request.getIdPuntoCarga());
				fos.setIdPaciente(request.getIdPaciente());
				fos.setIdCuentaAtencion(request.getIdCuentaAtencion());
				fos.setIdServicioPaciente(request.getIdServicio());
				fos.setIdTipoFinanciamiento(request.getIdTipoFinanciamiento());
				fos.setIdFuenteFinanciamiento(request.getIdFuenteFinanciamiento());
				fos.setFechaCreacion(request.getFechaCreacion());
				fos.setIdUsuario(request.getIdUsuario());
				fos.setFechaDespacho(request.getFechaDespacho());
				fos.setIdUsuarioDespacho(request.getIdUsuario());
				fos.setIdEstadoFacturacion(1);
			
			
			
//			FacturacionOrdenServicioEntity  fos = new FacturacionOrdenServicioEntity();
//			fos = request.getFacturacionOrdenServicioEntity();
//			fos.setIdCuentaAtencion(idCuentaAtencion);
			System.out.println("***Inicio CitaService_crearCita:  facturacionOrdenServicio");
			// --- Facturacion Orden Servicio  
			int idOrdenServicio = facturacionOrdenServicioService.crearFacturacionOrdenServicio(fos);
			if(idOrdenServicio!=0) {
//				List<FacturacionServicioDespachoEntity> listFacturacionServicioDespachoEntity = request.getListFacturacionServicioDespachoEntity() ; 
				List<FacturacionDetalleRequest> listFacturacionServicioDespacho = request.getListFacturacionDetalle() ; 
			     for ( int i=0; i<listFacturacionServicioDespacho.size();i++){
						//fosd.setIdOrden(idOrdenServicio);
						FacturacionDetalleRequest  fdr = new FacturacionDetalleRequest() ;
						fdr = listFacturacionServicioDespacho.get(i);
						FacturacionServicioDespachoEntity fosd = new FacturacionServicioDespachoEntity();	
						fosd.setIdOrden(idOrdenServicio);
						fosd.setIdProducto(fdr.getIdProducto());
						fosd.setCantidad(1);
						fosd.setPrecio(fdr.getPrecioUnitario());
						fosd.setTotal(fdr.getPrecioUnitario());
						fosd.setLabConfHIS(null);
						fosd.setGrupoHIS(0);
						fosd.setSubGrupoHIS(0);
						// -- Facturacion Servicio Despacho 
						facturacionServicioDespachoService.crearFacturacionServicioDespacho(fosd);
			     }
			}
						
			// -- Facturacion Orden Servicio Pagos
			
			// Cabecera de pagos
			//List<FacturacionOrdenServicioPagoEntity> listFosp = new ArrayList<>();   
			FacturacionOrdenServicioPagoEntity fosp = new FacturacionOrdenServicioPagoEntity();
			fosp.setIdOrdenPago(0);
			fosp.setFechaCreacion(request.getFechaCreacion());
			fosp.setIdComprobantePago(0);
			fosp.setIdEstadoFacturacion(1);
			fosp.setIdOrden(idOrdenServicio);			
			fosp.setIdUsuario(request.getIdUsuario());
			fosp.setIdUsuarioExonera(request.getIdUsuario());
			fosp.setImporteExonerado(0);
			
//			FacturacionOrdenServicioPagoEntity  fosp = new FacturacionOrdenServicioPagoEntity();
//			fosp = request.getFacturacionOrdenServicioPagoEntity();
//			fosp.setIdOrden(idOrdenServicio);			
			int idOrdenServicioPago = facturacionOrdenServicioPagoService.crearFacturacionOrdenServicioPago(fosp);
			if(idOrdenServicioPago!=0) {
				
				// -- Facturacion Servicio Pagos
//				List<FacturacionServicioPagoEntity> listFacturacionServicioPagoEntity = request.getListFacturacionServicioPagoEntity() ; 
				List<FacturacionDetalleRequest> listFacturacionServicioPago = request.getListFacturacionDetalle() ; 
			     for ( int i=0; i<listFacturacionServicioPago.size();i++){
			    	 
						FacturacionDetalleRequest  fdr = new FacturacionDetalleRequest() ;
						fdr = listFacturacionServicioPago.get(i);
						List<FacturacionServicioPagoEntity>  listFsp = new ArrayList<>();
					    FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
						fsp.setIdOrdenPago(idOrdenServicioPago);
						fsp.setCantidad(1);
					    fsp.setIdProducto(fdr.getIdProducto());
					    fsp.setPrecio(fdr.getPrecioUnitario());
					    fsp.setTotal(fdr.getPrecioUnitario());
			    	 
			    	 
//						FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
//						fsp = listFacturacionServicioPagoEntity.get(i);
//						fsp.setIdOrdenPago(idOrdenServicioPago);
						facturacionServicioPagoService.crearFacturacionServicioPago(fsp);
			     }
			}
			
			// -- Caja Comprobante pago
			
			CajaComprobantePagoEntity ccp = new CajaComprobantePagoEntity();
			ccp.setIdComprobantePago(0);                   
			ccp.setNroSerie("");
			ccp.setNroDocumento("");
			ccp.setRazonSocial("");
			ccp.setRuc("");
			ccp.setSubTotal(request.getListFacturacionDetalle().get(0).getPrecioUnitario());
			ccp.setIgv(0);
			ccp.setTotal(request.getListFacturacionDetalle().get(0).getPrecioUnitario());
			ccp.setFechaCobranza(request.getFecha());
			ccp.setTipoCambio(0);
			ccp.setObservaciones("");
			ccp.setIdTipoComprobante(1);
			ccp.setIdCuentaAtencion(0);
			ccp.setIdEstadoComprobante(1);
			ccp.setIdGestionCaja(1);
			ccp.setIdTipoPago(1);
			ccp.setIdTipoOrden(1);
			ccp.setDctos(0);
			ccp.setIdPaciente(request.getIdPaciente());
			ccp.setIdCajero(1);
			ccp.setIdTurno(1);
			ccp.setIdCaja(1);
			ccp.setIdFormaPago(1);
			ccp.setIdFarmacia(1);
			ccp.setExoneraciones(0);
			ccp.setAdelantos(0);
			ccp.setIdTipoFinanciamiento(request.getIdTipoFinanciamiento());
			ccp.setDni("");
			ccp.setIdCuentaAtencion(idCuentaAtencion);			
			
			
		//	CajaComprobantePagoEntity  ccp = new CajaComprobantePagoEntity();
		//	ccp = request.getCajaComprobantePagoEntity();
		//	ccp.setIdCuentaAtencion(idCuentaAtencion);			
			int idCajaComprobantePago = cajaComprobantePagoService.crearCajaComprobantePago(ccp);
			if( idCajaComprobantePago!=0) {
				System.out.println("***Termino Compraobante pago:   " );
				facturacionOrdenServicioPagoService.actualizaIdComprobantePago(idOrdenServicioPago, idCajaComprobantePago);
			}
			System.out.println("***Termino CitaService_crearCita :facturacionOrdenServicio " );
//		 int idOrden  = 	facturacionOrdenServicioDao.crearFacturacionOrdenServicio(request.getFacturacionOrdenServicioEntity());
		}
 
 * */





/*
 * 
 				
			//Servicio Despacho
				List<FacturacionServicioDespachoEntity> listFosd = new ArrayList<>();   
				FacturacionServicioDespachoEntity fosd = new FacturacionServicioDespachoEntity();	
				fosd.setIdOrden(0);
				fosd.setIdProducto(request.getIdProducto());
				fosd.setCantidad(1);
				fosd.setPrecio(request.getPrecioUnitario());
				fosd.setTotal(request.getPrecioUnitario());
				fosd.setLabConfHIS(null);
				fosd.setGrupoHIS(0);
				fosd.setSubGrupoHIS(0);
				listFosd.add(fosd);
				
			// Cabecera de pagos
				//List<FacturacionOrdenServicioPagoEntity> listFosp = new ArrayList<>();   
				FacturacionOrdenServicioPagoEntity fosp = new FacturacionOrdenServicioPagoEntity();
				fosp.setFechaCreacion(request.getFechaSolicitud());
				fosp.setIdComprobantePago(0);
				fosp.setIdEstadoFacturacion(1);
				fosp.setIdOrden(0);
				fosp.setIdOrdenPago(0);
				fosp.setIdUsuario(request.getIdUsuario());
				fosp.setIdUsuarioExonera(request.getIdUsuario());
				fosp.setImporteExonerado(0);
			//	listFosp.add(fosp);
				
			// Detalle de pagos
				List<FacturacionServicioPagoEntity>  listFsp = new ArrayList<>();
			    FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
			    fsp.setCantidad(1);
			    fsp.setIdOrdenPago(0);
			    fsp.setIdProducto(request.getIdProducto());
			    fsp.setPrecio(request.getPrecioUnitario());
			    fsp.setTotal(request.getPrecioUnitario());
			    listFsp.add(fsp);
			    System.out.println("Pasoooooo JSON ");
			    
			    
				CajaComprobantePagoEntity ccp = new CajaComprobantePagoEntity();
				ccp.setIdComprobantePago(0);                   
				ccp.setNroSerie("0001");
				ccp.setNroDocumento("001");
				ccp.setRazonSocial(usuario.getApellido_paterno()+""+usuario.getApellido_materno());
				ccp.setRuc("");
				ccp.setSubTotal(request.getPrecioUnitario());
				ccp.setIgv(0);
				ccp.setTotal(request.getPrecioUnitario());
				ccp.setFechaCobranza(request.getFechaSolicitud());
				ccp.setTipoCambio(0);
				ccp.setObservaciones("");
				ccp.setIdTipoComprobante(1);
				ccp.setIdCuentaAtencion(0);
				ccp.setIdEstadoComprobante(1);
				ccp.setIdGestionCaja(1);
				ccp.setIdTipoPago(1);
				ccp.setIdTipoOrden(1);
				ccp.setDctos(0);
				ccp.setIdPaciente(request.getIdPaciente());
				ccp.setIdCajero(1);
				ccp.setIdTurno(1);
				ccp.setIdCaja(1);
				ccp.setIdFormaPago(1);
				ccp.setIdFarmacia(1);
				ccp.setExoneraciones(0);
				ccp.setAdelantos(0);
				ccp.setIdTipoFinanciamiento(request.getdTipoFinanciamiento());
				ccp.setDni("");
				
				
				FacturacionRequest  facturacion = new  FacturacionRequest();
				facturacion.setFacturacionCuentaAtencionEntity(fca);
				facturacion.setFacturacionOrdenServicioEntity(fos);
				facturacion.setListFacturacionServicioDespachoEntity(listFosd);
				facturacion.setFacturacionOrdenServicioPagoEntity(fosp);
				facturacion.setListFacturacionServicioPagoEntity(listFsp);
				facturacion.setCajaComprobantePagoEntity(ccp);

 
 * 
 */
 


