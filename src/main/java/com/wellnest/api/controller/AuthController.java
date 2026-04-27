package com.wellnest.api.controller;

import com.wellnest.api.dto.AuthRequest;
import com.wellnest.api.dto.AuthResponse;
import com.wellnest.api.model.Role;
import com.wellnest.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/student/register")
  public ResponseEntity<AuthResponse> registerStudent(@Valid @RequestBody AuthRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
  }

  @PostMapping("/student/login")
  public AuthResponse loginStudent(@Valid @RequestBody AuthRequest request) {
    return authService.login(request, Role.STUDENT);
  }

  @PostMapping("/admin/login")
  public AuthResponse loginAdmin(@Valid @RequestBody AuthRequest request) {
    return authService.login(request, Role.ADMIN);
  }
}
