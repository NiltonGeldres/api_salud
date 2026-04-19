package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.context.TenantContext;
import com.api_salud.api_salud.repository.EspecialidadDao;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.EspecialidadResponse;

@Service
public class EspecialidadServiceImpl  implements EspecialidadService {
	@Autowired
	private EspecialidadDao especialidadDao  ;

	
	@Override
	public EspecialidadResponse web() {
		return especialidadDao.web();
	}
	

	@Override
	public EspecialidadResponse web1() {
//		return especialidadRepo.web();
		return null;
	}


	@Override
	public EspecialidadResponse xIdMedico(int idMedico) {
		return especialidadDao.xIdMedico(idMedico);
	}

	
	@Override
	public EspecialidadResponse xIdEntidad() {
		
        // 1️⃣ Obtener el id de la entidad desde el contexto (JWT → Filter)
        Long entidadId = TenantContext.getEntidadId();

        if (entidadId == null) {
            throw new RuntimeException("No se encontró la entidad en el contexto");
        }

		return especialidadDao.xIdEntidad(entidadId);
	}


}
