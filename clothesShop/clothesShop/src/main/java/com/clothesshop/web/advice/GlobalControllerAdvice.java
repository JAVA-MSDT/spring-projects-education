package com.clothesshop.web.advice;

import com.clothesshop.model.user.Customer;
import com.clothesshop.model.user.security.UserSecurity;
import com.clothesshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final CustomerService customerService;

    @ModelAttribute("customerProfileImage")
    public String getCustomer(@AuthenticationPrincipal UserSecurity user) {
        if (user != null) {
            Customer customer = customerService.getCustomerById(user.getId());
            return customer.getProfilePictureUrl();
        }
        return "/images/clothes.png";
    }
}
