package com.umiuni.shop.auth.repository;

import com.umiuni.shop.auth.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByPaypalEmail(String paypalEmail);
}
