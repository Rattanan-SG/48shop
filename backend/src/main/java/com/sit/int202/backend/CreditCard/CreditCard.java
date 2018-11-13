package com.sit.int202.backend.CreditCard;

public class CreditCard{
    
    private String cardId;
    private int expM;
    private int expY;
    private String name;
    private String address;
    private String zip;

    public void setCardId(String cardId){
        this.cardId = cardId;
    }
    public void setExpM(int expM){
        this.expM = expM;
    }
    public void setExpY(int expY){
        this.expY = expY;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setZip(String zip){
        this.zip = zip;
    }

    public String getCardId(){
        return cardId;
    }
    public int getExpM(){
        return expM;
    }
    public int getExpY(){
        return expY;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getZip(){
        return zip;
    }

}