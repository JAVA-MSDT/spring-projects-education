package com.clothesshop.repository.user;

import com.clothesshop.model.user.security.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByEmailIgnoreCase(String email);

    Optional<UserSecurity> findByUsernameIgnoreCase(String username);

    // Custom query to enforce fetching the roles.
    @Query("SELECT u FROM UserSecurity u JOIN FETCH u.roles WHERE u.id = :id")
    Optional<UserSecurity> findByIdWithRoles(@Param("id") Long id);
}
