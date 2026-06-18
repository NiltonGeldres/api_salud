package com.api_salud.api_salud;

import com.api_salud.api_salud.request.*;
import com.api_salud.api_salud.response.AtencionMedicaResponse;
import com.api_salud.api_salud.service.AtencionMedicaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GuardarAtencionAndPdfTest {

    @Autowired
    private AtencionMedicaService atencionMedicaService;

    @Test
    void testGuardarAtencionYGenerarPdfFisico() {
        // Instancia principal del Request
        AtencionMedicaRequest request = new AtencionMedicaRequest();
        
        // =====================================================================
        // I. DATOS PRIMITIVOS CONTROL BASE (OBLIGATORIOS CABECERA)
        // =====================================================================
        request.setIdPaciente(34);             
        request.setIdCuentaAtencion(34);       
        request.setIdServicio(1);              
        request.setIdEstadoAtencion(3);        
        request.setEstadoFirma("FIRMADO_ELECTRONICO"); 
        
        request.setIdMedicoIngreso(1); // Modificado para calzar con el Request corregido                
        request.setIdEntidad(1);               
        request.setIdUsuarioRegistro(12);      
        request.setOrigenRegistroUsuario("MEDICO_MOBILE_APP");

        // =====================================================================
        // II. TABLAS DE DETALLES TEXTO ÚNICO (CUMPLE CON @NotNull Y @Min(1))
        // =====================================================================

        // A. Antecedentes
        AtencionMedicaAntecedenteRequest antReq = new AtencionMedicaAntecedenteRequest();
        antReq.setIdAntecedente(1);                     
        antReq.setIdTipoAntecedente(1);                 
        antReq.setDescripcion("Enfermedad : colitis");     
        request.setAntecedentes(new ArrayList<>(Arrays.asList(antReq)));

        // B. Síntomas
        AtencionMedicaSintomaRequest sintReq = new AtencionMedicaSintomaRequest();
        sintReq.setIdSintoma(1);                        
        sintReq.setIdTipoSintoma(1); // Añadido para cumplir con la validación de la clase
        sintReq.setDescripcion("Dolor abdominal");
        request.setSintomas(new ArrayList<>(Arrays.asList(sintReq)));

        // C. Examen Físico
        AtencionMedicaExamenFisicoRequest examFisReq = new AtencionMedicaExamenFisicoRequest();
        examFisReq.setIdExamenFisico(1);                
        examFisReq.setIdTipoExamenFisico(1); // Añadido para cumplir con la validación de la clase
        examFisReq.setDescripcion("Regional dirigido huesos");
        request.setExamenFisico(new ArrayList<>(Arrays.asList(examFisReq)));

        // =====================================================================
        // III. DATOS COMPLEMENTARIOS (REQUERIDOS CON DATOS COMPLETOS)
        // =====================================================================

        // D. Triaje (Signos Vitales)
        AtencionMedicaTriajeRequest t1 = new AtencionMedicaTriajeRequest();
        t1.setIdTipoTriaje(1); 
        t1.setValorTriaje("22");     
        
        AtencionMedicaTriajeRequest t2 = new AtencionMedicaTriajeRequest();
        t2.setIdTipoTriaje(2); 
        t2.setValorTriaje("70");     
        
        AtencionMedicaTriajeRequest t3 = new AtencionMedicaTriajeRequest();
        t3.setIdTipoTriaje(3); 
        t3.setValorTriaje("168");    
        request.setTriaje(new ArrayList<>(Arrays.asList(t1, t2, t3)));

        // E. Diagnósticos (CIE-10)
        AtencionMedicaDiagnosticoRequest diagnostico = new AtencionMedicaDiagnosticoRequest();
        diagnostico.setIdDiagnostico(201);
        diagnostico.setIdDiagnosticoOrden(1); // Añadido para cumplir con el @NotNull de la clase
        request.setDiagnosticos(new ArrayList<>(Arrays.asList(diagnostico)));

        // F. Exámenes Auxiliares (Plan de Trabajo)
        AtencionMedicaExamenAuxiliarRequest exam1 = new AtencionMedicaExamenAuxiliarRequest();
        exam1.setIdPuntoCarga(1); // Añadido requerido
        exam1.setIdProducto(80061); 
        exam1.setCantidad(1);
        exam1.setIdDiagnostico(201); // Añadido requerido
        exam1.setObservacion("Muestra de sangre en ayunas");
        request.setExamenesAuxiliares(new ArrayList<>(Arrays.asList(exam1)));

        // G. Medicación (Tratamientos)
        AtencionMedicaMedicacionRequest med1 = new AtencionMedicaMedicacionRequest();
        med1.setIdAlmacen(1); // Añadido requerido
        med1.setIdProducto(5001); 
        med1.setCantidadDosis(1); 
        med1.setIdUmDosis(1); 
        med1.setIdFrecuenciaDosis(3); 
        med1.setCantidadPeriodo(3); 
        med1.setIdViaAdministracion(1); 
        med1.setCantidadTotal(9); 
        med1.setIdDiagnostico(201); // Añadido requerido
        med1.setIndicaciones("Tomar cada 8 horas con alimentos");
        request.setMedicacion(new ArrayList<>(Arrays.asList(med1)));

        // H. Alta Médica
        AtencionMedicaAltaRequest alta = new AtencionMedicaAltaRequest();
        alta.setDescripcion("15 dias");
        request.setAlta(alta);

        System.out.println("====== EJECUTANDO PRUEBA INTEGRAL ORDENADA V1 ======");
        
        // 3. Ejecución del Servicio Transaccional
        AtencionMedicaResponse response = atencionMedicaService.guardarAtencionMedica(request);

        // 4. Verificaciones de JUnit
        assertNotNull(response);
        assertTrue(response.isExito());
        assertNotNull(response.getIdAtencion());
        
        String rutaPdfGenerado = response.getRutaPdfFirmado();
        assertNotNull(rutaPdfGenerado);
        System.out.println("📂 PDF guardado y firmado correctamente en: " + rutaPdfGenerado);

        File archivoPdf = new File(rutaPdfGenerado);
        assertTrue(archivoPdf.exists(), "El archivo PDF no se escribió físicamente en la unidad D");
        
        System.out.println("✔ ¡Test completado con éxito! Estructura estricta validada.");
    }
}