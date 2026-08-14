package com.api_salud.api_salud;

import com.api_salud.api_salud.context.TenantContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class CatalogoBienesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        TenantContext.setEntidadId(2);
        TenantContext.setUsuarioId(10);
        TenantContext.setCurrentUser("usuario_test");
    }

    @AfterEach
    void tearDown() {
        TenantContext.clear();
    }

    @Test
    @WithMockUser
    @DisplayName("GET /api/v1/catalogos/bienes/buscar - Búsqueda de bienes exitosa")
    void testBuscarBienes_Exito() throws Exception {
        mockMvc.perform(get("/api/v1/catalogos/bienes/buscar")
                        .param("idEntidad", "2")
                        .param("termino", "PARACETAMOL")
                        .param("tipoProducto", "1")
                        .param("tamanoPagina", "10")
                        .param("paginaActual", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("GET /api/v1/catalogos/bienes/paquete/detalle - Obtener detalle de paquete de bienes exitoso")
    void testObtenerDetallePaqueteBienes_Exito() throws Exception {
        mockMvc.perform(get("/api/v1/catalogos/bienes/paquete/detalle")
                        .param("idPaquete", "67")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EXITO"))
                .andExpect(jsonPath("$.codigo").value(200))
                .andExpect(jsonPath("$.meta.idPaquete").value(67))
                .andExpect(jsonPath("$.meta.idEntidadConsulta").value(2))
                .andExpect(jsonPath("$.data").isArray());
    }
}