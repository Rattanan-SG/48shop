package com.sit.int202.backend.Order;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.omise.models.OmiseException;
import com.sit.int202.backend.exception.BadRequestException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrderList() {
        return new ResponseEntity<List<Order>>(orderService.getOrderList(), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return new ResponseEntity<Order>(orderService.getOrderById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        try {
            orderService.charge(order.getTotalPrice(), order.getOmiseToken());
        } catch (OmiseException o) {
            throw new BadRequestException(o.getMessage());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        return new ResponseEntity<Order>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.save(order), HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable long id) {
        return new ResponseEntity<Long>(orderService.delete(id), HttpStatus.OK);
    }

}