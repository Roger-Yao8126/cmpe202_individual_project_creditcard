package com.company;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class JsonEditor extends AbstractEditor  {
    public List<CardInput> readFile(String fileName) {
        List<CardInput> cardInputList = new ArrayList<>();
        try {
//            Object obj = parser.parse(new FileReader("./SampleOutput.json"));
//            JSONObject jsonObject = (JSONObject) obj;
//            JSONArray companyList = (JSONArray) jsonObject.get("")
            String contents = new String((Files.readAllBytes(Paths.get(fileName))));
            JSONArray jsonArray = new JSONArray(contents);

//            JSONArray tag = (JSONArray) arr.get("CardNumber");

            for (int i=0; i< jsonArray.length(); i++) {
//                System.out.println("line 1");
//                System.out.println(arr.get(i));
                JSONObject temp = jsonArray.getJSONObject(i);
//                String cardNum= String(temp.getLong(("CardNumber")));
                String cardNum= Long.toString(temp.getLong(("CardNumber")));
                String expDate  = temp.getString(("ExpirationDate"));
                String holderName = temp.getString(("NameOfCardholder"));

                System.out.println(cardNum);
                System.out.println(expDate);
                System.out.println(holderName);
                CardInput LineIn = new CardInput(cardNum,expDate,holderName);
                cardInputList.add(LineIn);
            }
        }  catch(Exception e) {
            e.printStackTrace();
        }
        return cardInputList;
    }

    @Override
    public void writeFile(List<CreditCard> cardList,String fileName){
        try {
            ListIterator<CreditCard> listIterator = cardList.listIterator();
            JSONArray fileObject = new JSONArray();
            while(listIterator.hasNext()) {
                CreditCard creditCard = listIterator.next();
                String errorMsg = "None";
                if (creditCard.cardType == "Invalid") errorMsg = "InvalidCardNumber";

                JSONObject cardObject = new JSONObject();
                cardObject.put("CardNumber", creditCard.cardNumber);
                cardObject.put("CardType", creditCard.cardType);
                cardObject.put("Error", errorMsg);
                fileObject.put(cardObject);
            }


            String jsonString = fileObject.toString(2);
            System.out.println(jsonString);

            File myfile = new File(fileName);
            if (myfile.createNewFile()) {
                System.out.println("File created: " + myfile.getName());
            } else {
                System.out.println("File already exists. Use a different name for output file");
                return;
            }


            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(jsonString);
            myWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
