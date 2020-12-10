package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;

public class XmlEditor extends AbstractEditor {
    @Override
    public List<CardInput> readFile(String fileName) {
        List<CardInput> cardInputList = new ArrayList<>();
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Document interface represent the entire HTML or XML document.
            Document doc = dBuilder.parse(xmlFile);

            //The Element interface represents an element in an HTML or XML document.
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("row");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String cardNum = eElement.getElementsByTagName("CardNumber").item(0).getTextContent();
                    cardNum = cardNum.replaceAll("\\s", "");
//                    System.out.println("CardNumber id : " + cardNum);
                    String expDate = eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent();
                    expDate = expDate.replaceAll("\\s", "");
//                    System.out.println("ExpirationDate : " + expDate);
                    String holderName =  eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();
//                    System.out.println("Name : " + holderName);
                    CardInput LineIn = new CardInput(cardNum,expDate,holderName);
                    cardInputList.add(LineIn);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        CardInput  newCard = cardInputList.get(0);
        System.out.println("newCard number is "+ newCard.cardNumber);

        return cardInputList;
    }

    @Override
    public void writeFile(List<CreditCard> cardList,String fileName){
        try {
            File myfile = new File(fileName);
            if (myfile.createNewFile()) {
                System.out.println("File created: " + myfile.getName());
            } else {
                System.out.println("File already exists. Use a different name for output file");
                return;
            }
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            // root element
            Element root = document.createElement("root");
            document.appendChild(root);
            // row element
            ListIterator<CreditCard> listIterator = cardList.listIterator();

            while(listIterator.hasNext()) {
                CreditCard creditCard = listIterator.next();
                String errorMsg = "None";
                if (creditCard.cardType == "Invalid") errorMsg = "InvalidCardNumber";
                Element row = document.createElement("row");
                root.appendChild(row);

                // CardNumber element
                Element cardNumber = document.createElement("CardNumber");
                cardNumber.appendChild(document.createTextNode(creditCard.cardNumber));
                row.appendChild(cardNumber);

                // CardType element
                Element cardtype = document.createElement("CardType");
                cardtype.appendChild(document.createTextNode(creditCard.cardType));
                row.appendChild(cardtype);

                // Error element
                Element errorEle = document.createElement("Error");
                errorEle.appendChild(document.createTextNode(errorMsg));
                row.appendChild(errorEle);
            }
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(myfile);

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

