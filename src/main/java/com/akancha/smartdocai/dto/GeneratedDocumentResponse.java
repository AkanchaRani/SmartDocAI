package com.akancha.smartdocai.dto;

import java.time.LocalDateTime;

public class GeneratedDocumentResponse {

    private Long id;
    private Long projectId;
    private String content;
    private LocalDateTime generatedDate;

    public GeneratedDocumentResponse() {
    }

    public GeneratedDocumentResponse(Long id, Long projectId, String content, LocalDateTime generatedDate) {
        this.id = id;
        this.projectId = projectId;
        this.content = content;
        this.generatedDate = generatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDateTime generatedDate) {
        this.generatedDate = generatedDate;
    }
}
