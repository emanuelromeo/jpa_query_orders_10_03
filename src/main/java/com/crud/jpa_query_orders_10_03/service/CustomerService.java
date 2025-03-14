package com.crud.jpa_query_orders_10_03.service;

import com.crud.jpa_query_orders_10_03.entity.Customer;
import com.crud.jpa_query_orders_10_03.entity.Employee;
import com.crud.jpa_query_orders_10_03.enumeration.EmployeeDepartment;
import com.crud.jpa_query_orders_10_03.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Selects all customers.
     *
     * @return a page of customers.
     */
    public Page<Customer> findAllCustomers(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return customerRepository.findAll(pageable);
    }

    /**
     * Returns a customer by its id.
     *
     * @param id
     * @return an optional containing the customer with the given id or Optional.empty() if none found.
     */
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Saves the given customer.
     *
     * @param customer
     * @return the saved customer.
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Updates a customer by its id with values from the given customer.
     *
     * @param id
     * @param updatedCustomer
     * @return an optional containing the updated customer or Optional.empty() if none found.
     */
    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            optionalCustomer.get().setName(updatedCustomer.getName());
            optionalCustomer.get().setRegistrationDate(updatedCustomer.getRegistrationDate());

            Customer savedCustomer = customerRepository.save(optionalCustomer.get());
            return Optional.of(savedCustomer);
        }

        return Optional.empty();
    }

    /**
     * Deletes a customer by its id.
     *
     * @param id
     * @return an optional containing the deleted customer or Optional.empty() if none found.
     */
    public Optional<Customer> deleteCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            optionalCustomer.get().setDeleted(true);

            Customer savedCustomer = customerRepository.save(optionalCustomer.get());
            return Optional.of(savedCustomer);
        }

        return Optional.empty();
    }
}
