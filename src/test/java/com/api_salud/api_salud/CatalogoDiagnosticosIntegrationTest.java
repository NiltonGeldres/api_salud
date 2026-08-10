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
class CatalogoDiagnosticosIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    @DisplayName("GET /api/v1/catalogos/diagnosticos/buscar - Búsqueda E2E de diagnósticos exitosa")
    void testBuscarDiagnosticos_Exito() throws Exception {
        mockMvc.perform(get("/api/v1/catalogos/diagnosticos/buscar")
                        .param("busqueda", "cholera")
                        .param("limite", "10")
                        .param("pagina", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EXITO"))
                .andExpect(jsonPath("$.codigo").value(200))
                .andExpect(jsonPath("$.meta.terminoBusqueda").value("cholera"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @WithMockUser
    @DisplayName("GET /api/v1/catalogos/diagnosticos/buscar - Búsqueda sin parámetros (valores por defecto)")
    void testBuscarDiagnosticos_SinParametros_Exito() throws Exception {
        mockMvc.perform(get("/api/v1/catalogos/diagnosticos/buscar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EXITO"))
                .andExpect(jsonPath("$.data").isArray());
    }
}