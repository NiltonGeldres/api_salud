package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;

public interface FacturacionCuentaAtencionDao {
	public int crearFacturacionCuentaAtencion(FacturacionCuentaAtencionEntity  facturacionCuentaAtencionEntity);;
}

/*
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
			 */