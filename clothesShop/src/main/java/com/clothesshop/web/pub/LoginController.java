package com.clothesshop.web.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    @GetMapping
    public String login(final ModelMap map, @RequestParam("error") final Optional<String> error) {
        error.ifPresent(err -> map.addAttribute("error", err));
        return "public/login";
    }
}
