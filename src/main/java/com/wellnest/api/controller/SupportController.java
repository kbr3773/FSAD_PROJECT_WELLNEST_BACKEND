package com.wellnest.api.controller;

import com.wellnest.api.model.SupportRequest;
import com.wellnest.api.repository.SupportRequestRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/support")
public class SupportController {
  private final SupportRequestRepository supportRequestRepository;

  public SupportController(SupportRequestRepository supportRequestRepository) {
    this.supportRequestRepository = supportRequestRepository;
  }

  @GetMapping
  public List<SupportRequest> list() {
    return supportRequestRepository.findAll();
  }

  @PostMapping
  public SupportRequest create(@RequestBody SupportRequest request) {
    return supportRequestRepository.save(request);
  }
}
