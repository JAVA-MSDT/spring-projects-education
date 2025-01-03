package com.clothesshop.web.pub;

import com.clothesshop.service.security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserSecurityService userSecurityService;

    @GetMapping
    public String registerPage() {
        return "register";
    }
}
