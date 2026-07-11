package com.api_salud.api_salud;

import com.api_salud.api_salud.repository.AtencionMedicaRepository;
import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.AtencionMedicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.util.FileCopyUtils;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource; // 🔥 Usa esta clase específica


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 🎯 Controla el orden de ejecución secuencial
public class GuardarAtencionAndPdfTest {

    @Autowired
    private AtencionMedicaService atencionMedicaService;

    @Autowired
    private AtencionMedicaRepository atencionMedicaRepository;

    // Variable estática para compartir el ID generado entre el Test 1 y el Test 2
    private static Long idAtencionCompartido;
    
    private final ClassPathResource jsonResource = new ClassPathResource("atencion_medica_test.json");
    
    @Autowired
    private ObjectMapper objectMapper;
    
    // =====================================================================
    // TEST 1: GUARDAR ATENCIÓN MÉDICA (PERSISTENCIA ATÓMICA)
    // =====================================================================
/*    @Test
    @Order(1)
    @Transactional
    @Commit // 🔥 Guarda físicamente en Postgres para que el Test 2 pueda leerlo
    void test1_GuardarAtencionMedica() {
        try {
        	


        	// Luego, en tu método de test:
        	String jsonContent = FileCopyUtils.copyToString(
        	    new InputStreamReader(jsonResource.getInputStream(), StandardCharsets.UTF_8)
        	);
        	
        	AtencionMedicaRequest request = objectMapper.readValue(jsonContent, AtencionMedicaRequest.class);
            System.out.println("====== STEP 1: EJECUTANDO PERSISTENCIA CLÍNICA ATÓMICA ======");
            AtencionMedicaResponse response = atencionMedicaService.guardarAtencionMedica(request);

            // Aserciones del Guardado exitoso
            assertNotNull(response, "La respuesta del servicio de guardado no debería ser nula.");
            assertTrue(response.isExito());
            assertNotNull(response.getIdAtencion(), "El ID generado en la BD es nulo.");
            assertEquals("PENDIENTE", response.getEstadoFirma(), "El estado inicial de la firma debe ser PENDIENTE.");
            assertNull(response.getRutaPdfFirmado(), "La ruta del PDF debería ser nula en el guardado inicial.");

            // Guardamos el ID en la variable estática para usarlo en el siguiente test
            idAtencionCompartido = response.getIdAtencion();
            System.out.println("✔ ÉXITO: Atención guardada correctamente en BD con ID: " + idAtencionCompartido);

        } catch (Exception e) {
            fail("El Test 1 (Guardar) falló por una excepción: " + e.getMessage());
        }
    }
*/
    // =====================================================================
    // TEST 2: FIRMAR DOCUMENTO Y GENERAR ARCHIVO PDF FISICO
    // =====================================================================
    @Test
    @Order(2)
    @Transactional
    @Commit // Asegura que los updates de la ruta y firma queden persistidos
    void test2_FirmarYGenerarDocumentoPdf() {
    	Long idAtencionCompartido =179L  ;
    	System.out.println("ANTES JSON RECUPERADO DE BD: " );
    	String jsonAtencion = atencionMedicaRepository.obtenerJsonAtencionPorId(idAtencionCompartido);
    	System.out.println("JSON RECUPERADO DE BD: " + jsonAtencion);
    	
        // Validamos que el primer test haya dejado un ID listo
        assertNotNull(idAtencionCompartido, "No se puede firmar porque el ID de la atención es nulo (Falló el Test 1).");

        try {
            System.out.println("====== STEP 2: EJECUTANDO FIRMA Y GENERACIÓN DE PDF PARA ID: " + idAtencionCompartido + " ======");
            
            // Invocamos el segundo método independiente basado en la verdad de la BD
            AtencionMedicaResponse response = atencionMedicaService.firmarYGenerarPdf(idAtencionCompartido);

            // Aserciones del proceso de firmado
            assertNotNull(response);
            assertTrue(response.isExito());
            assertEquals("FIRMADO_ELECTRONICO", response.getEstadoFirma());
            
            String rutaPdf = response.getRutaPdfFirmado();
            assertNotNull(rutaPdf, "La ruta del PDF firmado no debería ser nula.");
            System.out.println("📂 Archivo PDF generado y firmado en: " + rutaPdf);

            // Verificación física real en la unidad de almacenamiento/disco duro
            File archivoFisico = new File(rutaPdf);
            assertTrue(archivoFisico.exists(), "El archivo PDF no se encuentra físicamente en el disco.");
            System.out.println("✔ ÉXITO: PDF validado en disco correctamente.");

        } catch (Exception e) {
            fail("El Test 2 (Firma/PDF) falló por una excepción: " + e.getMessage());
        }
    }
}

/*String jsonNativo = "{\n" +
"  \"idPaciente\": 34,\n" +
"  \"idCuentaAtencion\": 34,\n" +
"  \"idServicio\": 1,\n" +
"  \"idEstadoAtencion\": 3,\n" +
"  \"estadoFirma\": \"FIRMADO_ELECTRONICO\",\n" + // El service lo forzará a PENDIENTE
"  \"origenRegistroUsuario\": \"MEDICO_MOBILE_APP\",\n" +
"  \"idMedicoIngreso\": 1,\n" +
"  \"idEntidad\": 2,\n" +
"  \"idUsuarioRegistro\": 12,\n" +
"  \"paciente\": {\n" +
"    \"hc\": \"HC-2026-0034\",\n" +
"    \"name\": \"CAYO BOHORQUEZ MARIA CONCEPCION\"\n" +
"  },\n" +
"  \"triaje\": [\n" +
"    {\"idTriaje\":49,\"valorTriaje\":\"37\"},\n" +
"    {\"idTriaje\":50,\"valorTriaje\":\"70\"},\n" +
"    {\"idTriaje\":51,\"valorTriaje\":\"160\"}\n" +
"  ],\n" +
"  \"antecedentes\": [\n" +
"    {\"descripcion\":\"Familiares: Cancer Colon\"}\n" +
"  ],\n" +
"  \"sintomas\": [\n" +
"    {\"idSintoma\": 1, \"descripcion\": \"Dolor lumbar agudo\"},\n" +
"    {\"idSintoma\": null, \"descripcion\": \"Sensación de hormigueo en miembros inferiores referida por el paciente\"}\n" +
"  ],\n" +
"  \"examenFisico\": [\n" +
"    {\"descripcion\":\"Inflamacion muscular en la espalda baja\"}\n" +
"  ],\n" +
"  \"diagnosticos\": [\n" +
"    {\"idDiagnostico\":174,\"idDiagnosticoOrden\":1,\"idSubclasificacion\":1}\n" +
"  ],\n" +
"  \"examenesAuxiliares\": [\n" +
"    {\"idPuntoCarga\":1,\"idProducto\":3,\"cantidad\":1,\"observacion\":\"Perfil Lipídico\",\"idDiagnostico\":201},\n" +
"    {\"idPuntoCarga\":1,\"idProducto\":4,\"cantidad\":1,\"observacion\":\"Glucosa en ayunas\",\"idDiagnostico\":201}\n" +
"  ],\n" +
"  \"medicacion\": [\n" +
"    {\"idAlmacen\":1,\"idProducto\":854,\"cantidadDosis\":1,\"idUmDosis\":1,\"idFrecuenciaDosis\":2,\"cantidadPeriodo\":14,\"idViaAdministracion\":1,\"cantidadTotal\":28,\"idDiagnostico\":201,\"indicaciones\":\"Omeprazol 20mg\"},\n" +
"    {\"idAlmacen\":1,\"idProducto\":862,\"cantidadDosis\":2,\"idUmDosis\":1,\"idFrecuenciaDosis\":2,\"cantidadPeriodo\":14,\"idViaAdministracion\":1,\"cantidadTotal\":56,\"idDiagnostico\":201,\"indicaciones\":\"Amoxicilina 500mg\"}\n" +
"  ],\n" +
"  \"alta\": {\n" +
"    \"descripcion\": \"Regresar en 16 dias\"\n" +
"  }\n" +
"}";



String jsonNativo = "{" +
"\"idPaciente\": 34, " +
"\"idCuentaAtencion\": 34, " +
"\"idServicio\": 1, " +
"\"idEstadoAtencion\": 3, " +
"\"estadoFirma\": \"PENDIENTE\", " +
"\"origenRegistroUsuario\": \"MEDICO_MOBILE_APP\", " +
"\"idMedicoIngreso\": 1, " +
"\"idEntidad\": 2, " +
"\"idUsuarioRegistro\": 12, " +
"\"paciente\": { \"hc\": \"HC-2026-0034\", \"name\": \"CAYO BOHORQUEZ MARIA CONCEPCION\" }, " +
"\"triaje\": [{\"idTriaje\": 49, \"valorTriaje\": \"37\"}, {\"idTriaje\": 50, \"valorTriaje\": \"70\"}, {\"idTriaje\": 51, \"valorTriaje\": \"160\"}], " +
"\"sintomas\": [{\"idSintoma\": 1, \"descripcion\": null}, {\"idSintoma\": 0, \"descripcion\": \"Dolor de cabeza intenso referido por paciente\"}], " +
"\"antecedentes\": [{\"idAntecedente\": 0, \"descripcion\": \"Paciente refiere alergia a penicilina\"}], " +
"\"examenFisico\": [{\"idExamenFisico\": 0, \"descripcion\": \"Paciente se muestra orientado en tiempo y espacio\"}], " +
"\"diagnosticos\": [{\"idDiagnostico\": 1, \"idDiagnosticoOrden\": 1, \"idSubclasificacion\": 1}], " +
"\"examenesAuxiliares\": [{\"idPuntoCarga\": 1, \"idProducto\": 3, \"cantidad\": 1, \"observacion\": \"Perfil Lipídico\", \"idDiagnostico\": 1}], " +
"\"medicacion\": [{\"idAlmacen\": 1, \"idProducto\": 854, \"cantidadDosis\": 1, \"idUmDosis\": 1, \"idFrecuenciaDosis\": 1, \"cantidadPeriodo\": 14, \"idViaAdministracion\": 1, \"cantidadTotal\": 28, \"idDiagnostico\": 1, \"indicaciones\": \"Omeprazol 20mg\"}], " +
"\"alta\": [{\"idAlta\": 0, \"descripcion\": \"Regresar en 15 días para control de laboratorio\"}] " +
"}";
*/