package com.sit.int202.backend.Order;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Transactional
    public List<Order> getOrderList(){
        return orderRepository.findAll();
    }
    
    public Optional<Order> getOrderById(long id){
        return orderRepository.findById(id);
    }
    
    public Order save(Order order){
        return orderRepository.save(order);
    }
}