package com.wellnest.api.repository;

import com.wellnest.api.model.UsageMetric;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageMetricRepository extends JpaRepository<UsageMetric, Long> {
  Optional<UsageMetric> findByMetricDate(LocalDate metricDate);
}
