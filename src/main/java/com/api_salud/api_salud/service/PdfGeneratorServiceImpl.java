package com.api_salud.api_salud.service;

import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine;

    public PdfGeneratorServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public byte[] generarPdfHistoriaClinica(AtencionMedicaPdfDTO atencionDto) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Context context = new Context();
            
            // Inyectar DTO completo al contexto de Thymeleaf
            context.setVariable("atencion", atencionDto);

            // Renderizar la plantilla HTML
            String htmlContent = templateEngine.process("atencion_medica", context);

            // Generar el PDF mediante FlyingSaucer / iText
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al renderizar el PDF: " + e.getMessage(), e);
        }
    }
}