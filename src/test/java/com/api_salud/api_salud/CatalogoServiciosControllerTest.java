package com.api_salud.api_salud;

import com.api_salud.api_salud.controller.CatalogoServiciosController;
import com.api_salud.api_salud.response.CatalogoServiciosResponse;
import com.api_salud.api_salud.service.CatalogoServiciosService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogoServiciosController.class)
@AutoConfigureMockMvc(addFilters = false) // Desactiva filtros de seguridad para aislar la prueba del Controller
public class CatalogoServiciosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogoServiciosService serviciosService;

    @Test
    @WithMockUser
    public void testBuscarServiciosExitoso() throws Exception {
        // Arrange
        CatalogoServiciosResponse mockResponse = new CatalogoServiciosResponse();
        mockResponse.setEstado("EXITO");
        mockResponse.setCodigo(200);

        Mockito.when(serviciosService.buscarServicios(
                eq(2), eq("HEMOGRAMA"), eq(1), eq(10), eq(1)
        )).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/catalogos/servicios/buscar")
                        .param("idEntidad", "2")
                        .param("busqueda", "HEMOGRAMA")
                        .param("tipoServicio", "1")
                        .param("limite", "10")
                        .param("pagina", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EXITO"))
                .andExpect(jsonPath("$.codigo").value(200));
    }

    @Test
    @WithMockUser
    public void testBuscarServiciosErrorValidacionSinIdEntidad() throws Exception {
        // Act & Assert: Debe retornar 400 Bad Request por la falta de 'idEntidad' (@NotNull)
        mockMvc.perform(get("/api/v1/catalogos/servicios/buscar")
                        .param("busqueda", "HEMOGRAMA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}