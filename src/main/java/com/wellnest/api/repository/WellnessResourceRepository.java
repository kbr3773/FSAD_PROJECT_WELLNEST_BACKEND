package com.wellnest.api.repository;

import com.wellnest.api.model.ResourceType;
import com.wellnest.api.model.WellnessResource;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WellnessResourceRepository extends JpaRepository<WellnessResource, Long> {
  List<WellnessResource> findByTypeAndActiveTrue(ResourceType type);

  List<WellnessResource> findByActiveTrue();
}
