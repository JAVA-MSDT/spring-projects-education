package com.clothesshop.service.security;

import com.clothesshop.model.user.Customer;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSecurityService implements UserDetailsService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9._]{3,20}$";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (isUsername(username)) {
            return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
        } else if (isEmail(username)) {
            return userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
        }
        throw new UsernameNotFoundException("username or email not matching... " + username);
    }

    public UserSecurity register(UserSecurity userSecurity) {
        setNewUserSecurity(userSecurity);
        return userRepository.save(userSecurity);
    }

    private void setNewUserSecurity(UserSecurity userSecurity) {
        userSecurity.setPassword(passwordEncoder.encode(userSecurity.getPassword()));
        userSecurity.setCustomer(new Customer());
        userSecurity.setEnabled(true);
        userSecurity.setAccountNonExpired(true);
        userSecurity.setAccountNonLocked(true);
        userSecurity.setCredentialsNonExpired(true);
        userSecurity.getRoles().add(roleService.findByRoleName("USER"));
    }

    private static boolean isEmail(String input) {
        return Pattern.matches(EMAIL_REGEX, input);
    }

    private static boolean isUsername(String input) {
        return Pattern.matches(USERNAME_REGEX, input);
    }

}
