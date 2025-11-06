package com.api_salud.api_salud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.entity.CitaSeparadaPagadaEntity;
import com.api_salud.api_salud.request.CitaSeparadaPagadaXMedicoRequest;
import com.api_salud.api_salud.request.CitaSeparadaPagoVirtualRequest;
import com.api_salud.api_salud.response.CitaSeparadaPagoVirtualResponse;
import com.api_salud.api_salud.service.CitaSeparadaPagoVirtualService;
import com.api_salud.api_salud.service.UsuarioService;

@RestController
@RequestMapping("")
public class CitaSeparadaPagoVirtualController {


	@Autowired
	CitaSeparadaPagoVirtualService citaSeparadaPagoVirtualService;

	@Autowired
	UsuarioService usuarioService;	
	
    @PostMapping("/citaSeparadaPagoVirtualCrear")
    public ResponseEntity<?> crearCitaSeparadaPagoVirtualCrear( @RequestBody CitaSeparadaPagoVirtualRequest request  ){
	    String textError="";

		int idUsuario = 0;
		String usuario= request.getUsuario();
		 idUsuario = usuarioService.xusername_leer(usuario);
		 request.setIdUsuario(idUsuario);
		if (idUsuario==0  ) {
			textError= "Usuario: se encuentra de baja" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
		} 

		
		System.out.println("ID Usuario:"+idUsuario );
		
		
    //	System.out.println("Ingreso a Controller crearCitaSeparada " +request.getIdServicio());
	    if(request.getFecha().isEmpty()) {
			textError= "Fecha: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
/*	    if(request.getIdCitaSeparada()<1 ) {
			textError= "IdCitaSeparada: no ha sido ingresado}" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
*/	    
	    if(request.getNroOperacion().isEmpty()) {
			textError= "IdCitaSeparada: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    if(request.getCorreo().isEmpty()) {
			textError= "Correo: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
	    if(request.getCelular().isEmpty()) {
			textError= "Celular: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    
	    System.out.println(request.getMonto());
	    if(request.getMonto()<1) {
			textError= "Monto: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    
	    if(request.getIdTipoOperacion()<1) {
			textError= "idTipoOperacion: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    
	    if(request.getOrigen().isEmpty()) {
			textError= "Origen: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    if(request.getDestino().isEmpty()) {
			textError= "Destino: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    if(request.getEntidadDestino().isEmpty()) {
			textError= "EntidadDestino: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }

		System.out.println("Validacion correcta:" );
	    
	    CitaSeparadaPagoVirtualResponse response = null; 
    	response = citaSeparadaPagoVirtualService.crearCitaSeparadaPagoVirtual(request);
//    	if(response != null) {
            return new ResponseEntity<CitaSeparadaPagoVirtualResponse>(response, HttpStatus.OK);
  //  	}
	
            
    }
	
    @PostMapping("/citaSeparadaConPagoVirtualXMedicoLeer")
    public ResponseEntity<?> leerCitaSeparadaPagadaXMedico( @RequestBody CitaSeparadaPagadaXMedicoRequest   request  ){
    	System.out.println("INGRESO A VER CITAS PAGADAS");
    	System.out.println(" Medico:  "+request.getMedico() );
//		 int idMedico = usuarioService.xusername_leer(request.getMedico());    	
		 int idMedico =1762;    	
	    String textError="";
    	System.out.println(" Medico:  "+idMedico );
		
//	    if(idMedico==0 ) {
	    if(request.getIdMedico()==0 ) {
			textError= "IdMedico: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	
		System.out.println("Validacion correcta:" );
	    
		List<CitaSeparadaPagadaEntity> response = null; 
    	response = citaSeparadaPagoVirtualService.leerCitaSeparadaPagadaXMedico(request.getIdMedico());
            return new ResponseEntity<List<CitaSeparadaPagadaEntity>>(response, HttpStatus.OK);
	
            
    }
	

    
}
