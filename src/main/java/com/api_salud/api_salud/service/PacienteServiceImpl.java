package com.api_salud.api_salud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.mapper.PacienteMapper;
import com.api_salud.api_salud.repository.PacienteDao;
import com.api_salud.api_salud.request.PacienteRequest;
import com.api_salud.api_salud.request.UsuarioRequest;


@Service
public class PacienteServiceImpl implements PacienteService{
	//@Autowired
	//PacienteDao pacienteDao;
	
	
	   private static final Logger log = LoggerFactory.getLogger(PacienteServiceImpl.class);

	    @Autowired
	    private PacienteDao pacienteDao;

	    @Autowired
	    private PacienteMapper pacienteMapper; // Mapper para convertir Request -> Entity

	
	    @Override
	    @Transactional(propagation = Propagation.REQUIRED)
	    public int crearDesdeRegistro(UsuarioRequest request) {
	        PacienteEntity entidad = pacienteMapper.usuarioRequestToPacienteEntity(request);
	        return pacienteDao.pacienteCrear(entidad);
	    }
	
	 

	    
	    @Override
	    @Transactional(propagation = Propagation.REQUIRED) // Se une a la transacción de UsuarioService
	    public int pacienteCrear(PacienteRequest request) {
	        try {
	            log.debug("Iniciando creación de paciente para documento: {}", request.getNroDocumento());

	            // 1. Transformamos el DTO de entrada (Request) a nuestra Entidad de persistencia
	            PacienteEntity pacienteEntity = pacienteMapper.requestToEntity(request);

	            // 2. Aplicamos Reglas de Negocio iniciales
	            // El número de historia clínica es NULL hasta que el cajero valide el primer pago
	            pacienteEntity.setNroHistoriaClinica(null);
	            
	            // Si el request trae el id_entidad, lo seteamos; si no, podemos manejar un default
	            // pacienteEntity.setIdEntidad(request.getIdEntidad());

	            // 3. Llamamos al DAO que ejecuta el SP "igm_clientes.paciente_crear"
	            int idGenerado = pacienteDao.pacienteCrear(pacienteEntity);

	            if (idGenerado <= 0) {
	                log.error("El SP no devolvió un ID válido para el paciente: {}", request.getNroDocumento());
	                throw new RuntimeException("Error interno al persistir los datos del paciente");
	            }

	            log.info("Paciente creado exitosamente con ID: {}", idGenerado);
	            return idGenerado;

	        } catch (Exception e) {
	            log.error("Error en la capa de servicio de Pacientes: ", e);
	            // Re-lanzamos para que UsuarioService pueda hacer Rollback
	            throw e; 
	        }
	    }

		    
		    
		@Override
		public int  crear(PacienteEntity request) {
			return pacienteDao.pacienteCrear(request);
		}
	
			
		@Override
		public  PacienteEntity leerNroDocumento(PacienteEntity request) {
			// TODO Auto-generated method stub
			return pacienteDao.leerNroDocumento(request);
		}
		
		@Override
		public  PacienteEntity leerNombres(String  request) {
			// TODO Auto-generated method stub
			return pacienteDao.leerNombres(request);
		}
		
		@Override
		public PacienteEntity actualizar(PacienteEntity request) {
			// TODO Auto-generated method stub
			return pacienteDao.actualizar(request);
		}
	
	
		@Override
		public int crearConUsuario(Usuario usuario) {
			PacienteEntity pacienteEncontrado  =  pacienteDao.leerIdUsuario(usuario.getId_usuario()); //Buscar paciente con id usuario
			if(pacienteEncontrado == null ) {   		//Si no tiene HC, crear   
				String nombresPacienteCrear =  usuario.getApellido_paterno() +" "+usuario.getApellido_materno()+" "+usuario.getPrimer_nombre()+" "+usuario.getSegundo_nombre();
				PacienteEntity pacienteDuplicado = pacienteDao.leerNombres(nombresPacienteCrear);// consulta si nombre ya existe
				if(pacienteDuplicado ==null) { //No esta duplicado, crear
					int idPaciente =pacienteDao.crear(  
									   usuario.getNumero_documento() 
									  ,usuario.getNumero_documento() 
									  ,usuario.getApellido_paterno() 
									  ,usuario.getApellido_materno() 
									  ,usuario.getPrimer_nombre() 
									  ,usuario.getSegundo_nombre() 
									  ,"" //tercer nombre
									  ,"" // fecha nacimiento 
									  ,usuario.getNumero_celular()
									  ,usuario.getEmail() 
									  , ""  , ""  , "" , "" , "" , ""
									  , null,null,null,null,null,null,null , null, null, null, null
									  , null , null, null, null, null, null, null, null, null , null, null
									  , usuario.getId_usuario()
									);
					return idPaciente;
				} else {return 0;} 
			} else {return pacienteEncontrado.getIdPaciente();} 
		}

		@Override
		public int leerIdPacientexIdUsuario(int  idusuario) {
			// TODO Auto-generated method stub
			PacienteEntity paciente =  pacienteDao.leerIdUsuario(idusuario);
			
			return(paciente.getIdPaciente()); 
		}
		
		
}
