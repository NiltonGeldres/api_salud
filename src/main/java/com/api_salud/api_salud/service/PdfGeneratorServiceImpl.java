package com.api_salud.api_salud.service;


import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.api_salud.api_salud.service.PdfGeneratorService;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.xhtmlrenderer.pdf.ITextRenderer; // 🎯 AGREGA ESTE IMPORT

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine; // Inyecta el motor de plantillas de Spring

    public PdfGeneratorServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

@Override
public byte[] generarPdfHistoriaClinica(Long idAtencion, AtencionMedicaRequest request) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
        // 1. Preparamos las variables que leerá la plantilla HTML de Thymeleaf
        Context context = new Context();
        context.setVariable("idAtencion", idAtencion);
        context.setVariable("atencion", request);
        context.setVariable("fecha", java.time.LocalDate.now().toString());

        // 2. Procesamos el archivo HTML a un String plano
        String htmlContent = templateEngine.process("atencion_medica", context);

        // 3. 🎯 USAMOS FLYING SAUCER (Esto reemplaza a HtmlConverter y compila perfecto)
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    } catch (Exception e) {
        throw new RuntimeException("Error crítico en Flying Saucer al procesar el HTML a PDF: " + e.getMessage(), e);
    }
}

// 🎯 Inyectamos de forma limpia la ruta de las firmas desde tus properties
    @org.springframework.beans.factory.annotation.Value("${app.storage.ruta-firmas}")
    private String rutaBaseFirmas;

    @Override
    public byte[] estamparRubricaMedico(byte[] pdfBytes, Integer idMedico) {
        try {
            // 1. Construimos la ruta dinámica usando la propiedad inyectada
            String rutaFirmaImg = this.rutaBaseFirmas + "medico_" + idMedico + ".png";
            File ficheroFirma = new File(rutaFirmaImg);

            // Control preventivo: Si la imagen no existe en el disco, devuelve el PDF original sin estampar
            if (!ficheroFirma.exists()) {
                System.out.println("⚠️ No se encontró la imagen de la firma física en: " + rutaFirmaImg);
                return pdfBytes; 
            }

            // 2. Lógica de Estampado sobre el PDF en memoria usando OpenPDF (com.lowagie.text)
            PdfReader reader = new PdfReader(pdfBytes);
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                PdfStamper stamper = new PdfStamper(reader, baos);
                
                // Obtenemos la última página para estampar la firma en el footer
                int ultimaPagina = reader.getNumberOfPages();
                PdfContentByte canvas = stamper.getOverContent(ultimaPagina);

                // Cargamos la imagen de la rúbrica
                Image firmaImg = Image.getInstance(rutaFirmaImg);
                
                // 📐 Escalamos la imagen para que calce bien en el reporte (Ancho: 120, Alto: 55 aprox)
                firmaImg.scaleToFit(120, 55);
                
                // 📍 Posicionamos la firma (X horizontal, Y vertical desde abajo)
                // Ajustado para alinearse justo arriba del texto "Firma Electrónica del Médico Tratante"
                float x = 420; 
                float y = 75;  
                firmaImg.setAbsolutePosition(x, y);

                // Añadimos la imagen al canvas del PDF
                canvas.addImage(firmaImg);
                
                stamper.close();
                reader.close();
                
                return baos.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al estampar la rúbrica del médico en el PDF: " + e.getMessage(), e);
        }
    }
}
