package com.clothesshop.mapper;

import com.clothesshop.dto.UserRegister;
import com.clothesshop.model.user.Customer;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserSecurity mapNewUserSecurityFromUserRegister(UserRegister userRegister, PasswordEncoder passwordEncoder, RoleService roleService) {

        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUsername(userRegister.username());
        userSecurity.setEmail(userRegister.email());
        userSecurity.setPassword(passwordEncoder.encode(userRegister.password()));
        userSecurity.setEnabled(true);
        userSecurity.setAccountNonExpired(true);
        userSecurity.setAccountNonLocked(true);
        userSecurity.setCredentialsNonExpired(true);
        userSecurity.getRoles().add(roleService.getRoleByName("USER"));

        Customer customer = new Customer();
        customer.setUserSecurity(userSecurity);
        userSecurity.setCustomer(customer);

        return userSecurity;
    }
}
