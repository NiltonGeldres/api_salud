package com.api_salud.api_salud.service;

import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import com.lowagie.text.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.ImageResource;

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
     * Descarga la imagen remota (Cloudflare R2) o procesa una cadena Data URI/Base64 existente.
     */
    private String descargarLogoABase64(String logoUrl) {
        if (logoUrl == null || logoUrl.trim().isEmpty()) {
            return null;
        }
        System.out.println("LOGO URL  "+logoUrl);
        String urlLimpia = logoUrl.trim();

        // 1. Si ya es una Data URI o Base64 puro, extraer solo la parte Base64 sin hacer peticiones de red
        if (urlLimpia.startsWith("data:image")) {
            return urlLimpia.substring(urlLimpia.indexOf(",") + 1);
        }

        // 2. Si es una URL remota (HTTP / HTTPS), descargar los bytes con RestTemplate
        if (urlLimpia.startsWith("http://") || urlLimpia.startsWith("https://")) {
            try {
                byte[] imageBytes = restTemplate.getForObject(urlLimpia, byte[].class);
                if (imageBytes != null && imageBytes.length > 0) {
                    return Base64.getEncoder().encodeToString(imageBytes);
                }
            } catch (Exception e) {
                System.err.println("Error al descargar logo desde R2 (" + urlLimpia + "): " + e.getMessage());
            }
            return null;
        }

        // 3. Si viene la cadena Base64 pura (sin prefijo http ni data:)
        return urlLimpia;
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

            // 4. Generar el PDF mediante FlyingSaucer / iText con Custom UserAgent para Data URI
            ITextRenderer renderer = new ITextRenderer();

            ITextUserAgent customUserAgent = new CustomITextUserAgent(renderer.getOutputDevice());
            customUserAgent.setSharedContext(renderer.getSharedContext());
            renderer.getSharedContext().setUserAgentCallback(customUserAgent);

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

    /**
     * UserAgent personalizado para interceptar imágenes codificadas en Base64 (Data URI)
     * y evitar que Flying Saucer las trate como URIs de red.
     */
    private static class CustomITextUserAgent extends ITextUserAgent {
        public CustomITextUserAgent(ITextOutputDevice outputDevice) {
            super(outputDevice);
        }

        @Override
        public ImageResource getImageResource(String uri) {
            if (uri != null && uri.startsWith("data:image")) {
                try {
                    String base64Data = uri.substring(uri.indexOf(",") + 1).replaceAll("\\s+", "");
                    byte[] imageBytes = Base64.getDecoder().decode(base64Data);
                    
                    // 1. Crear la imagen nativa de iText
                    Image image = Image.getInstance(imageBytes);
                    
                    // 2. Envolverla en un FSImage de Flying Saucer
                    ITextFSImage fsImage = new ITextFSImage(image);
                    
                    // 3. Retornar el ImageResource esperado por la interfaz
                    return new ImageResource(uri, fsImage);
                } catch (Exception e) {
                    System.err.println("Error al procesar Data URI en la plantilla del PDF: " + e.getMessage());
                    return null;
                }
            }
            return super.getImageResource(uri);
        }
    }
}