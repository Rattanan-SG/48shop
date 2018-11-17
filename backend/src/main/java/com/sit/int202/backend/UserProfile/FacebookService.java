package com.sit.int202.backend.UserProfile;

import java.util.Arrays;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.ProfilePictureSource;
import com.restfb.types.User;

import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Service;

@Service
public class FacebookService{
    
    

    public LinkedHashMap userinfo(String token){
        FacebookClient client = new DefaultFacebookClient(token,Version.LATEST);
        JsonObject data = client.fetchObject("me",JsonObject.class,Parameter.with("fields","name,id,picture"));
        LinkedHashMap userinfo = new LinkedHashMap();
        userinfo.put("name",(data.getString("name","name don't found")));
        userinfo.put("id",(data.getString("id","id don't found")));
        userinfo.put("picture",(data.get("picture").asObject().get("data").asObject().getString("url","url don't found")));
        return userinfo;
    }


}