package com.crud.jpa_query_orders_10_03.controller;

import com.crud.jpa_query_orders_10_03.entity.Customer;
import com.crud.jpa_query_orders_10_03.entity.Employee;
import com.crud.jpa_query_orders_10_03.enumeration.EmployeeDepartment;
import com.crud.jpa_query_orders_10_03.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Selects all customers.
     *
     * @return a response entity containing a page of customers.
     */
    @GetMapping("/select-all")
    public ResponseEntity<Page<Customer>> findAllcustomers(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {

        Page<Customer> customers = customerService.findAllCustomers(pageNumber, pageSize);
        return ResponseEntity.ok(customers);
    }

    /**
     * Returns a customer by its id.
     *
     * @param id
     * @return a response entity containing the customer with the given id or ResponseEntity.notFound() if none found.
     */
    @GetMapping("/select-by-id/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findCustomerById(id);

        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(optionalCustomer.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Saves the given customer.
     *
     * @param customer
     * @return a response entity containing the saved customer.
     */
    @PostMapping("/create")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    /**
     * Updates a customer by its id with values from the given customer.
     *
     * @param id
     * @param updatedCustomer
     * @return a response entity containing the updated customer or ResponseEntity.notFound() if none found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerService.updateCustomer(id, updatedCustomer);

        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(optionalCustomer.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a customer by its id.
     *
     * @param id
     * @return a response entity containing the deleted customer or ResponseEntity.notFound() if none found.
     */
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.deleteCustomerById(id);

        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(optionalCustomer.get());
        }

        return ResponseEntity.notFound().build();
    }
}
