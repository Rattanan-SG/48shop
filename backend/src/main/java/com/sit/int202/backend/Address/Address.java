package com.sit.int202.backend.Address;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sit.int202.backend.UserProfile.UserProfile;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "addresses")
@EntityListeners(AuditingEntityListener.class)
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "receiver_name")
    private String receiverName;

    @NotBlank
    private String detail;

    @NotBlank
    private String city;

    @NotBlank
    private String zipcode;

    @NotBlank
    @Column(name = "tel_number")
    private String telNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private UserProfile userProfile;

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelNumber() {
        return this.telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public UserProfile getUserProfie() {
        return this.userProfile;
    }

    public void setUserProfie(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
