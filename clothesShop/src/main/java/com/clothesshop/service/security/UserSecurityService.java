package com.clothesshop.service.security;

import com.clothesshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9._]{3,20}$";
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(isUsername(username)) {
            return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        } else if (isEmail(username)) {
            return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        }
        throw new UsernameNotFoundException("username or email not matching... " + username);
    }



    private static boolean isEmail(String input) {
        return Pattern.matches(EMAIL_REGEX, input);
    }

    private static boolean isUsername(String input) {
        return Pattern.matches(USERNAME_REGEX, input);
    }

}
