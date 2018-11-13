package com.sit.int202.backend.Order;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Card;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.Token;

@Service
public class OrderService {

    private static final String OMISE_OPUBLIC_KEY = "pkey_test_5dviz6scp4tdk4cm0au";
    private static final String OMISE_SECRET_KEY = "skey_test_5dviz7b84hcwu90jews";
    private static final String TH_BAHT = "thb";

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

    public Token getToken(LinkedHashMap data) throws ClientException, IOException, OmiseException{
        Client client = new Client(OMISE_OPUBLIC_KEY,OMISE_SECRET_KEY);
        System.out.print(data);
        Token token = client.tokens().create(
            new Token.Create().card(
                new Card.Create()
                .number((String)data.get("card_id"))
                .expirationMonth((int)data.get("exp_m"))
                .expirationYear((int)data.get("exp_y"))
                .securityCode((String)data.get("cvv"))
                .name((String)data.get("name"))
                .city((String)data.get("address"))
                .postalCode((String)data.get("zip"))
            )
        );
        return token;
    }

    public void charge(long amount,Token token) throws ClientException, IOException, OmiseException {
        Client client = new Client(OMISE_OPUBLIC_KEY,OMISE_SECRET_KEY);
        Charge charge = client.charges().create(
            new Charge.Create()
            .amount(amount).currency(TH_BAHT).card(token.getId()) //test
        );
    }
}