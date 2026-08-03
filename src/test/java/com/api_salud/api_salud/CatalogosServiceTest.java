package com.api_salud.api_salud;

import com.api_salud.api_salud.repository.CatalogoBienesRepository;
import com.api_salud.api_salud.repository.CatalogoServiciosRepository;
import com.api_salud.api_salud.request.CatalogoBienesRequest;
import com.api_salud.api_salud.response.CatalogoBienesResponse;
import com.api_salud.api_salud.response.CatalogoServiciosResponse;
import com.api_salud.api_salud.service.CatalogoBienesService;
import com.api_salud.api_salud.service.CatalogoServiciosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

class CatalogosServiceTest {

    private CatalogoBienesRepository bienRepository;
    private CatalogoServiciosRepository serviciosRepository;
    private ObjectMapper objectMapper;

    private CatalogoBienesService bienService;
    private CatalogoServiciosService serviciosService;
    
//    @Value("classpath:catalogo_bienes_response.json")
//    private Resource jsonResource;
    
    private final ClassPathResource jsonResource = new ClassPathResource("catalogo_bienes_response.json");    
    
    @BeforeEach
    void setUp() {
        bienRepository = mock(CatalogoBienesRepository.class);
        serviciosRepository = mock(CatalogoServiciosRepository.class);
        objectMapper = new ObjectMapper();

        bienService = new CatalogoBienesService(bienRepository, objectMapper);
        serviciosService = new CatalogoServiciosService(serviciosRepository, objectMapper);
    }

@Test
    @DisplayName("Debe buscar fármacos mediante SimpleJdbcCall y parsear el JSON de PostgreSQL")
    void testBuscarBienes_Exito() throws Exception {
		// 1. Given (Dado)
	    String mockJsonBienes = StreamUtils.copyToString(jsonResource.getInputStream(), StandardCharsets.UTF_8);
	
	    // Creamos y poblamos el DTO de entrada
	    CatalogoBienesRequest request = new CatalogoBienesRequest();
	    request.setIdEntidad(2);
	    request.setTermino("LORA");
	    request.setTipoProducto(0);
	    request.setTamanoPagina(3);
	    request.setPaginaActual(1);
	
	    // El Mockito when sigue mockeando el repositorio con los valores que saca del request
	    when(bienRepository.ejecutarFnBuscarCatalogoBienes(2, "LORA", 0, 3, 1))
	            .thenReturn(mockJsonBienes);
	
	    // 2. When (Cuando)
	    // Pasamos la instancia del DTO de request
	    CatalogoBienesResponse response = bienService.buscarBienes(request);


        // 3. Imprimir Resultados por Consola (Opcional para depurar)
        System.out.println("==========================================================================");
        System.out.println(">>> RESULTADO DE LA BÚSQUEDA EN CATÁLOGO DE BIENES (TEST)");
        System.out.println("==========================================================================");
        System.out.println("Estado de Respuesta : " + response.getEstado());
        System.out.println("Código HTTP Status  : " + response.getCodigo());
        System.out.println("Total de Registros  : " + response.getMeta().getTotalRegistros());
        System.out.println("Término de Búsqueda : " + response.getMeta().getTerminoBusqueda());
        System.out.println("--------------------------------------------------------------------------");

        ObjectMapper prettyPrinter = new ObjectMapper();
        System.out.println(">>> JSON MAPEADO Y SERIALIZADO NUEVAMENTE:");
        System.out.println(prettyPrinter.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        System.out.println("==========================================================================");

        // 4. Assertions / Verificaciones (Versión Profesional)
        assertNotNull(response);
        assertEquals("EXITO", response.getEstado());
        assertEquals(200, response.getCodigo());

        // A. Verificamos la colección completa devuelta
        assertNotNull(response.getData());
        assertEquals(20, response.getData().size()); 

        // B. Verificamos identificadores exactos del primer elemento (sin Strings propensos a fallar por espacios)
        assertEquals(1789, response.getData().get(0).getIdProducto());
        assertEquals("00565", response.getData().get(0).getCodigo());

        // C. Validación flexible del término de búsqueda (regla de negocio)
        assertTrue(response.getData().get(0).getNombre().toUpperCase().contains("LORA"),
                "El nombre del primer producto debe contener el filtro 'LORA'");

        // D. Verificamos que se ejecutó la llamada al repositorio
        verify(bienRepository, times(1)).ejecutarFnBuscarCatalogoBienes(2, "LORA", 0, 3, 1);
    }
/*
    @Test
    @DisplayName("Debe buscar servicios mediante SimpleJdbcCall y mapear tipoServicio (idopcs)")
    void testBuscarServicios_Exito() {
        String mockJsonServicios = "{"
            + "\"estado\":\"EXITO\","
            + "\"codigo\":200,"
            + "\"meta\":{\"idEntidad\":2,\"paginaActual\":1,\"tamanoPagina\":20,\"totalRegistros\":1,\"terminoBusqueda\":\"HEMOG\",\"tipoServicioFiltro\":1},"
            + "\"data\":[{"
            + "\"idProducto\":4,"
            + "\"codigo\":\"LAB001\","
            + "\"nombre\":\"HEMOGRAMA COMPLETO\","
            + "\"idPartida\":7,"
            + "\"codMinsa\":\"99001\","
            + "\"esCpt\":1,"
            + "\"tipoServicio\":1,"
            + "\"idEstado\":1,"
            + "\"codigoSis\":\"\","
            + "\"precioVenta\":35.00"
            + "}]"
            + "}";

        when(serviciosRepository.ejecutarFnBuscarCatalogoServicios(2, "HEMOG", 1, 20, 1))
            .thenReturn(mockJsonServicios);

        CatalogoServiciosResponse response = serviciosService.buscarServicios(2, "HEMOG", 1, 20, 1);

        assertNotNull(response);
        assertEquals("EXITO", response.getEstado());
        assertEquals(1, response.getData().size());
        assertEquals("HEMOGRAMA COMPLETO", response.getData().get(0).getNombre());
        assertEquals(1, response.getData().get(0).getTipoServicio());
        assertEquals(new BigDecimal("35.00"), response.getData().get(0).getPrecioVenta());

        verify(serviciosRepository, times(1)).ejecutarFnBuscarCatalogoServicios(2, "HEMOG", 1, 20, 1);
    }
    
*/   
}