package com.akancha.smartdocai.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "api_details")
public class ApiDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiName;

    private String method;

    private String endpoint;

    @Column(length = 1000)
    private String description;

    @Column(length = 2000)
    private String requestExample;

    @Column(length = 2000)
    private String responseExample;

    private Long projectId;

    public ApiDetails() {
    }

    public ApiDetails(Long id, String apiName, String method, String endpoint, String description,
                       String requestExample, String responseExample, Long projectId) {
        this.id = id;
        this.apiName = apiName;
        this.method = method;
        this.endpoint = endpoint;
        this.description = description;
        this.requestExample = requestExample;
        this.responseExample = responseExample;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestExample() {
        return requestExample;
    }

    public void setRequestExample(String requestExample) {
        this.requestExample = requestExample;
    }

    public String getResponseExample() {
        return responseExample;
    }

    public void setResponseExample(String responseExample) {
        this.responseExample = responseExample;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
