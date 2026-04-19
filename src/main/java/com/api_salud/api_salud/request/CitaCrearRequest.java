package com.api_salud.api_salud.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;
import com.api_salud.api_salud.response.CitaDisponibleResponse;



public class CitaCrearRequest {
	String usuario ;

	@NotNull(message = "El dato CitaEntity, no puede estar en blanco")
	CitaEntity citaEntity; 
	
	@NotNull(message = "El dato FacturacionCuentaAtencionEntity, no puede estar en blanco")
    FacturacionCuentaAtencionEntity  facturacionCuentaAtencionEntity;
	
	@NotNull(message = "El dato FacturacionOrdenServicioEntity, no puede estar en blanco")
    FacturacionOrdenServicioEntity  facturacionOrdenServicioEntity;
	
	@NotNull(message = "El dato ListFacturacionServicioDespachoEntity, no puede estar en blanco")
    List<FacturacionServicioDespachoEntity> listFacturacionServicioDespachoEntity;
   
	@NotNull(message = "El dato FacturacionOrdenServicioPagosEntity, no puede estar en blanco")
    FacturacionOrdenServicioPagoEntity facturacionOrdenServicioPagoEntity;
	
	@NotNull(message = "El dato ListFacturacionServicioPagosEntity, no puede estar en blanco")
    List<FacturacionServicioPagoEntity> listFacturacionServicioPagoEntity;
	
	@NotNull(message = "El dato CajaComprobantePagoEntity, no puede estar en blanco")
	CajaComprobantePagoEntity cajaComprobantePagoEntity;

	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public CitaEntity getCitaEntity() {
		return citaEntity;
	}

	public void setCitaEntity(CitaEntity citaEntity) {
		this.citaEntity = citaEntity;
	}

	public FacturacionCuentaAtencionEntity getFacturacionCuentaAtencionEntity() {
		return facturacionCuentaAtencionEntity;
	}

	public void setFacturacionCuentaAtencionEntity(FacturacionCuentaAtencionEntity facturacionCuentaAtencionEntity) {
		this.facturacionCuentaAtencionEntity = facturacionCuentaAtencionEntity;
	}

	public FacturacionOrdenServicioEntity getFacturacionOrdenServicioEntity() {
		return facturacionOrdenServicioEntity;
	}

	public void setFacturacionOrdenServicioEntity(FacturacionOrdenServicioEntity facturacionOrdenServicioEntity) {
		this.facturacionOrdenServicioEntity = facturacionOrdenServicioEntity;
	}

	public List<FacturacionServicioDespachoEntity> getListFacturacionServicioDespachoEntity() {
		return listFacturacionServicioDespachoEntity;
	}

	public void setListFacturacionServicioDespachoEntity(
			List<FacturacionServicioDespachoEntity> listFacturacionServicioDespachoEntity) {
		this.listFacturacionServicioDespachoEntity = listFacturacionServicioDespachoEntity;
	}

	public FacturacionOrdenServicioPagoEntity getFacturacionOrdenServicioPagoEntity() {
		return facturacionOrdenServicioPagoEntity;
	}

	public void setFacturacionOrdenServicioPagoEntity(
			FacturacionOrdenServicioPagoEntity facturacionOrdenServicioPagoEntity) {
		this.facturacionOrdenServicioPagoEntity = facturacionOrdenServicioPagoEntity;
	}

	public List<FacturacionServicioPagoEntity> getListFacturacionServicioPagoEntity() {
		return listFacturacionServicioPagoEntity;
	}

	public void setListFacturacionServicioPagoEntity(
			List<FacturacionServicioPagoEntity> listFacturacionServicioPagoEntity) {
		this.listFacturacionServicioPagoEntity = listFacturacionServicioPagoEntity;
	}

	public CajaComprobantePagoEntity getCajaComprobantePagoEntity() {
		return cajaComprobantePagoEntity;
	}

	public void setCajaComprobantePagoEntity(CajaComprobantePagoEntity cajaComprobantePagoEntity) {
		this.cajaComprobantePagoEntity = cajaComprobantePagoEntity;
	}
	

}
