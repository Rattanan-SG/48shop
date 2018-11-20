package com.sit.int202.backend.UserProfile;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

}