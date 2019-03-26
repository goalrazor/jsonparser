package ru.pflb.autotests.jsonparser;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        XmlToJson.xmlToJson();
        DomClass.addToken();
    }
}
