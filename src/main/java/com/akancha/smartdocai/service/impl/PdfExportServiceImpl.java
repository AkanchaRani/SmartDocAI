package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.service.PdfExportService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfExportServiceImpl implements PdfExportService {

    // Builds a simple PDF file from the generated documentation text.
    @Override
    public byte[] exportToPdf(String projectName, String content) {

        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            document.add(new Paragraph(projectName + " - Documentation", titleFont));
            document.add(new Paragraph(" "));

            // Content is added line by line so simple "#" headings still look readable in the PDF
            for (String line : content.split("\n")) {
                document.add(new Paragraph(line, bodyFont));
            }

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF: " + e.getMessage());
        }
    }
}
