package com.api_salud.api_salud;

import com.api_salud.atencionmedica.controller.AtencionMedicaController;
import com.api_salud.atencionmedica.request.AtencionMedicaRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

/**
 * Prueba de integración que inyecta el AtencionMedicaController
 * y simula el registro directo de una atención médica utilizando
 * el JSON ficticio.
 */
@SpringBootTest
public class TestAtencionMedicaRegistrar {

    // Inyectamos el controlador para llamarlo directamente
    @Autowired
    AtencionMedicaController atencionMedicaController;

    // Necesitamos el ObjectMapper para convertir la cadena JSON en un objeto DTO
    @Autowired
    ObjectMapper objectMapper;

    // El JSON con datos ficticios para la solicitud
    public static final String ATENCION_MEDICA_REQUEST_JSON = 
            "{" +
            "  \"idPaciente\": 10045," +
            "  \"idCuentaAtencion\": 501," +
            "  \"idServicio\": 3," +
            "  \"idMedicoIngreso\": 150," +
            "  \"idEstadoAtencion\": 1," +
            "  \"tsIngreso\": \"2025-11-18T14:30:00-05:00\"," +
            "  \"idUsuarioRegistro\": 10," +
            "  \"origenRegistroUsuario\": \"WEB_MEDICO\"," +
            "" +
            "  \"antecedentes\": [" +
            "    {" +
            "      \"idAntecedente\": 1," +
            "      \"idTipoAntecedente\": 5," +
            "      \"descripcion\": \"Alergia conocida a la Penicilina (rash cutáneo y dificultad para respirar).\"," +
            "      \"idUsuario\": 10," +
            "      \"tsRegistro\": \"2025-11-18T14:31:00-05:00\"" +
            "    }," +
            "    {" +
            "      \"idAntecedente\": 2," +
            "      \"idTipoAntecedente\": 1," +
            "      \"descripcion\": \"Antecedente de Hipertensión Arterial, controlada con Losartán.\"," +
            "      \"idUsuario\": 10," +
            "      \"tsRegistro\": \"2025-11-18T14:31:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"diagnosticos\": [" +
            "    {" +
            "      \"idDiagnostico\": 1," +
            "      \"idSubclasificacion\": 1," +
            "      \"idLab1\": 100," +
            "      \"idDiagnosticoOrden\": 1," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:45:00-05:00\"" +
            "    }," +
            "    {" +
            "      \"idDiagnostico\": 2," +
            "      \"idSubclasificacion\": 2," +
            "      \"idLab1\": 101," +
            "      \"idDiagnosticoOrden\": 2," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:45:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"discapacidades\": [" +
            "    {" +
            "      \"idDiscapacidad\": 3," +
            "      \"idGravedadDiscapacidad\": 2," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:46:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"discapacidadOtros\": [" +
            "    {" +
            "      \"idTipoActividad\": 1," +
            "      \"idTiempoDiscapacidadAnios\": 0," +
            "      \"idTiempoDiscapacidadMeses\": 3," +
            "      \"idTiempoDiscapacidadDias\": 15," +
            "      \"idTiempoSinTrabajarAnios\": 0," +
            "      \"idTiempoSinTrabajarMeses\": 1," +
            "      \"idTiempoSinTrabajarDias\": 0," +
            "      \"idAlta\": 1," +
            "      \"idProductividad\": 3," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:46:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"examenesFisicos\": [" +
            "    {" +
            "      \"idExamenFisico\": 10," +
            "      \"idTipoExamenFisico\": 1," +
            "      \"descripcion\": \"Examen General: Paciente lúcido, orientado en tiempo y espacio. Piel hidratada, sin cianosis.\"," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:40:00-05:00\"" +
            "    }," +
            "    {" +
            "      \"idExamenFisico\": 11," +
            "      \"idTipoExamenFisico\": 4," +
            "      \"descripcion\": \"Cardiopulmonar: Ruidos cardíacos rítmicos, sin soplos. Pulmones claros a la auscultación, murmullo vesicular conservado.\"," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:41:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"ordenesMedicas\": [" +
            "    {" +
            "      \"idPuntoCarga\": 20," +
            "      \"idEstado\": 1," +
            "      \"idProducto\": 8005," +
            "      \"cantidad\": 1," +
            "      \"precio\": 25.50," +
            "      \"total\": 25.50," +
            "      \"idDiagnostico\": 1," +
            "      \"observacion\": \"Radiografía de tórax (posteroanterior y lateral) por sospecha de neumonía.\"," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:50:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"medicacion\": [" +
            "    {" +
            "      \"idAlmacen\": 1," +
            "      \"idProducto\": 6010," +
            "      \"cantidadDosis\": 500," +
            "      \"idUmDosis\": 2," +
            "      \"idFrecuenciaDosis\": 3," +
            "      \"cantidadPeriodo\": 7," +
            "      \"idViaAdministracion\": 1," +
            "      \"cantidadTotal\": 21," +
            "      \"precio\": 1.25," +
            "      \"montoTotal\": 26.25," +
            "      \"indicaciones\": \"Tomar una cápsula cada 8 horas durante 7 días. Con alimentos.\"," +
            "      \"idDiagnostico\": 1," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:52:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"sintomas\": [" +
            "    {" +
            "      \"idSintoma\": 100," +
            "      \"idTipoSintoma\": 1," +
            "      \"descripcion\": \"Fiebre (pico de 38.9°C reportado por el paciente) de inicio hace 48 horas.\"," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:35:00-05:00\"" +
            "    }," +
            "    {" +
            "      \"idSintoma\": 101," +
            "      \"idTipoSintoma\": 2," +
            "      \"descripcion\": \"Tos seca persistente, sin flema.\"," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:35:00-05:00\"" +
            "    }" +
            "  ]," +
            "  \"tratamientos\": [" +
            "    {" +
            "      \"idTratamiento\": 50," +
            "      \"idTipoTratamiento\": 1," +
            "      \"descripcion\": \"Reposo absoluto por 48 horas y aumento de ingesta de líquidos.\"," +
            "      \"idUsuario\": 150," +
            "      \"tsRegistro\": \"2025-11-18T14:55:00-05:00\"" +
            "    }" +
            "  ]" +
            "}";

    @Test
    void testRegistrarAtencionMedica() throws Exception {
        // 1. Convertir la cadena JSON en el objeto DTO de Java
        AtencionMedicaRequestDTO requestDTO = objectMapper.readValue(ATENCION_MEDICA_REQUEST_JSON, AtencionMedicaRequestDTO.class);

        // 2. Llamar directamente al método del controlador
        // Asumimos que el método se llama 'registrarAtencionMedica' y devuelve un ResponseEntity
        ResponseEntity<?> response = atencionMedicaController.crearAtencionMedica(requestDTO);

        // 3. Verificaciones
        // Si todo va bien, el test pasará. Podemos agregar verificaciones explícitas:
        // Por ejemplo, verificar que el código de respuesta sea 201 (Created) o 200 (OK).
        if (response.getStatusCodeValue() != 201 && response.getStatusCodeValue() != 200) {
            throw new AssertionError("La llamada al controlador falló. Código de estado: " + response.getStatusCodeValue());
        }

        System.out.println("Registro de Atención Médica simulado exitosamente. Status: " + response.getStatusCodeValue());
    }
}