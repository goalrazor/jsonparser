package ru.pflb.autotests.jsonparser;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;

import static ru.pflb.autotests.jsonparser.DomClass.iniPath;
import static ru.pflb.autotests.jsonparser.DomClass.xmlPath;
import static ru.pflb.autotests.jsonparser.XmlToJson.jsonPath;
import static ru.pflb.autotests.jsonparser.XmlToJson.newXmlPath;


public class TestCase {

    DomClass domClass;
    XmlToJson xmlToJson;

    @BeforeClass
    public void seUp(){
        domClass = new DomClass();
        xmlToJson = new XmlToJson();

    }

    @Test
    public void testIniExists(){
        File temp = new File(iniPath);
        Assert.assertTrue(temp.isFile());
        System.out.println("Файл конфигурации ini существует");
    }

    @Test
    public void testXmlExist(){
        File temp = new File(xmlPath);
        Assert.assertTrue(temp.isFile());
        System.out.println("Файл конфигурации xml существует");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void failTestXmlExist(){
        File temp = new File(xmlPath+"somethingElse");
        System.out.println("Негативный сценарий");
        Assert.assertTrue(temp.isFile());
    }

    @Test
    public void testParsing(){
        File json = new File(jsonPath);
        json.delete();
        xmlToJson.xmlToJson();
        Assert.assertTrue(json.isFile());
        System.out.println("Файл json успешно создаётся");
        json.delete();
    }

}
