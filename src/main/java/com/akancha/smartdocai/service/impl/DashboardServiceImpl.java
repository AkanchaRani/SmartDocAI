package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.dto.DashboardResponse;
import com.akancha.smartdocai.dto.ProjectResponse;
import com.akancha.smartdocai.entity.Project;
import com.akancha.smartdocai.repository.GeneratedDocumentRepository;
import com.akancha.smartdocai.repository.ProjectRepository;
import com.akancha.smartdocai.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ProjectRepository projectRepository;
    private final GeneratedDocumentRepository generatedDocumentRepository;

    public DashboardServiceImpl(ProjectRepository projectRepository,
                                GeneratedDocumentRepository generatedDocumentRepository) {

        this.projectRepository = projectRepository;
        this.generatedDocumentRepository = generatedDocumentRepository;
    }

    @Override
    public DashboardResponse getDashboardSummary(Long userId) {

        List<Project> recentProjects = projectRepository.findByUserId(userId);

        long totalProjects = recentProjects.size();

        long totalDocuments = generatedDocumentRepository.count();

        List<ProjectResponse> projectResponses = recentProjects.stream()
                .map(project -> new ProjectResponse(
                        project.getId(),
                        project.getProjectName(),
                        project.getDescription(),
                        project.getTechnology(),
                        project.getVersion(),
                        project.getStatus(),
                        project.getCreatedDate(),
                        project.getUserId()
                ))
                .toList();

        return new DashboardResponse(
                totalProjects,
                totalDocuments,
                projectResponses
        );
    }
}