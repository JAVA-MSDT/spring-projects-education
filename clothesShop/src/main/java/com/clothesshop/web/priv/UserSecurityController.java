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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user-security")
@RequiredArgsConstructor
public class UserSecurityController {

    private final UserSecurityService userSecurityService;

    @GetMapping("/account-settings")
    public String getAccountSettings(Model model, @AuthenticationPrincipal UserSecurity userSecurity) {
        UserSecurity userSecurityDB = userSecurityService.getUserSecurityById(userSecurity.getId());
        PasswordUpdate passwordUpdate = PasswordUpdate.defaultInstance();
        model.addAttribute("passwordUpdate", passwordUpdate);
        model.addAttribute("userSecurity", userSecurityDB);
        model.addAttribute("fragment", "v-pills-contact-info");
        return "private/user/account_settings";
    }

    @PostMapping("/update-email")
    public String updateEmail(@RequestParam("newEmail") String newEmail, @RequestParam("id") Long id,
                              RedirectAttributes redirectAttributes) {
        userSecurityService.updateUserSecurityEmail(id, newEmail);
        redirectAttributes.addFlashAttribute("emailUpdateSuccess", "Email updated successfully");
        return "redirect:/user-security/account-settings#v-pills-security-settings";
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

        boolean success = userSecurityService.updateUserSecurityPassword(passwordUpdate);
        if (success) {
            redirectAttributes.addFlashAttribute("passwordUpdateSuccess", "Password updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("wrongPassword", "Current password is incorrect.");
        }
        return "redirect:/user-security/account-settings#v-pills-security-settings";
    }
}
