package com.api_salud.api_salud;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.api_salud.api_salud.repository.CitaDao;
import com.api_salud.api_salud.response.CitaDisponibleResponse;
import com.api_salud.api_salud.response.CitaResponse;

@SpringBootTest
public class TestCitaAsignada {
	@Autowired
	CitaDao citaDao;

	@Test
	void contextLoads() {
		
			CitaResponse data =	citaDao.obtenerCitasAsignadasDia(1762,"20240112" ,9 );
		for (CitaDisponibleResponse  element : data.getCita()) {
			//System.out.println("Elemento:  " +element.getHoraInicio());
		}
		
/*	CitaResponse data =	citaDao.asignadas(1762,"20220704" ,9 );
		for (CitaEntity  element : data.getCita()) {
			System.out.println("Elemento:  " +element.getHoraInicio());
		}*/
	}
	
}

