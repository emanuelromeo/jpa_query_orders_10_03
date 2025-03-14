package com.crud.jpa_query_orders_10_03.service;

import com.crud.jpa_query_orders_10_03.entity.Book;
import com.crud.jpa_query_orders_10_03.entity.Order;
import com.crud.jpa_query_orders_10_03.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Saves the given order.
     *
     * @param order
     * @return the saved order.
     */
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Selects all orders.
     *
     * @return the page of selected orders.
     */
    public Page<Order> findAllOrdersPaged(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAll(pageable);
    }


    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Selects an order by its id.
     *
     * @param id
     * @return an optional containing the order with the given id or Optional.empty() if none found.
     */
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Updates the order with the given id with values from the given updatedOrder.
     *
     * @param id
     * @param updatedOrder
     * @return an optional containing the updated order or Optional.empty() if none found.
     */
    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            optionalOrder.get().setCustomerName(updatedOrder.getCustomerName());
            optionalOrder.get().setOrderDate(updatedOrder.getOrderDate());

            Order savedOrder = orderRepository.save(optionalOrder.get());
            return Optional.of(savedOrder);
        }

        return Optional.empty();
    }

    /**
     * Soft deletes the order with the given id.
     *
     * @param id
     * @return an optional containing the deleted order or Optional.empty() if none found.
     */
    public Optional<Order> deleteOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            optionalOrder.get().setDeleted(true);

            Order savedOrder = orderRepository.save(optionalOrder.get());
            return Optional.of(savedOrder);
        }

        return Optional.empty();
    }

    /**
     * Selects all orders with the given customer name.
     *
     * @param customerName
     * @return the page of selected orders.
     */
    public Page<Order> findOrderByCustomerName(String customerName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findByCustomerName(customerName, pageable);
    }

    /**
     * Populates database with the given number of fake orders.
     *
     * @param numberOfOrders
     * @return the list of saved orders.
     */
    public List<Order> populateWithFakeOrders(Integer numberOfOrders) {
        List<Order> orderList = new ArrayList<>();

        for (int i = 0; i < numberOfOrders; i++) {
            Order savedOrder = orderRepository.save(new Order(null, "Customer " + i, LocalDate.now()));
            orderList.add(savedOrder);
        }

        return orderList;
    }
}
