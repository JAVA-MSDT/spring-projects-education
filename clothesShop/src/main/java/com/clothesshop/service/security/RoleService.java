package com.clothesshop.service.security;

import com.clothesshop.model.user.security.Role;
import com.clothesshop.repository.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        return roleRepository.findByRole(roleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }
}
