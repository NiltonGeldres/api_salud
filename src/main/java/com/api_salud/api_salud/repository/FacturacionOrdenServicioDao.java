package com.api_salud.api_salud.repository;


import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.response.FacturacionOrdenServicioResponse;

public interface FacturacionOrdenServicioDao {
	
	 public int crearFacturacionOrdenServicio(FacturacionOrdenServicioEntity facturacionOrdenServicioEntity);

}
/*
int idPuntoCarga
,int idPaciente
,int idCuentaAtencion
,int idServicioPaciente
,double idTipoFinanciamiento
,double idFuenteFinanciamiento
,String fechaCreacion
,int idUsuario
,String fechaDespacho
,int idUsuarioDespacho
,int idEstadoFacturacion
,String fechaRealizaCpt
,int idUsuarioAuditoria
*/