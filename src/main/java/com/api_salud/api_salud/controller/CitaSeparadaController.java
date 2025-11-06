package com.api_salud.api_salud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;
import com.api_salud.api_salud.service.CitaBloqueadaService;
import com.api_salud.api_salud.service.CitaSeparadaService;
import com.api_salud.api_salud.service.CitaService;
import com.api_salud.api_salud.service.UsuarioService;

@RestController
@RequestMapping("")
public class CitaSeparadaController {
	
	@Autowired
	CitaService citaService ;
	
	@Autowired
	CitaBloqueadaService citaBloqueadaService;

	@Autowired
	CitaSeparadaService citaSeparadaService;

	@Autowired
	UsuarioService usuarioService;	

    @PostMapping("/citaSeparadaCrear")
    public ResponseEntity<?> crearCitaSeparada(   @RequestBody CitaSeparadaRequest request  ){
	    String textError="";
	    System.out.println("PRECIO UNITARIO   "+request.getPrecioUnitario());
	    	
		int idUsuario = 0;
		String usuario= request.getUsuario();
		 idUsuario = usuarioService.xusername_leer(usuario);
		if (idUsuario==0  ) {
			textError= "Usuario: se encuentra de baja" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
		} 
			
    	System.out.println("Ingreso a Controller crearCitaSeparada " +request.getIdServicio());
	    if(request.getFecha().isEmpty()) {
			textError= "Fecha: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
	    if(request.getHoraInicio().isEmpty()) {
			textError= "Hora Inicio: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    
	    if(request.getHoraFin().isEmpty()) {
			textError= "Hora Fin: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
/*	    
	    if(request.getIdPaciente()<1) {
			textError= "idPaciente: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
*/
	    if(request.getIdProgramacion()<1) {
			textError= "idProgramacion: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
	    if(request.getIdMedico()<1) {
			textError= "idMedico: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    
	    if(request.getIdEspecialidad()<1) {
			textError= "idEspecialidad: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    
	    if(request.getIdServicio()<1) {
			textError= "idServicio: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
/*	    
	    if(request.getIdProducto()<1) {
			textError= "idProducto: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
*/	    

    
	    CitaSeparadaResponse response = null; 
    	response = citaSeparadaService.crearCitaSeparada(request,idUsuario);
//    	if(response != null) {
            return new ResponseEntity<CitaSeparadaResponse>(response, HttpStatus.OK);
  //  	}
	
    }

    @PostMapping("/citaSeparadaLeer")
    public ResponseEntity<?> leerCitaSeparadaXIdPaciente(   @RequestBody CitaSeparadaRequest request  ){
	    String textError="";
	    
		int idUsuario = 0;
		String usuario= request.getUsuario();
		 idUsuario = usuarioService.xusername_leer(usuario);
		if (idUsuario==0  ) {
			textError= "Usuario: se encuentra de baja" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
		} 
			
	    
	    if(request.getUsuario().isEmpty()) {
			textError= "Usuario: incorrecto}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
	    CitaSeparadaResponse response = null; 
		System.out.println("Procesando servicio");
    	response = citaSeparadaService.leerCitaSeparadaXIdPaciente(idUsuario);
         return new ResponseEntity<CitaSeparadaResponse>(response, HttpStatus.OK);
	
    }
    
    @PostMapping("/citaSeparadaConPagoVirtualLeer")
    public ResponseEntity<?> leerCitaSeparadaConPagoVirtualXIdPaciente(   @RequestBody CitaSeparadaRequest request  ){
	    String textError="";
	    
		int idUsuario = 0;
		String usuario= request.getUsuario();
		 idUsuario = usuarioService.xusername_leer(usuario);
		if (idUsuario==0  ) {
			textError= "Usuario: se encuentra de baja" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
		} 
			
	    
	    if(request.getUsuario().isEmpty()) {
			textError= "Usuario: incorrecto}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
	    CitaSeparadaResponse response = null; 
		System.out.println("Procesando servicio");
    	response = citaSeparadaService.leerCitaSeparadaConPagoVirtualXIdPaciente(idUsuario);
         return new ResponseEntity<CitaSeparadaResponse>(response, HttpStatus.OK);
	
    }
    
    @PostMapping("/citaSeparadaConComprobanteLeer")
    public ResponseEntity<?> leerCitaSeparadaConPagoVirtualConcomprobanteXIdPaciente(   @RequestBody CitaSeparadaRequest request  ){
	    String textError="";
	    
		int idUsuario = 0;
		String usuario= request.getUsuario();
		 idUsuario = usuarioService.xusername_leer(usuario);
		if (idUsuario==0  ) {
			textError= "Usuario: se encuentra de baja" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
		} 
			
	    
	    if(request.getUsuario().isEmpty()) {
			textError= "Usuario: incorrecto}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
	    CitaSeparadaResponse response = null; 
		System.out.println("Procesando servicio");
    	response = citaSeparadaService.leerCitaSeparadaXIdPaciente(idUsuario);
         return new ResponseEntity<CitaSeparadaResponse>(response, HttpStatus.OK);
	
    }
        

    @PostMapping("/confimarCitaSeparada")
    public ResponseEntity<?> confirmarCitaSeparada( @RequestBody citaSeparadaFacturarRequest   request ){
	    CitaResponse response = null;
		CitaFacturacionRequest facturaCita = new CitaFacturacionRequest();
	    CitaSeparadaEntityResponse cs = new CitaSeparadaEntityResponse();
	    System.out.println("****CITA SEPARADA"+ request.getIdCitaSeparada());
	     cs = citaSeparadaService.leerCitaSeparadaXIdCitaSeparada(request.getIdCitaSeparada());
	     facturaCita.setIdCitaSeparada(request.getIdCitaSeparada());
	     facturaCita.setUsuario(request.getUsuario());
	     facturaCita.setIdMedico(cs.getIdMedico());
	     facturaCita.setFecha("");
	     facturaCita.setIdPaciente(cs.getIdPaciente());
	     facturaCita.setIdEspecialidad(cs.getIdEspecialidad());
	     facturaCita.setIdCita(cs.getIdCitaSeparada());
	     facturaCita.setHoraInicio(cs.getHoraInicio());
	     facturaCita.setHoraFin(cs.getHoraFin());
	     facturaCita.setIdServicio(cs.getIdServicio());
	     facturaCita.setIdProgramacion(cs.getIdProgramacion());
	     facturaCita.setFechaSolicitud(cs.getFechaSolicitud());
	     facturaCita.setHoraSolicitud(cs.getFechaSolicitud());
	     facturaCita.setIdProducto(cs.getIdProducto()) ;
	     facturaCita.setPrecioUnitario(cs.getPrecioUnitario());
	     facturaCita.setIdPuntoCarga(0);
	     facturaCita.setIdTipoFinanciamiento(1);
	     facturaCita.setIdFuenteFinanciamiento(1);
	     facturaCita.setIdUsuario(cs.getIdUsuario()) ;
		 
		 System.out.println("***** LLEGO DATO DE CONFIRMAION"+facturaCita.getUsuario());
//    	response = citaService.crearCita(c);
    	 int idCita = citaSeparadaService.confirmarCitaSeparada(facturaCita);
    	 response = citaService.leerCita(idCita);
    	if(idCita != 0) 
            return new ResponseEntity<CitaResponse>(response, HttpStatus.OK);
        else    
            return new ResponseEntity<CitaResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	
   	 }
        

       
   //Entity
   static class citaSeparadaFacturarRequest {
	int idCitaSeparada ;
	
	String usuario ;
	public int getIdCitaSeparada() {
		return idCitaSeparada;
	}
	public void setIdCitaSeparada(int idCitaSeparada) {
		this.idCitaSeparada = idCitaSeparada;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
   	   
 }
       
    
}