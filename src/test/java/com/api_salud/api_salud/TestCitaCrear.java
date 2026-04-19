package com.api_salud.api_salud;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.controller.CitaController;
import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;

import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;
import com.api_salud.api_salud.request.CitaCrearRequest;






@SpringBootTest
public class TestCitaCrear {



@Autowired
CitaController citaController;

@Test
void contextLoads() {
	

	
// Cita
	CitaEntity c = new CitaEntity();
		c.setIdMedico(0);
		c.setFecha("20230101");
		c.setIdEspecialidad(0);
		c.setIdCita(0);
		c.setHoraInicio("10:10");
	    c.setHoraFin("10:10");
	    c.setIdPaciente(11);
	    c.setIdEstadoCita(11);
	    c.setIdAtencion(11);
	    c.setIdServicio(11);
	    c.setIdProgramacion(1);
	    c.setFechaSolicitud("20230101");
	    c.setHoraSolicitud("10:10");
	    c.setEsCitaAdicional(false);
	    c.setIdProducto(0);
	    c.setIdUsuario(0);
	
    // Cuenta Atencion
	    FacturacionCuentaAtencionEntity  fca = new  FacturacionCuentaAtencionEntity();
	    fca.setIdCuentaAtencion(0);
	    fca.setIdPaciente(11);
	    fca.setFechaApertura("20230501");
	    fca.setHoraApertura("11:00");
	    fca.setFechaCierre("20230301");
	    fca.setHoraCierre("l0:00");
	    fca.setTotalExonerado(1.0);
	    fca.setTotalAsegurado(1.0);
	    fca.setTotalPagado(10.0);
	    fca.setIdEstado(1);
	    fca.setTotalPorPagar(1.0);
	    fca.setIdUsuarioCrea(1);
	    fca.setIdUsuarioModifica(1);
	    fca.setFechaCreacion("20230101");
	    fca.setFechaModificacion("20230101");
	
    // Orden de servicio
		FacturacionOrdenServicioEntity  fos = new FacturacionOrdenServicioEntity();
		fos.setIdOrden(0) ;
		fos.setIdPuntoCarga(1);
		fos.setIdPaciente(11);
		fos.setIdCuentaAtencion(1);
		fos.setIdServicioPaciente(1);
		fos.setIdTipoFinanciamiento(1);
		fos.setIdFuenteFinanciamiento(1);
		fos.setFechaCreacion("20230101");
		fos.setIdUsuario(1);
		fos.setFechaDespacho("20230101");
		fos.setIdUsuarioDespacho(1);
		fos.setIdEstadoFacturacion(1);
		fos.setFechaHoraRealizaCpt("20230101");		
		
	// Servicio Despacho
		List<FacturacionServicioDespachoEntity> listFosd = new ArrayList<>();   
		FacturacionServicioDespachoEntity fosd = new FacturacionServicioDespachoEntity();	
		fosd.setIdOrden(9);
		fosd.setIdProducto(2481);
		fosd.setCantidad(1);
		fosd.setPrecio(10.9);
		fosd.setTotal(10.9);
		fosd.setLabConfHIS(null);
		fosd.setGrupoHIS(0);
		fosd.setSubGrupoHIS(0);
	
		listFosd.add(fosd);

		
	// Cabecera de pagos
		//List<FacturacionOrdenServicioPagoEntity> listFosp = new ArrayList<>();   
		FacturacionOrdenServicioPagoEntity fosp = new FacturacionOrdenServicioPagoEntity();
		fosp.setFechaCreacion("20230920");
		fosp.setIdComprobantePago(1);
		fosp.setIdEstadoFacturacion(1);
		fosp.setIdOrden(1);
		fosp.setIdOrdenPago(0);
		fosp.setIdUsuario(1);
		fosp.setIdUsuarioExonera(1);
		fosp.setImporteExonerado(0);
	//	listFosp.add(fosp);
		
	// Detalle de pagos
		List<FacturacionServicioPagoEntity>  listFsp = new ArrayList<>();
	    FacturacionServicioPagoEntity fsp = new FacturacionServicioPagoEntity();
	    fsp.setCantidad(1);
	    fsp.setIdOrdenPago(0);
	    fsp.setIdProducto(1212);
	    fsp.setPrecio(10.00);
	    fsp.setTotal(10.00);
	    listFsp.add(fsp);
	    System.out.println("Pasoooooo JSON ");
	    
	    
		CajaComprobantePagoEntity ccp = new CajaComprobantePagoEntity();
		ccp.setIdComprobantePago(0);                   
		ccp.setNroSerie("0001");
		ccp.setNroDocumento("001");
		ccp.setRazonSocial("Emprsa");
		ccp.setRuc("1234");
		ccp.setSubTotal(10.10);
		ccp.setIgv(10.10);
		ccp.setTotal(10.10);
		ccp.setFechaCobranza("20230922");
		ccp.setTipoCambio(10.2);
		ccp.setObservaciones("Empresa");
		ccp.setIdTipoComprobante(1);
		ccp.setIdCuentaAtencion(1);
		ccp.setIdEstadoComprobante(1);
		ccp.setIdGestionCaja(1);
		ccp.setIdTipoPago(1);
		ccp.setIdTipoOrden(1);
		ccp.setDctos(10.2);
		ccp.setIdPaciente(1);
		ccp.setIdCajero(1);
		ccp.setIdTurno(1);
		ccp.setIdCaja(1);
		ccp.setIdFormaPago(1);
		ccp.setIdFarmacia(1);
		ccp.setExoneraciones(10.3);
		ccp.setAdelantos(10.2);
		ccp.setIdTipoFinanciamiento(1);
		ccp.setDni("123456");
		
		
		CitaCrearRequest  request = new  CitaCrearRequest();
		request.setUsuario("lcastillo");
	    request.setCitaEntity(c);
	    request.setFacturacionCuentaAtencionEntity(fca);
	    request.setFacturacionOrdenServicioEntity(fos);
	    request.setListFacturacionServicioDespachoEntity(listFosd);
	    request.setFacturacionOrdenServicioPagoEntity(fosp);
	    request.setListFacturacionServicioPagoEntity(listFsp);
	    request.setCajaComprobantePagoEntity(ccp);
		
	    
	//    citaController.crearCita(request, null);
	    

}

}



