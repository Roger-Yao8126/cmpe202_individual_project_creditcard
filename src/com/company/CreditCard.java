package com.company;

import java.util.Date;

public abstract class CreditCard {

    String cardNumber;
    String expirationDate;
    String holderName;
    String cardType;

    //    String expirationDate;
//    String holderName;
    public CreditCard(CardInput cardInput, String ctype) {
        cardNumber = cardInput.cardNumber;
        expirationDate = cardInput.expiryDate;
        holderName = cardInput.name;
        cardType = ctype;
    }
//    boolean isValid(String number);
}
