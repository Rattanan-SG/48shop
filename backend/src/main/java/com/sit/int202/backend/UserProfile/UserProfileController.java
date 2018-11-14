package com.sit.int202.backend.UserProfile;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profiles")
    public ResponseEntity<List<UserProfile>> getUserProfileList() {
        return new ResponseEntity<>(userProfileService.getUserProfileList(), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable long id) {
        return new ResponseEntity<>(userProfileService.getUserProfileById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfile> createUserProfile(@Valid @RequestBody UserProfile userProfile) {
        return new ResponseEntity<>(userProfileService.save(userProfile), HttpStatus.CREATED);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfile> updateUserProfile(@Valid @RequestBody UserProfile userProfile) {
        return new ResponseEntity<>(userProfileService.save(userProfile), HttpStatus.OK);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<Long> deleteUserProfile(@PathVariable long id) {
        return new ResponseEntity<>(userProfileService.delete(id), HttpStatus.OK);
    }
}
