package com.sit.int202.backend.Order;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.omise.ClientException;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrderList() {
        return new ResponseEntity<>(orderService.getOrderList(), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return new ResponseEntity<>(orderService.getOrderById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable long id) {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/Charge")
    public void charge(@RequestBody LinkedHashMap payment) throws ClientException, IOException, OmiseException {
        Token token = orderService.getToken(payment);
        long price = Long.parseLong(payment.get("total_price").toString());
        orderService.charge(price, token);
    }

}
