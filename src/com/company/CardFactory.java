package com.company;

public class CardFactory {
//
//    public String number;
//    public String date;
//    public String name;
//    public CardFactory(String num, String date, String name) {
//        number = num;
//    }
    // if use builder
    // first  use reader  create different file object

    // then create Card
    static CreditCard createCard(CardInput cardInput) {
        System.out.println("Cardnumber: " + cardInput.cardNumber);
        if (cardInput.cardNumber.length()<13){
            System.out.println("Invalid Card number");
            return new InvalidCard(cardInput,"Invalid");
        }
        else if (VisaCard.isValid(cardInput.cardNumber)) {
            System.out.println("Visa Card");
            return new VisaCard(cardInput,"Visa");
        }
        else if (MasterCard.isValid(cardInput.cardNumber)){
            System.out.println("Master  Card");
            return new MasterCard(cardInput,"MasterCard");
        }
        else if (AmExCard.isValid(cardInput.cardNumber)){
            System.out.println("American Express Card");
            return new AmExCard(cardInput,"AmericanExpress");
        }
        else if (DiscoverCard.isValid(cardInput.cardNumber)){
            System.out.println("Discover Card");
            return new DiscoverCard(cardInput,"Discover");
        }

        System.out.println("Invalid Card number");
        return new InvalidCard(cardInput,"Invalid");
    }

    // then
}