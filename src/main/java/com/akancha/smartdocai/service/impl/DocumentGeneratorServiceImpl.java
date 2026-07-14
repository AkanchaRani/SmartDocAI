package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.dto.GeneratedDocumentResponse;
import com.akancha.smartdocai.entity.ApiDetails;
import com.akancha.smartdocai.entity.DatabaseTable;
import com.akancha.smartdocai.entity.GeneratedDocument;
import com.akancha.smartdocai.entity.Project;
import com.akancha.smartdocai.entity.TechnicalInfo;
import com.akancha.smartdocai.exception.ResourceNotFoundException;
import com.akancha.smartdocai.repository.ApiDetailsRepository;
import com.akancha.smartdocai.repository.DatabaseTableRepository;
import com.akancha.smartdocai.repository.GeneratedDocumentRepository;
import com.akancha.smartdocai.repository.ProjectRepository;
import com.akancha.smartdocai.repository.TechnicalInfoRepository;
import com.akancha.smartdocai.service.DocumentGeneratorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentGeneratorServiceImpl implements DocumentGeneratorService {

    private final ProjectRepository projectRepository;
    private final ApiDetailsRepository apiDetailsRepository;
    private final DatabaseTableRepository databaseTableRepository;
    private final TechnicalInfoRepository technicalInfoRepository;
    private final GeneratedDocumentRepository generatedDocumentRepository;

    public DocumentGeneratorServiceImpl(ProjectRepository projectRepository,
                                         ApiDetailsRepository apiDetailsRepository,
                                         DatabaseTableRepository databaseTableRepository,
                                         TechnicalInfoRepository technicalInfoRepository,
                                         GeneratedDocumentRepository generatedDocumentRepository) {
        this.projectRepository = projectRepository;
        this.apiDetailsRepository = apiDetailsRepository;
        this.databaseTableRepository = databaseTableRepository;
        this.technicalInfoRepository = technicalInfoRepository;
        this.generatedDocumentRepository = generatedDocumentRepository;
    }

    // Generates documentation text using StringBuilder (dummy logic for now).
    // Later this method can call an AI service (Gemini / OpenAI) instead of
    // building the text manually. See buildDummyContent() below.
    @Override
    public GeneratedDocumentResponse generateDocumentation(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id : " + projectId));

        List<ApiDetails> apiList = apiDetailsRepository.findByProjectId(projectId);
        List<DatabaseTable> tableList = databaseTableRepository.findByProjectId(projectId);
        List<TechnicalInfo> technicalInfoList = technicalInfoRepository.findByProjectId(projectId);

        String content = buildDummyContent(project, apiList, tableList, technicalInfoList);

        // Future: replace buildDummyContent() call above with something like:
        // String content = aiService.generateContent(project, apiList, tableList, technicalInfoList);

        GeneratedDocument document = new GeneratedDocument();
        document.setProjectId(projectId);
        document.setContent(content);
        document.setGeneratedDate(LocalDateTime.now());

        GeneratedDocument saved = generatedDocumentRepository.save(document);

        return mapToResponse(saved);
    }

    // Get Latest Documentation For A Project
    @Override
    public GeneratedDocumentResponse getLatestDocumentation(Long projectId) {

        GeneratedDocument document = generatedDocumentRepository
                .findTopByProjectIdOrderByGeneratedDateDesc(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No documentation generated yet for project id : " + projectId));

        return mapToResponse(document);
    }

    // Builds simple dummy documentation text using StringBuilder.
    // This is a placeholder so that Gemini/OpenAI can be plugged in later
    // without changing the controller or the rest of the module.
    private String buildDummyContent(Project project, List<ApiDetails> apiList,
                                      List<DatabaseTable> tableList, List<TechnicalInfo> technicalInfoList) {

        StringBuilder sb = new StringBuilder();

        // README / Overview
        sb.append("# ").append(project.getProjectName()).append("\n\n");
        sb.append("## Overview\n");
        sb.append(project.getDescription() == null ? "No description provided." : project.getDescription());
        sb.append("\n\n");

        sb.append("Version: ").append(project.getVersion()).append("\n");
        sb.append("Status: ").append(project.getStatus()).append("\n\n");

        // Technology Stack
        sb.append("## Technology Stack\n");
        sb.append(project.getTechnology() == null ? "Not specified." : project.getTechnology());
        sb.append("\n\n");

        if (!technicalInfoList.isEmpty()) {
            TechnicalInfo info = technicalInfoList.get(0);

            sb.append("## Features\n");
            sb.append(info.getFeatures() == null ? "Not added yet." : info.getFeatures()).append("\n\n");

            sb.append("## Requirements\n");
            sb.append(info.getRequirements() == null ? "Not added yet." : info.getRequirements()).append("\n\n");

            sb.append("## Architecture\n");
            sb.append(info.getArchitecture() == null ? "Not added yet." : info.getArchitecture()).append("\n\n");

            sb.append("## Installation Guide\n");
            sb.append(info.getInstallationSteps() == null ? "Not added yet." : info.getInstallationSteps()).append("\n\n");
        }

        // API Documentation
        sb.append("## API Documentation\n");
        if (apiList.isEmpty()) {
            sb.append("No APIs added for this project yet.\n\n");
        } else {
            for (ApiDetails api : apiList) {
                sb.append("### ").append(api.getApiName()).append("\n");
                sb.append("Method: ").append(api.getMethod()).append("\n");
                sb.append("Endpoint: ").append(api.getEndpoint()).append("\n");
                sb.append("Description: ").append(api.getDescription() == null ? "-" : api.getDescription()).append("\n");
                sb.append("Request Example: ").append(api.getRequestExample() == null ? "-" : api.getRequestExample()).append("\n");
                sb.append("Response Example: ").append(api.getResponseExample() == null ? "-" : api.getResponseExample()).append("\n\n");
            }
        }

        // Database Documentation
        sb.append("## Database Documentation\n");
        if (tableList.isEmpty()) {
            sb.append("No database tables added for this project yet.\n\n");
        } else {
            for (DatabaseTable table : tableList) {
                sb.append("### ").append(table.getTableName()).append("\n");
                sb.append("Columns: ").append(table.getColumns() == null ? "-" : table.getColumns()).append("\n");
                sb.append("Primary Key: ").append(table.getPrimaryKey() == null ? "-" : table.getPrimaryKey()).append("\n");
                sb.append("Foreign Key: ").append(table.getForeignKey() == null ? "-" : table.getForeignKey()).append("\n");
                sb.append("Datatype: ").append(table.getDatatype() == null ? "-" : table.getDatatype()).append("\n\n");
            }
        }

        sb.append("## Generated By\n");
        sb.append("SmartDocAI - AI Powered Technical Documentation Management System\n");
        sb.append("(This documentation is auto generated. AI based generation will be added later.)\n");

        return sb.toString();
    }

    // Helper method to convert Entity to Response DTO
    private GeneratedDocumentResponse mapToResponse(GeneratedDocument document) {
        return new GeneratedDocumentResponse(
                document.getId(),
                document.getProjectId(),
                document.getContent(),
                document.getGeneratedDate()
        );
    }
}
