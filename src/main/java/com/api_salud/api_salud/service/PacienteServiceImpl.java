package com.api_salud.api_salud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.PacienteDao;


@Service
public class PacienteServiceImpl implements PacienteService{
	@Autowired
	PacienteDao pacienteDao;
	
	@Override
	public int  crear(PacienteEntity request) {
		
		return pacienteDao.crear(
			     request.getNroHistoriaClinica(),
			     request.getNroDocumento(),
			     request.getApellidoPaterno(),
			     request.getApellidoMaterno(),
			     request.getPrimerNombre(),
			     request.getSegundoNombre(),
			     request.getTercerNombre(),
			     request.getFechaNacimiento(),
			     request.getTelefono(),
			     request.getEmail(),
			     request.getDireccionDomicilio(),
			     request.getNombrePadre(),
			     request.getNombreMadre(),
			     request.getObservacion(),
			     request.getGrupoSanguineo(),
			     request.getFactorRh(),
			     request.getIdTipoSexo(),
			     request.getIdGradoInstruccion(),
			     request.getIdEstadoCivil(),
			     request.getIdDocIdentidad(),
			     request.getIdTipoOcupacion(),
			     request.getNroOrdenHijo(),
			     request.getIdIdioma(),
			     request.getIdEtnia(),
			     request.getIdReligion(),
			     request.getIdProcedencia(),
			     request.getIdPaisDomicilio(),
			     request.getIdPaisProcedencia(),
			     request.getIdPaisNacimiento(),
			     request.getIdDepartamentoProcedencia(),
			     request.getIdDepartamentoDomicilio(),
			     request.getIdDepartamentoNacimiento(),
			     request.getIdDistritoProcedencia(),
			     request.getIdDistritoDomicilio(),
			     request.getIdDistritoNacimiento(),
			     request.getIdCentroPobladoProcedencia(),
			     request.getIdCentroPobladoDomicilio(),
			     request.getIdCentroPobladoNacimiento(),
			     request.getIdUsuario()
				);
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
				System.out.println("***PACIENTE CREADO :  "+idPaciente);		
				return idPaciente;
			} else {return 0;} 
		} else {return pacienteEncontrado.getIdPaciente();} 
	}

	
}
