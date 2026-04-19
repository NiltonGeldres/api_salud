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

		
		
	    if(request.getFecha().isEmpty()) {
			textError= "Fecha: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	    
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

	    //* Destino a pagar *//
	    if(request.getDestino().isEmpty()) {
			textError= "Destino: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }
	    
	    //* Empresa Destno ,Yape Plin *//
	    if(request.getEntidadDestino().isEmpty()) {
			textError= "EntidadDestino: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    }

	    
	    CitaSeparadaPagoVirtualResponse response = null; 
    	response = citaSeparadaPagoVirtualService.crearCitaSeparadaPagoVirtual(request);
//    	if(response != null) {
            return new ResponseEntity<CitaSeparadaPagoVirtualResponse>(response, HttpStatus.OK);
  //  	}
	
            
    }
	
    @PostMapping("/citaSeparadaConPagoVirtualXMedicoLeer")
    public ResponseEntity<?> leerCitaSeparadaPagadaXMedico( @RequestBody CitaSeparadaPagadaXMedicoRequest   request  ){
		 int idMedico =1762;    	
	    String textError="";
	    if(request.getIdMedico()==0 ) {
			textError= "IdMedico: no ha sido ingresado" ;
	        return new ResponseEntity<String>(textError, HttpStatus.BAD_REQUEST);
	    } 	
	
	    
		List<CitaSeparadaPagadaEntity> response = null; 
    	response = citaSeparadaPagoVirtualService.leerCitaSeparadaPagadaXMedico(request.getIdMedico());
            return new ResponseEntity<List<CitaSeparadaPagadaEntity>>(response, HttpStatus.OK);
	
            
    }
	

    
}
