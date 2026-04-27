package com.wellnest.api.service;

import com.wellnest.api.dto.AuthRequest;
import com.wellnest.api.dto.AuthResponse;
import com.wellnest.api.model.AppUser;
import com.wellnest.api.model.Role;
import com.wellnest.api.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public AuthResponse register(AuthRequest request) {
    if (userRepository.existsByEmail(request.email())) {
      throw new IllegalArgumentException("Email is already registered");
    }

    AppUser user = new AppUser();
    user.setName(request.name() == null || request.name().isBlank() ? "Student" : request.name());
    user.setEmail(request.email().toLowerCase());
    user.setPasswordHash(passwordEncoder.encode(request.password()));
    user.setRole(request.role() == null ? Role.STUDENT : request.role());
    AppUser saved = userRepository.save(user);
    return toResponse(saved, "Account created successfully");
  }

  public AuthResponse login(AuthRequest request, Role expectedRole) {
    AppUser user = userRepository.findByEmail(request.email().toLowerCase())
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

    if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
      throw new IllegalArgumentException("Invalid email or password");
    }

    if (expectedRole != null && user.getRole() != expectedRole) {
      throw new IllegalArgumentException("This account is not allowed in this portal");
    }

    return toResponse(user, "Login successful");
  }

  private AuthResponse toResponse(AppUser user, String message) {
    return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), message);
  }
}
