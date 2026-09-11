package com.api_salud.api_salud.service;

import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine;
    private final RestTemplate restTemplate;

    public PdfGeneratorServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Descarga la imagen remota (Cloudflare R2) y la convierte a Base64.
     */
    private String descargarLogoABase64(String logoUrl) {
        if (logoUrl == null || logoUrl.isEmpty()) {
            return null;
        }
        try {
            byte[] imageBytes = restTemplate.getForObject(logoUrl, byte[].class);
            if (imageBytes != null && imageBytes.length > 0) {
                return Base64.getEncoder().encodeToString(imageBytes);
            }
        } catch (Exception e) {
            System.err.println("Error al descargar logo desde R2 (" + logoUrl + "): " + e.getMessage());
        }
        return null;
    }

    /**
     * Método auxiliar privado para procesar la plantilla HTML Thymeleaf
     * y convertirla a bytes PDF mediante Flying Saucer / iText.
     */
    private byte[] generarPdfDesdePlantilla(String templateName, AtencionMedicaPdfDTO atencionDto) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Context context = new Context();

            // 1. Asegurar que el logo esté en Base64 para Flying Saucer
            if ((atencionDto.getLogoBase64() == null || atencionDto.getLogoBase64().isEmpty()) 
                    && atencionDto.getLogoTenantUrl() != null) {
                String base64Logo = descargarLogoABase64(atencionDto.getLogoTenantUrl());
                atencionDto.setLogoBase64(base64Logo);
            }

            // 2. Inyectar DTO completo al contexto de Thymeleaf bajo el nombre "atencion"
            context.setVariable("atencion", atencionDto);

            // 3. Renderizar la plantilla HTML
            String htmlContent = templateEngine.process(templateName, context);

            // 4. Generar el PDF mediante FlyingSaucer / iText
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al renderizar el PDF con la plantilla [" + templateName + "]: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarPdfHistoriaClinica(AtencionMedicaPdfDTO atencionDto) {
        return generarPdfDesdePlantilla("atencion_medica", atencionDto);
    }

    @Override
    public byte[] generarPdfReceta(AtencionMedicaPdfDTO atencionDto) {
        return generarPdfDesdePlantilla("atencion_medica_receta", atencionDto);
    }

    @Override
    public byte[] generarPdfOrdenes(AtencionMedicaPdfDTO atencionDto) {
        return generarPdfDesdePlantilla("atencion_medica_orden", atencionDto);
    }

    @Override
    public byte[] generarPdfIndicaciones(AtencionMedicaPdfDTO atencionDto) {
        return generarPdfDesdePlantilla("atencion_medica_indicaciones", atencionDto);
    }
}