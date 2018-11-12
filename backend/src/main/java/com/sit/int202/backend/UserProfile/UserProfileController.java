package com.sit.int202.backend.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
}