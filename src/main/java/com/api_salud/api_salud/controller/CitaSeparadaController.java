package com.api_salud.api_salud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.context.TenantContext;
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
    	
    	
        // Asignación de datos desde el contexto
        Long idEntidadContexto = TenantContext.getEntidadId();
        String usuarioContexto = TenantContext.getCurrentUser();
        
        if (idEntidadContexto != null) request.setIdEntidad(idEntidadContexto.intValue());
        if (usuarioContexto != null) request.setUsuario(usuarioContexto);

        // LLAMADA AL MÉTODO DE VALIDACIÓN
        String error = validarRequest(request);
        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        
    	
	    CitaSeparadaEntityResponse response = null; 
    	response = citaSeparadaService.crearCitaSeparada(request);
//    	if(response != null) {
            return new ResponseEntity<CitaSeparadaEntityResponse>(response, HttpStatus.OK);
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
    	response = citaSeparadaService.leerCitaSeparadaXIdPaciente(idUsuario);
         return new ResponseEntity<CitaSeparadaResponse>(response, HttpStatus.OK);
	
    }
        

    @PostMapping("/confimarCitaSeparada")
    public ResponseEntity<?> confirmarCitaSeparada( @RequestBody citaSeparadaFacturarRequest   request ){
	    CitaResponse response = null;
		CitaFacturacionRequest facturaCita = new CitaFacturacionRequest();
	    CitaSeparadaEntityResponse cs = new CitaSeparadaEntityResponse();
	     cs = citaSeparadaService.leerCitaSeparadaXIdCitaSeparada(request.getIdCitaSeparada());
	     facturaCita.setIdCitaSeparada(request.getIdCitaSeparada());
	     facturaCita.setUsuario(request.getUsuario());
	     facturaCita.setIdMedico(cs.getIdMedico());
	     facturaCita.setFecha(cs.getFecha());
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
       
   
	
	// Método de validación separado
	private String validarRequest(CitaSeparadaRequest r) {
	    // 1. Validaciones de Contexto y Entidad
	    if ( r.getIdEntidad() < 1) {
	        return "Id Entidad no se encuentra o es inválido";
	    }
	    
	    // 2. Validaciones de Usuario
	    if (r.getUsuario() == null || r.getUsuario().trim().isEmpty()) {
	        return "Usuario: no se ha identificado en el contexto";
	    }

	    // 3. Validaciones de Fecha y Hora
	    if (r.getFecha() == null || r.getFecha().trim().isEmpty()) {
	        return "Fecha: no ha sido ingresada";
	    }
	    if (r.getHoraInicio() == null || r.getHoraInicio().trim().isEmpty()) {
	        return "Hora Inicio: no ha sido ingresada";
	    }
	    if (r.getHoraFin() == null || r.getHoraFin().trim().isEmpty()) {
	        return "Hora Fin: no ha sido ingresada";
	    }

	    // 4. Validaciones de IDs de Negocio (Claves foráneas)
	    if (r.getIdProgramacion() < 1) {
	        return "idProgramacion: no ha sido ingresado o es inválido";
	    }
	    if (r.getIdMedico() == 0 || r.getIdMedico() < 1) {
	        return "idMedico: no ha sido ingresado";
	    }
	    if (r.getIdEspecialidad() < 1) {
	        return "idEspecialidad: no ha sido ingresado";
	    }
	    if (r.getIdServicio() < 1) {
	        return "idServicio: no ha sido ingresado";
	    }

	    // 5. Validaciones de Costo
	    if (r.getPrecioUnitario() < 0) {
	        return "Precio Unitario: debe ser un valor válido";
	    }

	    return null; // Si llega aquí, todo está correcto
	}
   
    
}