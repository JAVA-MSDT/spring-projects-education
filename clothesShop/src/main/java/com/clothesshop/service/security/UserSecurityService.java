package com.clothesshop.service.security;

import com.clothesshop.dto.PasswordUpdate;
import com.clothesshop.dto.UserRegister;
import com.clothesshop.mapper.UserMapper;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.repository.user.UserRepository;
import com.clothesshop.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (UserUtil.isUsername(username)) {
            return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
        } else if (UserUtil.isEmail(username)) {
            return userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
        }
        throw new UsernameNotFoundException("username or email not matching... " + username);
    }

    public UserSecurity register(UserRegister userRegister) {
        UserSecurity userSecurity = userMapper.mapNewUserSecurityFromUserRegister(userRegister, passwordEncoder, roleService);
        return userRepository.save(userSecurity);
    }

    public void saveUserSecurity(UserSecurity userSecurity) {
        userRepository.save(userSecurity);
    }

    public UserSecurity getUserSecurityById(Long id) {
        return findUserSecurityByID(id);
    }

    public void updateUserSecurityEmail(Long id, String email) {
        UserSecurity userSecurity = findUserSecurityByID(id);
        userSecurity.setEmail(email);
        userRepository.save(userSecurity);
    }

    public boolean updateUserSecurityPassword(PasswordUpdate passwordUpdate) {
        UserSecurity userSecurity = findUserSecurityByID(passwordUpdate.id());
        String currentPasswordDB = userSecurity.getPassword();
        String currentPasswordUI = passwordEncoder.encode(passwordUpdate.currentPassword());
        System.out.println("currentPasswordDB:: " + currentPasswordDB);
        System.out.println("currentPasswordUI:: " + currentPasswordUI);
        if (currentPasswordDB.equals(currentPasswordUI)) {
            userSecurity.setPassword(passwordEncoder.encode(passwordUpdate.newPassword()));
            userRepository.save(userSecurity);
            return true;
        }
        return false;
    }

    private UserSecurity findUserSecurityByID(Long id) {
        return userRepository.findByIdWithRoles(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Security Not found"));
    }

}
