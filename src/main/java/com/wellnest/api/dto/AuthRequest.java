package com.wellnest.api.dto;

import com.wellnest.api.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    String name,
    @Email String email,
    @NotBlank String password,
    Role role
) {
}
