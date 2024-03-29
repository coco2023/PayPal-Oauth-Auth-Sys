package com.umiuni.shop.auth.service.impl;

import com.umiuni.shop.auth.entity.Customer;
import com.umiuni.shop.auth.entity.Supplier;
import com.umiuni.shop.auth.repository.CustomerRepository;
import com.umiuni.shop.auth.repository.SupplierRepository;
import com.umiuni.shop.auth.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Supplier registerOrUpdateSupplier(OAuth2User oAuth2User) {
        // Extract information from oAuth2User
        String email = oAuth2User.getAttribute("email");
        Supplier supplier = supplierRepository.findByPaypalEmail(email)
                .orElse(new Supplier());
        // Set or update other fields from OAuth2User
        supplier.setPaypalEmail(email);
        supplier.setPaypalName(oAuth2User.getAttribute("name"));
        // ... other fields ...
        return supplierRepository.save(supplier);}

    @Override
    public Customer registerOrUpdateCustomer(OAuth2User oAuth2User) {
        log.info("OAuth2User: " + oAuth2User.getAttributes());
        String email = oAuth2User.getAttribute("email");
        Customer customer = customerRepository.findByPaypalEmail(email)
                .orElse(new Customer());
        // Set or update other fields from OAuth2User
        customer.setPaypalEmail(email);
        customer.setPaypalName(oAuth2User.getAttribute("name"));
        // ... other fields ...
        return customerRepository.save(customer);
    }
}
