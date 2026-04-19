package com.api_salud.api_salud.citas;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api_salud.api_salud.controller.CitaController;
import com.api_salud.api_salud.controller.CitaSeparadaController;
import com.api_salud.api_salud.dto.PacienteCitadoDto;
import com.api_salud.api_salud.request.CitaMedicoDiariaRequest;
import com.api_salud.api_salud.request.CitaSeparadaRequest;
import com.api_salud.api_salud.response.PacienteCitadoResponse;

@SpringBootTest
public class CitasMedicoxFecha {

	@Autowired
	CitaController citaController;
	
	@Test
	void contextLoads() {
		CitaMedicoDiariaRequest c = new CitaMedicoDiariaRequest();
		c.setFecha("20260313");
		c.setIdMedico(1762);;
		 ResponseEntity<?>  
		 response =	citaController.listarCitasDiarias(c);
		 

		   List<PacienteCitadoResponse> lista = (List<PacienteCitadoResponse>) response.getBody();

		    // 3. Verificamos y recorremos
		    if (lista != null) {
		        System.out.println("Cantidad de registros encontrados en el test: " + lista.size());
		        
		        for (PacienteCitadoResponse p : lista) {
		            System.out.println("Paciente: " + p.getNombres());
		        }
		    } else {
		        System.out.println("La respuesta no contiene datos.");
		    }
		  
	
		
	}
	
}


