package com.crud.jpa_query_orders_10_03.controller;

import com.crud.jpa_query_orders_10_03.entity.Order;
import com.crud.jpa_query_orders_10_03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Saves the given order.
     *
     * @param order
     * @return a response entity containing the saved order.
     */
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    /**
     * Populates database with the given number of fake orders.
     *
     * @param numberOfOrders
     * @return a response entity containing the list of saved orders.
     */
    @PostMapping("/populate/{numberOfOrders}")
    public ResponseEntity<List<Order>> populateWithFakeOrders(@PathVariable Integer numberOfOrders) {
        List<Order> orderList = orderService.populateWithFakeOrders(numberOfOrders);
        return ResponseEntity.ok(orderList);
    }

    /**
     * Selects all orders.
     *
     * @return a response entity containing the page of selected orders.
     */
    @GetMapping("/select-all-paged")
    public ResponseEntity<Page<Order>> selectAllOrdersPaged(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {

        Page<Order> orders = orderService.findAllOrdersPaged(pageNumber, pageSize);
        return ResponseEntity.ok(orders);

    }

    @GetMapping("/select-all")
    public ResponseEntity<List<Order>> selectAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Selects an order by its id.
     *
     * @param id
     * @return a response entity containing the order with the given id or ResponseEntity.notFound() if not found.
     */
    @GetMapping("/select-by-id/{id}")
    public ResponseEntity<Order> selectOrderById(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderService.findOrderById(id);

        if (optionalOrder.isPresent()) {
            return ResponseEntity.ok(optionalOrder.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Updates the order with the given id with values from the given updatedOrder.
     *
     * @param id
     * @param updatedOrder
     * @return a response entity containing the updated order or ResponseEntity.notFound() if not found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Optional<Order> optionalOrder = orderService.updateOrder(id, updatedOrder);

        if (optionalOrder.isPresent()) {
            return ResponseEntity.ok(optionalOrder.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Soft deletes th order with the given id.
     *
     * @param id
     * @return a response entity containing the deleted order or ResponseEntity.notFound() if none found.
     */
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderService.deleteOrderById(id);

        if (optionalOrder.isPresent()) {
            return ResponseEntity.ok(optionalOrder.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Selects all orders with the given customer name.
     *
     * @param customerName
     * @return a response entity containing the page of selected orders.
     */
    @GetMapping("/select-by-customer-name/{customerName}")
    public ResponseEntity<Page<Order>> selectOrdersByCustomerName(
            @PathVariable String customerName,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {

        Page<Order> orders = orderService.findOrderByCustomerName(customerName, pageNumber, pageSize);
        return ResponseEntity.ok(orders);

    }

}
