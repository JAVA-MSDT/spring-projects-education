package com.clothesshop.web.priv;

import com.clothesshop.model.user.security.Role;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.security.RoleService;
import com.clothesshop.service.security.UserSecurityService;
import com.clothesshop.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserSecurityService userSecurityService;
    private final RoleService roleService;

    @GetMapping("/update-user-account/{id}")
    public String getUserForUpdate(@PathVariable long id, Model model) {
        UserSecurity userSecurityDB = userSecurityService.getUserSecurityById(id);
        Set<Role> roles = roleService.getFilteredRoles(userSecurityDB);
        return UserUtil.getUserAccountSettings(model, userSecurityDB, roles);
    }

}
