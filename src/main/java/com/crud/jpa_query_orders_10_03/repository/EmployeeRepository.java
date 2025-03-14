package com.crud.jpa_query_orders_10_03.repository;

import com.crud.jpa_query_orders_10_03.entity.Employee;
import com.crud.jpa_query_orders_10_03.enumeration.EmployeeDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByDepartmentOrderByLastNameDesc(EmployeeDepartment department, Pageable pageable);
}
