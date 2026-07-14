package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.ProjectRequest;
import com.akancha.smartdocai.dto.ProjectResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create Project
    @PostMapping
    public ApiResponse<ProjectResponse> saveProject(@Valid @RequestBody ProjectRequest request) {

        ProjectResponse project = projectService.saveProject(request);

        return new ApiResponse<>(true, "Project created successfully", project);
    }

    // Get All Projects
    @GetMapping("/user/{userId}")
    public ApiResponse<List<ProjectResponse>> getProjectsByUserId(
            @PathVariable Long userId) {

        List<ProjectResponse> projects =
                projectService.getProjectsByUserId(userId);

        return new ApiResponse<>(true,
                "Projects fetched successfully",
                projects);
    }

    // Get Project By Id
    @GetMapping("/{id}")
    public ApiResponse<ProjectResponse> getProjectById(@PathVariable Long id) {

        ProjectResponse project = projectService.getProjectById(id);

        return new ApiResponse<>(true, "Project fetched successfully", project);
    }

    // Update Project
    @PutMapping("/{id}")
    public ApiResponse<ProjectResponse> updateProject(@PathVariable Long id,
                                                        @Valid @RequestBody ProjectRequest request) {

        ProjectResponse project = projectService.updateProject(id, request);

        return new ApiResponse<>(true, "Project updated successfully", project);
    }

    // Delete Project
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProject(@PathVariable Long id) {

        projectService.deleteProject(id);

        return new ApiResponse<>(true, "Project deleted successfully", null);
    }
}
