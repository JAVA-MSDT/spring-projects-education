package com.clothesshop.web.pub;

import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserSecurityService userRepository;

    @GetMapping
    public String getHome(Model model) {
        return "index";
    }

    @GetMapping("profile")
    public String getProfile(@AuthenticationPrincipal UserSecurity userSecurity, Model model) {
        if (userSecurity != null) {
            userSecurity = (UserSecurity) userRepository.loadUserByUsername(userSecurity.getUsername());
            model.addAttribute("customer", userSecurity.getCustomer());
            model.addAttribute("userSecurity", userSecurity);
        }
        return "private/user/user_profile";
    }

    @GetMapping("/account-settings")
    public String getAccountSettings(Model model) {
        return "private/user/account_settings";
    }
}
