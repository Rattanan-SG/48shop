package com.sit.int202.backend.CreditCard;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="creditcards")
public class CreditCard implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String creditCardToken;

    public long getId() {
        return id;
    }
    public String getCreditCardToken() {
        return creditCardToken;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public void setCreditCardToken(String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }
}