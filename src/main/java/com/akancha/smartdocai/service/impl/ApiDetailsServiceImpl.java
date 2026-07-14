package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.dto.ApiDetailsRequest;
import com.akancha.smartdocai.dto.ApiDetailsResponse;
import com.akancha.smartdocai.entity.ApiDetails;
import com.akancha.smartdocai.exception.ResourceNotFoundException;
import com.akancha.smartdocai.repository.ApiDetailsRepository;
import com.akancha.smartdocai.service.ApiDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiDetailsServiceImpl implements ApiDetailsService {

    private final ApiDetailsRepository apiDetailsRepository;

    public ApiDetailsServiceImpl(ApiDetailsRepository apiDetailsRepository) {
        this.apiDetailsRepository = apiDetailsRepository;
    }

    // Save Api Details
    @Override
    public ApiDetailsResponse saveApiDetails(ApiDetailsRequest request) {

        ApiDetails apiDetails = new ApiDetails();

        apiDetails.setApiName(request.getApiName());
        apiDetails.setMethod(request.getMethod());
        apiDetails.setEndpoint(request.getEndpoint());
        apiDetails.setDescription(request.getDescription());
        apiDetails.setRequestExample(request.getRequestExample());
        apiDetails.setResponseExample(request.getResponseExample());
        apiDetails.setProjectId(request.getProjectId());

        ApiDetails savedApiDetails = apiDetailsRepository.save(apiDetails);

        return mapToResponse(savedApiDetails);
    }

    // Get All Api Details
    @Override
    public List<ApiDetailsResponse> getAllApiDetails() {

        return apiDetailsRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Api Details By Project Id
    @Override
    public List<ApiDetailsResponse> getApiDetailsByProjectId(Long projectId) {

        return apiDetailsRepository.findByProjectId(projectId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Api Details By Id
    @Override
    public ApiDetailsResponse getApiDetailsById(Long id) {

        ApiDetails apiDetails = apiDetailsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Api details not found with id : " + id));

        return mapToResponse(apiDetails);
    }

    // Update Api Details
    @Override
    public ApiDetailsResponse updateApiDetails(Long id, ApiDetailsRequest request) {

        ApiDetails apiDetails = apiDetailsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Api details not found with id : " + id));

        apiDetails.setApiName(request.getApiName());
        apiDetails.setMethod(request.getMethod());
        apiDetails.setEndpoint(request.getEndpoint());
        apiDetails.setDescription(request.getDescription());
        apiDetails.setRequestExample(request.getRequestExample());
        apiDetails.setResponseExample(request.getResponseExample());
        apiDetails.setProjectId(request.getProjectId());

        ApiDetails updatedApiDetails = apiDetailsRepository.save(apiDetails);

        return mapToResponse(updatedApiDetails);
    }

    // Delete Api Details
    @Override
    public void deleteApiDetails(Long id) {

        ApiDetails apiDetails = apiDetailsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Api details not found with id : " + id));

        apiDetailsRepository.delete(apiDetails);
    }

    // Helper method to convert Entity to Response DTO
    private ApiDetailsResponse mapToResponse(ApiDetails apiDetails) {
        return new ApiDetailsResponse(
                apiDetails.getId(),
                apiDetails.getApiName(),
                apiDetails.getMethod(),
                apiDetails.getEndpoint(),
                apiDetails.getDescription(),
                apiDetails.getRequestExample(),
                apiDetails.getResponseExample(),
                apiDetails.getProjectId()
        );
    }
}
