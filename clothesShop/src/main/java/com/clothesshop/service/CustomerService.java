package com.clothesshop.service;

import com.clothesshop.model.clothe.Clothe;
import com.clothesshop.model.user.Customer;
import com.clothesshop.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ClotheService clotheService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void removeClotheFromCustomer(Long customerId, Long clotheId) {
        Customer customer = findById(customerId);
        Clothe clothe = clotheService.getClotheById(clotheId);
        customer.getClothes().remove(clothe);
        clothe.getCustomers().remove(customer);
        saveCustomer(customer);
        clotheService.addClotheToClothe(clotheId, 1);
    }

    public void addClotheToCustomer(Long customerId, Long clotheId) {
        Customer customer = findById(customerId);
        Clothe clothe = clotheService.getClotheById(clotheId);
        customer.getClothes().add(clothe);
        clothe.getCustomers().add(customer);
        saveCustomer(customer);
        clotheService.removeClotheFromClothe(clotheId, 1);
    }

    private Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id: " + id + " Not found"));
    }
}
