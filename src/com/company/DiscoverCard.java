package com.company;

class DiscoverCard extends CreditCard {
    public DiscoverCard(CardInput cardInput,String cardtype) {
        super(cardInput,cardtype);
    }
    static public boolean isValid(String number){
        if (number.length()==16 && number.substring(0,4).equals("6011")) {
            return true;
        }
        return false;
    }
}
