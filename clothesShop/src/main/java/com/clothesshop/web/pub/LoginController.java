package com.clothesshop.web.pub;

import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserSecurityService userSecurityService;

    @GetMapping("")
    public String loginPage(final ModelMap map, @RequestParam("error") final Optional<String> error) {
        System.out.println("login....");
        error.ifPresent(s -> map.addAttribute("error", s));
        return "public/login";
    }

    @PostMapping("/profile")
    public String login(@AuthenticationPrincipal UserSecurity userSecurity, Model model) {
        if (userSecurity != null) {
            model.addAttribute("customer", userSecurity.getCustomer());
            model.addAttribute("userSecurity", userSecurity);
        }
        return "private/user/user_profile";
    }
}
