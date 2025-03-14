package com.crud.jpa_query_orders_10_03.service;

import com.crud.jpa_query_orders_10_03.entity.Employee;
import com.crud.jpa_query_orders_10_03.enumeration.EmployeeDepartment;
import com.crud.jpa_query_orders_10_03.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Selects all employees.
     *
     * @return a page of employees.
     */
    public Page<Employee> findAllEmployees(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return employeeRepository.findAll(pageable);
    }

    /**
     * Returns an employee by its id.
     *
     * @param id
     * @return an optional containing the employee with the given id or Optional.empty() if none found.
     */
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Returns all employees with the given department.
     *
     * @param department
     * @return the page of employee with the given department.
     */
    public Page<Employee> findEmployeesByDepartment(EmployeeDepartment department, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return employeeRepository.findByDepartmentOrderByLastNameDesc(department, pageable);
    }

    /**
     * Saves the given employee.
     *
     * @param employee
     * @return the saved employee.
     */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Updates an employee by its id with values from the given employee.
     *
     * @param id
     * @param updatedEmployee
     * @return an optional containing the updated employee or Optional.empty() if none found.
     */
    public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            optionalEmployee.get().setFirstName(updatedEmployee.getFirstName());
            optionalEmployee.get().setLastName(updatedEmployee.getLastName());
            optionalEmployee.get().setDepartment(updatedEmployee.getDepartment());

            Employee savedEmployee = employeeRepository.save(optionalEmployee.get());
            return Optional.of(savedEmployee);
        }

        return Optional.empty();
    }

    /**
     * Deletes an employee by its id.
     *
     * @param id
     * @return an optional containing the deleted employee or Optional.empty() if none found.
     */
    public Optional<Employee> deleteEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            optionalEmployee.get().setDeleted(true);

            Employee savedEmployee = employeeRepository.save(optionalEmployee.get());
            return Optional.of(savedEmployee);
        }

        return Optional.empty();
    }

}

