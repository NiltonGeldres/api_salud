package com.api_salud.api_salud;

import com.api_salud.api_salud.repository.CatalogoBienesRepository;
import com.api_salud.api_salud.repository.CatalogoServiciosRepository;
import com.api_salud.api_salud.response.CatalogoBienesResponse;
import com.api_salud.api_salud.response.CatalogoServiciosResponse;
import com.api_salud.api_salud.service.CatalogoBienesService;
import com.api_salud.api_salud.service.CatalogoServiciosService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatalogosServiceTest {

    private CatalogoBienesRepository bienRepository;
    private CatalogoServiciosRepository serviciosRepository;
    private ObjectMapper objectMapper;

    private CatalogoBienesService bienService;
    private CatalogoServiciosService serviciosService;

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
    void testBuscarBienes_Exito() {
        String mockJsonBienes = "{"
            + "\"estado\":\"EXITO\","
            + "\"codigo\":200,"
            + "\"meta\":{\"idEntidad\":2,\"paginaActual\":1,\"tamanoPagina\":20,\"totalRegistros\":1,\"terminoBusqueda\":\"LORAT\",\"tipoProductoFiltro\":0},"
            + "\"data\":[{"
            + "\"idProducto\":101,"
            + "\"codigo\":\"MED01\","
            + "\"nombre\":\"LORATADINA 10 MG TAB\","
            + "\"nombreComercial\":\"LORATADINA\","
            + "\"denominacionPrincipio\":\"LORATADINA\","
            + "\"concentracion\":\"10 MG\","
            + "\"presentacion\":\"CAJA X 100\","
            + "\"formaFarmaceutica\":\"TABLETA\","
            + "\"tipoProducto\":0,"
            + "\"esProductoFarmaceutico\":true,"
            + "\"precioVenta\":1.50,"
            + "\"idViaDefault\":1,"
            + "\"idUmDosisDefault\":5"
            + "}]"
            + "}";

        when(bienRepository.ejecutarFnBuscarCatalogoBienes(2, "LORAT", 0, 20, 1))
            .thenReturn(mockJsonBienes);

        CatalogoBienesResponse response = bienService.buscarBienes(2, "LORAT", 0, 20, 1);

        assertNotNull(response);
        assertEquals("EXITO", response.getEstado());
        assertEquals(200, response.getCodigo());
        assertEquals(1, response.getData().size());
        assertEquals("LORATADINA 10 MG TAB", response.getData().get(0).getNombre());
        assertEquals(new BigDecimal("1.50"), response.getData().get(0).getPrecioVenta());

        verify(bienRepository, times(1)).ejecutarFnBuscarCatalogoBienes(2, "LORAT", 0, 20, 1);
    }

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
}