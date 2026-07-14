package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.ProjectRequest;
import com.akancha.smartdocai.dto.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse saveProject(ProjectRequest request);

    List<ProjectResponse> getProjectsByUserId(Long userId);

    ProjectResponse getProjectById(Long id);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void deleteProject(Long id);
}
