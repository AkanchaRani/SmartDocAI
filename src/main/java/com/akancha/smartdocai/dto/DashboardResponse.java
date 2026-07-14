package com.akancha.smartdocai.dto;

import java.util.List;

public class DashboardResponse {

    private long totalProjects;
    private long totalDocuments;
    private List<ProjectResponse> recentProjects;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalProjects, long totalDocuments, List<ProjectResponse> recentProjects) {
        this.totalProjects = totalProjects;
        this.totalDocuments = totalDocuments;
        this.recentProjects = recentProjects;
    }

    public long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public long getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(long totalDocuments) {
        this.totalDocuments = totalDocuments;
    }

    public List<ProjectResponse> getRecentProjects() {
        return recentProjects;
    }

    public void setRecentProjects(List<ProjectResponse> recentProjects) {
        this.recentProjects = recentProjects;
    }
}
