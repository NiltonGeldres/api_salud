package com.api_salud.api_salud;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.CitaSeparadaPagadaEntity;
import com.api_salud.api_salud.repository.CitaSeparadaPagoVirtualDao;

@SpringBootTest
public class TestCitaSeparadaPagadaXMedico {

	@Autowired
	CitaSeparadaPagoVirtualDao citaSeparadaPagoVirtualDao;
	
	@Test
	void contextLoads() {
	//	CitaSeparadaPagoVirtualRequest c = new CitaSeparadaPagoVirtualRequest();
	//	c.setIdCitaSeparadaPagoVirtual(0);
		
		System.out.println("Testeando");
		List<CitaSeparadaPagadaEntity> response =	
				citaSeparadaPagoVirtualDao.leerCitaSeparadaPagadaXMedico(1762);
		}


}