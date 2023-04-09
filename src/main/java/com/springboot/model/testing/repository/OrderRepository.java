package com.springboot.model.testing.repository;

import com.springboot.model.testing.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
