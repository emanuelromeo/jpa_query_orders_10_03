package com.crud.jpa_query_orders_10_03.repository;

import com.crud.jpa_query_orders_10_03.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
