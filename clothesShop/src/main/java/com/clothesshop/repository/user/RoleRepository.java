package com.clothesshop.repository.user;

import com.clothesshop.model.user.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
