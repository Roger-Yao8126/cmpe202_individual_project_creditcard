package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        String fileName = "./Sample.xml";
        String outputFile = "./output.xml";
//        AbstractReader r1 = new CsvReader();
        AbstractEditor r1 = EditorCreator.createEditor(fileName);

        List<CardInput> fileContent= r1.readFile(fileName);
        r1.readFile(fileName);
        ListIterator<CardInput> it = fileContent.listIterator();
        System.out.println("print fileCSV");

        List<CreditCard> cardList= new ArrayList<>();
        while(it.hasNext()) {
            cardList.add(CardFactory.createCard(it.next()));
        }
        r1.writeFile(cardList, outputFile);
    }
}

