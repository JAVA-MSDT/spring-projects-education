package com.clothesshop.web.pub;

import com.clothesshop.dto.UserRegister;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.UserSecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserSecurityService userSecurityService;

    @GetMapping
    public String registerPage(Model model) {
        UserRegister userSecurity = UserRegister.defaultInstance();
        model.addAttribute("userRegister", userSecurity);
        return "public/register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("userRegister") UserRegister userRegister,
                           BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes) {

        boolean isPasswordsMatched = !userRegister.password().equals(userRegister.repeatPassword());
        if (bindingResult.hasErrors() || isPasswordsMatched) {
            if (isPasswordsMatched) {
                model.addAttribute("passwordError", "Passwords do not match.");
            }
            return "public/register";
        }
        UserSecurity user = userSecurityService.register(userRegister);
        redirectAttributes.addAttribute("username", user.getUsername());
        return "redirect:/login";
    }
}
