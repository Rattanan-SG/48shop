package com.sit.int202.backend.UserProfile;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sit.int202.backend.Address.Address;
import com.sit.int202.backend.CreditCard.CreditCard;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user_profiles")
@EntityListeners(AuditingEntityListener.class)
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "facebook_id")
    private String facebookId;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Column(name = "customer_omise_token")
    private String customerOmiseToken;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JsonIgnore
    private List<Address> addresses;

    // @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY)
    // @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    // @JsonIgnore
    // private List<CreditCard> creditCards;

    public UserProfile() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Address> getAddress() {
        return this.addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}