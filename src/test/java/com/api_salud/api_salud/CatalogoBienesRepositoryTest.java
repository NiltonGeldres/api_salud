package com.api_salud.api_salud;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api_salud.api_salud.repository.CatalogoBienesRepository;

@SpringBootTest
class CatalogoBienesRepositoryTest {

    @Autowired
    private CatalogoBienesRepository bienRepository;

    @Test
    @DisplayName("Debe ejecutar la función en PostgreSQL y retornar el JSON de catálogo de bienes")
    void testEjecutarFnBuscarCatalogoBienes_Exito() {
        // Arrange (Parámetros de prueba reales)
        Integer idEntidad = 2;
        String termino = "amox";
        Integer tipoProducto = 0;
        Integer tamanoPagina = 20;
        Integer paginaActual = 1;
        System.out.println("***** Arrange (Parámetros de prueba reales");

        // Act (Ejecución real contra la BD o entorno Spring)
        String jsonResult = bienRepository.ejecutarFnBuscarCatalogoBienes(
                idEntidad, 
                termino, 
                tipoProducto, 
                tamanoPagina, 
                paginaActual
        );
        
        System.out.println("JSON  "+jsonResult);

        // Assert (Validaciones sobre la cadena JSON)
        assertNotNull(jsonResult, "El resultado de la función no debe ser nulo");
        assertTrue(jsonResult.contains("amox"), "El JSON resultante debe contener el término buscado");
        assertTrue(jsonResult.contains("estado"), "El JSON debe incluir la estructura de respuesta (estado)");
        
        // Impresión opcional en consola para verificar la respuesta devuelta por BD
        System.out.println("==================================================");
        System.out.println(">>> RESPUESTA REAL DE POSTGRESQL (INTEGRATION TEST)");
        System.out.println("==================================================");
        System.out.println(jsonResult);
    }
}