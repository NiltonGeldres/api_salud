package com.api_salud.api_salud_atencion_medica_test;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.AtencionMedicaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GuardarAtencionAndPdfTest {

    @Autowired
    private AtencionMedicaService atencionMedicaService;

    @Test
    void testGuardarAtencionYGenerarPdfFisico() {
        // 1. Instanciamos y poblamos el Request de simulación médica
        AtencionMedicaRequest request = new AtencionMedicaRequest();
        request.setIdPaciente(1050); // ID de prueba existente o ficticio
        request.setIdMedicoIngreso(1);        // El médico cuya rúbrica ("medico_1.png") está en tu unidad D
        request.setIdEstadoAtencion(3); // Estado Cerrado/Atendido
        request.setEstadoFirma("FIRMADO_ELECTRONICO"); // Disparador obligatorio del PDF
        request.setOrigenRegistroUsuario("MEDICO_MOBILE_APP");

        // Inicializamos colecciones internas si tu estructura las requiere para no enviar nulls
        request.setSintomas(new ArrayList<>()); 
        // Si tu request tiene un setDiagnosticos o campos adicionales, puedes inicializarlos aquí de forma básica:
        // request.setDiagnosticos(new ArrayList<>());

        System.out.println("====== INICIANDO TEST: GUARDADO ATÓMICO + GENERACIÓN PDF ======");

        // 2. Ejecutamos el flujo transaccional completo en el Service
        AtencionMedicaResponse response = atencionMedicaService.guardarAtencionMedica(request);

        // 3. ASERCIONES Y VERIFICACIONES DE CONTROL

        // Verificamos que la capa de base de datos haya operado con éxito
        assertNotNull(response, "La respuesta del servicio no debería ser nula.");
        assertTrue(response.isExito(), "El procesamiento de la atención debió retornar éxito.");
        assertNotNull(response.getIdAtencion(), "PostgreSQL debió retornar el ID único bigint de la atención.");
        
        System.out.println("✔ ÉXITO EN BASE DE DATOS - ID Atención Generado: " + response.getIdAtencion());

        // Verificamos que la ruta del PDF se haya construido y retornado en el JSON
        String rutaPdfGenerado = response.getRutaPdfFirmado();
        assertNotNull(rutaPdfGenerado, "El flujo debió retornar la ruta absoluta del PDF firmado.");
        
        System.out.println("📂 Ruta física registrada en BD: " + rutaPdfGenerado);

        // 4. VERIFICACIÓN FÍSICA EN WINDOWS (Unidad D)
        File archivoPdf = new File(rutaPdfGenerado);
        assertTrue(archivoPdf.exists(), "El archivo PDF no se encuentra físicamente en el directorio STORAGE.");
        assertTrue(archivoPdf.length() > 0, "El archivo PDF se creó pero está vacío (0 bytes).");

        System.out.println("✔ ÉXITO FÍSICO EN WINDOWS - Tamaño del archivo: " + archivoPdf.length() + " bytes.");
        System.out.println("================== TEST FINALIZADO CON ÉXITO ==================");
    }
}