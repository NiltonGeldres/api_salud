package com.api_salud.api_salud.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.MedicoDao;
import com.api_salud.api_salud.repository.RoleDao;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.request.MedicoRequest;
import com.api_salud.api_salud.response.MedicoResponse;
import com.api_salud.api_salud.dto.MedicoDTO;

import com.api_salud.api_salud.entity.UsuarioEntity;
import com.api_salud.api_salud.request.MedicoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MedicoServiceImpl  implements MedicoService{

	@Autowired
	private MedicoDao medicoDao  ;		

	private static final Logger log = LoggerFactory.getLogger(MedicoServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioDao usuarioDao; // Tu DAO existente que invoca usuario_crear

    @Autowired
    private RoleDao roleDao; // Tu DAO existente para asignar el rol en igm_security.usuario_rol

    @Autowired
    private PasswordEncoder encoder;
    


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer registrarMedicoCompleto(MedicoRequest request) {
        try {
            // 1. Hasheamos el password temporal antes de enviarlo al repositorio y a la BD
            if (request.getPasswordTemporal() != null && !request.getPasswordTemporal().isEmpty()) {
                request.setPasswordTemporal(passwordEncoder.encode(request.getPasswordTemporal()));
            }

            // 2. Ejecutamos el registro a través del DAO unificado por JSON
            int idMedico = medicoDao.registrarMedico(request);

            if (idMedico <= 0) {
                throw new RuntimeException("No se pudo obtener un ID válido para el nuevo médico.");
            }

            log.info("Médico registrado exitosamente con ID: {}", idMedico);
            return idMedico;

        } catch (Exception e) {
            log.error("Error en MedicoServiceImpl al registrar médico: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean actualizarMedico(int idMedico, MedicoRequest request) {
        try {
            return medicoDao.actualizarMedico(idMedico, request);
        } catch (Exception e) {
            log.error("Error en MedicoServiceImpl al actualizar médico: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean darBajaMedico(int idMedico, int idEntidad, String estado) {
        try {
            return medicoDao.darBajaMedico(idMedico, idEntidad, estado);
        } catch (Exception e) {
            log.error("Error en MedicoServiceImpl al dar de baja al médico: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    public List<MedicoDTO> listarMedicos(int idEntidad, String filtro, String estado, int limit, int offset) {
        try {
            // Delega directamente al repositorio que ejecuta la función RETURNS TABLE en Neon
            return medicoDao.listarMedicos(idEntidad, filtro, estado, limit, offset);
        } catch (Exception e) {
            log.error("Error en MedicoServiceImpl al listar médicos: {}", e.getMessage());
            throw new RuntimeException("Error al obtener el listado de médicos: " + e.getMessage());
        }
    }    
//--------------------------------------------------
    
	@Override
	public MedicoResponse medicoEntidad(MedicoRequest request) {
	    // 1. Llamamos al DAO para traer los datos crudos (DTOs)
		int idEntidad = request.getIdEntidad();
	    List<MedicoDTO> listaMedicos = medicoDao.medicoEntidad(idEntidad);
	    
	    // 2. Armamos el MedicoResponse aquí
	    MedicoResponse response = new MedicoResponse();
	    response.setMedico(listaMedicos);
	    response.setTotalRecord(String.valueOf(listaMedicos.size()));
	    return response;		
	}
	
	
	@Override
	public MedicoResponse medicoEspecialidad(MedicoRequest request) {
/*		MedicoResponse retorno = null ;
		int idEspecialidad = request.getIdEspecialidad();
		if(idEspecialidad>0) {
			retorno = medicoDao.medicoEspecialidad(idEspecialidad);
		}
		return retorno;*/
		
	    // 1. Llamamos al DAO para traer los datos crudos (DTOs)
		int idEspecialidad = request.getIdEspecialidad();
	    List<MedicoDTO> listaMedicos = medicoDao.medicoEspecialidad(idEspecialidad);
	    
	    // 2. Armamos el MedicoResponse aquí
	    MedicoResponse response = new MedicoResponse();
	    response.setMedico(listaMedicos);
	    response.setTotalRecord(String.valueOf(listaMedicos.size()));
	    
	    return response;		
	}
	
	@Override
	public int  medicoXUsuarioLeer(String usuario) {
		return	 medicoDao.medicoXUsuarioLeer(usuario);
	}		

	@Override
	public int tiempoPromedioAtencion_leer(int idMedico, int idEspecialidad) {	
		return	 medicoDao.tiempoPromedioAtencion_leer(idMedico,idEspecialidad);
	}

	@Override
	public MedicoResponse medicoXIdMedicosXEspecialidad(int idEspecialidad, int idMedico) {
		  		
		return medicoDao.medicoXIdMedicosXEspecialidad(idEspecialidad, idMedico);
	}		

	
}
