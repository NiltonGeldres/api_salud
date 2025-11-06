package com.api_salud.api_salud.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.repository.CitaBloqueadaDao;

import com.api_salud.api_salud.request.CitaBloqueadaRequest;
import com.api_salud.api_salud.response.CitaBloqueadaResponse;

import com.api_salud.api_salud.service.UsuarioService;


@Service
public class CitaBloqueadaServiceImpl implements CitaBloqueadaService{

	@Autowired
	CitaBloqueadaDao citaBloqueadaDao;
	
	@Autowired
	UsuarioService usuarioService;	
	
	@Override
	public CitaBloqueadaResponse crearCitaBloqueada(CitaBloqueadaRequest request) {
		int idUsuario = 0;
		String horaFin  = request.getHoraInicio();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        DateFormat horaFormat = new SimpleDateFormat("HH:mm");
        
	    String fechaBloqueo = dateFormat.format(new Date());
	    String horaBloqueo = horaFormat.format(new Date());
	    System.out.println(horaFin+"-"+fechaBloqueo+"-"+horaBloqueo);
	    
		CitaBloqueadaResponse response = null;

		String usuario= request.getUsuario();
		System.out.println("crearCitaBloqueada-Usuario Recibido "+request.getUsuario()	);
		 idUsuario = usuarioService.xusername_leer(usuario);
		System.out.println("Id usuario  "+idUsuario);
		if (idUsuario!=0  ) {
		    response = new CitaBloqueadaResponse();
			response = citaBloqueadaDao.crearCitaBloqueada(
		 		    request.getIdCitaBloqueada(),
		 		    idUsuario,
		 		    request.getFecha(),
		 		    request.getHoraInicio(),
		 		    horaFin ,
		 		    fechaBloqueo ,
		 		    horaBloqueo ,
		 		    request.getIdMedico(),
		 		    "WEB", 		
		 		    request.getUsuario()
					);
		
		}
		return response;
	}

	
	@Override
	public int eliminarCitaBloqueada(CitaBloqueadaRequest request) {
		System.out.println("Service id cita bloqueada "+request.getIdCitaBloqueada());
		return  citaBloqueadaDao.eliminarCitaBloqueada(request.getIdCitaBloqueada());
	}


	@Override
	public int eliminarCitaBloqueadaXUsuario(CitaBloqueadaRequest request) {
		String usuario = request.getUsuario();	
		 int idUsuario = usuarioService.xusername_leer(usuario);	
		return  citaBloqueadaDao.eliminarCitaBloqueadaXUsuario(idUsuario);
	}
	
	
	
}
