package com.clothesshop.web.pub;

import com.clothesshop.model.user.security.UserSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHome(Model model){
        return "index";
    }

    @GetMapping("profile")
    public String getProfile(@AuthenticationPrincipal UserSecurity userSecurity, Model model) {
        if (userSecurity != null) {
            model.addAttribute("customer", userSecurity.getCustomer());
            model.addAttribute("userSecurity", userSecurity);
        }
        return "private/user/user_profile";
    }
}
