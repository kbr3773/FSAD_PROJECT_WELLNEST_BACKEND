package com.wellnest.api.service;

import com.wellnest.api.dto.AdminStatsResponse;
import com.wellnest.api.model.Role;
import com.wellnest.api.repository.SupportRequestRepository;
import com.wellnest.api.repository.UsageMetricRepository;
import com.wellnest.api.repository.UserRepository;
import com.wellnest.api.repository.WellnessProgramRepository;
import com.wellnest.api.repository.WellnessResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
  private final UserRepository userRepository;
  private final WellnessResourceRepository resourceRepository;
  private final WellnessProgramRepository programRepository;
  private final SupportRequestRepository supportRequestRepository;
  private final UsageMetricRepository usageMetricRepository;

  public DashboardService(
      UserRepository userRepository,
      WellnessResourceRepository resourceRepository,
      WellnessProgramRepository programRepository,
      SupportRequestRepository supportRequestRepository,
      UsageMetricRepository usageMetricRepository) {
    this.userRepository = userRepository;
    this.resourceRepository = resourceRepository;
    this.programRepository = programRepository;
    this.supportRequestRepository = supportRequestRepository;
    this.usageMetricRepository = usageMetricRepository;
  }

  public AdminStatsResponse adminStats() {
    long views = usageMetricRepository.findAll().stream().mapToLong(metric -> metric.getResourceViews()).sum();
    long enrollments = usageMetricRepository.findAll().stream().mapToLong(metric -> metric.getProgramEnrollments()).sum();
    return new AdminStatsResponse(
        userRepository.countByRole(Role.STUDENT),
        resourceRepository.count(),
        programRepository.count(),
        supportRequestRepository.count(),
        views,
        enrollments
    );
  }
}
