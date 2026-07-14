package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.GeneratedDocumentResponse;
import com.akancha.smartdocai.dto.ProjectResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.DocumentGeneratorService;
import com.akancha.smartdocai.service.PdfExportService;
import com.akancha.smartdocai.service.ProjectService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate")
public class DocumentGenerationController {

    private final DocumentGeneratorService documentGeneratorService;
    private final PdfExportService pdfExportService;
    private final ProjectService projectService;

    public DocumentGenerationController(DocumentGeneratorService documentGeneratorService,
                                         PdfExportService pdfExportService,
                                         ProjectService projectService) {
        this.documentGeneratorService = documentGeneratorService;
        this.pdfExportService = pdfExportService;
        this.projectService = projectService;
    }

    // Generate Documentation For A Project (currently dummy content, AI can be added later)
    @PostMapping("/{projectId}")
    public ApiResponse<GeneratedDocumentResponse> generateDocumentation(@PathVariable Long projectId) {

        GeneratedDocumentResponse response = documentGeneratorService.generateDocumentation(projectId);

        return new ApiResponse<>(true, "Documentation generated successfully", response);
    }

    // Get The Latest Generated Documentation For A Project
    @GetMapping("/project/{projectId}")
    public ApiResponse<GeneratedDocumentResponse> getLatestDocumentation(@PathVariable Long projectId) {

        GeneratedDocumentResponse response = documentGeneratorService.getLatestDocumentation(projectId);

        return new ApiResponse<>(true, "Documentation fetched successfully", response);
    }

    // Export The Latest Generated Documentation As PDF
    @GetMapping("/pdf/{projectId}")
    public ResponseEntity<byte[]> exportDocumentationAsPdf(@PathVariable Long projectId) {

        GeneratedDocumentResponse document = documentGeneratorService.getLatestDocumentation(projectId);
        ProjectResponse project = projectService.getProjectById(projectId);

        byte[] pdfBytes = pdfExportService.exportToPdf(project.getProjectName(), document.getContent());

        String fileName = project.getProjectName().replaceAll("\\s+", "_") + "_documentation.pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(pdfBytes);
    }
}
