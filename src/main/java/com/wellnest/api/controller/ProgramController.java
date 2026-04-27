package com.wellnest.api.controller;

import com.wellnest.api.model.ResourceType;
import com.wellnest.api.model.WellnessProgram;
import com.wellnest.api.repository.WellnessProgramRepository;
import jakarta.validation.Valid;
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
@RequestMapping("/api/programs")
public class ProgramController {
  private final WellnessProgramRepository programRepository;

  public ProgramController(WellnessProgramRepository programRepository) {
    this.programRepository = programRepository;
  }

  @GetMapping
  public List<WellnessProgram> list(@RequestParam(required = false) ResourceType type) {
    return type == null ? programRepository.findAll() : programRepository.findByType(type);
  }

  @PostMapping
  public WellnessProgram create(@Valid @RequestBody WellnessProgram program) {
    return programRepository.save(program);
  }

  @PostMapping("/{id}/enroll")
  public WellnessProgram enroll(@PathVariable Long id) {
    WellnessProgram program = programRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Program not found"));
    program.setEnrolled(program.getEnrolled() + 1);
    return programRepository.save(program);
  }

  @PutMapping("/{id}")
  public WellnessProgram update(@PathVariable Long id, @Valid @RequestBody WellnessProgram incoming) {
    WellnessProgram program = programRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Program not found"));
    program.setTitle(incoming.getTitle());
    program.setDescription(incoming.getDescription());
    program.setInstructor(incoming.getInstructor());
    program.setLevel(incoming.getLevel());
    program.setCategory(incoming.getCategory());
    program.setDuration(incoming.getDuration());
    program.setImageUrl(incoming.getImageUrl());
    program.setEnrolled(incoming.getEnrolled());
    program.setStatus(incoming.getStatus());
    program.setType(incoming.getType());
    return programRepository.save(program);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    programRepository.deleteById(id);
  }
}
