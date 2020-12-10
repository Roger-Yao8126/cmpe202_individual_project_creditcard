package com.company;

class AmExCard extends CreditCard {
    public AmExCard(CardInput cardInput, String cardtype) {
        super(cardInput,cardtype);
    }
    static public boolean isValid(String number){
        if (number.length()==15 && number.charAt(0)== '3' && (number.charAt(1)== '4' || number.charAt(1)== '7')
               ) {
            return true;
        }
        return false;
    }
}