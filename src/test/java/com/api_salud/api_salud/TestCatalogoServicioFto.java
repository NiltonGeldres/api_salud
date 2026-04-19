package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.entity.CatalogoServicioFtoEntity;
import com.api_salud.api_salud.request.CatalogoServicioFtoRequest;
import com.api_salud.api_salud.response.CatalogoServicioFtoResponse;
import com.api_salud.api_salud.service.CatalogoServicioFtoService;


@SpringBootTest
public class TestCatalogoServicioFto {
	
	@Autowired
	CatalogoServicioFtoService catalogoServicioFtoService;

	@Test
	void contextLoads() {
		CatalogoServicioFtoRequest  c = new  CatalogoServicioFtoRequest();
		c.setIdProducto(4583);
		c.setIdTipoFinanciamiento(1);
		
		CatalogoServicioFtoResponse data =	catalogoServicioFtoService.leerCatalogoServiciosXIdTipoFinanciamiento(c);
		for (CatalogoServicioFtoEntity element : data.getCatalogoServicioFto()) {
			System.out.println("Elemento Entity :  " +element.getIdProducto());
		}
	}
}
