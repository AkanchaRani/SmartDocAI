package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.dto.TechnicalInfoRequest;
import com.akancha.smartdocai.dto.TechnicalInfoResponse;
import com.akancha.smartdocai.entity.TechnicalInfo;
import com.akancha.smartdocai.exception.ResourceNotFoundException;
import com.akancha.smartdocai.repository.TechnicalInfoRepository;
import com.akancha.smartdocai.service.TechnicalInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalInfoServiceImpl implements TechnicalInfoService {

    private final TechnicalInfoRepository technicalInfoRepository;

    public TechnicalInfoServiceImpl(TechnicalInfoRepository technicalInfoRepository) {
        this.technicalInfoRepository = technicalInfoRepository;
    }

    // Save Technical Info
    @Override
    public TechnicalInfoResponse saveTechnicalInfo(TechnicalInfoRequest request) {

        TechnicalInfo technicalInfo = new TechnicalInfo();

        technicalInfo.setFeatures(request.getFeatures());
        technicalInfo.setRequirements(request.getRequirements());
        technicalInfo.setInstallationSteps(request.getInstallationSteps());
        technicalInfo.setArchitecture(request.getArchitecture());
        technicalInfo.setTechnologyStack(request.getTechnologyStack());
        technicalInfo.setProjectId(request.getProjectId());

        TechnicalInfo saved = technicalInfoRepository.save(technicalInfo);

        return mapToResponse(saved);
    }

    // Get All Technical Info
    @Override
    public List<TechnicalInfoResponse> getAllTechnicalInfo() {

        return technicalInfoRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Technical Info By Project Id
    @Override
    public List<TechnicalInfoResponse> getTechnicalInfoByProjectId(Long projectId) {

        return technicalInfoRepository.findByProjectId(projectId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Technical Info By Id
    @Override
    public TechnicalInfoResponse getTechnicalInfoById(Long id) {

        TechnicalInfo technicalInfo = technicalInfoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Technical info not found with id : " + id));

        return mapToResponse(technicalInfo);
    }

    // Update Technical Info
    @Override
    public TechnicalInfoResponse updateTechnicalInfo(Long id, TechnicalInfoRequest request) {

        TechnicalInfo technicalInfo = technicalInfoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Technical info not found with id : " + id));

        technicalInfo.setFeatures(request.getFeatures());
        technicalInfo.setRequirements(request.getRequirements());
        technicalInfo.setInstallationSteps(request.getInstallationSteps());
        technicalInfo.setArchitecture(request.getArchitecture());
        technicalInfo.setTechnologyStack(request.getTechnologyStack());
        technicalInfo.setProjectId(request.getProjectId());

        TechnicalInfo updated = technicalInfoRepository.save(technicalInfo);

        return mapToResponse(updated);
    }

    // Delete Technical Info
    @Override
    public void deleteTechnicalInfo(Long id) {

        TechnicalInfo technicalInfo = technicalInfoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Technical info not found with id : " + id));

        technicalInfoRepository.delete(technicalInfo);
    }

    // Helper method to convert Entity to Response DTO
    private TechnicalInfoResponse mapToResponse(TechnicalInfo technicalInfo) {
        return new TechnicalInfoResponse(
                technicalInfo.getId(),
                technicalInfo.getFeatures(),
                technicalInfo.getRequirements(),
                technicalInfo.getInstallationSteps(),
                technicalInfo.getArchitecture(),
                technicalInfo.getTechnologyStack(),
                technicalInfo.getProjectId()
        );
    }
}
