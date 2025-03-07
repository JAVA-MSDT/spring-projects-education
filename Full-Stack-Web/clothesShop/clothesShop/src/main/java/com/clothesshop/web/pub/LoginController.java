package com.clothesshop.web.pub;

import com.clothesshop.service.security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserSecurityService userSecurityService;

    @GetMapping("")
    public String loginPage(@RequestParam(value = "username", required = false) String username, Model model, final ModelMap map, @RequestParam("error") final Optional<String> error) {
        model.addAttribute("username", username);
        error.ifPresent(s -> map.addAttribute("error", s));
        return "public/login";
    }

}
