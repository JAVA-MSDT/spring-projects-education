package com.clothesshop.repository.user;

import com.clothesshop.model.user.security.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByEmailIgnoreCase(String email);
    Optional<UserSecurity> findByUsernameIgnoreCase(String username);
}
