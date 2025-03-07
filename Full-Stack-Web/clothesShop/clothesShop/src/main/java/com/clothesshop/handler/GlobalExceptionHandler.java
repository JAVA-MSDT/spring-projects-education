package com.clothesshop.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public static final String ERROR_PAGE = "shared/error";
    // https://pandac.in/blogs/thymeleaf-errorhandle/

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        if (ex instanceof ResponseStatusException) {
            model.addAttribute("errorMessage", ex.getLocalizedMessage());
            log.error("ResponseStatusException", ex);
            return ERROR_PAGE;
        }

        if (ex instanceof AuthorizationDeniedException) {
            model.addAttribute("errorMessage", ex.getLocalizedMessage());
            log.error("AuthorizationDeniedException", ex);
            return ERROR_PAGE;
        }
        model.addAttribute("errorMessage", ex.getLocalizedMessage());
        log.error("Error is {}.", ex.getLocalizedMessage(), ex);
        return ERROR_PAGE;
    }
}
