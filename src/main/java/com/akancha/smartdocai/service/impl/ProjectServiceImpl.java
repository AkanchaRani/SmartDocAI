package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.dto.ProjectRequest;
import com.akancha.smartdocai.dto.ProjectResponse;
import com.akancha.smartdocai.entity.Project;
import com.akancha.smartdocai.exception.ResourceNotFoundException;
import com.akancha.smartdocai.repository.ProjectRepository;
import com.akancha.smartdocai.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Save Project
    @Override
    public ProjectResponse saveProject(ProjectRequest request) {

        Project project = new Project();

        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setTechnology(request.getTechnology());
        project.setVersion(request.getVersion());
        project.setStatus(request.getStatus());
        project.setUserId(request.getUserId());
        project.setCreatedDate(LocalDate.now());

        Project savedProject = projectRepository.save(project);

        return mapToResponse(savedProject);
    }

    // Get  Projects
    @Override
    public List<ProjectResponse> getProjectsByUserId(Long userId) {

        List<Project> projects = projectRepository.findByUserId(userId);

        return projects.stream()
                .map(this::mapToResponse)
                .toList();
    }
    // Get Project By Id
    @Override
    public ProjectResponse getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id : " + id));

        return mapToResponse(project);
    }

    // Update Project
    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id : " + id));

        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setTechnology(request.getTechnology());
        project.setVersion(request.getVersion());
        project.setStatus(request.getStatus());
        project.setUserId(request.getUserId());

        Project updatedProject = projectRepository.save(project);

        return mapToResponse(updatedProject);
    }

    // Delete Project
    @Override
    public void deleteProject(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id : " + id));

        projectRepository.delete(project);
    }

    // Helper method to convert Entity to Response DTO
    private ProjectResponse mapToResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getProjectName(),
                project.getDescription(),
                project.getTechnology(),
                project.getVersion(),
                project.getStatus(),
                project.getCreatedDate(),
                project.getUserId()
        );
    }
}
