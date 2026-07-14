package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.DashboardResponse;

public interface DashboardService {

    DashboardResponse getDashboardSummary(Long userId);

}