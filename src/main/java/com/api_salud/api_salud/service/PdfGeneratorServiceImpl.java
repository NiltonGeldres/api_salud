package com.api_salud.api_salud.service;

import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine;

    @Value("${app.storage.ruta-firmas:D:/08 PROYECTOS/STORAGE/firmas/}")
    private String rutaBaseFirmas;

    public PdfGeneratorServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public byte[] generarPdfHistoriaClinica(AtencionMedicaPdfDTO atencionDto) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
        	Context context = new Context();
            context.setVariable("atencion", atencionDto);
            
            // --- NUEVA LÓGICA PARA LA FIRMA ---
            String rutaFirma = rutaBaseFirmas + "medico_2.png";
            java.io.File file = new java.io.File(rutaFirma);
            
            if (file.exists()) {
                byte[] imageBytes = java.nio.file.Files.readAllBytes(file.toPath());
                String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
                // Inyectamos el formato data URI directamente
                context.setVariable("firmaBase64", "data:image/png;base64," + base64Image);
            } else {
                context.setVariable("firmaBase64", ""); // Opcional: manejar si no existe
            }
            // ----------------------------------

            String htmlContent = templateEngine.process("atencion_medica", context);

            ITextRenderer renderer = new ITextRenderer();
            // ... (tu configuración de BoundedReplacedElementFactory) ...
            
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al renderizar el PDF: " + e.getMessage(), e);
        }
    }
}