package com.sit.int202.backend.Order;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Charge;
import co.omise.models.OmiseException;

import com.sit.int202.backend.Address.AddressService;
import com.sit.int202.backend.UserProfile.UserProfileService;

@Service
public class OrderService {

    Properties prop = new Properties();
    private static final String TH_BAHT = "thb";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserProfileService userProfileService;

    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        addressService.save(order.getDestination());
        userProfileService.save(order.getUserProfile());
        return orderRepository.save(order);
    }

    public long delete(long id) {
        orderRepository.deleteById(id);
        return id;
    }
 
    public void charge(long amount,String token) throws ClientException, IOException, OmiseException {
        prop.load(this.getClass().getResourceAsStream("/application.properties"));
        final String PUBLIC_KEY = prop.getProperty("PUBLIC_KEY");
        final String SECRET_KEY = prop.getProperty("SECRET_KEY");
        Client client = new Client(PUBLIC_KEY,SECRET_KEY);
        Charge charge = client.charges().create(
                new Charge.Create()
                        .amount(amount * 100).currency(TH_BAHT).card(token)
        );
    }
}
