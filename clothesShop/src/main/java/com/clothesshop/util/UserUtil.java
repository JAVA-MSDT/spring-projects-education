package com.clothesshop.util;

import com.clothesshop.dto.UserRegister;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}
