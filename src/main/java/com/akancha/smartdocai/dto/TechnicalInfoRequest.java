package com.akancha.smartdocai.dto;

import jakarta.validation.constraints.NotNull;

public class TechnicalInfoRequest {

    private String features;

    private String requirements;

    private String installationSteps;

    private String architecture;

    private String technologyStack;

    @NotNull(message = "Project id is required")
    private Long projectId;

    public TechnicalInfoRequest() {
    }

    public TechnicalInfoRequest(String features, String requirements, String installationSteps,
                                 String architecture, String technologyStack, Long projectId) {
        this.features = features;
        this.requirements = requirements;
        this.installationSteps = installationSteps;
        this.architecture = architecture;
        this.technologyStack = technologyStack;
        this.projectId = projectId;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getInstallationSteps() {
        return installationSteps;
    }

    public void setInstallationSteps(String installationSteps) {
        this.installationSteps = installationSteps;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getTechnologyStack() {
        return technologyStack;
    }

    public void setTechnologyStack(String technologyStack) {
        this.technologyStack = technologyStack;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
