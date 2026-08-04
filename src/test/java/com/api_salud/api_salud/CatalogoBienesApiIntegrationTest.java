package com.api_salud.api_salud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@SpringBootTest
@AutoConfigureMockMvc
class CatalogoBienesApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser // Simula un usuario autenticado para Spring Security
    @DisplayName("GET /api/v1/catalogos/bienes/buscar - Prueba E2E completa")
    void testBuscarBienesApiCompleta_Exito() throws Exception {
        mockMvc.perform(get("/api/v1/catalogos/bienes/buscar")
                .param("idEntidad", "2")
                .param("termino", "paracetamol")
                .param("tipoProducto", "0")
                .param("tamanoPagina", "10")
                .param("paginaActual", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // 👈 Imprime la petición y el JSON de respuesta en la consola
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EXITO"))
                .andExpect(jsonPath("$.codigo").value(200))
                .andExpect(jsonPath("$.meta.idEntidad").value(2))
                .andExpect(jsonPath("$.meta.terminoBusqueda").value("paracetamol"))
                .andExpect(jsonPath("$.data").isArray());
    }
}