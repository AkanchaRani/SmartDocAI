package com.akancha.smartdocai.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "technical_info")
public class TechnicalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String features;

    @Column(length = 2000)
    private String requirements;

    @Column(length = 2000)
    private String installationSteps;

    @Column(length = 2000)
    private String architecture;

    @Column(length = 1000)
    private String technologyStack;

    private Long projectId;

    public TechnicalInfo() {
    }

    public TechnicalInfo(Long id, String features, String requirements, String installationSteps,
                          String architecture, String technologyStack, Long projectId) {
        this.id = id;
        this.features = features;
        this.requirements = requirements;
        this.installationSteps = installationSteps;
        this.architecture = architecture;
        this.technologyStack = technologyStack;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
