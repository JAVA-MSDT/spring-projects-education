package com.clothesshop.mapper;

import com.clothesshop.model.user.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public void mapCustomerBasicDetails(Customer customer, Customer oldCustomer) {
        oldCustomer.setName(customer.getName());
        oldCustomer.setContactName(customer.getContactName());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setPhoneNumber(customer.getPhoneNumber());
    }
}
