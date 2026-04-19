package com.api_salud.api_salud.service;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.dto.UsuarioDto;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioEntity;
import com.api_salud.api_salud.mapper.UsuarioMapper;
import com.api_salud.api_salud.repository.RoleDao;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.request.UsuarioRequest;
import com.api_salud.api_salud.response.UsuarioResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service

public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	UsuarioMapper usuarioMapper;
	
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private PacienteService pacienteService; // Inyectamos el servicio de pacientes

    
    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);   

    
    
    @Override
    @Transactional // Si falla el usuario o el rol, el paciente no se crea (Rollback)
    public UsuarioResponse usuarioCrear(UsuarioRequest request) {
        try {
        	
 
        	
            // 1. Mapeo inicial a Entidad para validaciones
            UsuarioEntity usuarioEntity = usuarioMapper.requestToEntity(request);

            // 2. VALIDACIÓN DE DUPLICADOS (DNI, Email, etc.) vía SP
            String resultadoValidacion = usuarioDao.validarRegistroCompleto(usuarioEntity);

            if (!"OK".equals(resultadoValidacion)) {
                log.warn("Registro rechazado por duplicidad: {}", resultadoValidacion);
                return crearResponseError(resultadoValidacion);
            }

            // 3. CREACIÓN DEL PACIENTE (Capa de Negocio)
            // Delegamos la creación al PacienteService y obtenemos el id_paciente
            int idPaciente = pacienteService.crearDesdeRegistro(request);
            
            if (idPaciente <= 0) {
                log.error("No se pudo obtener un ID válido para el paciente");
                return crearResponseError("ERROR_CREACION_PACIENTE");
            }

            // 4. PREPARACIÓN DEL USUARIO
            // Encriptamos la clave y vinculamos el ID del paciente recién creado
            usuarioEntity.setPassword(encoder.encode(request.getPassword()));
            usuarioEntity.setIdPaciente(idPaciente); // Vinculación 1 a 1

            // 5. GUARDAR USUARIO, ROL Y AUDITORÍA
            int idUsuario = usuarioDao.guardar(usuarioEntity);
            roleDao.create(idUsuario);
            
            // Opcional: Actualizar id_usuario en la tabla paciente si tu SP lo requiere para auditoría
            // pacienteService.actualizarUsuarioRegistro(idPaciente, idUsuario);

            // 6. RESPUESTA FINAL
            UsuarioDto usuarioDto = usuarioDao.buscarPorIdUsuario(idUsuario);
            return usuarioMapper.dtoToResponse(usuarioDto);

        } catch (Exception e) {
            log.error("Error crítico en el proceso de creación: ", e);
            // Al lanzar una RuntimeException, Spring dispara el @Transactional Rollback
            throw new RuntimeException("Error en el flujo de registro de usuario y paciente", e);
        }
    }


	@Override
	public Usuario signin(Usuario request) {
		// TODO Auto-generated method stub
		return usuarioDao.usuarioSave(request);
	}
	

	@Override
	public Integer xusername_leer(String request) {		// TODO Auto-generated method stub
		return usuarioDao.xusername_leer(request);
	}
	
		
	@Override
	public Usuario usuarioUsernameLeer(String username) {
		// TODO Auto-generated method stub
		return usuarioDao.usuarioUsernameLeer(username);
	}
	
	@Override
	public Usuario usuarioActualizar(Usuario request) {
		// TODO Auto-generated method stub
		return usuarioDao.usuarioActualizar(request);
	}

	public Usuario usuarioLeer(int id_usuario) {
		
		return usuarioDao.usuarioLeer(id_usuario);
		
	}	

    // Método utilitario para centralizar los errores de respuesta
    private UsuarioResponse crearResponseError(String mensaje) {
        UsuarioResponse errorResponse = new UsuarioResponse();
        errorResponse.setEstado("False");
        errorResponse.setUsername("ERROR: " + mensaje); 
        return errorResponse;
    }	
	
}
