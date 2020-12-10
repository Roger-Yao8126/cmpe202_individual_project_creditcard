package com.company;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ListIterator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class CsvEditor extends AbstractEditor {
    @Override
    public List<CardInput> readFile(String fileName) {
        List cardInputList = new ArrayList();

        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            myReader.nextLine();   // ignore the header

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] lineList = line.split(",");
                String cardNum = lineList[0].replaceAll("\\s", "");
                String expDate = lineList[1].replaceAll("\\s", "");
                String holderName = lineList[2];
//                System.out.println("cardNum is " + cardNum);
//                System.out.println("expDate is " + expDate);
//                System.out.println("holderName is " + holderName);
                CardInput LineIn = new CardInput(cardNum, expDate, holderName);
//                System.out.println("LineIn is " + LineIn.cardNumber);
                cardInputList.add(LineIn);
            }

            myReader.close();
        } catch (FileNotFoundException var11) {
            System.out.println("An error occurred.");
        }

        CardInput newCard = (CardInput) cardInputList.get(0);
        System.out.println("newCard number is " + newCard.cardNumber);
        return cardInputList;
    }

    public void writeFile(List<CreditCard> cardList,String fileName){
        try {
            // create file
            File myfile = new File(fileName);
            if (myfile.createNewFile()) {
                System.out.println("File created: " + myfile.getName());
            } else {
                System.out.println("File already exists.Use a different name for output file");
                return;
            }

            FileWriter myWriter = new FileWriter(fileName);

            ListIterator<CreditCard> listIterator = cardList.listIterator();
            myWriter.write("CardNumber,CardType,Error");
            while(listIterator.hasNext()) {
                CreditCard creditCard = listIterator.next();
                String errorMsg = "None";
                if (creditCard.cardType == "Invalid") errorMsg = "InvalidCardNumber";
                String oneLine = '\n'+creditCard.cardNumber+','+creditCard.cardType+','+ errorMsg ;
                myWriter.write(oneLine);
            }

            System.out.println("Successfully wrote to the file.");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
