package com.clothesshop.web.pub;

import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserSecurityService userSecurityService;

    @GetMapping
    public String registerPage(Model model) {
        UserSecurity userSecurity = new UserSecurity();
        model.addAttribute("userSecurity", userSecurity);
        return "public/register";
    }

    @PostMapping
    public String register( @ModelAttribute("userSecurity") UserSecurity userSecurity, BindingResult bindingResult,
            @RequestParam("repeatPassword") String repeatPassword,
            Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userSecurity.getPassword().equals(repeatPassword)) {
            if (!userSecurity.getPassword().equals(repeatPassword)) {
                model.addAttribute("passwordError", "Passwords do not match.");
            }
            return "public/register"; // Return to the registration page
        }

        userSecurityService.register(userSecurity);
        redirectAttributes.addAttribute("username", userSecurity.getUsername());
        return "redirect:/login"; // Redirect to login page after successful registration
    }
}
