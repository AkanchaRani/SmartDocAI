package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.GeneratedDocumentResponse;

public interface DocumentGeneratorService {

    // Generates dummy documentation for a project and stores it
    GeneratedDocumentResponse generateDocumentation(Long projectId);

    // Returns the latest generated documentation for a project
    GeneratedDocumentResponse getLatestDocumentation(Long projectId);
}
