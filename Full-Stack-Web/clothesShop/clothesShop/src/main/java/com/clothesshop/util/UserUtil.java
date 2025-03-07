package com.clothesshop.util;

import com.clothesshop.dto.PasswordUpdate;
import com.clothesshop.dto.UserRegister;
import com.clothesshop.model.user.security.Role;
import com.clothesshop.model.user.security.UserSecurity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;

import java.util.Set;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtil {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9._]{3,20}$";

    public static boolean isEmail(String input) {
        return Pattern.matches(EMAIL_REGEX, input);
    }

    public static boolean isUsername(String input) {
        return Pattern.matches(USERNAME_REGEX, input);
    }

    public static boolean isPasswordsNotMatched(UserRegister userRegister) {
        return !userRegister.password().equals(userRegister.repeatPassword());
    }

    public static String getUserAccountSettings(Model model, UserSecurity userSecurityDB, Set<Role> roles) {
        PasswordUpdate passwordUpdate = PasswordUpdate.defaultInstance();

        model.addAttribute("passwordUpdate", passwordUpdate);
        model.addAttribute("userSecurity", userSecurityDB);
        model.addAttribute("roles", roles);
        model.addAttribute("fragment", "v-pills-contact-info");
        return "private/user/account_settings";
    }
}
