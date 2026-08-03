package com.api_salud.api_salud;

//JUnit 5
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Mockito
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//Spring Test & MockMvc
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//Imports estáticos de MockMvc (Peticiones y Assertions)
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api_salud.api_salud.controller.CatalogoBienesController;
import com.api_salud.api_salud.request.CatalogoBienesRequest;
//Tus DTOs y Servicios
import com.api_salud.api_salud.response.CatalogoBienesResponse;
import com.api_salud.api_salud.service.CatalogoBienesService;

@WebMvcTest(CatalogoBienesController.class)
class CatalogoBienesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogoBienesService bienService; // Se mockea el Service

    @Test
    @DisplayName("GET /buscar - Debe retornar 200 OK con la estructura JSON")
    void buscarBienes_DebeRetornarOk() throws Exception {
        // Arrange: Prepara un objeto mock de respuesta básica
        CatalogoBienesResponse mockResponse = new CatalogoBienesResponse();
        mockResponse.setEstado("EXITO");
        mockResponse.setCodigo(200);

        // Mockeamos la llamada al servicio esperando el objeto DTO de Request
        when(bienService.buscarBienes(any(CatalogoBienesRequest.class)))
                .thenReturn(mockResponse);

        // Act & Assert: Simulas la llamada HTTP GET y verificas la respuesta del servidor
        mockMvc.perform(get("/api/v1/catalogos/bienes/buscar")
                .param("termino", "LORA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EXITO"))
                .andExpect(jsonPath("$.codigo").value(200));
    }
}