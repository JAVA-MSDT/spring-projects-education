package com.clothesshop.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public static final String USER_IS_BLOCKED = "User is blocked";
    public static final String BAD_CREDENTIALS = "Bad credentials";
    public static final String FAILURE_URL = "/login?error";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl(FAILURE_URL);
        super.onAuthenticationFailure(request, response, exception);
        String errorMessage = exception.getMessage().equalsIgnoreCase(USER_IS_BLOCKED)
                ? USER_IS_BLOCKED
                : BAD_CREDENTIALS;
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}
