package ru.pflb.autotests.jsonparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XmlToJson {
    static String newXmlPath = "src\\main\\resources\\newxml.xml";
    static String jsonPath = "src\\main\\resources\\test.json";

    public static void xmlToJson() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            File nodeFile = new File(newXmlPath);
            JsonNode node = xmlMapper.readTree(nodeFile);
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.writeValue(new File(jsonPath), node);

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(newXmlPath);
        if (file.isFile()) {
            file.delete();
        }

    }
}
