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
    private UsuarioDao usuarioDao; // Tu DAO existente que invoca usuario_crear

    @Autowired
    private RoleDao roleDao; // Tu DAO existente para asignar el rol en igm_security.usuario_rol

    @Autowired
    private PasswordEncoder encoder;
    

	@Transactional(rollbackFor = Exception.class)
    public Integer registrarMedicoCompleto(MedicoRequest request) {
        try {
            log.info("Iniciando registro atómico para el médico con DNI: {}", request.getDni());

            // --- PASO 1: Mapear y crear el Usuario de Seguridad (igm_security) ---
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setUsername(request.getDni()); // Usamos el DNI como username por defecto
            usuarioEntity.setPassword(encoder.encode(request.getPasswordTemporal()));
            usuarioEntity.setEmail(request.getEmail());
            usuarioEntity.setApellidoPaterno(request.getApellidoPaterno());
            usuarioEntity.setApellidoMaterno(request.getApellidoMaterno());
            usuarioEntity.setPrimerNombre(request.getNombres()); // Si requieres separar nombres, puedes hacerlo aquí
            usuarioEntity.setNumeroCelular(request.getNumeroCelular());
            
            // Conversiones de tipos acorde a tu UsuarioEntity (int)
            try {
                usuarioEntity.setIdSexo(request.getIdSexo() != null ? Integer.parseInt(request.getIdSexo()) : 0);
                usuarioEntity.setIdTipoDocumento(request.getIdTipoDocumento() != null ? Integer.parseInt(request.getIdTipoDocumento()) : 1);
            } catch (NumberFormatException e) {
                usuarioEntity.setIdSexo(1);
                usuarioEntity.setIdTipoDocumento(1);
            }

            usuarioEntity.setNumeroDocumento(request.getDni());
            usuarioEntity.setEstado("1"); // 1 = Activo
            usuarioEntity.setIdEntidad(request.getIdEntidad());

            // Ejecuta tu lógica existente para guardar el usuario y obtener su ID
            int idUsuario = usuarioDao.guardar(usuarioEntity); // O usuarioSave(usuarioEntity) según tu método en UsuarioDao
            if (idUsuario <= 0) {
                throw new RuntimeException("No se pudo generar el ID de usuario en el esquema de seguridad.");
            }
            log.info("Usuario de seguridad creado con éxito. ID: {}", idUsuario);

            // --- PASO 2: Asignar el Rol de Médico (igm_security.usuario_rol) ---
            int resultadoRol = roleDao.create(idUsuario);
            if (resultadoRol <= 0) {
                throw new RuntimeException("No se pudo asignar el rol de médico al usuario.");
            }
            log.info("Rol de médico asignado correctamente al usuario ID: {}", idUsuario);

            // --- PASO 3: Registrar Empleado y Perfil Médico (igm_maestros) ---
            int idMedico = medicoDao.registrarMedico(request);
            if (idMedico <= 0) {
                throw new RuntimeException("No se pudo registrar el perfil médico en la base de datos.");
            }
            log.info("Perfil médico registrado con éxito. ID Médico: {}", idMedico);

            return idMedico;

        } catch (Exception e) {
            log.error("Error crítico en el registro del médico, ejecutando Rollback automático: ", e);
            throw new RuntimeException("Error en el alta del médico: " + e.getMessage(), e);
        }
    }
	
	/**
     * 2. Listado Paginado con Filtros
     */
	public List<MedicoDTO> listarMedicosPaginado(int idEntidad, String filtro, String estado, int limit, int offset) {
        try {
            return medicoDao.listarMedicosPaginado(idEntidad, filtro, estado, limit, offset);
        } catch (Exception e) {
            log.error("Error en servicio al listar médicos: ", e);
            return Collections.emptyList(); // Compatible con cualquier versión de Java
        }
    }

    /**
     * 3. Actualización de Datos del Médico y Empleado
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean actualizarMedico(int idMedico, MedicoRequest request) {
        try {
            log.info("Actualizando datos para el médico ID: {}", idMedico);
            boolean actualizado = medicoDao.actualizarMedico(idMedico, request);
            if (!actualizado) {
                throw new RuntimeException("La función de actualización no retornó éxito.");
            }
            return true;
        } catch (Exception e) {
            log.error("Error al actualizar el médico en el servicio: ", e);
            throw new RuntimeException("Error al actualizar médico: " + e.getMessage(), e);
        }
    }

    /**
     * 4. Baja Lógica del Médico
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean darBajaMedico(int idMedico, int idEntidad, String estado) {
        try {
            log.info("Dando de baja al médico ID: {} en la entidad: {}", idMedico, idEntidad);
            boolean bajaEfectiva = medicoDao.darBajaMedico(idMedico, idEntidad, estado);
            if (!bajaEfectiva) {
                throw new RuntimeException("No se pudo procesar la baja lógica.");
            }
            return true;
        } catch (Exception e) {
            log.error("Error al dar de baja al médico en el servicio: ", e);
            throw new RuntimeException("Error al dar de baja al médico: " + e.getMessage(), e);
        }
    }
    
	
	
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
