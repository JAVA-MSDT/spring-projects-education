package com.clothesshop.handler;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String ERROR_PAGE = "shared/error";
    // https://pandac.in/blogs/thymeleaf-errorhandle/

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        if (ex instanceof ResponseStatusException) {
            model.addAttribute("errorMessage", ex.getLocalizedMessage());
            return ERROR_PAGE;
        }

        if (ex instanceof AuthorizationDeniedException) {
            model.addAttribute("errorMessage", ex.getLocalizedMessage());
            return ERROR_PAGE;
        }
        model.addAttribute("errorMessage", ex.getLocalizedMessage());
        return ERROR_PAGE;
    }
}
