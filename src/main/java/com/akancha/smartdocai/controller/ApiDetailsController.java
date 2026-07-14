package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.ApiDetailsRequest;
import com.akancha.smartdocai.dto.ApiDetailsResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.ApiDetailsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis")
public class ApiDetailsController {

    private final ApiDetailsService apiDetailsService;

    public ApiDetailsController(ApiDetailsService apiDetailsService) {
        this.apiDetailsService = apiDetailsService;
    }

    // Create Api Details
    @PostMapping
    public ApiResponse<ApiDetailsResponse> saveApiDetails(@Valid @RequestBody ApiDetailsRequest request) {

        ApiDetailsResponse response = apiDetailsService.saveApiDetails(request);

        return new ApiResponse<>(true, "API details created successfully", response);
    }

    // Get All Api Details
    @GetMapping
    public ApiResponse<List<ApiDetailsResponse>> getAllApiDetails() {

        List<ApiDetailsResponse> response = apiDetailsService.getAllApiDetails();

        return new ApiResponse<>(true, "API details fetched successfully", response);
    }

    // Get Api Details By Project Id
    @GetMapping("/project/{projectId}")
    public ApiResponse<List<ApiDetailsResponse>> getApiDetailsByProjectId(@PathVariable Long projectId) {

        List<ApiDetailsResponse> response = apiDetailsService.getApiDetailsByProjectId(projectId);

        return new ApiResponse<>(true, "API details fetched successfully", response);
    }

    // Get Api Details By Id
    @GetMapping("/{id}")
    public ApiResponse<ApiDetailsResponse> getApiDetailsById(@PathVariable Long id) {

        ApiDetailsResponse response = apiDetailsService.getApiDetailsById(id);

        return new ApiResponse<>(true, "API details fetched successfully", response);
    }

    // Update Api Details
    @PutMapping("/{id}")
    public ApiResponse<ApiDetailsResponse> updateApiDetails(@PathVariable Long id,
                                                              @Valid @RequestBody ApiDetailsRequest request) {

        ApiDetailsResponse response = apiDetailsService.updateApiDetails(id, request);

        return new ApiResponse<>(true, "API details updated successfully", response);
    }

    // Delete Api Details
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteApiDetails(@PathVariable Long id) {

        apiDetailsService.deleteApiDetails(id);

        return new ApiResponse<>(true, "API details deleted successfully", null);
    }
}
