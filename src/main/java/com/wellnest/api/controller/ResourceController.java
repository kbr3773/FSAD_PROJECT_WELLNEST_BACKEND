package com.wellnest.api.controller;

import com.wellnest.api.model.ResourceType;
import com.wellnest.api.model.WellnessResource;
import com.wellnest.api.repository.WellnessResourceRepository;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
  private final WellnessResourceRepository resourceRepository;

  public ResourceController(WellnessResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  @GetMapping
  public List<WellnessResource> list(@RequestParam(required = false) ResourceType type) {
    return type == null
        ? resourceRepository.findByActiveTrue()
        : resourceRepository.findByTypeAndActiveTrue(type);
  }

  @PostMapping
  public WellnessResource create(@Valid @RequestBody WellnessResource resource) {
    resource.setUpdatedAt(LocalDateTime.now());
    return resourceRepository.save(resource);
  }

  @PutMapping("/{id}")
  public WellnessResource update(@PathVariable Long id, @Valid @RequestBody WellnessResource incoming) {
    WellnessResource resource = resourceRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Resource not found"));
    resource.setTitle(incoming.getTitle());
    resource.setDescription(incoming.getDescription());
    resource.setType(incoming.getType());
    resource.setCategory(incoming.getCategory());
    resource.setDuration(incoming.getDuration());
    resource.setImageUrl(incoming.getImageUrl());
    resource.setAuthor(incoming.getAuthor());
    resource.setContent(incoming.getContent());
    resource.setActive(incoming.isActive());
    resource.setUpdatedAt(LocalDateTime.now());
    return resourceRepository.save(resource);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    resourceRepository.deleteById(id);
  }
}
