package com.clothesshop.web.priv;

import com.clothesshop.dto.PasswordUpdate;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.UserSecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user-security")
@RequiredArgsConstructor
public class UserSecurityController {

    private final UserSecurityService userSecurityService;

    @GetMapping("/account-settings")
    public String getAccountSettings(Model model, @AuthenticationPrincipal UserSecurity userSecurity) {
        PasswordUpdate passwordUpdate = PasswordUpdate.defaultInstance();
        model.addAttribute("passwordUpdate", passwordUpdate);
        model.addAttribute("userSecurity", userSecurityService.getUserSecurityById(userSecurity.getId()));
        model.addAttribute("fragment", "v-pills-contact-info");
        return "private/user/account_settings";
    }

    @PostMapping("/update-password")
    public String updatePassword(@Valid @ModelAttribute("passwordUpdate") PasswordUpdate passwordUpdate,
                                 BindingResult bindingResult, Model model, @AuthenticationPrincipal UserSecurity userSecurity,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userSecurity", userSecurity);
            model.addAttribute("fragment", "v-pills-security-settings");
            return "private/user/account_settings";
        }
        redirectAttributes.addFlashAttribute("passwordUpdateSuccess", "Password updated successfully");
        return "redirect:/user-security/account-settings#v-pills-security-settings";
    }
}
