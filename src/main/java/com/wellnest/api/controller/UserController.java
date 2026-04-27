package com.wellnest.api.controller;

import com.wellnest.api.dto.AuthResponse;
import com.wellnest.api.model.AppUser;
import com.wellnest.api.model.Role;
import com.wellnest.api.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/students")
  public List<AuthResponse> students() {
    return userRepository.findByRole(Role.STUDENT).stream()
        .map(this::toResponse)
        .toList();
  }

  private AuthResponse toResponse(AppUser user) {
    return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), "Loaded");
  }
}
