package com.api_salud.api_salud.citas;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.CitaController;
import com.api_salud.api_salud.controller.CitaSeparadaController;
import com.api_salud.api_salud.dto.PacienteCitadoDto;
import com.api_salud.api_salud.entity.ProgramacionMedicaEntity;
import com.api_salud.api_salud.request.CitaMedicoDiariaRequest;
import com.api_salud.api_salud.request.CitaPacientePendientesRequest;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.PacienteCitadoResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class CitasPacientePendientes {
		@Autowired
		CitaController citaController;
		
		@Test
		void contextLoads() {
			CitaPacientePendientesRequest c = new CitaPacientePendientesRequest();
			c.setFecha("20260313");
			c.setIdPaciente(19);;
			 ResponseEntity<?>  
			 response =	citaController.listarCitasPendientes(c);
			 
			 List<PacienteCitadoResponse> lista = (List<PacienteCitadoResponse>) response.getBody();
			    // 3. Verificamos y recorremos
			    if (lista != null) {
			        System.out.println("Cantidad de registros encontrados en el test: " + lista.size());
			        
			        for (PacienteCitadoResponse p : lista) {
			            System.out.println("Paciente: " + p.getNombres());
			            System.out.println("Fecha: " + p.getFecha());
			        }
			        
			        
			    } else {
			        System.out.println("La respuesta no contiene datos.");
			    }
			  
		
			
		}
		
	
	
	
}
