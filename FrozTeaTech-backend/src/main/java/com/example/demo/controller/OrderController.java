package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Order;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.service.OrderService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/restaurant/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        System.out.println("Received a request to fetch all orders.");
        
        List<Order> orders = orderService.getAllOrders();
        System.out.println("Returning all orders: " + orders);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        System.out.println("Received a request to fetch order by ID: " + orderId);

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found for ID: " + orderId);
            throw new OrderNotFoundException("Order with ID " + orderId + " not found.");
        }

        System.out.println("Returning order for ID " + orderId + ": " + order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/create/{itemId}/{customerId}")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @PathVariable long customerId,
                                             @PathVariable int itemId) {
        System.out.println("Received a request to create an order for customer ID: " + customerId + " and item ID: " + itemId);

        Order savedOrder = orderService.saveOrder(order, customerId, itemId);
        System.out.println("Order created successfully: " + savedOrder);

        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        System.out.println("Received a request to delete order by ID: " + orderId);

        orderService.deleteOrder(orderId);
        System.out.println("Order deleted successfully for ID: " + orderId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable long customerId) {
        System.out.println("Received a request to fetch orders by customer ID: " + customerId);

        try {
            List<Order> orders = orderService.findOrdersByCustomerId(customerId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for customer ID: " + customerId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            System.out.println("Returning orders for customer ID " + customerId + ": " + orders);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            System.out.println("An error occurred while fetching orders for customer ID " + customerId + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}
