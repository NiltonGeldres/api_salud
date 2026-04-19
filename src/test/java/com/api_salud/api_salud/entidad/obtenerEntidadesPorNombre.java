package com.api_salud.api_salud.entidad;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.CitaController;
import com.api_salud.api_salud.controller.EntidadController;
import com.api_salud.api_salud.request.CitaPacientePendientesRequest;
import com.api_salud.api_salud.request.EntidadRequest;
import com.api_salud.api_salud.response.EntidadResponse;
import com.api_salud.api_salud.response.PacienteCitadoResponse;
@SpringBootTest
public class obtenerEntidadesPorNombre {

	@Autowired
	EntidadController entidadController;
	
	@Test
	void contextLoads() {
		EntidadRequest c = new EntidadRequest();
		c.setNombre("reg");
		 ResponseEntity<?> response = entidadController.obtenerEntidadesPorNombre(c);
		 List<EntidadResponse> lista = (List<EntidadResponse>) response.getBody();
		    if (lista != null) {
		        System.out.println("Cantidad de registros encontrados en el test: " + lista.size());
		        
		        for (EntidadResponse p : lista) {
		            System.out.println("Paciente: " + p.getNombre());
		        }
		    } else {
		        System.out.println("La respuesta no contiene datos.");
		    }
		  
	
		
	}	
	
}
