package com.wellnest.api.repository;

import com.wellnest.api.model.ResourceType;
import com.wellnest.api.model.WellnessProgram;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WellnessProgramRepository extends JpaRepository<WellnessProgram, Long> {
  List<WellnessProgram> findByType(ResourceType type);
}
