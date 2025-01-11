package com.clothesshop.service.security;

import com.clothesshop.dto.UserRegister;
import com.clothesshop.mapper.UserMapper;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.repository.user.UserRepository;
import com.clothesshop.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
