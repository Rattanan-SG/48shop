package com.sit.int202.backend.CreditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;
}