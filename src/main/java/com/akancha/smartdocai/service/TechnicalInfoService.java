package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.TechnicalInfoRequest;
import com.akancha.smartdocai.dto.TechnicalInfoResponse;

import java.util.List;

public interface TechnicalInfoService {

    TechnicalInfoResponse saveTechnicalInfo(TechnicalInfoRequest request);

    List<TechnicalInfoResponse> getAllTechnicalInfo();

    List<TechnicalInfoResponse> getTechnicalInfoByProjectId(Long projectId);

    TechnicalInfoResponse getTechnicalInfoById(Long id);

    TechnicalInfoResponse updateTechnicalInfo(Long id, TechnicalInfoRequest request);

    void deleteTechnicalInfo(Long id);
}
