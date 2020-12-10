package com.company;

public class CardInput {
    public String cardNumber;
    public String expiryDate;
    public String name;

    public CardInput(String c, String e, String n) {
        this.cardNumber = c;
        this.expiryDate = e;
        this.name = n;
    }
}