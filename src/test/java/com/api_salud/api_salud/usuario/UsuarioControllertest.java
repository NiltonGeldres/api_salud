package com.api_salud.api_salud.usuario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.api_salud.api_salud.controller.UsuarioController;
import com.api_salud.api_salud.request.UsuarioRequest;
import com.api_salud.api_salud.response.UsuarioResponse;
import com.api_salud.api_salud.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UsuarioController.class) // Solo carga lo necesario para el controller
class UsuarioControllertest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService; // Simulamos el servicio

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    @Test
    @DisplayName("Debe retornar 201 Created cuando el registro es exitoso")
    void signin_Exito() throws Exception {
        // 1. Preparar datos de entrada
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("mcayo");
        request.setPassword("1234");
        request.setEmail("mcayo@hotmail.com");
        request.setApellidoPaterno("cayo");
        request.setApellidoMaterno("Bohorquez");
        request.setPrimerNombre("Maria");
        request.setSegundoNombre("Concepcion");
        request.setNumeroCelular("99999999");
        request.setIdSexo(2);
        request.setIdTipoDocumento(2);
        request.setNumeroDocumento("777777777");
        request.setFechaAlta("20260320");
        request.setFechaBaja("20260320");
        request.setFechaModificacion("20260320");
        request.setEstado("A");
        request.setIdEntidad(4);

        // 2. Simular respuesta exitosa del Service
        UsuarioResponse response = new UsuarioResponse();
        response.setIdUsuario(1);
        response.setUsername("mcayo");
        response.setEstado("A");
        System.out.println("ANTES");
        when(usuarioService.usuarioCrear(any(UsuarioRequest.class))).thenReturn(response);
        System.out.println("DESPUES");

        // 3. Ejecutar la petición y verificar resultados
        mockMvc.perform(post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated()) // Verifica código 201
                .andExpect(jsonPath("$.username").value("mcayo"))
                .andExpect(jsonPath("$.idUsuario").value(1));
    }

    @Test
    @DisplayName("Debe retornar 409 Conflict cuando hay un duplicado")
    void signin_ErrorDuplicado() throws Exception {
        UsuarioRequest request = new UsuarioRequest();
        request.setEmail("duplicado@mail.com");

        // Simular el error que ya conocemos del Service
        UsuarioResponse errorResponse = new UsuarioResponse();
        errorResponse.setEstado("False");
        errorResponse.setUsername("ERROR: EMAIL_DUPLICADO");

        when(usuarioService.usuarioCrear(any(UsuarioRequest.class))).thenReturn(errorResponse);

        mockMvc.perform(post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict()) // Verifica código 409
                .andExpect(jsonPath("$.username").value("ERROR: EMAIL_DUPLICADO"))
                .andExpect(jsonPath("$.estado").value("False"));
    }
    */
}