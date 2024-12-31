package com.clothesshop.repository.user;

import com.clothesshop.model.user.security.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByEmail(String email);
    Optional<UserSecurity> findByUsername(String username);
}
