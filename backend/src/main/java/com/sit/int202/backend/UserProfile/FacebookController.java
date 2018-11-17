package com.sit.int202.backend.UserProfile;

import java.util.LinkedHashMap;

import com.restfb.json.JsonObject;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins ="*",maxAge=3600)
public class FacebookController {
    @Autowired
    FacebookService facebookService;

    @GetMapping("/")

    public LinkedHashMap getInfo(OAuth2Authentication authentication){
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String token = details.getTokenValue();
        LinkedHashMap info = facebookService.userinfo(token);
        return info;
    }
    
}