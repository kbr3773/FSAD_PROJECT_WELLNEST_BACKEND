package com.wellnest.api.dto;

import com.wellnest.api.model.Role;

public record AuthResponse(
    Long id,
    String name,
    String email,
    Role role,
    String message
) {
}
