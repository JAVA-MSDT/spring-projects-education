package com.clothesshop.web.priv;

import com.clothesshop.dto.PasswordUpdate;
import com.clothesshop.model.user.security.Role;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.RoleService;
import com.clothesshop.service.security.UserSecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user-security")
@RequiredArgsConstructor
public class UserSecurityController {

    private final UserSecurityService userSecurityService;
    private final RoleService roleService;

    @GetMapping("/account-settings")
    public String getAccountSettings(Model model, @AuthenticationPrincipal UserSecurity userSecurity) {
        UserSecurity userSecurityDB = userSecurityService.getUserSecurityById(userSecurity.getId());
        Set<Role> roles = roleService.getRoles()
                .stream().filter(role -> !userSecurity.getRoles().contains(role))
                .collect(Collectors.toSet());

        PasswordUpdate passwordUpdate = PasswordUpdate.defaultInstance();
        model.addAttribute("passwordUpdate", passwordUpdate);
        model.addAttribute("userSecurity", userSecurityDB);
        model.addAttribute("roles", roles);
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

    @PostMapping("/update-roles")
    public String updateRoles(@RequestParam("userRolesHolder") String userRolesHolder, @RequestParam("id") Long userId) {
        System.out.println("userID: " + userId);
        System.out.println("userRoles: " + userRolesHolder);
        UserSecurity userSecurity = userSecurityService.getUserSecurityById(userId);
        System.out.println("userSecurity = " + userSecurity);
        List<Integer> userRolesId = fromStringToIntegers(userRolesHolder);
        userRolesId.forEach(System.out::println);
        return "redirect:/user-security/account-settings#v-pills-security-settings";
    }

    private List<Integer> fromStringToIntegers(String value) {
        String cleaned = value.replaceAll("[\\[\\]\"]", "");
        String[] tokens = cleaned.split(",");
        return Arrays.stream(tokens)
                .map(Integer::valueOf)
                .toList();
    }
}
