package ru.pflb.autotests.jsonparser;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.Wini;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DomClass {

    public static void addToken() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("src\\main\\resources\\message.xml");
            tokenCreation(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public static void tokenCreation(Document document) throws IOException, TransformerException, XPathExpressionException {
        Wini ini = new Wini(new File("src\\main\\resources\\token.ini"));
        String tokenValue = ini.get("main", "token");

        Element token = document.createElement("token");
        token.setTextContent(tokenValue);

        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        XPathExpression expr = xpath.compile("//xml/message");
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        nodes.item(0).appendChild(token);
        //System.out.println(nodes.item(0).getNodeName());
        writeDocument(document);
    }

    private static void writeDocument(Document document) throws TransformerException, FileNotFoundException {
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream("src\\main\\resources\\newxml.xml");
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);
    }
}

