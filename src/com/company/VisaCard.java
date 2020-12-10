package com.company;

class VisaCard extends CreditCard {

    public VisaCard(CardInput cardInput, String cardtype) {
        super(cardInput,cardtype);
    }
    static public boolean isValid(String number){
        if ((number.length()==16 || number.length()==13)  && number.charAt(0)== '4' )  {
            return true;
        }
        else {
            return false;
        }
    }
}

