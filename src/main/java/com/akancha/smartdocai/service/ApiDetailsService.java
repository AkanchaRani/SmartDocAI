package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.ApiDetailsRequest;
import com.akancha.smartdocai.dto.ApiDetailsResponse;

import java.util.List;

public interface ApiDetailsService {

    ApiDetailsResponse saveApiDetails(ApiDetailsRequest request);

    List<ApiDetailsResponse> getAllApiDetails();

    List<ApiDetailsResponse> getApiDetailsByProjectId(Long projectId);

    ApiDetailsResponse getApiDetailsById(Long id);

    ApiDetailsResponse updateApiDetails(Long id, ApiDetailsRequest request);

    void deleteApiDetails(Long id);
}
