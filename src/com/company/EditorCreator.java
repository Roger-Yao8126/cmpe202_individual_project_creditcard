package com.company;

public class EditorCreator {

    static AbstractEditor createEditor(String fileName) {
        System.out.println("fileName in editor creator " + fileName);
        if (fileName.substring(fileName.length()-4).equals(".csv")) {
            System.out.println("CSV file");
            return new CsvEditor();
        } else if (fileName.substring(fileName.length()-4).equals(".xml")) {
            System.out.println("xml file");
            return new XmlEditor();
        } else if (fileName.substring(fileName.length()-5).equals(".json")) {
            System.out.println("json file");
            return new JsonEditor();
        }
        return null;
    }
}
