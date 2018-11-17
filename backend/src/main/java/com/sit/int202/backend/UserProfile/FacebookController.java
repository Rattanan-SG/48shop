package com.sit.int202.backend.UserProfile;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.LinkedHashMap;

import com.restfb.FacebookClient;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {

    @GetMapping("/user")
    public Object user() {
        Object data = SecurityContextHolder.getContext().getAuthentication().getDetails();
        System.out.print(data);
        return data;
    }
}