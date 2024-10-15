package io.thoughtware.sward.confluence.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaxParseHtmlServiceImpl extends DefaultHandler  {



    List<Element> ElementList = new ArrayList<Element>();

    String[] propertyName = {"id", "title", "creator", "creationDate", "lastModificationDate"};
    String[] spacePropertyName = {"name", "creator", "creationDate"};
    private static Element globalElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 检查元素名称是否是 object，且 class 属性是否是 "Page"

        String className = attributes.getValue("class");
        String packageName = attributes.getValue("package");
        String name = attributes.getValue("name");

        if(qName.equals("object")) {

        }

    }

    public void createElement(String name){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            globalElement = document.createElement(name);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    public List<Element> getElementList(){
        return  ElementList;
    }
}
