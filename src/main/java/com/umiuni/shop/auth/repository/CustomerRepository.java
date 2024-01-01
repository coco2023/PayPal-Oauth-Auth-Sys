package com.umiuni.shop.auth.repository;

import com.umiuni.shop.auth.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPaypalEmail(String paypalEmail);
}
