package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;

public class ConfluenceUserHandler extends BaseHandler {
    private Element current;
    private boolean isConUser = false;
    private String currentProperty = "";
    private boolean isId = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("object".equals(qName) &&
                "ConfluenceUserImpl".equals(attributes.getValue("class")) &&
                "com.atlassian.confluence.user".equals(attributes.getValue("package"))) {
            isConUser = true;
            current = createElement("ConUser");
        }

        if (isConUser) {
            if ("property".equals(qName)) {
                currentProperty = attributes.getValue("name");
            }
            if ("id".equals(qName)) {
                isId = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!isConUser || current == null) return;
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) return;

        if (isId) {
            current.setAttribute("conUserId", value);
        }

        switch (currentProperty) {
            case "name":
                current.setAttribute("name", value);
                break;
            case "email":
                current.setAttribute("email", value);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("object".equals(qName) && isConUser) {
            elementList.add(current);
            isConUser = false;
        }
        if ("id".equals(qName)) {
            isId = false;
        }
        currentProperty = "";
    }
}
