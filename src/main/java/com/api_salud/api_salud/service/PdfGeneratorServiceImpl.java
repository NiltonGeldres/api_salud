package com.api_salud.api_salud.service;

import com.api_salud.api_salud.dto.AtencionMedicaPdfDTO;
import com.api_salud.api_salud.request.AtencionMedicaRequest;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine;

    @Value("${app.storage.ruta-firmas:D:/08_PROYECTOS/STORAGE/firmas/}")
    private String rutaBaseFirmas;

    @Value("${app.storage.ruta-pdfs:D:/08_PROYECTOS/STORAGE/pdfs/}")
    private String rutaBasePdfs;

    public PdfGeneratorServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public byte[] generarPdfHistoriaClinica(AtencionMedicaPdfDTO atencionDto) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Context context = new Context();
            
            // Pasamos el DTO completo. En el HTML accederás como:
            // ${atencion.nombreMedico}, ${atencion.paciente.name}, etc.
            context.setVariable("atencion", atencionDto);
            context.setVariable("fecha", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            // Generamos el HTML basándonos únicamente en el DTO
            String htmlContent = templateEngine.process("atencion_medica", context);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF con Flying Saucer: " + e.getMessage(), e);
        }
    }
    
/*
    @Override
    public byte[] generarPdfHistoriaClinica(Long idAtencion, AtencionMedicaRequest request, 
                                           String nombrePaciente, String numHistoriaClinica, 
                                           String nombreEspecialidad, String nombreMedico) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Context context = new Context();
            context.setVariable("idAtencion", idAtencion);
            context.setVariable("atencion", request); // Sigue pasando el objeto Request intacto con sus subestructuras
            context.setVariable("fecha", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            
            // Inyección complementaria de variables descriptivas de negocio (Cero IDs)
            context.setVariable("nombrePaciente", nombrePaciente);
            context.setVariable("numHistoriaClinica", numHistoriaClinica);
            context.setVariable("nombreEspecialidad", nombreEspecialidad);
            context.setVariable("nombreMedico", nombreMedico);

            String htmlContent = templateEngine.process("atencion_medica", context);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF en Flying Saucer: " + e.getMessage(), e);
        }
    }
*/
    @Override
    public byte[] estamparRubricaMedico(byte[] pdfBytes, Integer idMedico, String nombreMedico, String cmpMedico) {
        try {
            // Mapeo seguro utilizando el ID único del médico para el archivo PNG
            String rutaFirmaImg = rutaBaseFirmas + "medico_" + idMedico + ".png";
            File ficheroFirma = new File(rutaFirmaImg);

            if (!ficheroFirma.exists()) {
                System.out.println("⚠️ Advertencia: No existe rúbrica física para el médico ID: " + idMedico);
                return pdfBytes; 
            }

            PdfReader reader = new PdfReader(pdfBytes);
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                PdfStamper stamper = new PdfStamper(reader, baos);
                int totalPaginas = reader.getNumberOfPages();
                
                BaseFont fontBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
                BaseFont fontNormal = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);

                // 🎯 ESTAMPADO EN CADA HOJA: Bloque indivisible alineado de forma milimétrica
                for (int i = 1; i <= totalPaginas; i++) {
                    PdfContentByte canvas = stamper.getOverContent(i);

                    // 1. Imagen de la firma digital (PNG transparente)
                    Image firmaImg = Image.getInstance(rutaFirmaImg);
                    firmaImg.scaleToFit(110, 50);
                    firmaImg.setAbsolutePosition(430, 68); // Coordenadas fijas inalterables
                    canvas.addImage(firmaImg);

                    // 2. Línea formal de firma
                    canvas.beginText();
                    canvas.setFontAndSize(fontNormal, 10);
                    canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, "_____________________________________", 485, 58, 0);

                    // 3. Nombre completo del Profesional (Dinámico)
                    canvas.setFontAndSize(fontBold, 9);
                    canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, nombreMedico, 485, 46, 0);

                    // 4. Código de Colegiatura e Identificación Oficial
                    canvas.setFontAndSize(fontNormal, 8);
                    canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, "CMP: " + cmpMedico + " - FIRMA ELECTRÓNICA", 485, 36, 0);
                    canvas.endText();
                }

                stamper.close();
                reader.close();
                return baos.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error en post-procesamiento de firmas iText: " + e.getMessage(), e);
        }
    }
}