package ru.pflb.autotests.jsonparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XmlToJson {

    public static void xmlToJson(){

        XmlMapper xmlMapper = new XmlMapper();
        try {
            JsonNode node = xmlMapper.readTree(new File("src\\main\\resources\\newxml.xml"));

            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.writeValue(new File("src\\main\\resources\\test.json"), node);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
