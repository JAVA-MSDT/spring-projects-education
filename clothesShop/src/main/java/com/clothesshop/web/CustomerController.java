package com.clothesshop.web;

import com.clothesshop.model.user.Customer;
import com.clothesshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("module", "customers");
        return "customers";
    }

    @GetMapping(path = "/{id}")
    public String getCustomerById(@PathVariable("id") long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("userSecurity", customer.getUserSecurity());
        model.addAttribute("module", "customers");
        return "private/user/user_profile";
    }


//    @GetMapping("/update/{id}")
//    public String updateCustomerPage(@PathVariable(value = "id") long id, Model model) {
//        Customer customer = customerService.getCustomerById(id);
//        model.addAttribute("customer", customer);
//        return "private/add_update_customer";
//    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomer(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "DataIntegrityViolationException: Item with id " + id + " already used somewhere in the system");
            return "redirect:/customers";
        }
        redirectAttributes.addFlashAttribute("deleteMessage", "Deleted customer with id: " + id);
        return "redirect:/customers";
    }
}
