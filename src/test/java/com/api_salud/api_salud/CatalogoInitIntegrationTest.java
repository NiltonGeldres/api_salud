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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogoInitIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser // Simula un usuario autenticado para Spring Security
    @DisplayName("GET /api/v1/catalogos/init - Prueba E2E completa de carga de catálogos iniciales")
    void testObtenerCatalogosInit_Exito() throws Exception {
        mockMvc.perform(get("/api/v1/catalogos/init")
                        .param("idEntidad", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Imprime la petición y la respuesta en la consola
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.catalogoTriajes").isArray())
                .andExpect(jsonPath("$.catalogoTipoDiagnostico").isArray())
                .andExpect(jsonPath("$.catalogoViasAdministracion").isArray())
                .andExpect(jsonPath("$.catalogoPaquetesMedicacion").isArray())
                .andExpect(jsonPath("$.catalogoPaquetesExamenes").isArray());
    }

    @Test
    @WithMockUser
    @DisplayName("GET /api/v1/catalogos/init - Validación de parámetros faltantes (400 Bad Request)")
    void testObtenerCatalogosInit_SinIdEntidad_BadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/catalogos/init")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}