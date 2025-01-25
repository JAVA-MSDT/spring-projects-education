package com.clothesshop.service.security;

import com.clothesshop.model.user.security.Role;
import com.clothesshop.repository.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByName(String roleName) {
        return roleRepository.findByRole(roleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }

    public Set<Role> getRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    public Role getRoleById(int id) {
        return findById(id);
    }

    private Role findById(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }
}
