package com.crud.jpa_query_orders_10_03.repository;

import com.crud.jpa_query_orders_10_03.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByCustomerName(String customerName, Pageable pageable);
}
