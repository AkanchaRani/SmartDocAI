package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.DashboardResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary/{userId}")
    public ApiResponse<DashboardResponse> getDashboardSummary(@PathVariable Long userId) {

        DashboardResponse response = dashboardService.getDashboardSummary(userId);

        return new ApiResponse<>(
                true,
                "Dashboard fetched successfully",
                response
        );
    }
}