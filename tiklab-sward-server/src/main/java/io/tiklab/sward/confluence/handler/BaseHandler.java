package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseHandler extends DefaultHandler {
    protected List<Element> elementList = new ArrayList<>();

    public List<Element> getElementList() {
        return elementList;
    }

    protected Element createElement(String name) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            return doc.createElement(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
