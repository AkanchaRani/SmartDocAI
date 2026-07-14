package com.akancha.smartdocai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApiDetailsRequest {

    @NotBlank(message = "API name cannot be empty")
    private String apiName;

    @NotBlank(message = "Method cannot be empty")
    private String method;

    @NotBlank(message = "Endpoint cannot be empty")
    private String endpoint;

    private String description;

    private String requestExample;

    private String responseExample;

    @NotNull(message = "Project id is required")
    private Long projectId;

    public ApiDetailsRequest() {
    }

    public ApiDetailsRequest(String apiName, String method, String endpoint, String description,
                              String requestExample, String responseExample, Long projectId) {
        this.apiName = apiName;
        this.method = method;
        this.endpoint = endpoint;
        this.description = description;
        this.requestExample = requestExample;
        this.responseExample = responseExample;
        this.projectId = projectId;
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
