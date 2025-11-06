package com.api_salud.api_salud;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.TurnoEntity;
import com.api_salud.api_salud.repository.TurnoDao;
import com.api_salud.api_salud.response.TurnoResponse;

@SpringBootTest
public class check_TestTurnos {

	@Autowired
	TurnoDao dao; 
	@Test
	void contextLoads() {
		List<TurnoEntity> retorno1 = dao.turnoLeer();
	

		
	}
	
}
