package com.company;

class MasterCard extends CreditCard {
    public MasterCard(CardInput cardInput, String cardtype) {
        super(cardInput,cardtype);
    }
    static public boolean isValid(String number){
        if (number.length()==16 && number.charAt(0)== '5' &&
                Character.getNumericValue(number.charAt(1))>0 && Character.getNumericValue(number.charAt(1))<6) {
            return true;
        }
        return false;
    }
}