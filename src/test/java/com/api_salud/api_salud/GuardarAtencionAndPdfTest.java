package com.api_salud.api_salud;

import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.AtencionMedicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GuardarAtencionAndPdfTest {

    @Autowired
    private AtencionMedicaService atencionMedicaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuardarAtencionYGenerarPdfFisico() {
        try {
            // 1. JSON puro basado estrictamente en el contrato de tus DTOs
            String jsonNativo = "{\n" +
                    "  \"idPaciente\": 34,\n" +
                    "  \"idCuentaAtencion\": 34,\n" +
                    "  \"idServicio\": 1,\n" +
                    "  \"idEstadoAtencion\": 3,\n" +
                    "  \"estadoFirma\": \"FIRMADO_ELECTRONICO\",\n" +
                    "  \"origenRegistroUsuario\": \"MEDICO_MOBILE_APP\",\n" +
                    "  \"idMedicoIngreso\": 1,\n" +
                    "  \"idEntidad\": 2,\n" +
                    "  \"idUsuarioRegistro\": 12,\n" +
                    "  \"triaje\": [\n" +
                    "    { \"idTipoTriaje\": 1, \"valorTriaje\": \"37\" },\n" +
                    "    { \"idTipoTriaje\": 2, \"valorTriaje\": \"70\" },\n" +
                    "    { \"idTipoTriaje\": 3, \"valorTriaje\": \"160\" }\n" +
                    "  ],\n" +
                    "  \"antecedentes\": [\n" +
                    "    { \"idAntecedente\": 1, \"idTipoAntecedente\": 1, \"descripcion\": \"Familiares,: Cancer Colon\" }\n" +
                    "  ],\n" +
                    "  \"sintomas\": [\n" +
                    "    { \"idSintoma\": 1, \"idTipoSintoma\": 1, \"descripcion\": \"Dolor espalda baja\" }\n" +
                    "  ],\n" +
                    "  \"examenFisico\": [\n" +
                    "    { \"idExamenFisico\": 1, \"idTipoExamenFisico\": 1, \"descripcion\": \"Inflamacion muscular en la espalda baj\" }\n" +
                    "  ],\n" +
                    "  \"diagnosticos\": [\n" +
                    "    { \"idDiagnostico\": 204, \"idDiagnosticoOrden\": 1, \"idSubclasificacion\": 1, \"idLab1\": 1 }\n" +
                    "  ],\n" +
                    "  \"examenesAuxiliares\": [\n" +
                    "    { \"idPuntoCarga\": 1, \"idProducto\": 80061, \"cantidad\": 1, \"observacion\": \"Perfil Lipídico (Colesterol, Triglicéridos)\", \"idDiagnostico\": 204 },\n" +
                    "    { \"idPuntoCarga\": 1, \"idProducto\": 82947, \"cantidad\": 1, \"observacion\": \"Glucosa en ayunas en suero o plasma\", \"idDiagnostico\": 204 }\n" +
                    "  ],\n" +
                    "  \"medicacion\": [\n" +
                    "    { \"idAlmacen\": 1, \"idProducto\": 5001, \"cantidadDosis\": 1, \"idUmDosis\": 1, \"idFrecuenciaDosis\": 2, \"cantidadPeriodo\": 14, \"idViaAdministracion\": 1, \"cantidadTotal\": 28, \"idDiagnostico\": 204, \"indicaciones\": \"Omeprazol 20mg - Tomar según indicación médica\" },\n" +
                    "    { \"idAlmacen\": 1, \"idProducto\": 5002, \"cantidadDosis\": 2, \"idUmDosis\": 1, \"idFrecuenciaDosis\": 2, \"cantidadPeriodo\": 14, \"idViaAdministracion\": 1, \"cantidadTotal\": 56, \"idDiagnostico\": 204, \"indicaciones\": \"Amoxicilina 500mg - Tomar según indicación médica\" }\n" +
                    "  ],\n" +
                    "  \"alta\": {\n" +
                    "    \"descripcion\": \"Regresar en 16 dias\"\n" +
                    "  }\n" +
                    "}";

            // 2. Mapeo directo y seguro al DTO principal
            AtencionMedicaRequest request = objectMapper.readValue(jsonNativo, AtencionMedicaRequest.class);

            System.out.println("====== EJECUTANDO TEST BASADO EN EL NUEVO CONTRATO JSON NATIVO ======");

            // 3. Ejecución del flujo de tu servicio
            AtencionMedicaResponse response = atencionMedicaService.guardarAtencionMedica(request);

            // 4. Verificaciones de salida y aserciones JUnit
            assertNotNull(response, "La respuesta del servicio no debería ser nula.");
            assertTrue(response.isExito(), "El proceso de guardado e integración falló.");
            assertNotNull(response.getIdAtencion(), "El ID de la atención generado es nulo.");

            String rutaPdf = response.getRutaPdfFirmado();
            assertNotNull(rutaPdf, "La ruta del archivo PDF firmado es nula.");
            System.out.println("📂 Archivo PDF generado con éxito en el directorio: " + rutaPdf);

            // Validar existencia del archivo en la unidad física
            File archivoFisico = new File(rutaPdf);
            assertTrue(archivoFisico.exists(), "El archivo PDF no se escribió correctamente en el disco.");

            System.out.println("✔ ¡Test completado con éxito y barra en verde!");

        } catch (Exception e) {
            fail("La prueba falló debido a una excepción en el flujo de integración: " + e.getMessage());
        }
    }
}