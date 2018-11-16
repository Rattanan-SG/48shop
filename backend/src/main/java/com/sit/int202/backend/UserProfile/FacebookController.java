package com.sit.int202.backend.UserProfile;

import java.security.Principal;
import java.util.LinkedHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {

    @GetMapping("/user")
    public Principal user(Principal principal){
        System.out.print("");
        return principal;
    }
}