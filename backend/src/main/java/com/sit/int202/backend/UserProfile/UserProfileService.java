package com.sit.int202.backend.UserProfile;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.Properties;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Customer;
import co.omise.models.OmiseException;

@Service
public class UserProfileService {

    Properties prop = new Properties();

    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<UserProfile> getUserProfileList() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getUserProfileById(long id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public long delete(long id) {
        userProfileRepository.deleteById(id);
        return id;
    }

    public String createCustomerToken(LinkedHashMap userData) throws IOException, ClientException, OmiseException {
        prop.load(this.getClass().getResourceAsStream("/application.properties"));
        final String PUBLIC_KEY = prop.getProperty("PUBLIC_KEY");
        final String SECRET_KEY = prop.getProperty("SECRET_KEY");

        Client client = new Client(PUBLIC_KEY, SECRET_KEY);
        Customer customer = client.customers().create(
                new Customer.Create()
                        .description(userData.get("first_name").toString() + " " + userData.get("last_name").toString())
        );
        return customer.getId();
    }

    public void updateCard(String token, String customerToken) throws IOException, ClientException, OmiseException {
        prop.load(this.getClass().getResourceAsStream("/application.properties"));
        final String PUBLIC_KEY = prop.getProperty("PUBLIC_KEY");
        final String SECRET_KEY = prop.getProperty("SECRET_KEY");

        Client client = new Client(PUBLIC_KEY, SECRET_KEY);
        Customer customer = client.customers().update(
                customerToken, new Customer.Update().card(token)
        );
    }

}
