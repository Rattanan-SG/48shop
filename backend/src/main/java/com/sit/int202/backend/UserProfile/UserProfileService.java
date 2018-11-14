package com.sit.int202.backend.UserProfile;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

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
}
