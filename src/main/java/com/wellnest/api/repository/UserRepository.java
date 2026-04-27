package com.wellnest.api.repository;

import com.wellnest.api.model.AppUser;
import com.wellnest.api.model.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByEmail(String email);

  boolean existsByEmail(String email);

  long countByRole(Role role);

  List<AppUser> findByRole(Role role);
}
