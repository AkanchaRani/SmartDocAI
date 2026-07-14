package com.akancha.smartdocai.service;

public interface PdfExportService {

    // Converts plain documentation text into PDF bytes
    byte[] exportToPdf(String projectName, String content);
}
