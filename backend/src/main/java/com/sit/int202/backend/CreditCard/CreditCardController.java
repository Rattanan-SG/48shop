package com.sit.int202.backend.CreditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;
}