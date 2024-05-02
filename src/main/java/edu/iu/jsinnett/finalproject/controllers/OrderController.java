package edu.iu.jsinnett.finalproject.controllers;


import edu.iu.jsinnett.finalproject.model.Order;
import edu.iu.jsinnett.finalproject.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Iterable<Order>> getOrdersByCustomer(@PathVariable int customerId) {
        // Assuming you have a method to find orders by customer ID
        Iterable<Order> orders = orderRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
