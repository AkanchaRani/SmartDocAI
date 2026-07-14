package com.akancha.smartdocai.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "generated_documents")
public class GeneratedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    // Full documentation text generated for the project
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private LocalDateTime generatedDate;

    public GeneratedDocument() {
    }

    public GeneratedDocument(Long id, Long projectId, String content, LocalDateTime generatedDate) {
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
