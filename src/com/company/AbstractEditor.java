package com.company;

import java.util.*;

public abstract class AbstractEditor {

    abstract public List<CardInput> readFile(String fileName);
    abstract public void writeFile(List<CreditCard> cardList,String fileName);

}
