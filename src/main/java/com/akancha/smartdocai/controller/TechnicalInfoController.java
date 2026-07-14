package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.TechnicalInfoRequest;
import com.akancha.smartdocai.dto.TechnicalInfoResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.TechnicalInfoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technical-info")
public class TechnicalInfoController {

    private final TechnicalInfoService technicalInfoService;

    public TechnicalInfoController(TechnicalInfoService technicalInfoService) {
        this.technicalInfoService = technicalInfoService;
    }

    // Create Technical Info
    @PostMapping
    public ApiResponse<TechnicalInfoResponse> saveTechnicalInfo(@Valid @RequestBody TechnicalInfoRequest request) {

        TechnicalInfoResponse response = technicalInfoService.saveTechnicalInfo(request);

        return new ApiResponse<>(true, "Technical info created successfully", response);
    }

    // Get All Technical Info
    @GetMapping
    public ApiResponse<List<TechnicalInfoResponse>> getAllTechnicalInfo() {

        List<TechnicalInfoResponse> response = technicalInfoService.getAllTechnicalInfo();

        return new ApiResponse<>(true, "Technical info fetched successfully", response);
    }

    // Get Technical Info By Project Id
    @GetMapping("/project/{projectId}")
    public ApiResponse<List<TechnicalInfoResponse>> getTechnicalInfoByProjectId(@PathVariable Long projectId) {

        List<TechnicalInfoResponse> response = technicalInfoService.getTechnicalInfoByProjectId(projectId);

        return new ApiResponse<>(true, "Technical info fetched successfully", response);
    }

    // Get Technical Info By Id
    @GetMapping("/{id}")
    public ApiResponse<TechnicalInfoResponse> getTechnicalInfoById(@PathVariable Long id) {

        TechnicalInfoResponse response = technicalInfoService.getTechnicalInfoById(id);

        return new ApiResponse<>(true, "Technical info fetched successfully", response);
    }

    // Update Technical Info
    @PutMapping("/{id}")
    public ApiResponse<TechnicalInfoResponse> updateTechnicalInfo(@PathVariable Long id,
                                                                    @Valid @RequestBody TechnicalInfoRequest request) {

        TechnicalInfoResponse response = technicalInfoService.updateTechnicalInfo(id, request);

        return new ApiResponse<>(true, "Technical info updated successfully", response);
    }

    // Delete Technical Info
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteTechnicalInfo(@PathVariable Long id) {

        technicalInfoService.deleteTechnicalInfo(id);

        return new ApiResponse<>(true, "Technical info deleted successfully", null);
    }
}
