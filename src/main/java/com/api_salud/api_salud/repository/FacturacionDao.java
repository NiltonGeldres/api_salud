package com.api_salud.api_salud.repository;

import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;



import com.api_salud.api_salud.response.FacturacionResponse;

public interface FacturacionDao {
	

 /***************************** */
/*      Crear cita medica     */
/***************************** */
	
/* 2.    Crear Cuenta Atencion */
public FacturacionResponse  crearFacturacionCuentaAtencion(
		double totalPorPagar,
		int idEstado,
		double totalPagado,
		double totalAsegurado,
		double totalExonerado,
		String horaCierre,
		String fechaCierre,
		String horaApertura,
		String fechaApertura,
		int idPaciente,
		int idCuentaAtencion,
		String fechaCreacion,
		int idUsuarioAuditoria);		

/* 3.    Crear Orden Servicio   */
//public int crearFacturacionOrdenServicio(CitaEntity EntityCita,int idCuentaAtencion);
/* 4.    Crear Orden Servicio en pagos  */
//public int crearFacturacionOrdenServicioPagos(CitaEntity EntityCita,int idOrden);
/* 5.    Crear Registrar el despacho  */
//public void crearFacturacionServicioDespacho(CitaEntity EntityCita,int idOrden,int idProducto,double precio,int cantidad);
/* 6.  Crear en Servicio pagos */
//public void crearFacturacionServicioPagos(CitaEntity EntityCita, int idOrdenPago,int idProducto,double precio,int cantidad);
	
	
	
	
	

	

}
