package com.crud.jpa_query_orders_10_03.controller;

import com.crud.jpa_query_orders_10_03.entity.Employee;
import com.crud.jpa_query_orders_10_03.enumeration.EmployeeDepartment;
import com.crud.jpa_query_orders_10_03.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Selects all employees.
     *
     * @return a response entity containing a page of employees.
     */
    @GetMapping("/select-all")
    public ResponseEntity<Page<Employee>> findAllEmployees(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {

        Page<Employee> employees = employeeService.findAllEmployees(pageNumber, pageSize);
        return ResponseEntity.ok(employees);

    }

    /**
     * Selects all employees with the given department.
     *
     * @param department
     * @return a response entity containing the page of employees with the given department.
     */
    @GetMapping("/select-by-department/{department}")
    public ResponseEntity<Page<Employee>> findEmployeesByDepartment(
            @PathVariable EmployeeDepartment department,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {

        Page<Employee> employees = employeeService.findEmployeesByDepartment(department, pageNumber, pageSize);
        return ResponseEntity.ok(employees);

    }

    /**
     * Selects an employee by its id.
     *
     * @param id
     * @return a response entity containing the employee with the given id or ResponseEntity.notFound() if none found.
     */
    @GetMapping("/select-by-id/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(id);

        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Saves the given employee.
     *
     * @param employee
     * @return a response entity containing the saved employee.
     */
    @PostMapping("/create")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    /**
     * Updates an employee by its id with values from the given employee.
     *
     * @param id
     * @param updatedEmployee
     * @return a response entity containing the updated employee or ResponseEntity.notFound() if none found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeService.updateEmployee(id, updatedEmployee);

        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes an employee by its id.
     *
     * @param id
     * @return a response entity containing the deleted employee or ResponseEntity.notFound() if none found.
     */
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.deleteEmployeeById(id);

        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        }

        return ResponseEntity.notFound().build();
    }
}

