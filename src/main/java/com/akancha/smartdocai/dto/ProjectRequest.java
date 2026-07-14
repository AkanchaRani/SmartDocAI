package com.akancha.smartdocai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectRequest {

    @NotBlank(message = "Project name cannot be empty")
    private String projectName;

    private String description;

    private String technology;

    private String version;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    @NotNull(message = "User id is required")
    private Long userId;

    public ProjectRequest() {
    }

    public ProjectRequest(String projectName, String description, String technology,
                           String version, String status, Long userId) {
        this.projectName = projectName;
        this.description = description;
        this.technology = technology;
        this.version = version;
        this.status = status;
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
