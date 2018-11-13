package com.sit.int202.backend.Order;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.omise.ClientException;
import co.omise.models.OmiseException;
import co.omise.models.Token;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/Charge")
    public void charge(@RequestBody LinkedHashMap payment) throws ClientException, IOException, OmiseException{
        Token token = orderService.getToken(payment);
        long price = Long.parseLong(payment.get("total_price").toString());
        orderService.charge(price,token); // need to implement this method
    }
    
}