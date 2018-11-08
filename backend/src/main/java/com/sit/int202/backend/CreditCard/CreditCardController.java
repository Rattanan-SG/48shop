package com.sit.int202.backend.CreditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/test")
    public String testHeeloWorld(){
        return "Hello world!";
    }

    @GetMapping("/test/{param}")
    public String testParam(@PathVariable String param){
        return "Hello "+param;
    }

    @PostMapping("/test")
    public String testRequestBody(@RequestBody String name){
        return "Hello "+name;
    }
    
}