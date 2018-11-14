package com.sit.int202.backend.UserProfile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_profiles")
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    private String facebookToken;

    private String omiseToken;

    @NotBlank
    private String telNumber;

    public UserProfile() {
    }

    public long getId() {
        return id;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getOmiseToken() {
        return omiseToken;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setOmiseToken(String omiseToken) {
        this.omiseToken = omiseToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
