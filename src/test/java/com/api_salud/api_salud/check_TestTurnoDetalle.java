package com.api_salud.api_salud;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.TurnoDetalleEntity;
import com.api_salud.api_salud.repository.TurnoDetalleDao;
import com.api_salud.api_salud.service.TurnoDetalleService;

@SpringBootTest
public class check_TestTurnoDetalle {
	
	@Autowired
	TurnoDetalleService turnoDetalleService; 
	@Test
	void contextLoads() {
		List<TurnoDetalleEntity> retorno1 = turnoDetalleService.xIdTurnoLeer(7);
		for(TurnoDetalleEntity x :  retorno1){
			System.out.println("Inicio   "+x.getHoraInicio());
			
		};

		
	}
		

}
