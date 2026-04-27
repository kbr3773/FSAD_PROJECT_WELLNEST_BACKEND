package com.wellnest.api.controller;

import com.wellnest.api.dto.AdminStatsResponse;
import com.wellnest.api.repository.SupportRequestRepository;
import com.wellnest.api.repository.UsageMetricRepository;
import com.wellnest.api.service.DashboardService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
  private final DashboardService dashboardService;
  private final UsageMetricRepository usageMetricRepository;
  private final SupportRequestRepository supportRequestRepository;

  public DashboardController(
      DashboardService dashboardService,
      UsageMetricRepository usageMetricRepository,
      SupportRequestRepository supportRequestRepository) {
    this.dashboardService = dashboardService;
    this.usageMetricRepository = usageMetricRepository;
    this.supportRequestRepository = supportRequestRepository;
  }

  @GetMapping("/admin")
  public Map<String, Object> adminDashboard() {
    AdminStatsResponse stats = dashboardService.adminStats();
    return Map.of(
        "stats", stats,
        "metrics", usageMetricRepository.findAll(),
        "supportRequests", supportRequestRepository.findAll()
    );
  }

  @GetMapping("/student")
  public Map<String, Object> studentDashboard() {
    return Map.of(
        "wellnessScore", 85,
        "weeklyFitnessMinutes", 285,
        "meditationMinutes", 45,
        "calories", 1850,
        "message", "Keep your routine balanced today"
    );
  }
}
