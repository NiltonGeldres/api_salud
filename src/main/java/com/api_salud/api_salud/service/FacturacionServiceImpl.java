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
		idCuentaAtencion = facturacionCuentaAtencionService.crearFacturacionCuentaAtencion(fca);
		if(idCuentaAtencion!=0) {
			fca.setIdCuentaAtencion(idCuentaAtencion);
			fos.setIdCuentaAtencion(fca.getIdCuentaAtencion());
				// 2. Crear Orden de Servicio
				int idOrdenServicio = facturacionOrdenServicioService.crearFacturacionOrdenServicio(fos);
				if(idOrdenServicio!=0) {
				     for ( int i=0; i<listFacturacionServicioDespacho.size();i++){
							FacturacionServicioDespachoEntity fosd = new FacturacionServicioDespachoEntity();	
							fosd = listFacturacionServicioDespacho.get(i);;
							fosd.setIdOrden(idOrdenServicio);
							// 3. Crar Facturacion Servicio Despacho 
							facturacionServicioDespachoService.crearFacturacionServicioDespacho(fosd);
				     }
				}
			
				// 4. Crear Cabecera de Pagos
				fosp.setIdOrden(idOrdenServicio);			
				int idOrdenServicioPago = facturacionOrdenServicioPagoService.crearFacturacionOrdenServicioPago(fosp);
				if(idOrdenServicioPago!=0) {
						// 5. Crear Facturacion Servicio Pagos
					     for ( int i=0; i<listFacturacionServicioPago.size();i++){
							    FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
								fsp = listFacturacionServicioPago.get(i);
								fsp.setIdOrdenPago(idOrdenServicioPago);
								facturacionServicioPagoService.crearFacturacionServicioPago(fsp);
					     }
				     
				     
						// 6. Crear Caja Comprobante Pago
						ccp.setIdCuentaAtencion(idCuentaAtencion);
						int idCajaComprobantePago = cajaComprobantePagoService.crearCajaComprobantePago(ccp);
						facturacionOrdenServicioPagoService.actualizaIdComprobantePago(idOrdenServicioPago, idCajaComprobantePago);
				     
				     
				}	
			
		}		
		return null;
		
	}
}	

