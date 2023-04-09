package com.springboot.model.testing.repository;

import com.springboot.model.testing.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    Page<Customer> findByNameLimitedToOne(String name, Pageable pageable);


}
